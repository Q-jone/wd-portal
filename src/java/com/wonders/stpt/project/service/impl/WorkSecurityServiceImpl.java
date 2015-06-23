package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.common.ExcelDataUtil;
import com.wonders.stpt.project.dao.WorkSecurityDao;
import com.wonders.stpt.project.model.WorkSecurity;
import com.wonders.stpt.project.service.WorkSecurityService;
import com.wonders.stpt.util.DateUtil;
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
 * Created by Administrator on 2014/6/25.
 */

@Repository("workSecurityService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkSecurityServiceImpl implements WorkSecurityService {
    private final Logger logger = Logger.getLogger(WorkSecurityServiceImpl.class);

    @Autowired
    private WorkSecurityDao workSecurityDao;

    @Override
    public PageResultSet<WorkSecurity> getWorkSecurities(WorkSecurity workSecurity, Integer year, int page, int pageSize) throws Exception {
        if (year == null) {
            year = 2014;
        }
        workSecurity.setPlanBeginDate(new GregorianCalendar(year, 0, 1).getTime());
        workSecurity.setPlanEndDate(new GregorianCalendar(year, 11, 31).getTime());
        return workSecurityDao.find(workSecurity, page, pageSize);
    }

    @Override
    public WorkSecurity save(WorkSecurity workSecurity) throws Exception {
        return workSecurityDao.save(workSecurity);
    }

    @Override
    public WorkSecurity getWorkSecurity(String workSecurityId) throws Exception {
        return workSecurityDao.load(workSecurityId);
    }

    @Override
    public int delete(String[] workSecurityId) throws Exception {
        return workSecurityDao.delete(workSecurityId);
    }

    @Override
    public List<String> imports(File file, String user) throws Exception {
        ArrayList<WorkSecurity> workSecurities = new ArrayList<WorkSecurity>();
        ExcelDataUtil util = new ExcelDataUtil();
        int row = 0;
        try {
            Workbook book = Workbook.getWorkbook(file);
            Sheet[] sheets = book.getSheets();
            Sheet sheet = sheets[1];
            List<String> result = util.validExcelTemplate(sheet,3,new Integer[]{7,8});
            if (result != null && result.size()>0) {
                               return result;
            }
            for (int i = 3; i < sheet.getRows(); i++) {
                Cell[] cells = sheet.getRow(i);
                row = i;
                if (cells != null) {
                    WorkSecurity security = new WorkSecurity();
                    security.setCreator(user);
                    security.setUpdater(user);
                    security.setConfirm("1");
                    if (StringUtils.isBlank(cells[1].getContents()))
                        continue;
                    security.setSysName(StringUtil.getNotNullValueString(cells[1].getContents()));
                    security.setCategory(StringUtil.getNotNullValueString(cells[2].getContents()));
                    security.setImportant(StringUtil.getNotNullValueString(cells[3].getContents()));
                    security.setSysLevel(StringUtil.getNotNullValueString(cells[4].getContents()));
                    security.setDepartment(StringUtil.getNotNullValueString(cells[5].getContents()));
                    security.setExcuteQuantity(Integer.parseInt(StringUtil.getNotNullValueString(cells[6].getContents())));
                    security.setPlanBeginDate(util.getDate(cells[7]));
                    security.setPlanEndDate(util.getDate(cells[8]));
                    security.setMemo(StringUtil.getNotNullValueString(cells[9].getContents()));
                    workSecurities.add(security);
                }
            }
            workSecurityDao.deleteAll();
            workSecurityDao.save(workSecurities);
            book.close();
        } catch (Exception e) {
            logger.error("excel格式错误导入数据失败:第" + (row + 1) + "行");
            throw new Exception("excel格式错误导入数据失败:第" + (row + 1) + "行");
        }
        return null;
    }




    @Override
    public String countDataToJson(Integer year) throws Exception {
        WorkSecurity security = new WorkSecurity();
        security.setConfirm("1");
        ArrayList<WorkSecurity> workSecurities = new ArrayList<WorkSecurity>();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"createTime", "updateTime", "delay"});

        JSONObject jsonObject = new JSONObject();
        List result = workSecurityDao.count(year);
        if (result != null) {
            jsonObject.put("total", ((BigDecimal) result.get(0)).intValue());
            jsonObject.put("completed", ((BigDecimal) result.get(1)).intValue());
            if (((BigDecimal) result.get(2)).intValue() > 0)
                jsonObject.put("inPlanNotEndObj", JSONArray.fromObject(workSecurityDao.findByRealEndDate(year, 1, Integer.MAX_VALUE), config));
            else
                jsonObject.put("inPlanNotEndObj", workSecurities);
            jsonObject.put("inPlanNotEnd", ((BigDecimal) result.get(2)).intValue());
        } else {
            jsonObject.put("total", 0);
            jsonObject.put("completed", 0);
            jsonObject.put("inPlanNotEnd", 0);
            jsonObject.put("inPlanNotEndObj", workSecurities);
        }

        return jsonObject.toString();
    }

    @Override
    public List<WorkSecurity> getNotBegin(Integer year) {
        return workSecurityDao.findByRealBeginDate(year, 1, Integer.MAX_VALUE);
    }

    @Override
    public List<WorkSecurity> getNotEnd(Integer year) {
        return workSecurityDao.findByRealEndDate(year, 1, Integer.MAX_VALUE);
    }



//    static Date getDate(Cell cell) throws Exception {
//        if (cell.getType() == CellType.NUMBER) {
//            GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
//            calendar.add(Calendar.DATE, Integer.parseInt(StringUtil.getNotNullValueString(cell.getContents())) - 2);
//            return calendar.getTime();
//        }else{
//            System.out.println(StringUtil.getNotNullValueString(cell.getContents()));
//        }
//        return null;
//    }


}
