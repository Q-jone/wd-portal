package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.common.ExcelDataUtil;
import com.wonders.stpt.project.dao.WorkInspectDao;
import com.wonders.stpt.project.model.WorkInspect;
import com.wonders.stpt.project.service.WorkInspectService;
import com.wonders.stpt.util.StringUtil;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2014/6/26.
 */
@Repository("workInspectService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkInspectServiceImpl implements WorkInspectService {

    private final Logger logger = Logger.getLogger(WorkInspectServiceImpl.class);

    @Autowired
    private WorkInspectDao workInspectDao;

    /**
     * 根据传入的文件路径和上传人名上传文件
     */
    @Override
    public List<String> imports(File file, String user) throws Exception {
        ArrayList<WorkInspect> workSecurities = new ArrayList<WorkInspect>();
        ExcelDataUtil util = new ExcelDataUtil();
        int row = 0;
        try {
            Workbook book = Workbook.getWorkbook(file);
            Sheet[] sheets = book.getSheets();
            Sheet sheet = sheets[0];

            List<String> result = util.validExcelTemplate(sheet,3,new Integer[]{1,10,11,14});
            if (result != null && result.size()>0) {
                return result;
            }
            for (int i = 3; i < sheet.getRows(); i++) {
                Cell[] cells = sheet.getRow(i);
                row = i;
                if (cells != null) {
                    WorkInspect inspect = new WorkInspect();
                    if(StringUtils.isBlank(cells[0].getContents()))
                        continue;
                    inspect.setCreator(user);
                    inspect.setUpdater(user);
                    inspect.setDepartment(StringUtil.getNotNullValueString(cells[0].getContents()));
                    inspect.setInspectDate(util.getDate(cells[1]));
                    inspect.setInspectInfo(StringUtil.getNotNullValueString(cells[3].getContents()));
                    inspect.setSysName(StringUtil.getNotNullValueString(cells[4].getContents()));
                    inspect.setDegree(StringUtil.getNotNullValueString(cells[5].getContents()));
                    inspect.setCategory(StringUtil.getNotNullValueString(cells[6].getContents()));
                    inspect.setSubject(StringUtil.getNotNullValueString(cells[7].getContents()));
                    inspect.setSubSubject(StringUtil.getNotNullValueString(cells[8].getContents()));
                    inspect.setPlan(StringUtil.getNotNullValueString(cells[9].getContents()));
                    inspect.setPlanBeginDate(util.getDate(cells[10]));
                    inspect.setPlanEndDate(util.getDate(cells[11]));
                    inspect.setWorkable(StringUtil.getNotNullValueString(cells[12].getContents()));
                    inspect.setReformMemo(StringUtil.getNotNullValueString(cells[13].getContents()));
                    inspect.setTractDate(util.getDate(cells[14]));
                    inspect.setReview(StringUtil.getNotNullValueString(cells[15].getContents()));
                    inspect.setTractMemo(StringUtil.getNotNullValueString(cells[16].getContents()));
                    workSecurities.add(inspect);
                }
            }
            workInspectDao.deleteAll();
            workInspectDao.save(workSecurities);
            book.close();
        } catch (Exception e) {
            logger.error("excel格式错误导入数据失败:第" + (row + 1) + "行");
            throw new Exception("excel格式错误导入数据失败:第" + (row + 1) + "行");
        }
        return null;
    }

    @Override
    public WorkInspect save(WorkInspect workInspect) throws Exception {
        return workInspectDao.save(workInspect);
    }

    @Override
    public PageResultSet<WorkInspect> getWorkInspects(WorkInspect workInspect, Integer year, int page, int pageSize) throws Exception {
        if(year == null){
            year = 2014;
        }
//        workInspect.setPlanBeginDate(new GregorianCalendar(year, 0, 1).getTime());
//        workInspect.setPlanEndDate(new GregorianCalendar(year, 11, 31).getTime());
        return workInspectDao.find(workInspect, page, pageSize);
    }

	@Override
	public WorkInspect getInspect(String workInspectId) throws Exception {
		// TODO Auto-generated method stub
		return workInspectDao.load(workInspectId);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return workInspectDao.delete(id);
	}

    @Override
    public String countDataToJson(Integer year) {
        ArrayList<WorkInspect> workInspects = new ArrayList<WorkInspect>();

        JSONObject jsonObject = new JSONObject();
        List result = workInspectDao.count(year);
        if (result != null) {
            jsonObject.put("total", ((BigDecimal) result.get(0)).intValue());
            jsonObject.put("normal", ((BigDecimal) result.get(1)).intValue());
            jsonObject.put("increase", ((BigDecimal) result.get(2)).intValue());
            jsonObject.put("history", ((BigDecimal) result.get(3)).intValue());

            if(((BigDecimal) result.get(3)).intValue()>0){
                jsonObject.put("historyObj",  workInspectDao.findByPlanEndDate(year,1,3));
            }else{
                jsonObject.put("historyObj", workInspects);
            }
            if(((BigDecimal) result.get(2)).intValue()>0) {
                jsonObject.put("increaseObj", workInspectDao.findByInspectDate(year, 1, Integer.MAX_VALUE));
            }
            else
                jsonObject.put("increaseObj", workInspects);

        } else {
            jsonObject.put("total", 0);
            jsonObject.put("normal", 0);
            jsonObject.put("increase", 0);
            jsonObject.put("history", 0);
            jsonObject.put("historyObj", workInspects);
            jsonObject.put("increaseObj", workInspects);
        }

        return jsonObject.toString();
    }

    @Override
    public List<WorkInspect> getDepartmentData(Integer year) {
        return workInspectDao.countByInspectDate(year);
    }
}
