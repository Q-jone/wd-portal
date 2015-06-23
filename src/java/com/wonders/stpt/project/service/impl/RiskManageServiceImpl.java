package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.common.ExcelDataUtil;
import com.wonders.stpt.project.dao.RiskManageDao;
import com.wonders.stpt.project.model.RiskManage;
import com.wonders.stpt.project.service.RiskManageService;
import com.wonders.stpt.util.StringUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/6/26.
 */
@Repository("riskManageService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public class RiskManageServiceImpl implements RiskManageService {

    private final Logger logger = Logger.getLogger(RiskManageServiceImpl.class);
    @Autowired
    private RiskManageDao riskManageDao;

    @Override
    public List<String> imports(File file, String user) throws Exception {
        ArrayList<RiskManage> riskManages = new ArrayList<RiskManage>();
        ExcelDataUtil util = new ExcelDataUtil();
        int row = 0;
        try {
            Workbook book = Workbook.getWorkbook(file);
            Sheet[] sheets = book.getSheets();
            Sheet sheet = sheets[0];
            List<String> result = util.validExcelTemplate(sheet,2,new Integer[]{2,10});
            if (result != null && result.size()>0) {
                for (String o : result) {
                    System.out.println(o);
                }
                return result;
            }
            for (int i = 2; i < sheet.getRows(); i++) {
                Cell[] cells = sheet.getRow(i);
                row = i;
                if (cells != null) {
                    RiskManage risk = new RiskManage();
                    risk.setCreator(user);
                    risk.setUpdater(user);
                    if (StringUtils.isBlank(cells[0].getContents()))
                        continue;
                    risk.setDepartment(StringUtil.getNotNullValueString(cells[0].getContents()));
                    risk.setRiskFrom(StringUtil.getNotNullValueString(cells[1].getContents()));
                    risk.setDiscovery(util.getDate(cells[2]));
                    risk.setRiskInfo(StringUtil.getNotNullValueString(cells[3].getContents()));
                    risk.setSysName(StringUtil.getNotNullValueString(cells[4].getContents()));
                    risk.setCategory(StringUtil.getNotNullValueString(cells[5].getContents()));
                    risk.setRiskLevel(StringUtil.getNotNullValueString(cells[6].getContents()));
                    risk.setPlan(StringUtil.getNotNullValueString(cells[7].getContents()));
                    risk.setDateLimit(StringUtil.getNotNullValueString(cells[8].getContents()));
                    risk.setTrackInfo(StringUtil.getNotNullValueString(cells[9].getContents()));
                    risk.setTrackDate(util.getDate(cells[10]));
                    riskManages.add(risk);
                }
            }
            riskManageDao.deleteAll();
            riskManageDao.save(riskManages);
            book.close();
        } catch (Exception e) {
        	
            logger.error("excel格式错误导入数据失败:第" + (row + 1) + "行");
            throw new Exception("excel格式错误导入数据失败:第" + (row + 1) + "行"+e.getMessage());
        }
        return null;
    }

    @Override
    public RiskManage save(RiskManage riskManage) throws Exception {
        return riskManageDao.save(riskManage);
    }

    @Override
    public PageResultSet<RiskManage> getRiskManages(RiskManage riskManage, Integer year, int page, int pageSize) throws Exception {
        return riskManageDao.find(riskManage, page, pageSize);
    }

    @Override
    public RiskManage riskManage(String riskManageId) throws Exception {
        // TODO Auto-generated method stub
        return riskManageDao.load(riskManageId);
    }

    @Override
    public int deletes(String riskManageId) {
        // TODO Auto-generated method stub
        return riskManageDao.deletes(riskManageId);
    }

    @Override
    public String countDataToJson(Integer year) {
        ArrayList<RiskManage> riskManages = new ArrayList<RiskManage>();

        JSONObject jsonObject = new JSONObject();
        List result = riskManageDao.count(year);
        if (result != null) {
            jsonObject.put("total", ((BigDecimal) result.get(0)).intValue());
            jsonObject.put("normal", ((BigDecimal) result.get(1)).intValue());
            jsonObject.put("increase", ((BigDecimal) result.get(2)).intValue());
            jsonObject.put("history", ((BigDecimal) result.get(3)).intValue());

            if (((BigDecimal) result.get(3)).intValue() > 0) {
                jsonObject.put("historyObj", riskManageDao.findRisManageByDiscovery(year, 1, 3));
            } else {
                jsonObject.put("historyObj", riskManages);
            }
            if (((BigDecimal) result.get(2)).intValue() > 0) {
                jsonObject.put("increaseObj", riskManageDao.findByDiscovery(year, 1, Integer.MAX_VALUE));
            }
            else {
                jsonObject.put("increaseObj", riskManages);
            }


        } else {
            jsonObject.put("total", 0);
            jsonObject.put("normal", 0);
            jsonObject.put("increase", 0);
            jsonObject.put("history", 0);
            jsonObject.put("historyObj", riskManages);
        }

        return jsonObject.toString();
    }

    @Override
    public List<RiskManage> getDepartmentData(Integer year) {
        return riskManageDao.countByDepartment(year);
    }

}
