/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.mscp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.mscp.dao.MscpAlarmStatDao;
import com.wonders.stpt.mscp.dao.MscpDao;
import com.wonders.stpt.mscp.dao.MscpExpertStatDao;
import com.wonders.stpt.mscp.dao.MscpMonthBidDao;
import com.wonders.stpt.mscp.dao.MscpMonthBidStatDao;
import com.wonders.stpt.mscp.dao.MscpMonthNoticeDao;
import com.wonders.stpt.mscp.dao.MscpMonthPortalVisitDao;
import com.wonders.stpt.mscp.dao.MscpMonthSupplierStatDao;
import com.wonders.stpt.mscp.dao.MscpMonthVisitDao;
import com.wonders.stpt.mscp.dao.MscpOrganTradeDao;
import com.wonders.stpt.mscp.dao.MscpProfileDao;
import com.wonders.stpt.mscp.dao.MscpSupplierStatDao;
import com.wonders.stpt.mscp.dao.MscpSupplyBidDao;
import com.wonders.stpt.mscp.dao.MscpTotalBidDao;
import com.wonders.stpt.mscp.entity.bo.MscpAlarmStat;
import com.wonders.stpt.mscp.entity.bo.MscpExpertStat;
import com.wonders.stpt.mscp.entity.bo.MscpMonthBid;
import com.wonders.stpt.mscp.entity.bo.MscpMonthNotice;
import com.wonders.stpt.mscp.entity.bo.MscpMonthPortalVisit;
import com.wonders.stpt.mscp.entity.bo.MscpMonthVisit;
import com.wonders.stpt.mscp.entity.bo.MscpOrganTrade;
import com.wonders.stpt.mscp.entity.bo.MscpProfile;
import com.wonders.stpt.mscp.entity.bo.MscpSupplyBid;
import com.wonders.stpt.mscp.entity.bo.MscpTotalBid;
import com.wonders.stpt.mscp.entity.vo.MscpInfo;
import com.wonders.stpt.mscp.entity.vo.MscpMonthBidVo;
import com.wonders.stpt.mscp.service.MscpService;

public class MscpServiceImpl implements MscpService {
	
	private MscpDao mscpDao;
	private MscpMonthBidDao mscpMonthBidDao;
	
	public MscpMonthBidDao getMscpMonthBidDao() {
		return mscpMonthBidDao;
	}
	public void setMscpMonthBidDao(MscpMonthBidDao mscpMonthBidDao) {
		this.mscpMonthBidDao = mscpMonthBidDao;
	}
	
