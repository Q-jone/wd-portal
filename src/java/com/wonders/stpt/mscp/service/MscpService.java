package com.wonders.stpt.mscp.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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




public interface MscpService {

	/**
	 * 查询基本信息
	 */
	public MscpInfo findMscpInfo(String date) throws ParseException;
	
	
	/**
	 * 查询组织机构前5名
	 */
	public List<MscpOrganTrade> findMscpOrganTrade(String date);
	
	/**
	 * 查询供应商中标金额
	 */
	public List<MscpSupplyBid> findMscpSupplyBid(String date);
	
	/**
	 * 查询门户月度访问量
	 */
	public List<MscpMonthPortalVisit> findMonthPortalVisit();
	
	/**
	 * 查询平台月度访问量
	 */
	public List<MscpMonthVisit> findMonthVisit();
	
	/**
	 * 查询月度公告分类统计
	 */
	public List<MscpMonthNotice> findMonthNotice();
	
	/**
	 * 查询竞价
	 */
	public List<MscpTotalBid> findMscpTotalBid();
	
	/**
	 * 查询月度竞价
	 */
	public MscpMonthBidVo findMonthBid();
	
	/**
	 * 查询总数分类
	 */
	public List<MscpExpertStat> findMscpExpertStat();
	
	public List<Object[]> findMscpMonthBidStatByMonth();
	
	public List<Object[]> findMscpMonthBidMoneySumByMonth();
	
	public List<Object[]> findMscpMonth();
	
	public List<Object[]> findMscpSupplierStatNum();
	
	public List<Object[]> findMscpMonthSupplierStatNumThisMonth(String month);
	
	public List<Object[]> findMscpMonthSupplierStatSumByMonth();
	
	public List<MscpAlarmStat> findMscpAlarmStat();
}
