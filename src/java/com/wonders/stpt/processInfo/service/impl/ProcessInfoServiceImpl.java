package com.wonders.stpt.processInfo.service.impl;

import com.wonders.stpt.processInfo.dao.ProcessInfoDao;
import com.wonders.stpt.processInfo.service.ProcessInfoService;
import java.util.List;

public class ProcessInfoServiceImpl
  implements ProcessInfoService
{
  private ProcessInfoDao processInfoDao;

  public void setProcessInfoDao(ProcessInfoDao processInfoDao)
  {
    this.processInfoDao = processInfoDao;
  }

  public List<Object[]> findDocSendByPage(int startRow, int pageSize, String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator, String msgState, String oldLoginName, String newLoginName, String myPage)
  {
    return this.processInfoDao.findDocSendByPage(startRow, pageSize, doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator, msgState, oldLoginName, newLoginName, myPage);
  }

  public int countDocSend(String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator, String msgState, String oldLoginName, String newLoginName, String myPage)
  {
    return this.processInfoDao.countDocSend(doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator, msgState, oldLoginName, newLoginName, myPage);
  }

  public List<Object[]> findDocReceiveByPage(int startRow, int pageSize, String title, String swid, String num, String swunit, String filezh, String pstatus, String swdate_start, String swdate_end, String oldLoginName, String newLoginName, String myPage)
  {
    return this.processInfoDao.findDocReceiveByPage(startRow, pageSize, title, swid, num, swunit, 
      filezh, pstatus, swdate_start, swdate_end, oldLoginName, newLoginName, myPage);
  }

  public int countDocReceive(String title, String swid, String num, String swunit, String filezh, String pstatus, String swdate_start, String swdate_end, String oldLoginName, String newLoginName, String myPage)
  {
    return this.processInfoDao.countDocReceive(title, swid, num, swunit, filezh, pstatus, swdate_start, 
      swdate_end, oldLoginName, newLoginName, myPage);
  }

  public List<Object[]> findDocDirectiveByPage(int startRow, int pageSize, String title, String deptid, String zleader, String submitdate_start, String submitdate_end, String submitdept, String pstatus)
  {
    return this.processInfoDao.findDocDirectiveByPage(startRow, pageSize, title, deptid, zleader, 
      submitdate_start, submitdate_end, submitdept, pstatus);
  }

  public int countDocDirective(String title, String deptid, String zleader, String submitdate_start, String submitdate_end, String submitdept, String pstatus)
  {
    return this.processInfoDao.countDocDirective(title, deptid, zleader, 
      submitdate_start, submitdate_end, submitdept, pstatus);
  }

  public List<Object[]> findHtspOaByPage(int startRow, int pageSize, String plan_num, String contract_num, String self_num, String contract_name, String project_num, String sign_cop, String add_time_start, String add_time_end, String add_person, String contract_money, String pstatus)
  {
    return this.processInfoDao.findHtspOaByPage(startRow, pageSize, plan_num, contract_num, self_num, 
      contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, 
      contract_money, pstatus);
  }

  public int countHtspOa(String plan_num, String contract_num, String self_num, String contract_name, String project_num, String sign_cop, String add_time_start, String add_time_end, String add_person, String contract_money, String pstatus)
  {
    return this.processInfoDao.countHtspOa(plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, 
      contract_money, pstatus);
  }

  public List<Object[]> findDbByPage(int startRow, int pageSize, String bh_all, String depement_z, String depement_x, String flags)
  {
    return this.processInfoDao.findDbByPage(startRow, pageSize, bh_all, depement_z, depement_x, flags);
  }

  public int countDb(String bh_all, String depement_z, String depement_x, String flags) {
    return this.processInfoDao.countDb(bh_all, depement_z, depement_x, flags);
  }

  public List<Object[]> findJobContactByPage(int startRow, int pageSize, String serial, String theme, String main_unit, String copy_unit, String flag,String processType)
  {
    return this.processInfoDao.findJobContactByPage(startRow, pageSize, serial, theme, main_unit, copy_unit, flag,processType);
  }

  public int countJobContact(String serial, String theme, String main_unit, String copy_unit, String flag,String processType) {
    return this.processInfoDao.countJobContact(serial, theme, main_unit, copy_unit, flag,processType);
  }

  public List<Object> findZleader() {
    return this.processInfoDao.findZleader();
  }

  public List<Object[]> findNewDocSendByPage(int startRow, int pageSize, String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator)
  {
    return this.processInfoDao.findNewDocSendByPage(startRow, pageSize, doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator);
  }

  public int countNewDocSend(String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator)
  {
    return this.processInfoDao.countNewDocSend(doc_title, send_id, doc_count, send_date_start, 
      send_date_end, content, pstatus, name, operator);
  }

  public String findLoginNameById(String id) {
    return this.processInfoDao.findLoginNameById(id);
  }
  
  public List<Object[]> findPartySendByPage(int startRow,int pageSize,String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
	  return this.processInfoDao.findPartySendByPage(startRow, pageSize, dept, title, code, filezh,
			  pstatus, processType, date_start, date_end);
  }

  public int countPartySend(String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
	  return this.processInfoDao.countPartySend(dept, title, code, filezh,
			  pstatus, processType, date_start, date_end);
  }
  
  public List<Object[]> findDisciplineSendByPage(int startRow,int pageSize,String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
	  return this.processInfoDao.findDisciplineSendByPage(startRow, pageSize, dept, title, code, filezh, pstatus, processType, date_start, date_end);
  }

  public int countDisciplineSend(String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
	return this.processInfoDao.countDisciplineSend(dept, title, code, filezh, pstatus, processType, date_start, date_end);
  }
}