/**
 * 
 */
package com.wonders.stpt.validFile.action;

import java.util.Map;

import com.wonders.stpt.validFile.constants.ValidFileConstants;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.validFile.model.vo.ValidFileListVo;
import com.wonders.stpt.validFile.service.ValidFileService;

/** 
 * @ClassName: ValidFileAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月22日 上午11:37:18 
 *  
 */
@ParentPackage("custom-default")
@Namespace(value="/validFileList")
@Controller("validFileListAction")
@Scope("prototype")
public class ValidFileListAction extends AbstractParamAction implements ModelDriven<ValidFileListVo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1506902245737557876L;
	private ValidFileListVo vo = new ValidFileListVo();
	private ValidFileService service ;
	private ActionWriter aw = new ActionWriter(response);
	private PageResultSet<Map<String,Object>> pageResultSet;
	
	
	@Action(value="list")
	public String list(){
		this.pageResultSet = this.service.list(new StringBuffer(ValidFileConstants.VALID_FILE_SQL),vo);
		aw.writeJson(pageResultSet);
		return null;
	}

    @Action(value="listAll" ,
            interceptorRefs = {@InterceptorRef(value = "myValid")}  )
    public String listAll(){
        this.pageResultSet = this.service.list(new StringBuffer(ValidFileConstants.SEND_FILE_SQL),vo);
        aw.writeJson(pageResultSet);
        return null;
    }
	
	
	
	public ValidFileService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("validFileService")ValidFileService service) {
		this.service = service;
	}

	@Override
	public ValidFileListVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}

	public ValidFileListVo getVo() {
		return vo;
	}

	public void setVo(ValidFileListVo vo) {
		this.vo = vo;
	}
	
	
}
