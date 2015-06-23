package com.wonders.stpt.core.userManage.action;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.core.userManage.entity.vo.ManagerVo;
import com.wonders.stpt.core.userManage.service.StptUserService;
import com.wonders.stpt.core.userManage.util.ExcelUpload;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/userManage")
@Controller("userManageAction")
@Scope("prototype")
public class UserManageAction extends BaseAjaxAction{
	private List<File> fileupload;//这里的"fileName"一定要与表单中的文件域名相同  
    private List<String> fileuploadContentType;//格式同上"fileName"+ContentType  
    private List<String> fileuploadFileName;//格式同上"fileName"+FileName  
    
	private StptUserService stptUserService;

	private StptUser stptUser = new StptUser();
	
	public List<File> getFileupload() {
		return fileupload;
	}

	public void setFileupload(List<File> fileupload) {
		this.fileupload = fileupload;
	}

	public List<String> getFileuploadContentType() {
		return fileuploadContentType;
	}

	public void setFileuploadContentType(List<String> fileuploadContentType) {
		this.fileuploadContentType = fileuploadContentType;
	}

	public List<String> getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(List<String> fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public StptUserService getStptUserService() {
		return stptUserService;
	}

	@Autowired(required=false)
	public void setStptUserService(@Qualifier("stptUserService")StptUserService stptUserService) {
		this.stptUserService = stptUserService;
	}

	public StptUser getStptUser() {
		return stptUser;
	}

	public void setStptUser(StptUser stptUser) {
		this.stptUser = stptUser;
	}

	@Override
	public StptUser getModel() {
		// TODO Auto-generated method stub
		return stptUser;
	}
	
	@Action(value="stptUserExist")	
	public String stptUserExist(){
		int result = 0;
		String loginName = StringUtil.getNotNullValueString(servletRequest.getParameter("loginName"));
		List<StptUser> list = stptUserService.stptUserExist(loginName);
		if(list!=null&&list.size()>0){
			result = 1;
		}
		createJSonData("{\"result\":"+result+"}");
		return AJAX;
	}
	
	@Action(value="stptUserAdd",results={
			@Result(name="success",location="/userManage/stptUserAdd.jsp")
			})		
	public String stptUserAdd(){
		this.stptUserService.addStptUser(stptUser);
		return SUCCESS;
	}
	
	@Action(value="stptUserUpdate",results={
			@Result(name="stptUserEdited",params={"actionName","stptUserView","stptUserId","%{stptUser.id}"},type="redirectAction")
			})		
	public String stptUserUpdate(){
		this.stptUserService.updateStptUser(stptUser);	
		super.getServletRequest().getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, stptUser);
		return "stptUserEdited";
	}
	
