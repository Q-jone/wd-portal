package com.wonders.stpt.processInfo.dao;

import java.util.List;

public abstract interface ProcessInfoDao
{
  public abstract List<Object[]> findDocSendByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13);

  public abstract int countDocSend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13);

  public abstract List<Object[]> findDocReceiveByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11);

  public abstract int countDocReceive(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11);

  public abstract List<Object[]> findDocDirectiveByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);

  public abstract int countDocDirective(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);

  public abstract List<Object[]> findHtspOaByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11);

  public abstract int countHtspOa(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11);

  public abstract List<Object[]> findDbByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract int countDb(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract List<Object[]> findJobContactByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract int countJobContact(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract List<Object> findZleader();

  public abstract List<Object[]> findNewDocSendByPage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9);

  public abstract int countNewDocSend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9);

  public abstract String findLoginNameById(String paramString);
  
  public abstract List<Object[]> findPartySendByPage(int startRow,int pageSize,String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end);
  
  public abstract int countPartySend(String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end);
  
  public List<Object[]> findDisciplineSendByPage(int startRow,int pageSize,String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end);
  
  public int countDisciplineSend(String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end);
  
}