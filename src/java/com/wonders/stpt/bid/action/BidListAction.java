/**
 * 
 */
package com.wonders.stpt.bid.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.bid.model.vo.BidPlanListVo;
import com.wonders.stpt.bid.service.BidService;
import com.wonders.stpt.innerWork.util.ExcelUtil;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: BidListAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:51:41 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/bidList")
@Controller("bidListAction")
@Scope("prototype")
public class BidListAction extends AbstractParamAction implements ModelDriven<BidPlanListVo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5531476414655057774L;
	private BidPlanListVo bo = new BidPlanListVo();
	private ActionWriter aw = new ActionWriter(response);
	private BidService service;
	private PageResultSet<Map<String,Object>> pageResultSet;
	private String downloadFileName  ="招标计划管理";
	
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
	public BidPlanListVo getBo() {
		return bo;
	}
	public void setBo(BidPlanListVo bo) {
		this.bo = bo;
	}
	public BidService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("bidService")BidService service) {
		this.service = service;
	}

	@Override
	public BidPlanListVo getModel() {
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
		this.downloadFileName = "招标计划管理";
		downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
		downloadFileName = downloadFileName.replace("+", "%20"); // encode后替换  解决空格问题
		this.pageResultSet = this.service.list(bo);
        List<String> head = new ArrayList<String>();
        head.add("项目公司");head.add("线路");
        head.add("子目");head.add("类别");
        head.add("专业");head.add("标段号");
        head.add("工程名称");head.add("招标计划");
        head.add("中标金额");head.add("中标通知书发出日期");head.add("状态");
        List<List<String>> data = new ArrayList<List<String>>();
        List<Map<String,Object>> map = this.pageResultSet.getList();
        for(Map<String, Object> m : map){
        	List<String> inner = new ArrayList<String>();
        	inner.add(StringUtil.getNotNullValueString(m.get("PROJECTCOMPANY")));
        	inner.add(StringUtil.getNotNullValueString(m.get("LINE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("CATALOGNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("TYPENAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("MAJORNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDNUM")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PROJECTNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDPLANDATE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDAMOUNT")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDINFODATE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("STATUS")));
        	data.add(inner);
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        ExcelUtil.createXls(output, "招标计划管理", head, data);
        
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
		this.downloadFileName = "招标计划管理";
		downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
		downloadFileName = downloadFileName.replace("+", "%20"); // encode后替换  解决空格问题
		this.pageResultSet = this.service.listAll(bo);
        List<String> head = new ArrayList<String>();
        head.add("项目公司");head.add("线路");
        head.add("子目");head.add("类别");
        head.add("专业");head.add("标段号");
        head.add("工程名称");head.add("招标计划");
        head.add("中标金额");head.add("中标通知书发出日期");head.add("状态");
        List<List<String>> data = new ArrayList<List<String>>();
        List<Map<String,Object>> map = this.pageResultSet.getList();
        for(Map<String, Object> m : map){
        	List<String> inner = new ArrayList<String>();
        	inner.add(StringUtil.getNotNullValueString(m.get("PROJECTCOMPANY")));
        	inner.add(StringUtil.getNotNullValueString(m.get("LINE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("CATALOGNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("TYPENAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("MAJORNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDNUM")));
        	inner.add(StringUtil.getNotNullValueString(m.get("PROJECTNAME")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDPLANDATE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDAMOUNT")));
        	inner.add(StringUtil.getNotNullValueString(m.get("BIDINFODATE")));
        	inner.add(StringUtil.getNotNullValueString(m.get("STATUS")));
        	data.add(inner);
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        ExcelUtil.createXls(output, "招标计划管理", head, data);
        
        byte[] ba = output.toByteArray();  
        excelFile = new ByteArrayInputStream(ba);  
        output.flush();
        output.close();
        return "success";  
    }
}
