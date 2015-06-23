/**
 * 
 */
package com.wonders.stpt.innerWork.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.innerWork.model.vo.InnerWorkListVo;
import com.wonders.stpt.innerWork.service.InnerWorkService;
import com.wonders.stpt.innerWork.util.ExcelUtil;
import com.wonders.stpt.innerWork.util.InnerWorkUtil;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;


/** 
 * @ClassName: InnerWorkListAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:51:41 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/innerWorkList")
@Controller("innerListAction")
@Scope("prototype")
public class InnerWorkListAction extends AbstractParamAction implements ModelDriven<InnerWorkListVo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6137396100679830447L;
	private InnerWorkListVo bo = new InnerWorkListVo();
	private ActionWriter aw = new ActionWriter(response);
	private InnerWorkService service;
	private PageResultSet<Map<String,Object>> pageResultSet;
	private String downloadFileName  ="部门内部工作";
	
	
	public String getDownloadFileName() {
		return downloadFileName;
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	public PageResultSet<Map<String,Object>> getPageResultSet() {
		return pageResultSet;
	}
	public void setPageResultSet(PageResultSet<Map<String,Object>> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	public InnerWorkListVo getBo() {
		return bo;
	}
	public void setBo(InnerWorkListVo bo) {
		this.bo = bo;
	}
	public InnerWorkService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("innerWorkService")InnerWorkService service) {
		this.service = service;
	}

	@Override
	public InnerWorkListVo getModel() {
		// TODO Auto-generated method stub
		return bo;
	}
	
	@Action(value="list")
	public String list(){
		this.pageResultSet = this.service.list(bo);
		aw.writeJson(pageResultSet);
		return null;
	}
	
	private InputStream excelFile;  
	
	public InputStream getExcelFile() {  
        return excelFile;  
    }  
  
    public void setExcelFile(InputStream excelFile) {  
        this.excelFile = excelFile;  
    }  
    
	@Action(value = "excel", results = { @Result(name = "success", type = "stream", params = {  
            "contentType", "application/vnd.ms-excel", "inputName",  
            "excelFile", "contentDisposition",  
            "attachment;filename=${downloadFileName}.xls", "bufferSize", "4096" }) })  
    public String export() throws Exception {  
		this.downloadFileName = "部门内部工作";
		downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
		downloadFileName = downloadFileName.replace("+", "%20"); // encode后替换  解决空格问题
		this.pageResultSet = this.service.list(bo);
        List<String> head = new ArrayList<String>();
        head.add("督办工作");head.add("负责人");head.add("完成标志");
        head.add("要求完成时间"); head.add("最新状态");head.add("进展标志");
        head.add("开始时间");head.add("完成状态");head.add("分管领导");
        head.add("工作要求");head.add("延迟原因分类");head.add("延迟原因描述");
        
        
        List<List<String>> data = new ArrayList<List<String>>();
        List<Map<String,Object>> map = this.pageResultSet.getList();
        for(Map<String, Object> m : map){
        	List<String> inner = new ArrayList<String>();
        	inner.add(StringUtil.getNotNullValueString(m.get("JOBNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("RPEOPLE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("FFLAG")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PFTIME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PROCESS")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PFLAG")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BTIME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("STATUS")));
        	inner.add(StringUtil.getNotNullValueString(m.get("RLEADER")));
        	inner.add(StringUtil.getNotNullValueString(InnerWorkUtil.oracleClob2Str((Clob)m.get("JOBDEMAND"))));
        	inner.add(StringUtil.getNotNullValueString(m.get("DELAYTYPE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("DELAYDETAIL")));
        	data.add(inner);
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        ExcelUtil.createXls(output, "部门内部工作", head, data);
        
        byte[] ba = output.toByteArray();  
        excelFile = new ByteArrayInputStream(ba);  
        output.flush();
        output.close();
        return "success";  
    }  
	
	@Action(value = "excelAll", results = { @Result(name = "success", type = "stream", params = {  
            "contentType", "application/vnd.ms-excel", "inputName",  
            "excelFile", "contentDisposition",  
            "attachment;filename=${downloadFileName}.xls", "bufferSize", "4096" }) })  
    public String exportAll() throws Exception {  
		this.downloadFileName = "部门内部工作";
		downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
		downloadFileName = downloadFileName.replace("+", "%20"); // encode后替换  解决空格问题
		this.pageResultSet = this.service.listAll(bo);
        List<String> head = new ArrayList<String>();
        head.add("督办工作");head.add("负责人");head.add("完成标志");
        head.add("要求完成时间"); head.add("最新状态");head.add("进展标志");
        head.add("开始时间");head.add("完成状态");head.add("分管领导");
        head.add("工作要求");head.add("延迟原因分类");head.add("延迟原因描述");
        
        
        List<List<String>> data = new ArrayList<List<String>>();
        List<Map<String,Object>> map = this.pageResultSet.getList();
        for(Map<String, Object> m : map){
        	List<String> inner = new ArrayList<String>();
        	inner.add(StringUtil.getNotNullValueString(m.get("JOBNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("RPEOPLE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("FFLAG")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PFTIME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PROCESS")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PFLAG")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BTIME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("STATUS")));
        	inner.add(StringUtil.getNotNullValueString(m.get("RLEADER")));
        	inner.add(StringUtil.getNotNullValueString(InnerWorkUtil.oracleClob2Str((Clob)m.get("JOBDEMAND"))));
        	inner.add(StringUtil.getNotNullValueString(m.get("DELAYTYPE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("DELAYDETAIL")));
        	data.add(inner);
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        ExcelUtil.createXls(output, "部门内部工作", head, data);
        
        byte[] ba = output.toByteArray();  
        excelFile = new ByteArrayInputStream(ba);  
        output.flush();
        output.close();
        return "success";  
    }
}
