package com.wonders.stpt.doneConfig.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.model.TProcessConfig;
import com.wonders.stpt.doneConfig.service.ITDoneConfigClassicService;
import com.wonders.stpt.doneConfig.service.ITProcessConfigService;
import com.wonders.stpt.doneConfig.service.TDoneConfigInfoService;
import com.wondersgroup.framework.security.bo.SecurityUser;

@ParentPackage("struts-default")
@Namespace(value = "/doneConfig/classic")
@Controller("tTodoClassicAction")
@Scope("prototype")
public class TDoneConfigClassicAction implements SessionAware,ServletResponseAware {
	@Autowired
	private ITDoneConfigClassicService doneConfigClassicService;
	@Autowired
	private ITProcessConfigService processConfigService;
	@Autowired
	private TDoneConfigInfoService doneConfigInfoService;
	private HttpServletResponse response;
	private String name;
	private PageResultSet pageResultSet;
	private TDoneConfigClassic doneConfigClassic=new TDoneConfigClassic();
	private Logger logger=Logger.getLogger(TProcessConfigAction.class);
	private String id;
	private List chk;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Map<String, Object> session;
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public List getChk() {
		return chk;
	}
	public void setChk(List chk) {
		this.chk = chk;
	}
	@Action(value="checkName",results={@Result(name="checkName",location="/doneConfig/classic/save.jsp")})
	public void checkName()throws Exception {//验证重复
		TDoneConfigClassic classic=new TDoneConfigClassic();
		classic.setName(name);
		
		if(StringUtils.isBlank(id)){
		response.getWriter().print(doneConfigClassicService.find(classic, 1, Integer.MAX_VALUE).getList().size());
		}else{
			HashMap param = new HashMap();
	        param.put("removed", "0");
	        param.put("id", id);
	        param.put("name", name);
			response.getWriter().print(doneConfigClassicService.findByHql("from TDoneConfigClassic t where t.removed='0' and t.id!=:id and t.name=:name ", param).size());
		}
	}
	
	private List<TProcessConfig> listRecorder;
	@Action(value="dosave",results={@Result(name="dosave",location="/doneConfig/classic/save.jsp")})
	public String dosave()throws Exception{
		return "dosave";
	}//根据主键查找类型
	@Action(value="findById",results={@Result(name="findById",location="/doneConfig/classic/save.jsp")})
	public String findById()throws Exception{
		logger.info(id+"  ------------------");
		doneConfigClassic=doneConfigClassicService.findById(id);
		return "findById";
	}
	@Action(value="save",results={@Result(name="save",type="redirectAction",location="list.action")})///doneConfig/classic/save.jsp
	public String save()throws Exception{
		logger.info("save");
		doneConfigClassicService.save(doneConfigClassic);
		return "save";
	}
	@Action(value="list",results={@Result(name="list",location="/doneConfig/classic/list.jsp")})
	public String list()throws Exception{
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        logger.info("登陆名称工号："+loginName+"    姓名："+user);
		pageResultSet=doneConfigClassicService.find(doneConfigClassic, 1, Integer.MAX_VALUE);
		return "list";
	}
	/**
	 * 获取未归入类型下的记录（初始化时）
	 * @return
	 * @throws Exception
	 */
	@Action(value="addList",results={@Result(name="addList",location="/doneConfig/classic/addList.jsp")})
    public String addList()throws Exception{
		TDoneConfigClassic classic=doneConfigClassicService.findById(id);//获取当前类型
		//获取当前尚未归入类别中的记录
		listRecorder=processConfigService.findByHQL("from TProcessConfig p where removed='0' and typeId is null", null);
		//获取当前未归类记录type=1
		//listRecorder=processConfigService.findByIds(ids, 1);
		doneConfigClassic=classic;
    	logger.info("addList    "+id);
    	return "addList";
    }
	/**
	 * 批量将记录归入类型下(初始化)
	 * @return
	 * @throws Exception
	 */
	@Action(value="addRecord",results={@Result(name="addRecord",type="redirect",location="/doneConfig/classic/list.action")})
	public String addRecord()throws Exception{
		logger.info(id+"    addRecord    "+chk);
		TDoneConfigClassic classic=doneConfigClassicService.findById(id);
		//List<TProcessConfig> config=processConfigService.findByIds((String[])chk.toArray(new String[chk.size()]), 0);
		TProcessConfig config;
		for(int i=0;i<chk.size();i++){
			config=processConfigService.findById((String)chk.get(i));
			config.setTypeId(id);
			processConfigService.save(config);
			//doneConfigClassicService.
		}
		return "addRecord";
	}
	@Action(value="showList",results={@Result(name="showList",location="/doneConfig/classic/listRecord.jsp")})
	public String showList()throws Exception{
		logger.info("sdsd");
		String hql="from TProcessConfig where removed=? and typeId=?";
		listRecorder=processConfigService.findByHQL(hql, new Object[]{"0",id});
		return "showList";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value="deleteRecord",results={@Result(name="deleteRecord",type="redirectAction",location="showList.action",params={"id","%{id}"})})
	public String deleteRecord()throws Exception{
		logger.info(id);
		//将info表中recordID=id的记录删除
		//String hql="update TDoneConfigInfo set removed='1' where recordId=? ";
		//doneConfigInfoService.delete(hql, new Object[]{id});
		String typeId=processConfigService.findById(id).getTypeId();//获取类型
		//将记录从类别中删除
		String hql1="update TProcessConfig set typeId=null where id=?";
		processConfigService.deleteByType(hql1, new Object[]{id});
		id=typeId;
		return "deleteRecord";
	}
	@Action(value="deleteType",results={@Result(name="deleteType",type="redirectAction",location="list.action")})
	public String deleteType()throws Exception{
		//删除type=id的info中的记录；
		String hql="update TDoneConfigInfo set removed='1' where typeId=? ";
		doneConfigInfoService.delete(hql, new Object[]{id});
		//将类型中的记录剔除
		String hql1="update TProcessConfig set typeId=null where typeId=?";
		processConfigService.deleteByType(hql1, new Object[]{id});
		//删除记录本身
		doneConfigClassicService.delete(id);
		return "deleteType";
	}
	
	
	public TDoneConfigClassic getDoneConfigClassic() {
		return doneConfigClassic;
	}
	public void setDoneConfigClassic(TDoneConfigClassic doneConfigClassic) {
		this.doneConfigClassic = doneConfigClassic;
	}
	public PageResultSet getPageResultSet() {
		return pageResultSet;
	}
	public void setPageResultSet(PageResultSet pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<TProcessConfig> getListRecorder() {
		return listRecorder;
	}
	public void setListRecorder(List<TProcessConfig> listRecorder) {
		this.listRecorder = listRecorder;
	}
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		response = httpServletResponse;
        response.setContentType("text/html;charset=utf-8");
	}
	
}
