/**
 * 
 */
package com.wonders.stpt.processDone.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeKey;
import com.wonders.stpt.processDone.model.bo.*;
import com.wonders.stpt.processDone.model.vo.*;
import com.wonders.stpt.processDone.service.ProcessDoneService;
import com.wonders.stpt.processDone.util.Constants;
import com.wonders.stpt.processDone.util.WebServiceFunc;
import com.wonders.stpt.qqbz.model.bo.*;
import com.wonders.stpt.qqbz.model.vo.*;
import com.wonders.stpt.util.SpringBeanUtil;
import com.wonders.stpt.util.StringUtil;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/** 
 * @ClassName: ProcessDoneAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lushuaifeng 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/processDone")
@Controller("processDoneItemAction")
@Scope("prototype")
public class ProcessDoneAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	
	@Autowired
	private ProcessDoneService processDoneService;
	
	@Action(value="findYbxByPage",results={@Result(name="success",location="/processDone/processDoneList.jsp")})
	public String findYbxByPage() {

		HashMap<String,String> filter=new HashMap<String,String>();				
		//String loginName=(String)session.getAttribute("loginName");工号+部门ID
		String loginName=(String)session.getAttribute("cs_login_name");//工号
		filter.put("doneUsers", loginName);

		Map mParams=request.getParameterMap();		
		Set<String> key = mParams.keySet();
	    for (Iterator it = key.iterator(); it.hasNext();) {
	        String paramName = (String) it.next();

	        String paramValue=request.getParameter(paramName);
	        if(!paramValue.equals("")){
		        request.setAttribute(paramName, paramValue);
		        
		        paramName=Constants.getFieldNameByParamName(paramName)==null?paramName:Constants.getFieldNameByParamName(paramName);
	        	filter.put(paramName, paramValue);

	        }
	    }
		
	    if(filter.get("page")==null||filter.get("page").equals("")){
	        request.setAttribute("page", "1");	
	        filter.put("page", "1");
	    }
	    
		Page msgPage=processDoneService.findByPage(filter, "desc");
	
		request.setAttribute("page", msgPage);

		return "success";
	}

	
	
	@Action(value="viewProcessInfo")
	public String viewProcessInfoById(){
		String id =  request.getParameter("id");
		ProcessDone data = processDoneService.loadById(id);
		String xmlStr=data.getData();
		String type=data.getPtype();
		xmlStr=xmlStr.replace("<Datas>","<?xml-stylesheet type=\"text/xsl\" href=\"/portal/processDone/xsl/"+type+".xsl\"?>\n<Datas>\n<processDoneId>"+id+"</processDoneId>\n");
		actionWriteXML(xmlStr);
		return null;
	}	
	
	
	@Action(value="printProcessInfo")
	public String printProcessInfoById(){
		String id =  request.getParameter("id");		
		if(id==null){
			String ptype = request.getParameter("ptype");//流程类型	
			HashMap<String,String> hmParam=new HashMap<String,String>();
			if(ptype.equalsIgnoreCase("jobContact")){
				String pid =  request.getParameter("pid");	
				hmParam.put("pid", pid);					
				id=parseJobContact("updateSingleData",hmParam);
				
			}else if(ptype.equalsIgnoreCase("docSend")){
				String pid =  request.getParameter("pid");	
				hmParam.put("pid", pid);					
				id=parseDocSend("updateSingleData",hmParam);
				
			}else if(ptype.equalsIgnoreCase("newJobContact")){
				String deptContactMainId =  request.getParameter("deptContactMainId");
				TDeptContactMain deptContact=processDoneService.getDeptContactMainById(deptContactMainId);		
				hmParam.put("pid", deptContact.getIncidentno());				
				id=parseNewJobContact("updateSingleData",hmParam);
				
			}else if(ptype.equalsIgnoreCase("htxx")){
				String pid =  request.getParameter("pid");	
				hmParam.put("pid", pid);					
				id=parseHtxx("updateSingleData",hmParam);
				
			}else if(ptype.equalsIgnoreCase("docReceive")){
				String pid =  request.getParameter("pid");	
				hmParam.put("pid", pid);					
				id=parseDocReceive("updateSingleData",hmParam);
				
			}else if(ptype.equalsIgnoreCase("reDirective")){
				String pid =  request.getParameter("pid");	
				hmParam.put("pid", pid);					
				id=parseReDirective("updateSingleData",hmParam);
			} 
		}
		ProcessDone pdRec = processDoneService.loadById(id);
		if(pdRec!=null){
			String xmlStr=pdRec.getData();
			String type=pdRec.getPtype();
			xmlStr=xmlStr.replace("<Datas","<?xml-stylesheet type=\"text/xsl\" href=\"/portal/processDone/xsl/"+type+"_print.xsl\"?>\n<Datas");
			actionWriteXML(xmlStr);
		}else{
			String returnUrl=request.getParameter("returnUrl");
			if(returnUrl!=null){
				try {
					response.sendRedirect(returnUrl);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					actionWrite("{\"success\":false,\"msg\":\"redirect to old print page failed!\"}");
				}
			}else{
				actionWrite("{\"success\":false,\"msg\":\"have not returnUrl and can not find process done record by id\""+id+"}");
			}
			
		}
		return null;
	}		

	
	public void actionWriteXML(String str){
		if(response==null) return;
		Writer w = null;
		
		try {
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			
			w = response.getWriter();
			
			w.write(str);
			
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(w!=null){
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionWrite(String str){
		if(response==null) return;
		Writer w = null;
		
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			w = response.getWriter();
			
			w.write(str);
			
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(w!=null){
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private int getLatestDays(String latestDays){
    	int iLatestDays = 0;
    	try{
    		iLatestDays = Integer.valueOf(latestDays);
		}catch(Exception e){
			iLatestDays = 7;
		}
		return iLatestDays;
    }
    
    private int getStartNum(String startNum){
    	int iStartNum = 0;
    	try{
			iStartNum = Integer.valueOf(startNum);
		}catch(Exception e){
			iStartNum = -1;
		}
		return iStartNum;
    }
    
    private int getLength(String length){
    	int iLength = 2000;
    	try{
			iLength = Integer.valueOf(length);
			if(iLength>2000){
				iLength = 2000;
			}
		}catch(Exception e){
			iLength = -1;
		}
		return iLength;
    }
	
    
	@SuppressWarnings("unchecked")
	@Action(value="updateProcessDone")
	public String updateProcessDone() {
		HashMap<String,String> hmParam=new HashMap<String,String>();		
		String ptype = request.getParameter("ptype");//流程类型				
		String funcType = request.getParameter("funcType");//方法类型
		
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			String latestDays = request.getParameter("latestDays");//更新最近几天内变为已完成的流程及所有进行中的流程
			if(latestDays==null){
				hmParam.put("latestDays", "7");//默认更新7天内的数据
			}else{
				hmParam.put("latestDays", latestDays);
			}
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			String pid = request.getParameter("pid");//单条记录更新所需
			if(pid==null){
				actionWrite("{\"success\":false,\"msg\":\"the param 'pid' is needed for the function 'updateSingleData'\"}");
				return null;
			}else{
				hmParam.put("pid", pid);
			}

		}else if("addDatas".equals(funcType)){//批量插入数据
			String startNum = request.getParameter("startNum");//批量插入的起始实例号
			String length = request.getParameter("length");//批量插入的流程数，最多2000条一次
			if(startNum==null ||length==null){
				actionWrite("{\"success\":false,\"msg\":\"the params 'startNum' and 'length' are needed for the function 'addDatas'!\"}");
				return null;
			}else{
				hmParam.put("startNum", startNum);
				hmParam.put("length", length);
			}
		}

		String updateResult="noSuchMethod";
		if(ptype.equalsIgnoreCase("jobContact")){
			updateResult=parseJobContact(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("docSend")){
			updateResult=parseDocSend(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("newJobContact")){
			updateResult=parseNewJobContact(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("htxx")){
			updateResult=parseHtxx(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("docReceive")){
			updateResult=parseDocReceive(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("reDirective")){
			updateResult=parseReDirective(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("newDocSend")){
			updateResult=parseNewDocSend(funcType,hmParam);
		}else if(ptype.equalsIgnoreCase("project")){
			updateResult=parseProject(funcType,hmParam);
		}else if("contractReviewResult".equalsIgnoreCase(ptype)){
			updateResult=parseContractReviewResult(funcType,hmParam);
		}
		
		
		if(updateResult.equalsIgnoreCase("noSuchMethod")||updateResult.equalsIgnoreCase("noRecExeute")||updateResult.equalsIgnoreCase("exception")){
			actionWrite("{\"success\":false,\"msg\":\""+updateResult+"\"}");
		}else if(updateResult.equalsIgnoreCase("success")){
			actionWrite("{\"success\":true}");
		}else{
			actionWrite("{\"success\":true,\"msg\":\"pid="+updateResult+"\"}");
		}
		return null;
	}
	
	/**
     * 工作联系单
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseJobContact(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "jobContact";
		String pname = "工作联系单";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from JobContact t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and t.processname = s.id.processname and t.instanceID = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from JobContact t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and s.id.incident = "+hmParam.get("pid")+" and t.processname = s.id.processname and t.instanceID = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from JobContact t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and t.processname = s.id.processname and t.instanceID = s.id.incident and s.id.incident>"+iStartNum+" and s.id.incident<="+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(JobContactData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					JobContact jobContact = (JobContact) list.get(i)[0];
					Incidents incidents = (Incidents)list.get(i)[1];
					List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
					List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+jobContact.getContent_attachment_id()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					
					List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
					if(approvedList!=null&&approvedList.size()>0){
						for(int j=0;j<approvedList.size();j++){
							TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
							ApprovedinfoXml vo = new ApprovedinfoXml();
							BeanUtils.copyProperties(bo, vo);
							vo.setUpddateStr(df.format(bo.getUpddate()));
							voList.add(vo);
						}
					}
					
					if(attachList!=null&&attachList.size()>0){
						for(int j=0;j<attachList.size();j++){
							AttachFile2 bo = (AttachFile2)attachList.get(j);
							bo.setFileSize(bo.getFileSize()/1024l);
						}
					}
					
					
					TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
					AttachFileList attachFileList = new AttachFileList(attachList);
					JobContactData data = new JobContactData(jobContact,attachFileList,tApprovedinfoList);
					//TDocSend tDocSend = new TDocSend();
					//tDocSend.setDocTitle("测试");
	//				FileWriter fw = new FileWriter("D:\\test\\"+jobContact.getId()+".xml");
	//				m.marshal(data, fw);
					
					
					StringWriter sw = new StringWriter();
					m.marshal(data, sw);
					//System.out.println(sw.toString());
					
					ProcessDone pd = new ProcessDone();
					List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
					if(downList!=null&&downList.size()>0){
						pd = (ProcessDone) downList.get(0);
					}
					pd.setPid(String.valueOf(incidents.getId().getIncident()));
					pd.setPname(incidents.getId().getProcessname());
					pd.setPtype(ptype);
					pd.setSummary(incidents.getSummary());
					pd.setStartTime(incidents.getStarttime());
					pd.setEndTime(incidents.getEndtime());
					pd.setApplyUser(jobContact.getContent_operator());
					pd.setApplyDept(jobContact.getContent_inscribe());
					pd.setStatus(incidents.getStatus().longValue());
					pd.setData(sw.toString());
					
					List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
					if(userList!=null&&userList.size()>0){
						String doneUsers = "";
						for(int j=0;j<userList.size();j++){
							if(j>0){
								doneUsers += ",";
							}
							doneUsers += String.valueOf(userList.get(j));
						}
						pd.setDoneUsers(doneUsers);
						//System.out.println(i+"&&"+doneUsers);
					}
					String taskid = processDoneService.findTaskId(pname, pd.getPid());
					if(taskid!=null){
						pd.setTaskid(taskid);
					}
					
					pdList.add(pd);
					
					//System.out.println(i+"--finish+id===="+jobContact.getId());
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	/**
     * 发文流程
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseDocSend(String funcType,HashMap<String,String> hmParam){
		System.out.println("inDocSend..");
		return parseDocSendBase(funcType,hmParam,"docSend","发文流程");
	}
	
	/**
     * 新发文流程
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseNewDocSend(String funcType,HashMap<String,String> hmParam){
		System.out.println("inNewDocSend..");
		return parseDocSendBase(funcType,hmParam,"newDocSend","新发文流程");
	}
	
	public String parseDocSendBase(String funcType,HashMap<String,String> hmParam,String ptype,String pname){
		String returnStr="noRecExeute";
		String hql = "";
		String pname_param = "";
		if("发文流程".equals(pname)){
			pname_param = " and (s.id.processname = '发文流程' or s.id.processname = '党委发文流程' or s.id.processname = '纪委发文流程') ";
		}else if("新发文流程".equals(pname)){
			pname_param = " and (s.id.processname = '新发文流程' or s.id.processname = '新党委发文流程' or s.id.processname = '新纪委发文流程') ";
		}
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from TDocSend t,Incidents s where t.removed = '0' "+pname_param+" and t.modelid = s.id.processname and t.pinstanceid = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from TDocSend t,Incidents s where t.removed = '0' "+pname_param+" and s.id.incident = "+hmParam.get("pid")+" and t.modelid = s.id.processname and t.pinstanceid = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from TDocSend t,Incidents s where t.removed = '0' "+pname_param+" and t.modelid = s.id.processname and t.pinstanceid = s.id.incident and s.id.incident>"+iStartNum+" and s.id.incident<="+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(DocSendData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					TDocSend tDocSend = (TDocSend) list.get(i)[0];
					Incidents incidents = (Incidents)list.get(i)[1];
					List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
					List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+tDocSend.getContentAtt()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					List attachListMain = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+tDocSend.getContentAttMain()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					
					List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
					if(approvedList!=null&&approvedList.size()>0){
						for(int j=0;j<approvedList.size();j++){
							TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
							ApprovedinfoXml vo = new ApprovedinfoXml();
							BeanUtils.copyProperties(bo, vo);
							vo.setUpddateStr(df.format(bo.getUpddate()));
							voList.add(vo);
						}
					}
					
					if(attachList!=null&&attachList.size()>0){
						for(int j=0;j<attachList.size();j++){
							AttachFile2 bo = (AttachFile2)attachList.get(j);
							bo.setFileSize(bo.getFileSize()/1024l);
						}
					}
					
					
					TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
					AttachFileList attachFileList = new AttachFileList(attachList);
					AttachFileList attachFileListMain = new AttachFileList(attachListMain);
					AttMain attMain = new AttMain(attachFileListMain);
					tDocSend.attMain = attMain;
					DocSendData data = new DocSendData(tDocSend,attachFileList,tApprovedinfoList);
					//TDocSend tDocSend = new TDocSend();
					//tDocSend.setDocTitle("测试");
	//				FileWriter fw = new FileWriter("D:\\test\\"+jobContact.getId()+".xml");
	//				m.marshal(data, fw);
					
					
					StringWriter sw = new StringWriter();
					m.marshal(data, sw);
					//System.out.println(sw.toString());
					
					ProcessDone pd = new ProcessDone();
					List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
					if(downList!=null&&downList.size()>0){
						pd = (ProcessDone) downList.get(0);
					}else{
						pd.setRemark("0");
					}
					pd.setPid(String.valueOf(incidents.getId().getIncident()));
					pd.setPname(incidents.getId().getProcessname());
					pd.setPtype(ptype);
					pd.setSummary(incidents.getSummary());
					pd.setStartTime(incidents.getStarttime());
					pd.setEndTime(incidents.getEndtime());
					pd.setApplyUser(tDocSend.getSendUser());
					pd.setApplyDept(tDocSend.getSendUserdept());
					pd.setStatus(incidents.getStatus().longValue());
					pd.setData(sw.toString());
					
					List<Object[]> userList = processDoneService.findDoneUsers(pd.getPname(),pd.getPid());
					if(userList!=null&&userList.size()>0){
						String doneUsers = "";
						for(int j=0;j<userList.size();j++){
							if(j>0){
								doneUsers += ",";
							}
							doneUsers += String.valueOf(userList.get(j));
						}
						pd.setDoneUsers(doneUsers);
						//System.out.println(i+"&&"+doneUsers);
					}
					
					String taskid = processDoneService.findTaskId(pd.getPname(), pd.getPid());
					if(taskid!=null){
						pd.setTaskid(taskid);
					}
					
					pdList.add(pd);
					//processDoneService.saveOrUpdate(pd);
					
					//System.out.println(i+"--finish+id===="+tDocSend.getId());
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	/**
     * 多极工作联系单
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseNewJobContact(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "newJobContact";
		String pname = "多级工作联系单";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from TDeptContactMain t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and t.processname = s.id.processname and t.incidentno = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from TDeptContactMain t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and s.id.incident = "+hmParam.get("pid")+" and t.processname = s.id.processname and t.incidentno = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from TDeptContactMain t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and t.processname = s.id.processname and t.incidentno = s.id.incident and s.id.incident>"+iStartNum+" and s.id.incident<="+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(JobContactData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					TDeptContactMain tDeptContactMain = (TDeptContactMain) list.get(i)[0];
					JobContact jobContact = new JobContact();
					jobContact.setId(tDeptContactMain.getId());
					jobContact.setMain_unit(tDeptContactMain.getMainUnitId());
					jobContact.setMain_unit(tDeptContactMain.getMainUnit());
					jobContact.setCopy_unit_id(tDeptContactMain.getCopyUnitId());
					jobContact.setCopy_unit(tDeptContactMain.getCopyUnit());
					jobContact.setContact_date(tDeptContactMain.getContactDate());
					jobContact.setReply_date(tDeptContactMain.getReplyDate());
					jobContact.setTime_diff(tDeptContactMain.getTimeDiff());
					jobContact.setTheme(tDeptContactMain.getTheme());
					jobContact.setContent(tDeptContactMain.getContent());
					jobContact.setContent_attachment_id(tDeptContactMain.getContentAttachmentId());
					jobContact.setProcessname(tDeptContactMain.getProcessname());
					jobContact.setInstanceID(tDeptContactMain.getIncidentno());
					if(tDeptContactMain.getSerial()!=null){
						jobContact.setSerial(String.valueOf(tDeptContactMain.getSerial()));
					}
					jobContact.setApply_deptId(tDeptContactMain.getCreateDeptid());
					jobContact.setContent_inscribe(tDeptContactMain.getCreateDeptname());
					jobContact.setContent_operator(tDeptContactMain.getInitiatorName());
					jobContact.setRemoved(String.valueOf(tDeptContactMain.getRemoved()));
					
					
					Incidents incidents = (Incidents)list.get(i)[1];
					List approvedList = processDoneService.findByHQL("select a from TApprovedinfo a where a.guid in (select b.guid from TApprovedinfo b where b.process = '"+incidents.getId().getProcessname()+"' and b.incidentno = "+incidents.getId().getIncident()+
								" )");
					List approvedList2 = processDoneService.findByHQL("select a from TApprovedinfo a where a.guid in ( select t.guid from TApprovedinfo t,TSubprocess s where s.cname = t.process and s.cincident = t.incidentno and s.pname = '"+incidents.getId().getProcessname()+"' and s.pincident = "+incidents.getId().getIncident()+
							" ) order by a.incidentno,a.upddate");
					approvedList.addAll(approvedList2);
					List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+jobContact.getContent_attachment_id()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					
					List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
					if(approvedList!=null&&approvedList.size()>0){
						for(int j=0;j<approvedList.size();j++){
							TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
							ApprovedinfoXml vo = new ApprovedinfoXml();
							BeanUtils.copyProperties(bo, vo);
							vo.setUpddateStr(df.format(bo.getUpddate()));
							voList.add(vo);
						}
					}
					
					if(attachList!=null&&attachList.size()>0){
						for(int j=0;j<attachList.size();j++){
							AttachFile2 bo = (AttachFile2)attachList.get(j);
							bo.setFileSize(bo.getFileSize()/1024l);
						}
					}
					
					
					TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
					AttachFileList attachFileList = new AttachFileList(attachList);
					JobContactData data = new JobContactData(jobContact,attachFileList,tApprovedinfoList);
					//TDocSend tDocSend = new TDocSend();
					//tDocSend.setDocTitle("测试");
	//				FileWriter fw = new FileWriter("D:\\test\\"+jobContact.getId()+".xml");
	//				m.marshal(data, fw);
					
					
					StringWriter sw = new StringWriter();
					m.marshal(data, sw);
					//System.out.println(sw.toString());
					
					ProcessDone pd = new ProcessDone();
					List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
					if(downList!=null&&downList.size()>0){
						pd = (ProcessDone) downList.get(0);
					}
					pd.setPid(String.valueOf(incidents.getId().getIncident()));
					pd.setPname(incidents.getId().getProcessname());
					pd.setPtype(ptype);
					pd.setSummary(incidents.getSummary());
					pd.setStartTime(incidents.getStarttime());
					pd.setEndTime(incidents.getEndtime());
					pd.setApplyUser(jobContact.getContent_operator());
					pd.setApplyDept(jobContact.getContent_inscribe());
					pd.setStatus(incidents.getStatus().longValue());
					pd.setData(sw.toString());
					//System.out.println(sw.toString());
					List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
					if(userList!=null&&userList.size()>0){
						String doneUsers = "";
						for(int j=0;j<userList.size();j++){
							if(j>0){
								doneUsers += ",";
							}
							doneUsers += String.valueOf(userList.get(j));
						}
						pd.setDoneUsers(doneUsers);
						//System.out.println(i+"&&"+doneUsers);
					}
					String taskid = processDoneService.findTaskId(pname, pd.getPid());
					if(taskid!=null){
						pd.setTaskid(taskid);
					}
					
					pdList.add(pd);
					
					//System.out.println(i+"--finish+id===="+jobContact.getId());
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	/**
     * 合同审批流程
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseHtxx(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "htxx";
		String pname = "合同审批流程";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from HtXx t,HtBa v,Incidents s where t.id = v.htId and s.id.processname = '"+pname+"'  and t.ModelId = s.id.processname and t.PinstanceId = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from HtXx t,HtBa v,Incidents s where t.id = v.htId and s.id.processname = '"+pname+"'  and s.id.incident = "+hmParam.get("pid")+" and t.ModelId = s.id.processname and t.PinstanceId = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from HtXx t,HtBa v,Incidents s where t.id = v.htId and s.id.processname = '"+pname+"'  and t.ModelId = s.id.processname and t.PinstanceId = s.id.incident and s.id.incident>"+iStartNum+" and s.id.incident<="+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(HtxxData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				int last_incident = 0;
				for(int i=0;i<list.size();i++){
					HtXx htxx = (HtXx) list.get(i)[0];
					HtBa htba = (HtBa) list.get(i)[1];
					Incidents incidents = (Incidents)list.get(i)[2];
					if(incidents.getId().getIncident().intValue()!=last_incident){
						List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
						List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+htxx.getHtAttach()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
						
						List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
						if(approvedList!=null&&approvedList.size()>0){
							for(int j=0;j<approvedList.size();j++){
								TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
								ApprovedinfoXml vo = new ApprovedinfoXml();
								BeanUtils.copyProperties(bo, vo);
								vo.setUpddateStr(df.format(bo.getUpddate()));
								voList.add(vo);
							}
						}
						
						if(attachList!=null&&attachList.size()>0){
							for(int j=0;j<attachList.size();j++){
								AttachFile2 bo = (AttachFile2)attachList.get(j);
								bo.setFileSize(bo.getFileSize()/1024l);
							}
						}
						
						
						TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
						AttachFileList attachFileList = new AttachFileList(attachList);
						HtBasicData htBasicData = new HtBasicData(htxx,htba);
						HtxxData data = new HtxxData(htBasicData,attachFileList,tApprovedinfoList);
						//TDocSend tDocSend = new TDocSend();
						//tDocSend.setDocTitle("测试");
		//				FileWriter fw = new FileWriter("D:\\test\\"+jobContact.getId()+".xml");
		//				m.marshal(data, fw);
						
						
						StringWriter sw = new StringWriter();
						m.marshal(data, sw);
						//System.out.println(sw.toString());
						
						ProcessDone pd = new ProcessDone();
						List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
						if(downList!=null&&downList.size()>0){
							pd = (ProcessDone) downList.get(0);
						}
						pd.setPid(String.valueOf(incidents.getId().getIncident()));
						pd.setPname(incidents.getId().getProcessname());
						pd.setPtype(ptype);
						pd.setSummary(incidents.getSummary());
						pd.setStartTime(incidents.getStarttime());
						pd.setEndTime(incidents.getEndtime());
						pd.setApplyUser(htxx.getAddPerson());
						pd.setApplyDept(processDoneService.findAdderDept(pname, pd.getPid()).replace("null",""));
						pd.setStatus(incidents.getStatus().longValue());
						pd.setData(sw.toString());
						
						List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
						if(userList!=null&&userList.size()>0){
							String doneUsers = "";
							for(int j=0;j<userList.size();j++){
								if(j>0){
									doneUsers += ",";
								}
								doneUsers += String.valueOf(userList.get(j));
							}
							pd.setDoneUsers(doneUsers);
							//System.out.println(i+"&&"+doneUsers);
						}
						String taskid = processDoneService.findTaskId(pname, pd.getPid());
						if(taskid!=null){
							pd.setTaskid(taskid);
						}
						
						pdList.add(pd);
						
						//System.out.println(i+"--finish+id===="+htxx.getId());
					}
					last_incident = incidents.getId().getIncident().intValue();
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	public String parseContractReviewResult(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "contractReviewResult";
		String pname = "合同后审流程";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from ReviewBo t,Incidents s where s.id.processname = '"+pname+"'  and t.process = s.id.processname and t.incident = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from ReviewBo t ,Incidents s where s.id.processname = '"+pname+"'  and s.id.incident = "+hmParam.get("pid")+" and t.process = s.id.processname and t.incident = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from ReviewBo t,Incidents s where s.id.processname = '"+pname+"'  and t.process = s.id.processname and t.process = s.id.incident and s.id.incident>"+iStartNum+" and s.id.incident<="+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(ReviewInfoXml.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				int last_incident = 0;
				for(int i=0;i<list.size();i++){
					ReviewBo reviewBo = (ReviewBo) list.get(i)[0];
					ReviewVo reviewVo = new ReviewVo();
                    String registerLoginName = reviewBo.getRegLoginName();

					org.apache.commons.beanutils.BeanUtils.copyProperties(reviewVo, reviewBo);

                    //增加 登记人部门ID
                    List<Object[]> objList = this.processDoneService.getUserDeptInfo(registerLoginName);
					if(objList != null && objList.size() > 0){
                        reviewVo.setregisterDeptId(StringUtil.getNotNullValueString(objList.get(0)[0]));
                        reviewVo.setRegisterDeptName(StringUtil.getNotNullValueString(objList.get(0)[1]));
                    }

                    Incidents incidents = (Incidents)list.get(i)[1];
					if(incidents.getId().getIncident().intValue()!=last_incident){
						List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
						List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+reviewBo.getAttach()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
						
						List<ApproveVo> voList = new ArrayList<ApproveVo>();
						if(approvedList!=null&&approvedList.size()>0){
							for(int j=0;j<approvedList.size();j++){
								TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
								ApproveVo vo = new ApproveVo();
								BeanUtils.copyProperties(bo, vo, new String[]{"incidentno","upddate"});
								vo.setIncidentno(bo.getIncidentno()+"");
								vo.setUpddate(df.format(bo.getUpddate()));
								vo.setApproveAttach(bo.getFileGroupId()==null?"":bo.getFileGroupId());
								voList.add(vo);
							}
						}
						
						if(attachList!=null&&attachList.size()>0){
							for(int j=0;j<attachList.size();j++){
								AttachFile2 bo = (AttachFile2)attachList.get(j);
								bo.setFileSize(bo.getFileSize()/1024l);
							}
						}
						
						
						ReviewInfoXml xml = new ReviewInfoXml();
						ApproveVoList approveList = new ApproveVoList();
						approveList.list = voList;
						AttachVoList attachsList = new AttachVoList();
						attachsList.attachFileList = attachList;
						xml.mainBo = reviewVo;
						xml.approveVo = approveList;
						xml.attachVo = attachsList;
						StringWriter sw = new StringWriter();
						m.marshal(xml, sw);
						//System.out.println(sw.toString());
						
						ProcessDone pd = new ProcessDone();
						List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
						if(downList!=null&&downList.size()>0){
							pd = (ProcessDone) downList.get(0);
						}
						pd.setPid(String.valueOf(incidents.getId().getIncident()));
						pd.setPname(incidents.getId().getProcessname());
						pd.setPtype(ptype);
						pd.setSummary(incidents.getSummary());
						pd.setStartTime(incidents.getStarttime());
						pd.setEndTime(incidents.getEndtime());
						pd.setApplyUser(reviewBo.getRegPerson());
						pd.setApplyDept(processDoneService.findAdderDept(pname, pd.getPid()).replace("null",""));
						pd.setStatus(incidents.getStatus().longValue());
						pd.setData(sw.toString());
						
						List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
						if(userList!=null&&userList.size()>0){
							String doneUsers = "";
							for(int j=0;j<userList.size();j++){
								if(j>0){
									doneUsers += ",";
								}
								doneUsers += String.valueOf(userList.get(j));
							}
							pd.setDoneUsers(doneUsers);
							//System.out.println(i+"&&"+doneUsers);
						}
						String taskid = processDoneService.findTaskId(pname, pd.getPid());
						if(taskid!=null){
							pd.setTaskid(taskid);
						}
						
						pdList.add(pd);
						
						//System.out.println(i+"--finish+id===="+htxx.getId());
					}
					last_incident = incidents.getId().getIncident().intValue();
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	
	/**
     * 收文流程
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseDocReceive(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "docReceive";
		String pname = "收文流程";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from DocReceiveBo t,Incidents s where t.removed = 0 and s.id.processname = '"+pname+"' and t.modelId = s.id.processname and t.instanceId = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from DocReceiveBo t,Incidents s where t.removed = 0 and s.id.processname = '"+pname+"' and s.id.incident = "+hmParam.get("pid")+" and t.modelId = s.id.processname and t.instanceId = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from DocReceiveBo t,Incidents s where t.removed = 0 and s.id.processname = '"+pname+"' and t.modelId = s.id.processname and t.instanceId = s.id.incident and to_number(s.id.incident) between "+iStartNum+" and "+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(DocReceiveData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					DocReceiveBo docReceiveBo = (DocReceiveBo) list.get(i)[0];
					Incidents incidents = (Incidents)list.get(i)[1];
					List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
					List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+docReceiveBo.getDrAttach()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					
					List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
					if(approvedList!=null&&approvedList.size()>0){
						for(int j=0;j<approvedList.size();j++){
							TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
							ApprovedinfoXml vo = new ApprovedinfoXml();
							BeanUtils.copyProperties(bo, vo);
							vo.setUpddateStr(df.format(bo.getUpddate()));
							voList.add(vo);
						}
					}
					
					if(attachList!=null&&attachList.size()>0){
						for(int j=0;j<attachList.size();j++){
							AttachFile2 bo = (AttachFile2)attachList.get(j);
							bo.setFileSize(bo.getFileSize()/1024l);
						}
					}
					
					
					TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
					AttachFileList attachFileList = new AttachFileList(attachList);
					DocReceiveData data = new DocReceiveData(docReceiveBo,attachFileList,tApprovedinfoList);
					//TDocSend tDocSend = new TDocSend();
					//tDocSend.setDocTitle("测试");
	//				FileWriter fw = new FileWriter("D:\\test\\"+jobContact.getId()+".xml");
	//				m.marshal(data, fw);
					
					
					StringWriter sw = new StringWriter();
					m.marshal(data, sw);
					//System.out.println(sw.toString());
					
					ProcessDone pd = new ProcessDone();
					List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
					if(downList!=null&&downList.size()>0){
						pd = (ProcessDone) downList.get(0);
					}
					pd.setPid(String.valueOf(incidents.getId().getIncident()));
					pd.setPname(incidents.getId().getProcessname());
					pd.setPtype(ptype);
					pd.setSummary(incidents.getSummary());
					pd.setStartTime(incidents.getStarttime());
					pd.setEndTime(incidents.getEndtime());
					pd.setApplyUser(processDoneService.findAdderName(pname, pd.getPid()).replace("null",""));
					pd.setApplyDept(docReceiveBo.getOrdinartyDep());
					pd.setStatus(incidents.getStatus().longValue());
					pd.setData(sw.toString());
					
					List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
					if(userList!=null&&userList.size()>0){
						String doneUsers = "";
						for(int j=0;j<userList.size();j++){
							if(j>0){
								doneUsers += ",";
							}
							doneUsers += String.valueOf(userList.get(j));
						}
						pd.setDoneUsers(doneUsers);
						//System.out.println(i+"&&"+doneUsers);
					}
					String taskid = processDoneService.findTaskId(pname, pd.getPid());
					if(taskid!=null){
						pd.setTaskid(taskid);
					}
					
					pdList.add(pd);
					
					//System.out.println(i+"--finish+id===="+docReceiveBo.getId());
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	/**
     * 收呈批件
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseReDirective(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "reDirective";
		String pname = "收呈批件";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from ReDirective t,Incidents s where t.removed = 0 and s.id.processname = '"+pname+"' and t.activeid = s.id.processname and t.processinstanceid = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from ReDirective t,Incidents s where t.removed = 0 and s.id.processname = '"+pname+"' and s.id.incident = "+hmParam.get("pid")+" and t.activeid = s.id.processname and t.processinstanceid = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from ReDirective t,Incidents s where t.removed = 0 and s.id.processname = '"+pname+"' and t.activeid = s.id.processname and t.processinstanceid = s.id.incident and to_number(s.id.incident) between "+iStartNum+" and "+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(ReDirectiveData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					ReDirective reDirective = (ReDirective) list.get(i)[0];
					Incidents incidents = (Incidents)list.get(i)[1];
					List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
					List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+reDirective.getAttach()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					
					List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
					if(approvedList!=null&&approvedList.size()>0){
						for(int j=0;j<approvedList.size();j++){
							TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
							ApprovedinfoXml vo = new ApprovedinfoXml();
							BeanUtils.copyProperties(bo, vo);
							vo.setUpddateStr(df.format(bo.getUpddate()));
							voList.add(vo);
						}
					}
					
					if(attachList!=null&&attachList.size()>0){
						for(int j=0;j<attachList.size();j++){
							AttachFile2 bo = (AttachFile2)attachList.get(j);
							bo.setFileSize(bo.getFileSize()/1024l);
						}
					}
					
					
					TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
					AttachFileList attachFileList = new AttachFileList(attachList);
					ReDirectiveData data = new ReDirectiveData(reDirective,attachFileList,tApprovedinfoList);
					//TDocSend tDocSend = new TDocSend();
					//tDocSend.setDocTitle("测试");
	//				FileWriter fw = new FileWriter("D:\\test\\"+jobContact.getId()+".xml");
	//				m.marshal(data, fw);
					
					
					StringWriter sw = new StringWriter();
					m.marshal(data, sw);
					//System.out.println(sw.toString());
					
					ProcessDone pd = new ProcessDone();
					List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
					if(downList!=null&&downList.size()>0){
						pd = (ProcessDone) downList.get(0);
					}
					pd.setPid(String.valueOf(incidents.getId().getIncident()));
					pd.setPname(incidents.getId().getProcessname());
					pd.setPtype(ptype);
					pd.setSummary(incidents.getSummary());
					pd.setStartTime(incidents.getStarttime());
					pd.setEndTime(incidents.getEndtime());
					pd.setApplyUser(reDirective.getOperator());
					pd.setApplyDept(reDirective.getSubmitdept());
					pd.setStatus(incidents.getStatus().longValue());
					pd.setData(sw.toString());
					
					List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
					if(userList!=null&&userList.size()>0){
						String doneUsers = "";
						for(int j=0;j<userList.size();j++){
							if(j>0){
								doneUsers += ",";
							}
							doneUsers += String.valueOf(userList.get(j));
						}
						pd.setDoneUsers(doneUsers);
						//System.out.println(i+"&&"+doneUsers);
					}
					String taskid = processDoneService.findTaskId(pname, pd.getPid());
					if(taskid!=null){
						pd.setTaskid(taskid);
					}
					
					pdList.add(pd);
					
					//System.out.println(i+"--finish+id===="+reDirective.getId());
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	@Action(value="setDocSendDataByWebService")
	public void setDocSendDataByWebService(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		
		String toDate = df.format(c.getTime());

		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		List<Object[]> typeList = processDoneService.findAllDocSendType();
		
		String stptPath = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("stptPath").trim();
		String workflowPath = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("workflowPreviewPath").trim();
		List<Object[]> codeIdList = processDoneService.findCodeIds();
		Map<String,String> codeIdMap = new HashMap<String,String>();
		if(codeIdList!=null&&codeIdList.size()>0){
			for(int i=0;i<codeIdList.size();i++){
				codeIdMap.put(codeIdList.get(i)[1]+"", codeIdList.get(i)[0]+"");
			}
		}
		
		String hql = "from ProcessDone t where (t.ptype = 'newDocSend' or t.ptype = 'docSend') and (t.data like '%<flag>1</flag>%' or t.data like '%<flag>2</flag>%') and t.endTime >(sysdate-1/48) and t.status = 2 and t.remark = '0' ";
		//String hql = "from ProcessDone t where t.ptype = 'docSend' and (t.data like '%<flag>1</flag>%' or t.data like '%<flag>2</flag>%') and t.pname = '党委发文流程' and to_char(t.endTime,'yyyy-mm-dd') like '2014-04%' and t.status = 2";
		List<Object> downList = processDoneService.findByHQL(hql);
		List<ProcessDone> plist = new ArrayList<ProcessDone>();
		if(downList!=null&&downList.size()>0){
			String data = "";
			for(int i=0;i<downList.size();i++){
				Document doc=null;
				Document docNew=null;
				ProcessDone pd = (ProcessDone)downList.get(i);
				pd.setRemark("1");
				plist.add(pd);
				data = pd.getData();
				try {
					doc = DocumentHelper.parseText(data);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Node sendMainId=doc.selectSingleNode("//sendMainId");//主送
				Node sendInsideId=doc.selectSingleNode("//sendInsideId");//抄送
				Node sendReportId=doc.selectSingleNode("//sendReportId");//内发
				Node modelid=doc.selectSingleNode("//modelid");//流程类型
				String deptIds = "";
				if(sendMainId!=null){
					String sendMainIdText = sendMainId.getStringValue();
					if(sendMainIdText!=null&&sendMainIdText.length()>0){
						deptIds += ","+sendMainIdText;
					}
				}
				if(sendInsideId!=null){
					String sendInsideIdText = sendInsideId.getStringValue();
					if(sendInsideIdText!=null&&sendInsideIdText.length()>0){
						deptIds += ","+sendInsideIdText;
					}
				}
				if(sendReportId!=null){
					String sendReportIdText = sendReportId.getStringValue();
					if(sendReportIdText!=null&&sendReportIdText.length()>0){
						deptIds += ","+sendReportIdText;
					}
				}
				deptIds += ",";
				if(typeList!=null&&typeList.size()>0){
					for(int j=0;j<typeList.size();j++){
						if("docSend".equals(typeList.get(j))||(deptIds.indexOf((","+typeList.get(j)+",").replace("docSend", ""))>-1&&"新发文流程".equals(modelid.getStringValue()))){
							docNew = DocumentHelper.createDocument(); 
							Element rootElemtnt = docNew.addElement("root"); 
							rootElemtnt.addAttribute("date",df.format(pd.getEndTime()) );
							rootElemtnt.addAttribute("type",typeList.get(j)+"");
							
							Node DatasNode=doc.selectSingleNode("Datas");
							rootElemtnt.add((Node)DatasNode.clone());
							if("docSend".equals(typeList.get(j))&&modelid.getStringValue().indexOf("新")==-1){
								sendMainId=docNew.selectSingleNode("//sendMainId");//主送
								sendInsideId=docNew.selectSingleNode("//sendInsideId");//抄送
								sendReportId=docNew.selectSingleNode("//sendReportId");//内发
								if(sendMainId!=null){
									sendMainId.setText(replaceOldDeptIdByNewDeptId(sendMainId.getStringValue()));
								}
								if(sendInsideId!=null){
									sendInsideId.setText(replaceOldDeptIdByNewDeptId(sendInsideId.getStringValue()));
								}
								if(sendReportId!=null){
									sendReportId.setText(replaceOldDeptIdByNewDeptId(sendReportId.getStringValue()));
								}
							}
							if(!"docSend".equals(typeList.get(j))){
								List<Element> AttachFiles=docNew.selectNodes("/root/Datas/AttachFileList/AttachFile");
								if(AttachFiles!=null&&AttachFiles.size()>0){
									for(int k=0;k<AttachFiles.size();k++){
										Element AttachFile = (Element) AttachFiles.get(k);
										Node attachIdNode = AttachFile.selectSingleNode("id");
										String attachId = attachIdNode.getStringValue();
										Node pathNode = AttachFile.selectSingleNode("path");
										pathNode.setText(stptPath+"/downloadFile.action?fileId="+attachId);
									}
								}
								
								List<Element> AttachFileMains = docNew.selectNodes("/root/Datas/BasicData/AttMain/AttachFileList/AttachFile");
								if(AttachFileMains!=null&&AttachFileMains.size()>0){
									for(int k=0;k<AttachFileMains.size();k++){
										Element AttachFile = (Element) AttachFileMains.get(k);
										Node attachIdNode = AttachFile.selectSingleNode("id");
										String attachId = attachIdNode.getStringValue();
										Element pathNode = (Element) AttachFile.selectSingleNode("path");
										//pathNode.setText(stptPath+"/downloadFile.action?fileId="+attachId);
										pathNode.clearContent();
										pathNode.addCDATA(workflowPath+"/pdfPreview/preview/download.action?fileId="+attachId+"&codeId="+codeIdMap.get((typeList.get(j)+"").replace("docSend","")));
									}
								}
							}
							
							
							StringWriter out = new StringWriter();
							OutputFormat format = OutputFormat.createPrettyPrint();
					        
					        format.setEncoding("UTF-8"); // 设置XML文档的编码类型
			                format.setIndent(true);      // 设置是否缩进
			                format.setIndent("   ");     // 以空格方式实现缩进
			                format.setNewlines(true);    // 设置是否换行
							XMLWriter xw = new XMLWriter (out, format);
							try {
								//xw.setEscapeText(false);
								xw.write(docNew);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String xmlstring = out.toString();
							//System.out.print(xmlstring);
							Map<String,String> map = new HashMap<String,String>();
							map.put("date", df.format(pd.getEndTime()));
							map.put("content", xmlstring);
							list.add(map);
						}
					}
				}
			}
			processDoneService.saveOrUpdateAll(plist);
			WebServiceFunc ws = new WebServiceFunc();
			List<String> resultList = ws.setDataInfo(list);
			System.out.println(resultList);
		}
	}
	
	private String replaceOldDeptIdByNewDeptId(String str){
		return str.replace("2102", "2502")
				.replace("2117", "2503")
				.replace("2119", "2504")
				.replace("2103", "2505")
				.replace("2104", "2506")
				.replace("2106", "2507")
				.replace("2107", "2508")
				.replace("11473", "2509")
				.replace("2111", "2510")
				.replace("2112", "2512")
				.replace("2113", "2513")
				.replace("2109", "2514")
				.replace("10590", "2515")
				.replace("2114", "2516")
				.replace("11290", "2517")
				.replace("11470", "2518")
				.replace("10190", "2519")
				.replace("2116", "2549")
				.replace("11030", "2550")
				.replace("11476", "2551")
				.replace("10114", "2920")
				.replace("10115", "2921")
				.replace("10116", "2922")
				.replace("10117", "2923")
				.replace("10118", "2924")
				.replace("2132", "2925")
				.replace("10096", "2941")
				.replace("2133", "2942")
				.replace("11190", "2943")
				.replace("2120", "2944")
				.replace("2135", "2945")
				.replace("2134", "2946")
				.replace("2125", "2947")
				.replace("2126", "2948")
				.replace("2129", "2949")
				.replace("10094", "2950")
				.replace("10095", "2951")
				.replace("2122", "2952")
				.replace("2128", "2953")
				.replace("2130", "2954")
				.replace("10093", "2955")
				.replace("2127", "2956")
				.replace("10090", "2957")
				.replace("2124", "2958")
				.replace("2137", "2959")
				.replace("2123", "2960")
				.replace("10711", "2961")
				.replace("12610", "2962")
				.replace("2121", "3020");
	}
	
	/**
     * 项目立项流程
     * @return
     * @throws JAXBException
     * @throws IOException
     */
	public String parseProject(String funcType,HashMap<String,String> hmParam){
		String returnStr="noRecExeute";
		String ptype = "project";
		String pname = "项目立项流程";
		
		String hql = "";
		if("updateLatestDatas".equals(funcType)){//更新最近的数据
			int iLatestDays = getLatestDays(hmParam.get("latestDays"));
			hql = "from PclProjectBasicInfo t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and t.extCode2 = s.id.processname and t.extCode1 = s.id.incident and (s.status = 1 or (s.status = 2 and s.endtime >(sysdate-"+iLatestDays+"))) order by s.id.incident";
		}else if("updateSingleData".equals(funcType)){//更新单条数据
			hql = "from PclProjectBasicInfo t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and s.id.incident = "+hmParam.get("pid")+" and t.extCode2 = s.id.processname and t.extCode1 = s.id.incident ";
		}else if("addDatas".equals(funcType)){//批量插入数据

			int iStartNum = getStartNum(hmParam.get("startNum"));
			int iLength = getLength(hmParam.get("length"));
			if(iStartNum!=-1&&iLength!=-1){
				hql = "from PclProjectBasicInfo t,Incidents s where t.removed = '0' and s.id.processname = '"+pname+"' and t.extCode2 = s.id.processname and t.extCode1 = s.id.incident and s.id.incident>"+iStartNum+" and s.id.incident<="+(iStartNum+iLength)+" order by s.id.incident";
			}
		}
		
		try{
			JAXBContext context1 = JAXBContext.newInstance(ProjectData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "GBK"); 
			
			List<Object[]> list = processDoneService.findDatasByHQL(hql);
			List<ProcessDone> pdList = new ArrayList<ProcessDone>();
			//System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					PclProjectBasicInfo pclProjectBasicInfo = (PclProjectBasicInfo) list.get(i)[0];
					Incidents incidents = (Incidents)list.get(i)[1];
					List approvedList = processDoneService.findByHQL("from TApprovedinfo t where t.process = '"+incidents.getId().getProcessname()+"' and t.incidentno = "+incidents.getId().getIncident()+" order by t.upddate");
					List attachList = processDoneService.findByHQL("from AttachFile2 t where t.groupId = '"+pclProjectBasicInfo.getProjectAttachId()+"' and t.status = 'upload' and t.removed = 0 order by t.uploadDate");
					
					List<ApprovedinfoXml> voList = new ArrayList<ApprovedinfoXml>();
					if(approvedList!=null&&approvedList.size()>0){
						for(int j=0;j<approvedList.size();j++){
							TApprovedinfo bo = (TApprovedinfo) approvedList.get(j);
							ApprovedinfoXml vo = new ApprovedinfoXml();
							BeanUtils.copyProperties(bo, vo);
							vo.setUpddateStr(df.format(bo.getUpddate()));
							voList.add(vo);
						}
					}
					
					if(attachList!=null&&attachList.size()>0){
						for(int j=0;j<attachList.size();j++){
							AttachFile2 bo = (AttachFile2)attachList.get(j);
							bo.setFileSize(bo.getFileSize()/1024l);
						}
					}
					
					ProjectVo projectVo = new ProjectVo();
					projectVo.setProjectName(pclProjectBasicInfo.getPName());
					projectVo.setKeywords(pclProjectBasicInfo.getKeywords());
					projectVo.setProjectNum(pclProjectBasicInfo.getApprovalCode());
					projectVo.setProjectType("内部立项");
					projectVo.setInvestCost(pclProjectBasicInfo.getInvestCost());
					projectVo.setPlanStartDate(df.format(pclProjectBasicInfo.getPlanStartDate()));
					projectVo.setPlanEndDate(df.format(pclProjectBasicInfo.getPlanEndDate()));
					projectVo.setMoneySource(pclProjectBasicInfo.getMoneySource());
					projectVo.setMoneySourceDetail(pclProjectBasicInfo.getMoneySourceDetail());
					projectVo.setMajor(pclProjectBasicInfo.getMajor());
					projectVo.setProjectClass(pclProjectBasicInfo.getExtCode19());
					projectVo.setReportCompany(pclProjectBasicInfo.getInfoDept());
					String name = processDoneService.findNameByLoginName(pclProjectBasicInfo.getMainPerson());
					if(name==null||"null".equals(name)){
						name = pclProjectBasicInfo.getMainPerson();
					}
					projectVo.setReportChargePerson(name);
					projectVo.setReportPerson(pclProjectBasicInfo.getInfoMan());
					projectVo.setReportCompanyTel(pclProjectBasicInfo.getInfoManTel());
					projectVo.setExcuteCompany(pclProjectBasicInfo.getMainCompany());
					projectVo.setExcuteChargePerson(pclProjectBasicInfo.getMainCompanyPerson());
					projectVo.setExcuteChargePersonTel(pclProjectBasicInfo.getMainCompanyPersonTel());
					projectVo.setExcuteLinkPerson(pclProjectBasicInfo.getLinkMan());
					projectVo.setExcuteLinkPersonTel(pclProjectBasicInfo.getLinkManTel());
					projectVo.setEstablishAccording(pclProjectBasicInfo.getLegislationInfo());
					projectVo.setEstablishAccordingAttach(pclProjectBasicInfo.getLegislationInfoAttachId());
					projectVo.setEstablishAccordingAttachNum(pclProjectBasicInfo.getExtCode14());
					projectVo.setExcuteSolution(pclProjectBasicInfo.getExcuteSolution());
					projectVo.setExcuteSolutionAttach(pclProjectBasicInfo.getExcuteSolutionAttachId());
					projectVo.setExcuteSolutionAttachNum(pclProjectBasicInfo.getExtCode15());
					projectVo.setProjectBudget(pclProjectBasicInfo.getProjectBudget());
					projectVo.setProjectBudgetAttach(pclProjectBasicInfo.getProjectBudgetAttachId());
					projectVo.setProjectBudgetAttachNum(pclProjectBasicInfo.getExtCode16());
					projectVo.setProjectPlan(pclProjectBasicInfo.getProjectPlan());
					projectVo.setProjectPlanAttach(pclProjectBasicInfo.getProjectPlanAttachId());
					projectVo.setProjectPlanAttachNum(pclProjectBasicInfo.getExtCode17());
					projectVo.setProjectDesign(pclProjectBasicInfo.getProjectDevise());
					projectVo.setProjectDesignAttach(pclProjectBasicInfo.getProjectDeviseAttachId());
					projectVo.setProjectDesignAttachNum(pclProjectBasicInfo.getExtCode18());
					projectVo.setMonitor(pclProjectBasicInfo.getMonitor());
					projectVo.setIncident(pclProjectBasicInfo.getExtCode1());
					
					//既有资产
					String asset = pclProjectBasicInfo.getExtCode20();
					List<OldAsset> oldAssetList = new ArrayList<OldAsset>();
					Map<String,OldAsset> assetMap = new HashMap<String,OldAsset>();
					if(asset!=null&&asset.length()>0){
						asset = asset.replace("/更新", "").replace("/报废", "").replace("/停用", "").replace("/改造", "");
						String str = "";
						String id = "";
						String asset_name = "";
						String use_time = "";
						String use_life = "";
						String shelf_life = "";
						String useEndDate = "";
						String assetId = "";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						int year = 0;
						int month = 0;
						if(!"".equals(asset)){
							String asset_ids = "'"+asset.replace(",", "','")+"'";
							List<Object[]> assetList = processDoneService.findProjectAssets(asset_ids);
							if(assetList!=null&&assetList.size()>0){
								for(int j=0;j<assetList.size();j++){
									id = String.valueOf(assetList.get(j)[0]);
									asset_name = String.valueOf(assetList.get(j)[1]);
									use_time = String.valueOf(assetList.get(j)[2]);
									use_life = String.valueOf(assetList.get(j)[3]);
									shelf_life = String.valueOf(assetList.get(j)[4]);
									assetId = String.valueOf(assetList.get(j)[5]);
									if(use_time.indexOf("-")>-1){
										try {
											Date date = simpleDateFormat.parse(use_time);
											year = date.getYear();
											month = date.getMonth();
											if(!"".equals(use_life)&&!"0".equals(use_life)){
												int use_life_num = Integer.parseInt(use_life);
												month += use_life_num;
												year += month/12;
												month = month%12;
												date.setYear(year);
												date.setMonth(month);
												useEndDate = simpleDateFormat.format(date);
											}
											
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									OldAsset oldAsset = new OldAsset();
									oldAsset.setId(id);
									oldAsset.setAssetName(asset_name);
									oldAsset.setUseEndDate(useEndDate);
									oldAsset.setRepairEndDate(shelf_life);
									assetMap.put(assetId, oldAsset);
								}
							}
						}
						String[] asset_split = pclProjectBasicInfo.getExtCode20().split(",");
						String asset_split_id = "";
						for(int j=0;j<asset_split.length;j++){
							asset_split_id = asset_split[j].replace("/更新", "").replace("/报废", "").replace("/停用", "").replace("/改造", "");
							OldAsset oldAsset = new OldAsset();
							if(assetMap.get(asset_split_id)!=null){
								oldAsset = assetMap.get(asset_split_id);
							}
							oldAsset.setAssetInfo(asset_split[j]);
							oldAssetList.add(oldAsset);
						}
					}
					
					
					//新增资产
					String assetAdd = pclProjectBasicInfo.getAssetAdd();
					List<NewAsset> newAssetList = new ArrayList<NewAsset>();
					if(assetAdd!=null&&assetAdd.length()>0){
						String[] assetAdd_split = assetAdd.split("\\|");
						for(int j=0;j<assetAdd_split.length;j++){
							String[] assetAdd_split_split = assetAdd_split[j].split(",");
							if(assetAdd_split_split.length==3){
								NewAsset newAsset = new NewAsset();
								newAsset.setAssetId(assetAdd_split_split[0]);
								newAsset.setAssetName(assetAdd_split_split[1]);
								newAsset.setAssetNum(assetAdd_split_split[2]);
								newAssetList.add(newAsset);
							}
						}
					}
					
					OldAssetList oldAssetListObj = new OldAssetList(oldAssetList);
					NewAssetList newAssetListObj = new NewAssetList(newAssetList);
					Assets assets = new Assets(oldAssetListObj,newAssetListObj);
					TApprovedinfoList tApprovedinfoList = new TApprovedinfoList(voList);
					AttachFileList attachFileList = new AttachFileList(attachList);
					ProjectData data = new ProjectData(projectVo,attachFileList,tApprovedinfoList,assets);
					
					FileWriter fw = new FileWriter("D:\\test\\"+pclProjectBasicInfo.getPId()+".xml");
					m.marshal(data, fw);
					
					
//					StringWriter sw = new StringWriter();
//					m.marshal(data, sw);
//					//System.out.println(sw.toString());
//					
//					ProcessDone pd = new ProcessDone();
//					List<Object> downList = processDoneService.findByHQL("from ProcessDone t where t.pname = '"+incidents.getId().getProcessname()+"' and t.pid = '"+incidents.getId().getIncident()+"'");
//					if(downList!=null&&downList.size()>0){
//						pd = (ProcessDone) downList.get(0);
//					}
//					pd.setPid(String.valueOf(incidents.getId().getIncident()));
//					pd.setPname(incidents.getId().getProcessname());
//					pd.setPtype(ptype);
//					pd.setSummary(incidents.getSummary());
//					pd.setStartTime(incidents.getStarttime());
//					pd.setEndTime(incidents.getEndtime());
//					pd.setApplyUser(jobContact.getContent_operator());
//					pd.setApplyDept(jobContact.getContent_inscribe());
//					pd.setStatus(incidents.getStatus().longValue());
//					pd.setData(sw.toString());
//					
//					List<Object[]> userList = processDoneService.findDoneUsers(pname,pd.getPid());
//					if(userList!=null&&userList.size()>0){
//						String doneUsers = "";
//						for(int j=0;j<userList.size();j++){
//							if(j>0){
//								doneUsers += ",";
//							}
//							doneUsers += String.valueOf(userList.get(j));
//						}
//						pd.setDoneUsers(doneUsers);
//						//System.out.println(i+"&&"+doneUsers);
//					}
//					String taskid = processDoneService.findTaskId(pname, pd.getPid());
//					if(taskid!=null){
//						pd.setTaskid(taskid);
//					}
//					
//					pdList.add(pd);
					
					//System.out.println(i+"--finish+id===="+jobContact.getId());
				}
				if(pdList!=null&&pdList.size()>0){
					processDoneService.saveOrUpdateAll(pdList);
					if("updateSingleData".equals(funcType)){
						returnStr=pdList.get(0).getId();
					}else{
						returnStr="success";
					}
					System.out.println("over");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnStr="exception";
		}
		return returnStr;
	}
	
	@Action(value="setHtxxDataByWebService")
	public void setHtxxDataByWebService(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		
		String toDate = df.format(c.getTime());
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String hql = "from ProcessDone t where t.ptype = 'htxx' and t.endTime >(sysdate-1) and t.status = 2";
//		String hql = "from ProcessDone t where t.ptype = 'htxx' and " +
//				" (t.data like '%轨10J-2014-001-十号线%' or t.data like '%轨10q-2014-001-十号线%' or t.data like '%轨10Z-2014-003十号线%'" +
//				" or t.data like '%轨10Z-2014-001十号线%' or t.data like '%轨10S-2014-001-十号线%' or t.data like '%轨10Z-2014-002十号线%') " +
//				" and t.status = 2";
		List<Object> downList = processDoneService.findByHQL(hql);
		if(downList!=null&&downList.size()>0){
			for(int i=0;i<downList.size();i++){
				String data = "";
				Document doc=null;
				Document docNew=null;
				ProcessDone pd = (ProcessDone)downList.get(i);
				data = pd.getData();
				try {
					doc = DocumentHelper.parseText(data);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Element Datas = doc.getRootElement();
				Element BasicData = Datas.element("BasicData");
				Element Htxx = BasicData.element("Htxx");
				Element ContractNum = Htxx.element("ContractNum");
				String ContractNumText = "";
				if(ContractNum!=null){
					ContractNumText = ContractNum.getTextTrim();
				}
				if(ContractNumText.length()>0){
					List<Object> keyList = processDoneService.findByHQLFromStptdemo("from DwDataExchangeKey t where t.keyname = 'ContractNum' and t.type = 'greataContract' and t.removed = 0 and t.keyvalue = '"+ContractNumText+"'");
					if(keyList!=null&&keyList.size()>0){
						Element contractStatus = Htxx.addElement("contractStatus");
						contractStatus.setText("2");
						Node DatasNode=doc.selectSingleNode("Datas");
						
						docNew = DocumentHelper.createDocument(); 
						Element rootElemtnt = docNew.addElement("root"); 
						rootElemtnt.addAttribute("date",toDate );
						rootElemtnt.addAttribute("type","htxx");
						rootElemtnt.add(DatasNode);
						StringWriter out = new StringWriter();
						OutputFormat format = OutputFormat.createPrettyPrint();
				        
				        format.setEncoding("UTF-8"); // 设置XML文档的编码类型
		                format.setIndent(true);      // 设置是否缩进
		                format.setIndent("   ");     // 以空格方式实现缩进
		                format.setNewlines(true);    // 设置是否换行
						XMLWriter xw = new XMLWriter (out, format);
						try {
							xw.write(docNew);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String xmlstring = out.toString();
						//System.out.print(xmlstring);
						Map<String,String> map = new HashMap<String,String>();
						map.put("date", toDate);
						map.put("content", xmlstring);
						list.add(map);
						
						DwDataExchangeKey key = (DwDataExchangeKey)keyList.get(0);
						key.setRemoved(1);
						key.setOperateTime(new Date());
						processDoneService.update(key);
					}
				}
			}
			WebServiceFunc ws = new WebServiceFunc();
			List<String> resultList = ws.setDataInfo(list);
			//System.out.println(resultList);
		}
	}
	
	@Action(value="setContractReviewResultDataByWebService")
	public void setContractReviewResultDataByWebService(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		
		String toDate = df.format(c.getTime());
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String hql = "from ProcessDone t where t.ptype = 'contractReviewResult' and t.endTime >(sysdate-1) and t.status = 2";
		//hql = "from ProcessDone t where t.ptype = 'contractReviewResult'  and t.status = 2";
		List<Object> downList = processDoneService.findByHQL(hql);
		if(downList!=null&&downList.size()>0){
			for(int i=0;i<downList.size();i++){
				String data = "";
				Document doc=null;
				ProcessDone pd = (ProcessDone)downList.get(i);
				data = pd.getData();
				try {
					doc = DocumentHelper.parseText(data);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Element root = doc.getRootElement();
				root.addAttribute("date", toDate);
				
				Element types = (Element) root.selectSingleNode("//contractType1");
				String typesValue = types.getStringValue();
				if("运维类".equals(typesValue)){
					root.addAttribute("type", "eamContractReviewResult");
				}else if("建设类".equals(typesValue)){
					root.addAttribute("type", "greataContractReviewResult");
				}else{
					root.addAttribute("type", "contractReviewResult");
				}
				StringWriter out = new StringWriter();
				OutputFormat format = OutputFormat.createPrettyPrint();
		        
		        format.setEncoding("UTF-8"); // 设置XML文档的编码类型
                format.setIndent(true);      // 设置是否缩进
                format.setIndent("   ");     // 以空格方式实现缩进
                format.setNewlines(true);    // 设置是否换行
				XMLWriter xw = new XMLWriter (out, format);
				try {
					xw.write(doc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String xmlstring = out.toString();
				//System.out.print(xmlstring);
				Map<String,String> map = new HashMap<String,String>();
				map.put("date", toDate);
				map.put("content", xmlstring);
				list.add(map);
			}
		}
			WebServiceFunc ws = new WebServiceFunc();
			List<String> resultList = ws.setDataInfo(list);
			//System.out.println(resultList);
	}
	
	@Action(value="/setQqbzDataByWebService")
	public void setQqbzDataByWebService(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//		list.add(getBuildTotalData());
//		list.add(getBuildAcceptClassData());
//		list.add(getBuildDcqTotalData());
//		list.add(getBuildMonomerData());
//		list.add(getBuildNMonomerData());
//		list.add(getBuildProjectData());
//		list.add(getBuildQqbzTotalData());
		
		list.add(getBeforeHistoryData());
		
		WebServiceFunc ws = new WebServiceFunc();
		List<String> resultList = ws.setDataInfo(list,"greata","greata2013!");
		System.out.println(resultList);
	}
	
	@Action(value="/setProjectDiscardResultByWebService")
	public void setProjectDiscardResultByWebService(){
		WebServiceFunc ws = new WebServiceFunc();
		List<String> resultList = ws.setDataInfo(this.getProjectDiscardData(),"eam","eam2013!");
		//System.out.println(resultList);
	}
	
	private Map<String,String> getBuildTotalData(){
		String hql = "from BuildTotal t ";
		String toDate = df.format(new Date());
		String type = "wonderLh";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildTotalList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			//List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select * from t_build_total", BuildTotal.class);
			List<BuildTotal> dataList = new ArrayList<BuildTotal>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildTotal bo = (BuildTotal)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
				}
				BuildTotalList buildTotalList = new BuildTotalList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String,String> getBuildAcceptClassData(){
		String hql = "from BuildAcceptClass t ";
		String hql1 = "from BuildAcceptLatestItem t ";
		String hql2 = "from BuildAcceptLatestMain t ";
		String toDate = df.format(new Date());
		String type = "wonderXmys";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildAcceptClassList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
//			List<Object> list = processDoneService.findByHQL(hql);
//			List<Object> list1 = processDoneService.findByHQL(hql1);
//			List<Object> list2 = processDoneService.findByHQL(hql2);
			
			List<Object> list = processDoneService.queryForList("select * from T_BUILD_ACCEPT_CLASS", BuildAcceptClass.class);
			List<Object> list1 = processDoneService.queryForList("select * from T_BUILD_ACCEPT_LATEST_ITEM", BuildAcceptLatestItem.class);
			List<Object> list2 = processDoneService.queryForList("select * from T_BUILD_ACCEPT_LATEST_MAIN", BuildAcceptLatestMain.class);
			
			List<BuildAcceptClass> dataList = new ArrayList<BuildAcceptClass>();
			List<BuildAcceptLatestItem> dataList1 = new ArrayList<BuildAcceptLatestItem>();
			List<BuildAcceptLatestMain> dataList2 = new ArrayList<BuildAcceptLatestMain>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildAcceptClass bo = (BuildAcceptClass)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				for(int i=0;i<list1.size();i++){
					BuildAcceptLatestItem bo = (BuildAcceptLatestItem)list1.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList1.add(bo);
					
				}
				for(int i=0;i<list2.size();i++){
					BuildAcceptLatestMain bo = (BuildAcceptLatestMain)list2.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList2.add(bo);
					
				}
				BuildAcceptClassList buildTotalList = new BuildAcceptClassList(dataList,dataList1,dataList2);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String,String> getBuildDcqTotalData(){
		String hql = "from BuildDcqTotal t ";
		String toDate = df.format(new Date());
		String type = "wonderDcq";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildDcqTotalList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
//			List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select * from t_build_dcq_total", BuildDcqTotal.class);
			List<BuildDcqTotal> dataList = new ArrayList<BuildDcqTotal>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildDcqTotal bo = (BuildDcqTotal)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				BuildDcqTotalList buildTotalList = new BuildDcqTotalList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String,String> getBuildMonomerData(){
		String hql = "from BuildMonomer t ";
		String toDate = df.format(new Date());
		String type = "wonderDt";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildMonomerList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
//			List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select * from t_build_monomer", BuildMonomer.class);
			
			List<BuildMonomer> dataList = new ArrayList<BuildMonomer>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildMonomer bo = (BuildMonomer)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				BuildMonomerList buildTotalList = new BuildMonomerList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String,String> getBuildNMonomerData(){
		String hql = "from BuildNMonomer t ";
		String toDate = df.format(new Date());
		String type = "wonderDtn";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildNMonomerList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
//			List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select * from t_build_n_monomer", BuildNMonomer.class);
			
			List<BuildNMonomer> dataList = new ArrayList<BuildNMonomer>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildNMonomer bo = (BuildNMonomer)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				BuildNMonomerList buildTotalList = new BuildNMonomerList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String,String> getBuildProjectData(){
		String hql = "from BuildProject t ";
		String toDate = df.format(new Date());
		String type = "wonderXm";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildProjectList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
//			List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select * from t_build_project", BuildProject.class);
			
			List<BuildProject> dataList = new ArrayList<BuildProject>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildProject bo = (BuildProject)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				BuildProjectList buildTotalList = new BuildProjectList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String,String> getBuildQqbzTotalData(){
		String hql = "from BuildQqbzTotal t ";
		String toDate = df.format(new Date());
		String type = "wonderQqbz";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BuildQqbzTotalList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			//List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select * from t_build_qqbz_total", BuildQqbzTotal.class);
			List<BuildQqbzTotal> dataList = new ArrayList<BuildQqbzTotal>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BuildQqbzTotal bo = (BuildQqbzTotal)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				BuildQqbzTotalList buildTotalList = new BuildQqbzTotalList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(buildTotalList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String,String> getBeforeHistoryData(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		String toDate = df.format(date);//昨天
		
		String type = "wonderQqbzx";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(BeforeHistoryList.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
			List<Object> list = processDoneService.queryForList("select * from t_before_history t where t.create_time like '%"+toDate+"%' or t.update_time like '%"+toDate+"%'", BeforeHistory.class);
			List<BeforeHistory> dataList = new ArrayList<BeforeHistory>();
			System.out.println(list.size());
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					BeforeHistory bo = (BeforeHistory)list.get(i);
					Field[] fields = bo.getClass().getDeclaredFields();
			          //写数据
			          for(Field ff : fields) {
			        	  ff.setAccessible(true);
			           PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), bo.getClass());
			           Method rM = pd.getReadMethod();//获得读方法
			           Object num = (Object) rM.invoke(bo);
			           String classType = ff.getGenericType().toString();    //获取属性的类型
			           
			           if(num == null) {
				           Method wM = pd.getWriteMethod();//获得写方法
				           if(classType.equals("class java.lang.String")){
				        	   wM.invoke(bo, "");
				           }else if(classType.equals("class java.lang.BigDecimal")){
				        	   wM.invoke(bo, new BigDecimal(0));
				           }else if(classType.equals("class java.lang.Boolean")){
				        	   wM.invoke(bo, true);
				           }
			           }
			          }

					
					
					dataList.add(bo);
					
				}
				BeforeHistoryList beforeHistoryList = new BeforeHistoryList(dataList);
				StringWriter sw = new StringWriter();
				m.marshal(beforeHistoryList, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private List<Map<String,String>> getProjectDiscardData(){
		List<Map<String,String>> datalist = new ArrayList<Map<String,String>>();

		String toDate = df.format(new Date());
		String type = "eamProjectDiscardResult";
		try{
			JAXBContext context1 = JAXBContext.newInstance(DiscardInfoXml.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			
//			List<Object> list = processDoneService.findByHQL(hql);
			List<Object> list = processDoneService.queryForList("select t.ID id,t.INDEXNUM indexNum,t.OPERATE_TIME approveDate from PCL_PROJECT_DISCARD t where TO_DATE(t.OPERATE_TIME, 'yyyy-MM-dd hh24:mi:ss') >(sysdate-1) and t.FLAG = '1' and t.EXTERNAL_LAUNCH = '1'", DiscardVo.class);
			
			List<BuildMonomer> dataList = new ArrayList<BuildMonomer>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,String> map = new HashMap<String,String>();
					DiscardInfoXml xml = new DiscardInfoXml();
					xml.mainBo = (DiscardVo)list.get(i);
					
					StringWriter sw = new StringWriter();
					m.marshal(xml, sw);
					
					map.put("date", toDate);
					map.put("content", sw.toString().replace("<root>", "<root type=\""+type+"\" date=\""+toDate+"\">"));
					
					datalist.add(map);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return datalist;
	}
	
	@Action(value="setHtxxBySelfNum")
	public String setHtxxBySelfNum(){
		String selfNum = this.request.getParameter("selfNum");
		String type = this.request.getParameter("type");
		String toDate = df.format(new Date());
		String returnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>0000</code><description>导入失败</description><params/></result>";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(HtxxDataWithStep.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			List<Object> list = processDoneService.findByHQL("from HtXx t where t.SelfNum = '"+selfNum+"' ");
			if(list!=null&&list.size()>0){
				HtXx htxx = (HtXx)list.get(0);
				HtBa htba = new HtBa();
				String processCurrentStep = "";
				long htId = htxx.getId();
				String modelId = htxx.getModelId();
				String pinstanceId = htxx.getPinstanceId();
				List<Object> list1 = processDoneService.findByHQL("from HtBa t where t.htId = "+htId);
				if(list1!=null&&list1.size()>0){
					htba = (HtBa)list1.get(0);
				}
				List<Object> list2 = processDoneService.findByHQL("select t.stepLabel from Tasks t where t.processName = '"+modelId+"' and t.incident = "+pinstanceId+" and t.status = 1 ");
				if(list2!=null&&list2.size()>0){
					processCurrentStep = (String)list2.get(0);
				}else{
					processCurrentStep = "已备案";
				}
				HtBasicDataWithStep htBasicData = new HtBasicDataWithStep(processCurrentStep,htxx,htba);
				HtxxDataWithStep data = new HtxxDataWithStep(htBasicData);
				StringWriter sw = new StringWriter();
				m.marshal(data, sw);
				//System.out.println(sw.toString());
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<Datas>", "<root type=\""+type+"\" date=\""+toDate+"\"><Datas>").replace("</Datas>", "</Datas></root>"));
				
				List<Map<String,String>> dwList = new ArrayList<Map<String,String>>();
				dwList.add(map);
				
				WebServiceFunc ws = new WebServiceFunc();
				List<String> resultList = ws.setDataInfo(dwList,"eam","eam2013!");
				//System.out.println(resultList);
				if(resultList.size()==1){
					returnXml = resultList.get(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//returnStr="exception";
		}
		actionWriteXML(returnXml);
		return null;
	}
	
	@Action(value="setProjectByIndexNum")
	public String setProjectByIndexNum(){
		String indexNum = this.request.getParameter("indexNum");
		String type = this.request.getParameter("type");
		String toDate = df.format(new Date());
		String returnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>0000</code><description>导入失败</description><params/></result>";
		Map<String,String> map = new HashMap<String,String>();
		try{
			JAXBContext context1 = JAXBContext.newInstance(ProjectInfoData.class);
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			List<Object> list = processDoneService.findByHQL("from PclProjectBasicInfo t where t.extCode8 = '"+indexNum+"' ");
			if(list!=null&&list.size()>0){
				PclProjectBasicInfo pclProjectBasicInfo = (PclProjectBasicInfo)list.get(0);
				ProjectVo projectVo = new ProjectVo();
				projectVo.setProjectName(pclProjectBasicInfo.getPName());
				projectVo.setKeywords(pclProjectBasicInfo.getKeywords());
				projectVo.setProjectNum(pclProjectBasicInfo.getApprovalCode());
				projectVo.setProjectType("内部立项");
				projectVo.setInvestCost(pclProjectBasicInfo.getInvestCost());
				projectVo.setPlanStartDate(df.format(pclProjectBasicInfo.getPlanStartDate()));
				projectVo.setPlanEndDate(df.format(pclProjectBasicInfo.getPlanEndDate()));
				projectVo.setMoneySource(pclProjectBasicInfo.getMoneySource());
				projectVo.setMoneySourceDetail(pclProjectBasicInfo.getMoneySourceDetail());
				projectVo.setMajor(pclProjectBasicInfo.getMajor());
				projectVo.setProjectClass(pclProjectBasicInfo.getExtCode19());
				projectVo.setReportCompany(pclProjectBasicInfo.getInfoDept());
				projectVo.setReportChargePerson(pclProjectBasicInfo.getMainPerson());
				projectVo.setReportPerson(pclProjectBasicInfo.getInfoMan());
				projectVo.setReportCompanyTel(pclProjectBasicInfo.getInfoManTel());
				projectVo.setExcuteCompany(pclProjectBasicInfo.getMainCompany());
				projectVo.setExcuteChargePerson(pclProjectBasicInfo.getMainCompanyPerson());
				projectVo.setExcuteChargePersonTel(pclProjectBasicInfo.getMainCompanyPersonTel());
				projectVo.setExcuteLinkPerson(pclProjectBasicInfo.getLinkMan());
				projectVo.setExcuteLinkPersonTel(pclProjectBasicInfo.getLinkManTel());
				projectVo.setEstablishAccording(pclProjectBasicInfo.getLegislationInfo());
				projectVo.setEstablishAccordingAttach(pclProjectBasicInfo.getLegislationInfoAttachId());
				projectVo.setEstablishAccordingAttachNum(pclProjectBasicInfo.getExtCode14());
				projectVo.setExcuteSolution(pclProjectBasicInfo.getExcuteSolution());
				projectVo.setExcuteSolutionAttach(pclProjectBasicInfo.getExcuteSolutionAttachId());
				projectVo.setExcuteSolutionAttachNum(pclProjectBasicInfo.getExtCode15());
				projectVo.setProjectBudget(pclProjectBasicInfo.getProjectBudget());
				projectVo.setProjectBudgetAttach(pclProjectBasicInfo.getProjectBudgetAttachId());
				projectVo.setProjectBudgetAttachNum(pclProjectBasicInfo.getExtCode16());
				projectVo.setProjectPlan(pclProjectBasicInfo.getProjectPlan());
				projectVo.setProjectPlanAttach(pclProjectBasicInfo.getProjectPlanAttachId());
				projectVo.setProjectPlanAttachNum(pclProjectBasicInfo.getExtCode17());
				projectVo.setProjectDesign(pclProjectBasicInfo.getProjectDevise());
				projectVo.setProjectDesignAttach(pclProjectBasicInfo.getProjectDeviseAttachId());
				projectVo.setProjectDesignAttachNum(pclProjectBasicInfo.getExtCode18());
				projectVo.setMonitor(pclProjectBasicInfo.getMonitor());
				projectVo.setIncident(pclProjectBasicInfo.getExtCode1());
				projectVo.setDispatchNo(pclProjectBasicInfo.getDispatchNo());
				projectVo.setDispatchDate(pclProjectBasicInfo.getExtCode9());
				projectVo.setIndexNum(pclProjectBasicInfo.getExtCode8());
				String incident = pclProjectBasicInfo.getExtCode1();
				String processname = pclProjectBasicInfo.getExtCode2();
				String status = processDoneService.findProjectStatus(incident, processname);
				projectVo.setProjectStatus(status);
				String processCurrentStep = "已备案";
				List<Object> list2 = processDoneService.findByHQL("select t.stepLabel from Tasks t where t.processName = '"+processname+"' and t.incident = "+incident+" and t.status = 1 ");
				if(list2!=null&&list2.size()>0){
					processCurrentStep = (String)list2.get(0);
				}
				projectVo.setProcessCurrentStep(processCurrentStep);
				ProjectInfoData projectInfoData = new ProjectInfoData(projectVo);
				
				StringWriter sw = new StringWriter();
				m.marshal(projectInfoData, sw);
				
				map.put("date", toDate);
				map.put("content", sw.toString().replace("<Datas>", "<root type=\""+type+"\" date=\""+toDate+"\"><Datas>").replace("</Datas>", "</Datas></root>"));
				
				List<Map<String,String>> dwList = new ArrayList<Map<String,String>>();
				dwList.add(map);
				
				WebServiceFunc ws = new WebServiceFunc();
				List<String> resultList = ws.setDataInfo(dwList,"eam","eam2013!");
				if(resultList.size()==1){
					returnXml = resultList.get(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		actionWriteXML(returnXml);
		return null;
	}
}
