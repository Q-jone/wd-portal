package com.wonders.stpt.mscp.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wonders.stpt.mscp.entity.bo.MscpAlarmStat;
import com.wonders.stpt.mscp.entity.bo.MscpExpertStat;
import com.wonders.stpt.mscp.entity.bo.MscpMonthBid;
import com.wonders.stpt.mscp.entity.bo.MscpMonthNotice;
import com.wonders.stpt.mscp.entity.bo.MscpMonthPortalVisit;
import com.wonders.stpt.mscp.entity.bo.MscpMonthVisit;
import com.wonders.stpt.mscp.entity.bo.MscpOrganTrade;
import com.wonders.stpt.mscp.entity.bo.MscpSupplyBid;
import com.wonders.stpt.mscp.entity.bo.MscpTotalBid;
import com.wonders.stpt.mscp.entity.vo.MscpInfo;
import com.wonders.stpt.mscp.entity.vo.MscpMonthBidVo;
import com.wonders.stpt.mscp.service.MscpService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;



public class MscpAction extends AbstractParamAction {

	private static final long serialVersionUID = -661190421108027253L;
	private MscpService mscpService; 
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
	public MscpService getMscpService() {
		return mscpService;
	}
	
	public void setMscpService(MscpService mscpService) {
		this.mscpService = mscpService;
	}

	/**
	 * 查询月度竞价模式分类数量与金额
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void findMscpInfo() throws IOException, ParseException{
		String yearMonth = sdf.format(new Date());
		MscpInfo mscpInfo = mscpService.findMscpInfo(yearMonth);
		String jsonString = JSONObject.fromObject(mscpInfo).toString();
		renderJson(jsonString);
	}
	
	/**
	 *	显示组织机构 
	 */
	public void findOrganizationTrade() throws IOException{
		String yearMonth = "";
		String type = this.servletRequest.getParameter("type");
		String year = this.servletRequest.getParameter("year");
		String month = this.servletRequest.getParameter("month");
		if("thisMonth".equals(type)){
			yearMonth = sdf.format(new Date());
		}else if("switchMonth".equals(type)){
			yearMonth = year + "-" + month;
		}
		List<MscpOrganTrade> list = mscpService.findMscpOrganTrade(yearMonth);
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	/**
	 * 显示供应商中标金额
	 */
	public void findSupplyBid() throws IOException{
		String yearMonth = "";
		String type = this.servletRequest.getParameter("type");
		String year = this.servletRequest.getParameter("year");
		String month = this.servletRequest.getParameter("month");
		if("thisMonth".equals(type)){
			yearMonth = sdf.format(new Date());
		}else if("switchMonth".equals(type)){
			yearMonth = year + "-" + month;
		}
		List<MscpSupplyBid> list = mscpService.findMscpSupplyBid(yearMonth);
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString); 
	}
	
	/**
	 * 显示专家总数统计
	 */
	public void findMscpExpertStat() throws IOException{
		List<MscpExpertStat> list = mscpService.findMscpExpertStat();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	/**
	 *	门户月度访问量 
	 */
	public void findPortalVisit() throws IOException{
		List<MscpMonthPortalVisit> list = mscpService.findMonthPortalVisit();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString); 
	}
	
	/**
	 * 平台月度访问量
	 * @throws IOException 
	 */
	public void findMonthVisit() throws IOException{
		List<MscpMonthVisit> list = mscpService.findMonthVisit();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	/**
	 *	查询月度公告分类数量统计 
	 * @throws IOException 
	 */
	public void findMonthNotice() throws IOException{
		List<MscpMonthNotice> list = mscpService.findMonthNotice();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	/**
	 *	月度分类交易金额统计 
	 * @throws IOException 
	 */
	public void findMonthBidByMonth() throws IOException{
		List<Object[]> list0 = mscpService.findMscpMonth();
		List<Object[]> list1 = mscpService.findMscpMonthBidStatByMonth();
		List<Object[]> list2 = mscpService.findMscpMonthBidMoneySumByMonth();
		Object[] obj = new Object[3];
		obj[0] = list0;
		obj[1] = list1; 
		obj[2] = list2;
		String jsonString = JSONArray.fromObject(obj).toString();
		//System.out.println(jsonString);
		renderJson(jsonString);
	}
	
	/**
	 * 查询月度竞价模式
	 * @throws IOException 
	 */
	public void findMonthBid() throws IOException{
		MscpMonthBidVo result = mscpService.findMonthBid();
		String jsonString = JSONObject.fromObject(result).toString();
		renderJson(jsonString);
	}
	
	/**
	 * 查询总竞价模式
	 * @throws IOException 
	 */
	public void findTotalBid() throws IOException{
		List<MscpTotalBid> list = mscpService.findMscpTotalBid();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	public void renderJson(String content) throws IOException{
		HttpServletResponse response = getServletResponse(); 
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(content);
		response.getWriter().flush();
	}
	
	public void showSupplierNum() throws IOException{
		String month = sdf.format(new Date());
		List<Object[]> list1 = mscpService.findMscpSupplierStatNum();
		List<Object[]> list2 = mscpService.findMscpMonthSupplierStatNumThisMonth(month);
		List<Object[]> list = new ArrayList<Object[]>();
		if(list1!=null&&list1.size()>0&&list2!=null&&list2.size()>0&&list1.size()==list2.size()){
			for(int i=0;i<list1.size();i++){
				Object[] obj = new Object[3];
				obj[0] = list1.get(i)[0];
				obj[1] = list1.get(i)[1];
				obj[2] = list2.get(i);
				list.add(obj);
			}
		}
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	public void findMscpMonthSupplierStatSumByMonth() throws IOException{
		List<Object[]> list = mscpService.findMscpMonthSupplierStatSumByMonth();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
	
	public void findMscpAlarmStat() throws IOException{
		List<MscpAlarmStat> list = mscpService.findMscpAlarmStat();
		String jsonString = JSONArray.fromObject(list).toString();
		renderJson(jsonString);
	}
}
