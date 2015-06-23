/** 
* @Title: DataExchangeAction.java 
* @Package com.wonders.stpt.dataExchange.action 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-8-7 下午03:14:45 
* @version V1.0 
*/
package com.wonders.stpt.dataExchange.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.dataExchange.contractWebServiceClient.ContractConnectorDelegate;
import com.wonders.stpt.dataExchange.contractWebServiceClient.ContractConnectorService;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeKey;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeStore;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeUser;
import com.wonders.stpt.dataExchange.model.vo.DwDataExchangeStoreContractVo;
import com.wonders.stpt.dataExchange.model.vo.DwDataExchangeStoreEamProjectVo;
import com.wonders.stpt.dataExchange.model.vo.DwDataExchangeStoreGreataSendVo;
import com.wonders.stpt.dataExchange.service.DataExchangeService;
import com.wonders.stpt.processDone.model.vo.Page;



/** 
 * @ClassName: DataExchangeAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lushuaifeng
 * @date 2013-8-7 下午03:14:45 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/dataExchange")
@Controller("dataExchangeAction")
@Scope("prototype")
public class DataExchangeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private DataExchangeService dataExchangeService;
	
	@Action(value="findByPage",results={@Result(name="success",location="/dataExchange/list.jsp")})
	public String findByPage(){
		HashMap<String,String> filter=new HashMap<String,String>();		
		Map mParams=request.getParameterMap();		
		Set<String> key = mParams.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String paramName = (String) it.next();
            String paramValue=request.getParameter(paramName);
            if(!paramValue.equals("")){
		        request.setAttribute(paramName, paramValue);
            	filter.put(paramName, paramValue);
            }
        }
		
        if(filter.get("page")==null||filter.get("page").equals("")){
        	 filter.put("page", "1");
        }
       
		Page msgPage=dataExchangeService.findByPage(filter, "desc");

		request.setAttribute("page", msgPage);
        
        List<DwDataExchangeUser> users=dataExchangeService.findSetUsers();
        HashMap<String,String> dataTypes=new HashMap<String,String>();
        for(int i=0;i<users.size();i++){
        	DwDataExchangeUser user=users.get(i);
        	dataTypes.put(user.getDataType(), user.getCompany()+"."+user.getAppName());
        }
        
        request.setAttribute("dataTypes", dataTypes);
		return "success";
		
	}
		
	@Action(value="viewXML")
	public String viewXMLById(){
		String id =  request.getParameter("id");
		DwDataExchangeStore data = dataExchangeService.loadById(id);
		String xmlStr=data.getContent();
		String type=data.getDataType();
		Long valid=data.getValid();		
		xmlStr=xmlStr.replace("<root","<?xml-stylesheet type=\"text/xsl\" href=\"/portal/dataExchange/xsl/"+type+".xsl\"?>\n<root id=\""+id+"\" valid=\""+String.valueOf(valid)+"\"");
		
		Document doc=null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element deptIdEle=(Element)doc.selectSingleNode("root/Datas/BasicData/reportCompany");
		Element nameEle=(Element)doc.selectSingleNode("root/Datas/BasicData/reportPerson");
		if(deptIdEle!=null&&nameEle!=null){
			Attribute attr = deptIdEle.attribute("id");
			if(attr!=null){
				String deptId = attr.getText();
                // 10114运管中心，10115运一，10116运二，10117运三 ，10118运四，
                if("10114".equals(deptId)){
                    Attribute attr1 = nameEle.attribute("id");
                    if(attr1!=null){
                        attr1.setValue("G01012000005");
                    }
                    nameEle.setText("李琳");
                }
                if("10115".equals(deptId)){
                    Attribute attr1 = nameEle.attribute("id");
                    if(attr1!=null){
                        attr1.setValue("G01001000039");
                    }
                    nameEle.setText("沈惠鑫");
                }
                if("10116".equals(deptId)){
                    Attribute attr1 = nameEle.attribute("id");
                    if(attr1!=null){
                        attr1.setValue("G01002002246");
                    }
                    nameEle.setText("徐亦炜");
                }
                if("10117".equals(deptId)){
                    Attribute attr1 = nameEle.attribute("id");
                    if(attr1!=null){
                        attr1.setValue("G01003000773");
                    }
                    nameEle.setText("阮方");
                }
                if("10118".equals(deptId)){
                    Attribute attr1 = nameEle.attribute("id");
                    if(attr1!=null){
                        attr1.setValue("G04000000137");
                    }
                    nameEle.setText("沈红漫");
                }




				if("10218".equals(deptId)){
					Attribute attr1 = nameEle.attribute("id");
					if(attr1!=null){
						attr1.setValue("G01009000200");
					}
					nameEle.setText("裘珏莹");
				}
				if("10215".equals(deptId)){
					Attribute attr1 = nameEle.attribute("id");
					if(attr1!=null){
						attr1.setValue("G01007000257");
					}
					nameEle.setText("魏宗浩");
				}
				if("10216".equals(deptId)){
					Attribute attr1 = nameEle.attribute("id");
					if(attr1!=null){
						attr1.setValue("G08006002287");
					}
					nameEle.setText("陆寅昇");
				}
				if("10219".equals(deptId)){
					Attribute attr1 = nameEle.attribute("id");
					if(attr1!=null){
						attr1.setValue("G08008000457");
					}
					nameEle.setText("金忠");
				}
			}
		}
		xmlStr = doc.asXML();
		actionWriteXML(xmlStr);
		return null;
	}	
	
	
	@Action(value="confirmValid")
	public String confirmValid() throws IOException{
		String id =  request.getParameter("id");
		String greataContractNum =  request.getParameter("greataContractNum");
		DwDataExchangeStore data = dataExchangeService.loadById(id);
		
		Timestamp createTime =new Timestamp(System.currentTimeMillis());
		data.setValid(0L);		
		data.setOperateTime(createTime);
		dataExchangeService.updateDwDataExchangeStore(data);
		
		if(greataContractNum!=null){
			DwDataExchangeKey key = new DwDataExchangeKey();
			key.setKeyname("ContractNum");
			key.setKeyvalue(greataContractNum);
			key.setType("greataContract");
			key.setCreateTime(new Date());
			key.setRemoved(0);
			dataExchangeService.save(key);
		}
		
		response.sendRedirect("viewXML.action?id="+id);
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
		
	@Action(value="findGreataSendByPage",results={@Result(name="success",location="/dataExchange/greataSendList.jsp")})
	public String findGreataSendByPage(){
		HashMap<String,String> filter=new HashMap<String,String>();		
		Map mParams=request.getParameterMap();		
		Set<String> key = mParams.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String paramName = (String) it.next();
            String paramValue=request.getParameter(paramName);
            if(!paramValue.equals("")){
		        request.setAttribute(paramName, paramValue);
		        if(!paramName.equals("oldPortalDeptId")){
		        	filter.put(paramName, paramValue);
		        }
            }
        }
        
        filter.put("dataType", "greataSend");
        filter.put("valid", "1");
        String oldUserId = (String) this.session.getAttribute("oldUserId");        
        String oldDeptId = (String) this.session.getAttribute("oldDeptId");
        if(request.getParameter("oldPortalDeptId")!=null&&!request.getParameter("oldPortalDeptId").equals("")){
        	oldDeptId=request.getParameter("oldPortalDeptId");
        }
        //String oldUserId = "15211";
		//String oldDeptId = "10094";//十一号线南,测试人：G06000000081 黄卫凤
		String loginName = (String) this.session.getAttribute("t_login_name");
		
		//System.out.println("oldUserId+,oldDeptId,loginName:"+oldUserId+","+oldDeptId+","+loginName);
		boolean ifReceiver = dataExchangeService.ifReceiver("部门接受人工作分发","收文流程", loginName, oldDeptId,oldUserId);
		if(ifReceiver){
			filter.put("content", "drSwunit id=\""+oldDeptId+"\"");
		}else{
			filter.put("content", "drSwunit id=00000");
		}
		
        if(filter.get("page")==null||filter.get("page").equals("")){
        	 filter.put("page", "1");
        }
       
		Page msgPage=dataExchangeService.findByPage(filter, "desc");
		List<DwDataExchangeStore> list = msgPage.getResult();
		List<DwDataExchangeStoreGreataSendVo> list1 = new ArrayList<DwDataExchangeStoreGreataSendVo>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				DwDataExchangeStore bo = list.get(i);
				String content = bo.getContent();
				Document doc=null;
				try {
					doc = DocumentHelper.parseText(content);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Node drTitleNode=doc.selectSingleNode("//drTitle");
				Node drSwidNode=doc.selectSingleNode("//drSwid");
				Node drFilezhNode=doc.selectSingleNode("//drFilezh");
				
				DwDataExchangeStoreGreataSendVo vo = new DwDataExchangeStoreGreataSendVo();
				BeanUtils.copyProperties(bo, vo);
				if(drTitleNode!=null){
					vo.setTitle(drTitleNode.getStringValue());
				}
				if(drSwidNode!=null){
					vo.setSwid(drSwidNode.getStringValue());
				}
				if(drFilezhNode!=null){
					vo.setFilezh(drFilezhNode.getStringValue());
				}
				list1.add(vo);
			}
			msgPage.setResult(list1);
		}

		request.setAttribute("page", msgPage);
        
        List<DwDataExchangeUser> users=dataExchangeService.findSetUsers();
        HashMap<String,String> dataTypes=new HashMap<String,String>();
        for(int i=0;i<users.size();i++){
        	DwDataExchangeUser user=users.get(i);
        	dataTypes.put(user.getDataType(), user.getCompany()+"."+user.getAppName());
        }
        request.setAttribute("dataType", "greataSend");
        request.setAttribute("dataTypes", dataTypes);
		return "success";
		
	}
	
	@Action(value="findEamProjectByPage",results={@Result(name="success",location="/dataExchange/eamProjectList.jsp")})
	public String findEamProjectByPage(){
		HashMap<String,String> filter=new HashMap<String,String>();		
		Map mParams=request.getParameterMap();		
		Set<String> key = mParams.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String paramName = (String) it.next();
            String paramValue=request.getParameter(paramName);
            if(!paramValue.equals("")){
		        request.setAttribute(paramName, paramValue);
            	filter.put(paramName, paramValue);
            }
        }
        
        filter.put("dataType", "eamProject");
        filter.put("valid", "1");
        String loginName = (String) this.session.getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
        //String oldDeptId = (String) this.session.getAttribute("oldDeptId");


		HashMap<String,String> reportInfo = dataExchangeService.getEamProjectReportInfo(loginName);//修改   10114运管中心，10115运一，10116运二，10117运三 ，10118运四，
		if(reportInfo==null){
			filter.put("content", "<reportCompany id='00000'>");
		}else{
			filter.put("content", "<reportCompany id=\""+reportInfo.get("deptId")+"\"");
			//filter.put("content2", "<reportPerson id=\""+reportInfo.get("loginName")+"\"");
		}
		
        if(filter.get("page")==null||filter.get("page").equals("")){
        	 filter.put("page", "1");
        }
       
		Page msgPage=dataExchangeService.findByPage(filter, "desc");
		List<DwDataExchangeStore> list = msgPage.getResult();
		List<DwDataExchangeStoreEamProjectVo> list1 = new ArrayList<DwDataExchangeStoreEamProjectVo>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				DwDataExchangeStore bo = list.get(i);
				String content = bo.getContent();
				Document doc=null;
				try {
					doc = DocumentHelper.parseText(content);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Node drProjectNameNode=doc.selectSingleNode("//projectName");
				Node drProjectClassNode=doc.selectSingleNode("//projectClass");
				Node drReportCompanyNode=doc.selectSingleNode("//reportCompany");
				Node drReportPersonNode=doc.selectSingleNode("//reportPerson");
				
				DwDataExchangeStoreEamProjectVo vo = new DwDataExchangeStoreEamProjectVo();
				BeanUtils.copyProperties(bo, vo);
				if(drProjectNameNode!=null){
					vo.setProjectName(drProjectNameNode.getStringValue());
				}
				if(drProjectClassNode!=null){
					vo.setProjectClass(drProjectClassNode.getStringValue());
				}
				if(drReportCompanyNode!=null){
					vo.setReportCompany(drReportCompanyNode.getStringValue());
				}
				if(drReportPersonNode!=null){
					vo.setReportPerson(drReportPersonNode.getStringValue());
					
					Element deptIdEle=(Element)doc.selectSingleNode("root/Datas/BasicData/reportCompany");
					if(deptIdEle!=null){
						Attribute attr = deptIdEle.attribute("id");
						if(attr!=null){
							String deptId = attr.getText();

                            if("10114".equals(deptId)){
                                vo.setReportPerson("李琳");
                            }
                            if("10115".equals(deptId)){
                                vo.setReportPerson("沈惠鑫");
                            }
                            if("10116".equals(deptId)){
                                vo.setReportPerson("徐亦炜");
                            }
                            if("10117".equals(deptId)){
                                vo.setReportPerson("阮方");
                            }
                            if("10118".equals(deptId)){
                                vo.setReportPerson("沈红漫");
                            }


							if("10218".equals(deptId)){//后勤
								vo.setReportPerson("裘珏莹");
							}
							if("10215".equals(deptId)){//通号
								vo.setReportPerson("魏宗浩");
							}
							if("10216".equals(deptId)){//供电
								vo.setReportPerson("陆寅昇");
							}
							if("10219".equals(deptId)){//工务
								vo.setReportPerson("金忠");
							}
						}
					}
				}				
				list1.add(vo);
			}
			msgPage.setResult(list1);
		}

		request.setAttribute("page", msgPage);
        
        List<DwDataExchangeUser> users=dataExchangeService.findSetUsers();
        HashMap<String,String> dataTypes=new HashMap<String,String>();
        for(int i=0;i<users.size();i++){
        	DwDataExchangeUser user=users.get(i);
        	dataTypes.put(user.getDataType(), user.getCompany()+"."+user.getAppName());
        }
        request.setAttribute("dataType", "eamProject");
        request.setAttribute("dataTypes", dataTypes);
		return "success";
		
	}	
	
	@Action(value="findContractByPage",results={@Result(name="success",location="/dataExchange/contractList.jsp")})
	public String findContractByPage(){
		HashMap<String,String> filter=new HashMap<String,String>();
		Map mParams=request.getParameterMap();		
		Set<String> key = mParams.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String paramName = (String) it.next();
            String paramValue=request.getParameter(paramName);
            if(!paramValue.equals("")){
		        request.setAttribute(paramName, paramValue);
		        if(!paramName.equals("oldPortalDeptId")){
		        	filter.put(paramName, paramValue);
		        }
            }
        }
        filter.put("valid", "1");
        if(request.getParameter("dataType")!=null && request.getParameter("dataType").equalsIgnoreCase("greataContract")){
	        String oldUserId = (String) this.session.getAttribute("oldUserId");        
	        String oldDeptId = (String) this.session.getAttribute("oldDeptId");
	        if(request.getParameter("oldPortalDeptId")!=null&&!request.getParameter("oldPortalDeptId").equals("")){
	        	oldDeptId=request.getParameter("oldPortalDeptId");
	        }
	        //String oldUserId = "15211";
			//String oldDeptId = "10094";//十一号线南,测试人：G06000000081 黄卫凤
			String loginName = (String) this.session.getAttribute("t_login_name");
			
			System.out.println("oldUserId+,oldDeptId,loginName:"+oldUserId+","+oldDeptId+","+loginName);
			boolean ifReceiver = dataExchangeService.ifReceiver("部门业务经办人","合同审批流程", loginName, oldDeptId,oldUserId);
			if(ifReceiver){
				filter.put("content", "ProjectCOName id=\""+oldDeptId+"\"");
			}else{
				filter.put("content", "ProjectCOName id=00000");
			}
        }
        if(filter.get("page")==null||filter.get("page").equals("")){
        	 filter.put("page", "1");
        }

        //Eam合同申报页面数据
        if(request.getParameter("dataType")!=null && request.getParameter("dataType").equalsIgnoreCase("eamContractBasic")){
            String loginName = (String) this.session.getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
			if(loginName.equals("G08009000250")){
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("<ProjectCOName>").append("维保公司");
				filter.put("content", stringBuffer.toString());
			}else if(loginName.equals("G01012000003")){
				filter.put("content","<ProjectCOName>上海地铁第一运营有限公司");

			}else if(loginName.equals("G01002002246")){
				filter.put("content","<ProjectCOName>上海地铁第二运营有限公司");

			}else if(loginName.equals("G01003000773")){
				filter.put("content","<ProjectCOName>上海地铁第三运营有限公司");

			}else if(loginName.equals("G04000000137")){
				filter.put("content","<ProjectCOName>上海地铁第四运营有限公司");
			}else if(loginName.equals("G01012000005")){
				filter.put("content","<ProjectCOName>上海申通地铁集团运营管理中心");
			}
        }
       
		Page msgPage=dataExchangeService.findByPage(filter, "desc");
		List<DwDataExchangeStore> list = msgPage.getResult();
		List<DwDataExchangeStoreContractVo> list1 = new ArrayList<DwDataExchangeStoreContractVo>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				DwDataExchangeStore bo = list.get(i);
				String content = bo.getContent();
				Document doc=null;
				try {
					doc = DocumentHelper.parseText(content);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Node contractNumNode=doc.selectSingleNode("//ContractNum");
				Node contractNameNode=doc.selectSingleNode("//ContractName");
				
				DwDataExchangeStoreContractVo vo = new DwDataExchangeStoreContractVo();
				BeanUtils.copyProperties(bo, vo);
				if(contractNumNode!=null){
					vo.setContractNum(contractNumNode.getStringValue());
				}
				if(contractNameNode!=null){
					vo.setContractName(contractNameNode.getStringValue());
				}
				list1.add(vo);
			}
			msgPage.setResult(list1);
		}
		
		request.setAttribute("page", msgPage);
        
        List<DwDataExchangeUser> users=dataExchangeService.findSetUsers();
        HashMap<String,String> dataTypes=new HashMap<String,String>();
        for(int i=0;i<users.size();i++){
        	DwDataExchangeUser user=users.get(i);
        	dataTypes.put(user.getDataType(), user.getCompany()+"."+user.getAppName());
        }
        
        request.setAttribute("dataTypes", dataTypes);
		return "success";
		
	}
	
	@Action(value="backUpContract")
	public String backUpContract() throws IOException{
		String id =  request.getParameter("id");
		DwDataExchangeStore data = dataExchangeService.loadById(id);
		String content = data.getContent();
		//System.out.println("content=="+content);
		ContractConnectorService ws = new ContractConnectorService();
		ContractConnectorDelegate delegate = ws.getContractConnectorPort();
		
		String result = delegate.saveContract(content,"71ED833FF46E404C84F2C480A645E149" );
		System.out.println("result=="+result);
		actionWrite("{\"result\":\""+result+"\"}");
		if("保存成功".equals(result)){
			data.setValid(0L);
			dataExchangeService.updateDwDataExchangeStore(data);			
		}
		return null;
	}
	
	@Action(value="saveAttach")
	public String saveAttach(){		
        String uploader = (String) this.session.getAttribute(LoginConstant.SECURITY_USER_NAME);        
        String uploaderLoginName = (String) this.session.getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);  
        String appName =  request.getParameter("appName");
		String filePaths =  request.getParameter("filePath");
		String fileNames =  request.getParameter("fileName");
		String fileExtNames =  request.getParameter("fileExtName");	
		String fileSizes =  request.getParameter("fileSize");		
		String versions =  request.getParameter("version");
		String memos =  request.getParameter("memo");
		String groupId="";
		if(filePaths!=null && !filePaths.equals("")){
			String[] sFilePath=filePaths.split(",");
			String[] sFileName=fileNames.split(",");
			String[] sFileExtName=fileExtNames.split(",");			
			String[] sFileSize=fileSizes.split(",");
			String[] sVersion=versions.split(",");
			String[] sMemo=memos.split(",");
			
			groupId=dataExchangeService.uploadNewFiles(appName,sFilePath,sFileName,sFileExtName,sFileSize,sVersion,sMemo, uploader, uploaderLoginName);
		}

		actionWrite("{\"success\":true,\"groupId\":\""+groupId+"\"}");
		return null;
	}
	
	@Action(value="getDeptLeaders")
	public String getDeptLeaders(){
		String oldDeptId =  request.getParameter("deptId");
		List<HashMap<String,String>> leaders=dataExchangeService.getDeptLeaderName(oldDeptId);
        
		JSONArray ja=JSONArray.fromObject(leaders);
		String sLeader=ja.toString();
		actionWrite(sLeader);
		return null;
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
}