	private MscpMonthNoticeDao mscpMonthNoticeDao;
	private MscpMonthPortalVisitDao mscpMonthPortalVisitDao;
	private MscpMonthVisitDao mscpMonthVisitDao;
	private MscpOrganTradeDao mscpOrganTradeDao;
	private MscpProfileDao mscpProfileDao;
	private MscpSupplyBidDao mscpSupplyBidDao;
	private MscpTotalBidDao mscpTotalBidDao;
	private MscpAlarmStatDao mscpAlarmStatDao;
	private MscpExpertStatDao mscpExpertStatDao;
	private MscpSupplierStatDao mscpSupplierStatDao;
	private MscpMonthSupplierStatDao mscpMonthSupplierStatDao;
	private MscpMonthBidStatDao mscpMonthBidStatDao;
	
	
	public MscpDao getMscpDao() {
		return mscpDao;
	}
	public void setMscpDao(MscpDao mscpDao) {
		this.mscpDao = mscpDao;
	}
	public MscpMonthNoticeDao getMscpMonthNoticeDao() {
		return mscpMonthNoticeDao;
	}
	public void setMscpMonthNoticeDao(MscpMonthNoticeDao mscpMonthNoticeDao) {
		this.mscpMonthNoticeDao = mscpMonthNoticeDao;
	}
	public MscpMonthPortalVisitDao getMscpMonthPortalVisitDao() {
		return mscpMonthPortalVisitDao;
	}
	public void setMscpMonthPortalVisitDao(
			MscpMonthPortalVisitDao mscpMonthPortalVisitDao) {
		this.mscpMonthPortalVisitDao = mscpMonthPortalVisitDao;
	}
	public MscpMonthVisitDao getMscpMonthVisitDao() {
		return mscpMonthVisitDao;
	}
	public void setMscpMonthVisitDao(MscpMonthVisitDao mscpMonthVisitDao) {
		this.mscpMonthVisitDao = mscpMonthVisitDao;
	}
	public MscpOrganTradeDao getMscpOrganTradeDao() {
		return mscpOrganTradeDao;
	}
	public void setMscpOrganTradeDao(MscpOrganTradeDao mscpOrganTradeDao) {
		this.mscpOrganTradeDao = mscpOrganTradeDao;
	}
	public MscpProfileDao getMscpProfileDao() {
		return mscpProfileDao;
	}
	public void setMscpProfileDao(MscpProfileDao mscpProfileDao) {
		this.mscpProfileDao = mscpProfileDao;
	}
	public MscpSupplyBidDao getMscpSupplyBidDao() {
		return mscpSupplyBidDao;
	}
	public void setMscpSupplyBidDao(MscpSupplyBidDao mscpSupplyBidDao) {
		this.mscpSupplyBidDao = mscpSupplyBidDao;
	}
	public MscpTotalBidDao getMscpTotalBidDao() {
		return mscpTotalBidDao;
	}
	public void setMscpTotalBidDao(MscpTotalBidDao mscpTotalBidDao) {
		this.mscpTotalBidDao = mscpTotalBidDao;
	}
	
	
	
	
	public MscpAlarmStatDao getMscpAlarmStatDao() {
		return mscpAlarmStatDao;
	}
	public void setMscpAlarmStatDao(MscpAlarmStatDao mscpAlarmStatDao) {
		this.mscpAlarmStatDao = mscpAlarmStatDao;
	}
	public MscpExpertStatDao getMscpExpertStatDao() {
		return mscpExpertStatDao;
	}
	public void setMscpExpertStatDao(MscpExpertStatDao mscpExpertStatDao) {
		this.mscpExpertStatDao = mscpExpertStatDao;
	}
	public MscpSupplierStatDao getMscpSupplierStatDao() {
		return mscpSupplierStatDao;
	}
	public void setMscpSupplierStatDao(MscpSupplierStatDao mscpSupplierStatDao) {
		this.mscpSupplierStatDao = mscpSupplierStatDao;
	}
	public MscpMonthSupplierStatDao getMscpMonthSupplierStatDao() {
		return mscpMonthSupplierStatDao;
	}
	public void setMscpMonthSupplierStatDao(
			MscpMonthSupplierStatDao mscpMonthSupplierStatDao) {
		this.mscpMonthSupplierStatDao = mscpMonthSupplierStatDao;
	}
	public MscpMonthBidStatDao getMscpMonthBidStatDao() {
		return mscpMonthBidStatDao;
	}
	public void setMscpMonthBidStatDao(MscpMonthBidStatDao mscpMonthBidStatDao) {
		this.mscpMonthBidStatDao = mscpMonthBidStatDao;
	}
	@Override
	public MscpInfo findMscpInfo(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,-1);
		String now = df.format(c.getTime());
		String last = getLastMonthDay(now);
		String snow = now.substring(0,7);
		String slast = last.substring(0,7);
		
