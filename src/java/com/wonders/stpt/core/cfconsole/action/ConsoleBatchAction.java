package com.wonders.stpt.core.cfconsole.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wonders.stpt.core.cfconsole.entity.vo.ConsoleVo;
import com.wonders.stpt.core.cfconsole.util.Upload;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;

@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/cfconsole")
@Controller("consoleBatchAction")
@Scope("prototype")
public class ConsoleBatchAction extends AbstractParamAction{
	private List<File> fileupload;//这里的"fileName"一定要与表单中的文件域名相同  
    private List<String> fileuploadContentType;//格式同上"fileName"+ContentType  
    private List<String> fileuploadFileName;//格式同上"fileName"+FileName  
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
	private UserService userService;
	private OrganNodeService organNodeService;
	@Autowired(required=false)
	public void setUserService(@Qualifier("userService")UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public OrganNodeService getOrganNodeService() {
		return organNodeService;
	}
	@Autowired(required=false)
	public void setOrganNodeService(@Qualifier("organNodeService")OrganNodeService organNodeService) {
		this.organNodeService = organNodeService;
	}
	
	//excel导入user记录
	public void add(List<ConsoleVo> list){
		String loginName = "";
		String userName = "";
		String deptId = "";
		String orders = "";
		String password = "888888";
		if(list!=null&&list.size()>0){
			deptId = list.get(0).getDeptId();
			orders = list.get(0).getOrders();
		}else{
			return;
		}
		OrganNode organ = this.organNodeService.loadOrganNodeWithLazy(Long.parseLong(deptId), new String[] {
            "users"
        });
		Set<SecurityUser> users = new HashSet<SecurityUser>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				ConsoleVo vo = list.get(i);
				loginName = vo.getLoginName()+deptId;
				userName = vo.getUserName();
				SecurityUser user = new SecurityUser();
				user.setLoginName(loginName);
				user.setName(userName);
				user.setPassword(password);
				user.setStatus(1);
				user.setAccountType(1);
				user.setOrders(Integer.parseInt(orders)+i);
				this.userService.createNewUser(user);
				users.add(user);
			}
		}
		if(users.size()>0){
			organ.getUsers().addAll(users);
	        organNodeService.updateOrganNode(organ);
			this.organNodeService.updateOrganOrderByUserOrder(organ, users);
		}
	}
	
	
	
	
	
	
	
	
	
	
	/*-------------------excel-------------------------------------------------*/
	
	//excel 导入
	@Action(value="addExcel")	
	public String addExcel() throws Exception{
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
            fos.flush();
            fos.close();
            fis.close();
        }  
        
        System.out.println(this.servletRequest.getParameter("test"));
       
      // List<ConsoleVo> list = Upload.readExcel(getText("savePath")+"//"+newFileName);
     
      //  this.add(list);
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
        super.getServletResponse().setContentType("application/x-msdownload"); 
        super.getServletResponse().setCharacterEncoding("UTF-8");
        super.getServletResponse().addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8")); 
        OutputStream os=super.getServletResponse().getOutputStream();  
        //把文件内容通过输出流打印到页面上供下载  
        while((len=is.read(buffers))!=-1){     
            os.write(buffers, 0, len);  
        }  
          
        is.close();  
        os.flush();
        os.close(); 
        return null;  
	}
}