	@Action(value="stptUserToFix",results={
			@Result(name="success",location="/userManage/stptUserFix.jsp")
			})		
	public String stptUserToFix(){
		Long id = 0L;
		Tuser u = (Tuser)super.getServletRequest().getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER);
		if(u!=null){
			id = u.getId();
		}
		this.stptUser = this.stptUserService.findStptUserById(id);
		List<ManagerVo> list = this.stptUserService.getUserInfo(id+"");
		super.getServletRequest().setAttribute("voList", list);
		return SUCCESS;
	}
	
	@Action(value="stptUserFix")	
	//基本信息完善
	public String stptUserFix(){
		stptUser.setFlag("1");
		this.stptUserService.updateStptUser(stptUser);	
		super.getServletRequest().getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, stptUser);
		Writer w = null;
		try {
			super.getServletResponse().setContentType("text/html");
			super.getServletResponse().setCharacterEncoding("UTF-8");
			w = super.getServletResponse().getWriter();
			w.write("<script>alert('修改成功!'); window.opener=null;window.open('','_self');window.close();</script>");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (w != null)
					w.flush();
				if (w != null)
					w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}
	
	@Action(value="stptUserDelete")
	public String stptUserDelete(){
		String id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("stptUserId"));
		this.stptUser = this.stptUserService.findStptUserById(Long.parseLong(id));
		stptUser.setRemoved("1");
		this.stptUserService.updateStptUser(stptUser);
		createJSonData("{\"success\":true}");
		return AJAX;
	}
	
	@Action(value="stptUserView",results={
			@Result(name="success",location="/userManage/stptUserView.jsp")
			})		
	public String stptUserView(){
		String id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("stptUserId"));
		this.stptUser = this.stptUserService.findStptUserById(Long.parseLong(id));
		List<ManagerVo> list = this.stptUserService.getUserInfo(id);
		List<ManagerVo> agentList = this.stptUserService.getAgentInfo(id);
		super.getServletRequest().setAttribute("voList", list);
		super.getServletRequest().setAttribute("agentList", agentList);
		return SUCCESS;
	}
	
	@Action(value="stptUserEdit",results={
			@Result(name="success",location="/userManage/stptUserEdit.jsp")
			})	
	public String stptUserEdit(){
		String id = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("stptUserId"));
		this.stptUser = this.stptUserService.findStptUserById(Long.parseLong(id));
		List<ManagerVo> list = this.stptUserService.getUserInfo(id);
		List<ManagerVo> agentList = this.stptUserService.getAgentInfo(id);
		super.getServletRequest().setAttribute("voList", list);
		super.getServletRequest().setAttribute("agentList", agentList);
		return SUCCESS;
	}
	
	public Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}
	
	@Action(value="stptUserManageByPage",results={
			@Result(name="success",location="/userManage/stptUserManageList.jsp")
			})
	public String stptUserManageByPage() {
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		int currentPage = 0;
		if(currentPageStr!=null && !currentPageStr.equals("")){
			currentPage = Integer.valueOf(currentPageStr);
		}

		int start = 0;
		int size = 20;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.stptUser);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.stptUser,
						key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		if(currentPage==0){
			page = stptUserService.findStptUserByPage(
					filter, start / size + 1, size);
		}else{
			page = stptUserService.findStptUserByPage(
					filter,currentPage, size);
		}
		
		this.servletRequest.setAttribute("page", page);
		
		return SUCCESS;
}
	
	@Action(value="excelImport")
	public String excelImport() throws Exception{
		SimpleDateFormat sDateFormat; 
    	Random r = new Random(); 
    	String extName = "";
    	String nowTimeStr = "";
    	String newFileName = "";
        File dir=new File(getText("savePath"));  
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        List<File> files=getFileupload();  
        String result = "";
        for(int i=0;i<files.size();i++){  
        	//生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）   
            int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //获取随机数 
            sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //时间格式化的格式 
            nowTimeStr = sDateFormat.format(new Date()); //当前时间 
        	//获取拓展名 
            if (getFileuploadFileName().get(i).lastIndexOf(".") >= 0){   
                extName = getFileuploadFileName().get(i).substring(getFileuploadFileName().get(i).lastIndexOf("."));   
            } 
            newFileName = nowTimeStr +"-"+ rannum + extName; //文件重命名后的名字 
        	result += newFileName;
            FileOutputStream fos=new FileOutputStream(getText("savePath")+"//"+newFileName);  
            FileInputStream fis=new FileInputStream(getFileupload().get(i));  
            byte []buffers=new byte[1024];  
            int len=0;  
            while((len=fis.read(buffers))!=-1){  
                fos.write(buffers,0,len);  
            }  
        }  
        System.out.println(result);
        List<StptUser> list2 = this.stptUserService.stptUserList();
        Map<String,String> map1 = new HashMap<String,String>();
        for(StptUser s:list2){
        	map1.put(s.getLoginName(), s.getLoginName());
        }
        List<StptUser> list = ExcelUpload.readExcel(getText("savePath")+"//"+newFileName);
        List<StptUser> list3 = new ArrayList<StptUser>();
        if(list!=null&&list.size()>0){
        	for(StptUser u : list){
        		if(u.getLoginName()!=null&&!"".equals(u.getLoginName())&&map1.containsKey(u.getLoginName())){
        			list3.add(u);
        		}
        	}
        }
        list.removeAll(list3);
        this.stptUserService.addPatch(list);
        super.getServletResponse().setCharacterEncoding("UTF-8");
        super.getServletResponse().getWriter().write(getText(result));
        return null;
	}
	
	@Action(value="excelDownload")
	public String excelDownload() throws Exception{
		String fileName=super.getServletRequest().getParameter("fileName");  
        String saveName=super.getServletRequest().getParameter("saveName");  
        String fullPath=getText("savePath")+"//"+saveName;  
        FileInputStream is=new FileInputStream(fullPath);  
        int len=0;  
        byte []buffers=new byte[1024];  
        super.getServletResponse().reset();  
        super.getServletResponse().setContentType("application/octet-stream"); 
        super.getServletResponse().setCharacterEncoding("UTF-8");
        super.getServletResponse().addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8").replace("+", "%20")); 
        //把文件内容通过输出流打印到页面上供下载  
        while((len=is.read(buffers))!=-1){  
            OutputStream os=super.getServletResponse().getOutputStream();  
            os.write(buffers, 0, len);  
        }  
          
        is.close();  
          
        return null;  
	}
	
	//
	@Action(value="findStptUserName")
	public String findStptUserName(){
		String maxRows=super.getServletRequest().getParameter("maxRows");  
        String name_startsWith=super.getServletRequest().getParameter("name_startsWith");  
		List<ManagerVo> list = this.stptUserService.getAgentInfo(maxRows,name_startsWith);
		String result = VOUtils.getJsonDataFromCollection(list);
		createJSonData(result);
		return AJAX;  
	}
	
	@Action(value="saveAgent")
	public String saveAgent(){
		String tid=super.getServletRequest().getParameter("tid");  
		String cid=super.getServletRequest().getParameter("cid"); 
		this.stptUserService.saveAgent(tid, cid);
		createJSonData("{\"success\":true}");
		return AJAX;  
	}
	
	@Action(value="deleteAgent")
	public String deleteAgent(){
		String tid=super.getServletRequest().getParameter("tid");  
		String cid=super.getServletRequest().getParameter("cid"); 
		this.stptUserService.deleteAgent(tid, cid);
		createJSonData("{\"success\":true}");
		return AJAX;  
	}

	
}