		Map<String,String> map1 = new HashMap<String,String>();initMap(map1,now,last);
		Map<String,String> map2 = new HashMap<String,String>();initMap(map2,now,last);
		Map<String,String> map3 = new HashMap<String,String>();initMap(map3,now,last);
		Map<String,String> map4 = new HashMap<String,String>();initMap(map4,now,last);
		Map<String,String> map5 = new HashMap<String,String>();initMap(map5,now,last);
		Map<String,String> map6 = new HashMap<String,String>();initMap(map6,now,last);
		Map<String,String> map7 = new HashMap<String,String>();initMap(map7,now,last);
		mscpMonthPortalVisitDao.findMscpPortalVisitCompare(now,last,map1);
		mscpMonthVisitDao.findMscpMonthVisitCompare(now,last,map2);
		mscpMonthNoticeDao.findMscpMonthNoticeCompare(now, last,map3,map4,map5,map6,map7);
		
		
		
		MscpInfo mscpInfo = new MscpInfo();
		mscpInfo.setGetBidNoticeMoney(mscpMonthBidStatDao.findMscpMonthBidStatSum());
		mscpInfo.setPortalVisit(mscpMonthPortalVisitDao.findMscpMonthPortalVisitByDate(date));
		mscpInfo.setPlatformUserVisit(mscpMonthVisitDao.findCountByDate(date));
		mscpInfo.setPortalVisitY(calMap(map1,snow,slast));
		mscpInfo.setPlatformUserVisitY(calMap(map2,snow,slast));
		
		MscpProfile mscpProfile = mscpProfileDao.findMscpProfileByDate(date);
		if(mscpProfile!=null){
			mscpInfo.setRegisterSupplyCount(mscpProfile.getRegSupplyNum());
			mscpInfo.setQualifedSupplyCount(mscpProfile.getQualifySupplyNum());
			mscpInfo.setTotalComplainCount(mscpProfile.getComplainNum());
			mscpInfo.setExpertsCount(mscpProfile.getExpertNum());
			mscpInfo.setPurchaseCount(mscpProfile.getPurchaseNum());
		}
		List<Object[]> list = mscpMonthNoticeDao.findMscpMonthNoticeByDate(date);
		if(list!=null&&list.size()==1){
			mscpInfo.setSingleNoticeCount(String.valueOf(list.get(0)[0]));
			mscpInfo.setBidNoticeCount(String.valueOf(list.get(0)[1]));
			mscpInfo.setGetBidNoticeCount(String.valueOf(list.get(0)[2]));
			mscpInfo.setCompeteNoticeCount(String.valueOf(list.get(0)[3]));
			mscpInfo.setCompeteResultPrice(String.valueOf(list.get(0)[4]));
			mscpInfo.setSingleNoticeCountY(calMap(map3,snow,slast));
			mscpInfo.setBidNoticeCountY(calMap(map4,snow,slast));
			mscpInfo.setGetBidNoticeCountY(calMap(map5,snow,slast));
			mscpInfo.setCompeteNoticeCountY(calMap(map6,snow,slast));
			mscpInfo.setCompeteResultPriceY(calMap(map7,snow,slast));
		}
		
		mscpInfo.setTradePrice(mscpSupplyBidDao.findMscpSupplyBidCountByDate(date));
		
		
		return mscpInfo;
	}
	
	
	
	@Override
	public List<MscpOrganTrade> findMscpOrganTrade(String date) {
		return mscpOrganTradeDao.findMscpOrganTrade(date);
	}
	
	@Override
	public List<MscpSupplyBid> findMscpSupplyBid(String date) {
		List<MscpSupplyBid> list = mscpSupplyBidDao.findMscpSupplyBid(date);
		return  list;
	}
	
	@Override
	public List<MscpExpertStat> findMscpExpertStat(){
		return this.mscpExpertStatDao.findMscpExpertStat();
	}
	
	@Override
	public List<MscpMonthPortalVisit> findMonthPortalVisit() {
		
		return mscpMonthPortalVisitDao.findMscpMonthPortalVisit();
	}
	@Override
	public List<MscpMonthVisit> findMonthVisit() {
	
		return mscpMonthVisitDao.findMscpMonthVisit();
	}
	@Override
	public List<MscpMonthNotice> findMonthNotice() {
		
		return mscpMonthNoticeDao.findMscpMonthNotice();
	}
	
	@Override
	public List<Object[]> findMscpMonthBidStatByMonth(){
		return this.mscpMonthBidStatDao.findMscpMonthBidStatByMonth();
	}
	
	@Override
	public List<Object[]> findMscpMonthBidMoneySumByMonth(){
		return this.mscpMonthBidDao.findMscpMonthBidMoneySumByMonth();
	}
	
	@Override
	public List<Object[]> findMscpMonth(){
		return this.mscpMonthBidDao.findMscpMonth();
	}
	
	@Override
	public MscpMonthBidVo findMonthBid() {
		MscpMonthBidVo vo = new MscpMonthBidVo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-3);
		for(int i=0;i<4;i++){
			String yearMonth = sdf.format(c.getTime());
			List<MscpMonthBid> list = mscpMonthBidDao.findMscpMonthBidByDate(yearMonth);
			if(list!=null && list.size()==3){
				vo.putX(yearMonth);
				vo.putY(list);
			}	
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
		}
		return vo;
	}
	@Override
	public List<MscpTotalBid> findMscpTotalBid() {
		
		return mscpTotalBidDao.findMscpTotalBid();
	}
	
