package com.wonders.stpt.standardWork.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.processInfo.model.TodoItemVo;
import com.wonders.stpt.standardWork.model.bo.StandardWorkType;
import com.wonders.stpt.standardWork.service.StandardWorkService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/standardWork")
@Controller("standardWorkAction")
@Scope("prototype")
public class StandardWorkAction extends ActionSupport implements ModelDriven<TodoItemVo>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private StandardWorkService standardWorkService;
	
	ActionWriter ac = new ActionWriter(response);
	
	private TodoItemVo vo = new TodoItemVo();

	  public TodoItemVo getModel()
	  {
	    return this.vo;
	  }
	  
	@Action(value="showTree")
	public String showTree(){
		String openId = this.request.getParameter("openId");
		String jsonData = standardWorkService.findEventsTypeByTree(openId);
		ac.writeAjax(jsonData);
		//System.out.println(jsonData);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="getFileListByPage",results={@Result(name="success",location="/standardWork/list.jsp")})
	public String getFileListByPage(){
		String typeId = this.request.getParameter("typeId");
		if(typeId==null){
			typeId = "";
		}
		String searchParam = this.request.getParameter("searchParam");
		String myPage = this.request.getParameter("myPage");
	    this.request.setAttribute("myPage", myPage);
	    this.request.setAttribute("typeId", typeId);
	    this.request.setAttribute("searchParam", searchParam);
	    if(typeId!=null&&typeId.length()>0){
	    	//StandardWorkType standardWorkType = (StandardWorkType)standardWorkService.load(StandardWorkType.class, typeId);
	    	//this.request.setAttribute("typeName", standardWorkType.getTypeName());
	    	
	    	this.request.setAttribute("typeName", standardWorkService.getParentNodes(typeId));
	    }
	    int totalRows = standardWorkService.getFileListCount(typeId, searchParam);
	    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
	    List<Object[]> list = standardWorkService.getFileListByPage(pageinfo.getBeginIndex(), this.vo.pageSize, typeId, searchParam);
	    PageResultSet result = new PageResultSet();
	    result.setList(list);
	    result.setPageInfo(pageinfo);
	    this.request.setAttribute("result", result);
		return "success";
	}
	
	@Action(value="openFile")
	public String openFile() throws Exception{
		String id = this.request.getParameter("id");
		String filePath = standardWorkService.getFilePathById(id);
		downLoad(filePath,response,true);
		return null;
	}
	
	public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(f.getName(),"utf-8"));
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(f.getName(),"utf-8"));
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
	
	@SuppressWarnings("unchecked")
	@Action(value="getContentListByPage",results={@Result(name="success",location="/standardWork/standardList.jsp")})
	public String getContentListByPage(){
		String myPage = this.request.getParameter("myPage");
	    this.request.setAttribute("myPage", myPage);
	    int totalRows = standardWorkService.getContentListCount();
	    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
	    List<Object[]> list = standardWorkService.getContentListByPage(pageinfo.getBeginIndex(), this.vo.pageSize);
	    PageResultSet result = new PageResultSet();
	    result.setList(list);
	    result.setPageInfo(pageinfo);
	    this.request.setAttribute("result", result);
		return "success";
	}
}
