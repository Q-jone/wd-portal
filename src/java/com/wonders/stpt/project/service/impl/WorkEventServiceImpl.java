package com.wonders.stpt.project.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.common.ExcelDataUtil;
import com.wonders.stpt.project.dao.WorkEventDao;
import com.wonders.stpt.project.model.WorkEvent;
import com.wonders.stpt.project.service.IWorkEventService;
import com.wonders.stpt.util.StringUtil;
@Repository("workEventService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkEventServiceImpl implements IWorkEventService {
	private static final Logger logger=Logger.getLogger(WorkEventServiceImpl.class);
	@Autowired
	private WorkEventDao workEventDao;
	
	@Override
	public void save(List<WorkEvent> workEvents) throws Exception {
		// TODO Auto-generated method stub
		workEventDao.save(workEvents);
	}

	@Override
	public PageResultSet<WorkEvent> find(WorkEvent workEvent, int page,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return workEventDao.find(workEvent, page, pageSize);
	}

	@Override
	public int deleteAll() throws Exception{
		// TODO Auto-generated method stub
		return workEventDao.deleteAll();
	}

	@Override
	public WorkEvent load(String workEventId) throws Exception {
		// TODO Auto-generated method stub
		WorkEvent event=workEventDao.load(workEventId);
		return event;
	}

	@Override
	public WorkEvent save(WorkEvent workEvent) throws Exception {
		// TODO Auto-generated method stub
		return workEventDao.save(workEvent);
	}

	@Override
	public int deletes(String workEventId) throws Exception{
		// TODO Auto-generated method stub
		return workEventDao.deletes(workEventId);
	}

	@Override
	public List<String> imports(File file, String user) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<WorkEvent> workEvents=new ArrayList<WorkEvent>();//声明一个存储对象数据的集合，用于存储从file读取的数据
		ExcelDataUtil util = new ExcelDataUtil();//判断日期格式
		int row=0;
		try {
			Workbook workbook = Workbook.getWorkbook(file);//将文件数据转换成excel对象
			Sheet[] sheets = workbook.getSheets();//获取excel中的每一张表数组
			Sheet sheet = sheets[0];//获取第一张表
			List<String> result = util.validExcelTemplate(sheet,3,new Integer[]{2,3});//日期列号
            if (result != null && result.size()>0) {
                return result;
            }
			for (int i = 3; i < sheet.getRows(); i++) {//遍历表中的每一行
				Cell[] cells = sheet.getRow(i);//获取第i行中的列集合(及每条记录的数据集合)
				row = i;//标记当前读取的记录在第几行(从0开始)
				if (cells != null) {//判断当前行有数据
					WorkEvent workEvent = new WorkEvent();
					if(StringUtils.isBlank(cells[0].getContents()))//判断当前行的第一列是否有值，没有就读取下一条数据
						continue;
					//以下开始将excel表中每条记录每列的值赋予新建对象的属性
					workEvent.setCreator(user);
					workEvent.setUpdator(user);
					workEvent.setCompanyName(StringUtil.getNotNullValueString(cells[0].getContents()));
					workEvent.setTelTime(util.getDate(cells[2]));
					workEvent.setBeginTime(util.getDate(cells[3]));
					workEvent.setMessageSource(StringUtil.getNotNullValueString(cells[4].getContents()));
					workEvent.setClassification(StringUtil.getNotNullValueString(cells[5].getContents()));
					workEvent.setEventType(StringUtil.getNotNullValueString(cells[6].getContents()));
					workEvent.setNetworkSystem(StringUtil.getNotNullValueString(cells[7].getContents()));
					workEvent.setDescriptions(StringUtil.getNotNullValueString(cells[8].getContents()));
					workEvent.setRanks(StringUtil.getNotNullValueString(cells[9].getContents()));
					workEvent.setReasons(StringUtil.getNotNullValueString(cells[10].getContents()));
					workEvent.setProcess(StringUtil.getNotNullValueString(cells[11].getContents()));
					workEvent.setResults(StringUtil.getNotNullValueString(cells[12].getContents()));
					workEvent.setReporter(StringUtil.getNotNullValueString(cells[13].getContents()));
					workEvent.setTelphone(StringUtil.getNotNullValueString(cells[14].getContents()));
					workEvent.setMemo(StringUtil.getNotNullValueString(cells[15].getContents()));
					workEvents.add(workEvent);
				}
			}
			workEventDao.deleteAll();//清空原始数据
			workEventDao.save(workEvents);//保存导入数据
		} catch (Exception e) {
			logger.error("excel格式错误导入数据失败:第" + (row + 1) + "行");
            throw new Exception("excel格式错误导入数据失败:第" + (row + 1) + "行");
		}
		return null;
	}

	/**
	 * 根据传过来的值转换成日期类型
	 * @param cell
	 * @return
	 */
	private Date getDate(Cell cell){
		String content = StringUtil.getNotNullValueString(cell.getContents());//格式为yyyy/mm/dd
        if(StringUtils.isBlank(content))//判断值是否为blank
            return null;
        Date date=new Date(content);
		return date;
	}

	@Override
	public String countDataToJson(Integer year)throws Exception {
		ArrayList<WorkEvent> workEvents=new ArrayList<WorkEvent>();//声明一个空集合
		JSONObject json=new JSONObject();
		List result=workEventDao.count(year);
		if(result!=null){
			//年度事件总数
			System.out.println(((BigDecimal)result.get(0)).intValue());
			json.put("yeartotal", ((BigDecimal)result.get(0)).intValue());
			//特别重大事件（Ⅰ级）总数
			json.put("eventstotal", ((BigDecimal)result.get(1)).intValue());
			//当月新发生事件总数(异常，红色字体)
			json.put("monthtotal", ((BigDecimal)result.get(2)).intValue());
			if(((BigDecimal)result.get(2)).intValue()>0){
				
				json.put("monthObj", workEventDao.monthTotal(year, 3, 1));//获取当月发生时间总数
			}else{
				json.put("monthObj", workEvents);
			}
			
		}else{
			//年度事件总数
			json.put("yeartotal", 0);
			//特别重大事件（Ⅰ级）总数
			json.put("eventstotal", 0);
			//当月新发生事件总数(异常，红色字体)
			json.put("monthtotal", 0);
			json.put("monthObj", workEvents);
		}
		return json.toString();
	}

	@Override
	public  List  monthTotal(int year) throws Exception {
		
		return workEventDao.monthTotals(year, Integer.MAX_VALUE, 1);
	}
}