//	public static void main(String[] args){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
//		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-3);
//		String yearMonth = sdf.format(c.getTime());
//		System.out.println(yearMonth);
//		
//		java.text.DecimalFormat df = new java.text.DecimalFormat("0%");
//		System.out.println(df.format(-19.44/44.5));
//	}
	
	private void initMap(Map<String,String> map,String now,String last){
		map.put(now, "");
		map.put(last, "");
	}
	
	private String calMap(Map<String,String> map,String now,String last){
		java.text.DecimalFormat df = new java.text.DecimalFormat("0%");
		String result = "";
		String n = map.get(now);
		String l = map.get(last);
		int rr = 0;
		double nn = 0.0;
		double ll = 0.0;
		try {
			nn = Double.valueOf(n);
			ll = Double.valueOf(l);
		}catch(Exception e){
			
		}
		
		if(ll != 0 && nn !=0){
			double r = (nn-ll)/ll;	
			result = df.format(r);
		}else if(ll == 0){
			result = "100%";
		}else if(nn == 0){
			result = "0%";
		}
		
		return result;
	}
	
	@Override
	public List<Object[]> findMscpSupplierStatNum(){
		return this.mscpSupplierStatDao.findMscpSupplierStatNum();
	}
	
	@Override
	public List<Object[]> findMscpMonthSupplierStatNumThisMonth(String month){
		return this.mscpMonthSupplierStatDao.findMscpMonthSupplierStatNumThisMonth(month);
	}
	
	@Override
	public List<Object[]> findMscpMonthSupplierStatSumByMonth(){
		return this.mscpMonthSupplierStatDao.findMscpMonthSupplierStatSumByMonth();
	}
	
	@Override
	public List<MscpAlarmStat> findMscpAlarmStat(){
		return this.mscpAlarmStatDao.findMscpAlarmStat();
	}
	
	public String getLastMonthDay(String day) throws ParseException{
		String lastMonthDay = "";
		if(day.indexOf("03-29")>-1||day.indexOf("03-30")>-1||day.indexOf("03-31")>-1){
			lastMonthDay = day.replace("03-29", "02-28").replace("03-30", "02-28").replace("03-31", "02-28");
		}else if(day.indexOf("05-31")>-1){
			lastMonthDay = day.replace("05-31", "04-30");
		}else if(day.indexOf("07-31")>-1){
			lastMonthDay = day.replace("07-31", "06-30");
		}else if(day.indexOf("10-31")>-1){
			lastMonthDay = day.replace("10-31", "09-30");
		}else if(day.indexOf("12-31")>-1){
			lastMonthDay = day.replace("12-31", "11-30");
		}else{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(day));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
			lastMonthDay = df.format(c.getTime());
		}
		return lastMonthDay;
	}
}
