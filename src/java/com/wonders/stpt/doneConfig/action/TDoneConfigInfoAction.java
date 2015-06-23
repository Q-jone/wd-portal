package com.wonders.stpt.doneConfig.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.model.TDoneConfigInfo;
import com.wonders.stpt.doneConfig.model.TProcessConfig;
import com.wonders.stpt.doneConfig.service.ITDoneConfigClassicService;
import com.wonders.stpt.doneConfig.service.ITDoneConfigUserService;
import com.wonders.stpt.doneConfig.service.ITProcessConfigService;
import com.wonders.stpt.doneConfig.service.TDoneConfigInfoService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.security.bo.SecurityUser;
@ParentPackage("struts-default")
@Namespace(value = "/doneConfig/userInfo")
@Controller("doneConfigInfoAction")
@Scope("prototype")
public class TDoneConfigInfoAction implements SessionAware, ServletRequestAware, ServletResponseAware {
	@Autowired
	private TDoneConfigInfoService doneConfigInfoService;
	@Autowired
	private ITDoneConfigClassicService doneConfigClassicService;
	@Autowired
	private ITProcessConfigService processConfigService;
	@Autowired
	private ITDoneConfigUserService doneConfigUserService;
	private TDoneConfigInfo info=new TDoneConfigInfo();
	private TDoneConfigClassic doneConfigClassic=new TDoneConfigClassic();
	private Map<String, Object> session;
	private Logger logger=Logger.getLogger(TDoneConfigInfoAction.class);
	List<Object[]> listType;
	private String typeId;
	private String id;
	List<TProcessConfig> list;
	private Integer countType=0;
	private List<TDoneConfigClassic> classic;
	private List<String> chk;
	private List<TDoneConfigClassic> reverseclassic;
	private Integer count=0;
	private List<TProcessConfig> listRecorder;
	/**
	 * 初始化时接收输入的账号
	 */
	private String nums;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	public TDoneConfigInfo getInfo() {
		return info;
	}
	public void setInfo(TDoneConfigInfo info) {
		this.info = info;
	}
	/**
	 * 显示初始化类型
	 * @return
	 */
	@Action(value="init",results={@Result(name="init",location="/doneConfig/init.jsp")})
	public String init()throws Exception{
		classic=doneConfigClassicService.find(new TDoneConfigClassic(), 1, Integer.MAX_VALUE).getList();
		return "init";
	}
	
	/**
	 * 初始化
	 * @returninitType
	 */
	@Action(value="initType",results={@Result(name="initType",type="redirectAction",location="init.action")})
	public String initType()throws Exception{
		logger.info("aadfadsadsff");
		String[] loginNames=nums.split(",");
		TDoneConfigInfo info;
		for(String loginName:loginNames){//先删除当前账号在info中的数据在初始化
			if(!loginName.startsWith("ST/"))
				loginName="ST/"+loginName;
			int orders=0;
			String hql="update TDoneConfigInfo i set i.removed='1'  where i.removed='0' and i.loginName=:loginName ";//删除原始数据
			HashMap param = new HashMap();
	        param.put("removed", "0");
	        param.put("loginName", loginName);
			doneConfigInfoService.deleteByHql(hql, param);
			//end 删除当前账号在info中的数据在初始化
			for(String ids:chk){
				info=new TDoneConfigInfo();
				info.setLoginName(loginName);
				info.setOrders(String.valueOf(orders++));
				info.setTypeId(ids);
				doneConfigInfoService.save(info);
			}
		}
		return "initType";
	}
	
	@Action(value="list",results={@Result(name="list",location="/doneConfig/userInfo/list.jsp")})
	public String getUserInfo() throws Exception{
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        logger.info("登陆名称工号："+loginName+"    姓名："+user);
        if(!loginName.startsWith("ST/"))
        	loginName="ST/"+loginName;
        //list=doneConfigInfoService.findByLoginName(loginName);
        
       // String hql="select distinct id,name from TDoneConfigClassic t where removed=:removed and t.id in (select i.typeId from TDoneConfigInfo i where i.loginName=:loginName and i.removed=:removed)";
        
       // String hql="select t.id,t.name from TDoneConfigClassic t left join TDoneConfigInfo i where (t.id=i.typeId) and t.removed=:removed and i.removed=:removed and i.loginName=:loginName order by i.orders asc ";
        String hql="select distinct t.id,t.name,i.orders from TDoneConfigClassic t,TDoneConfigInfo i where t.id=i.typeId and t.removed=:removed and i.removed=:removed and i.loginName=:loginName order by i.orders asc ";
        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("loginName", loginName);
        listType=doneConfigInfoService.findByHQL(hql,param);
        for(int i=0;i<listType.size();i++){
        	logger.info(listType.get(i)[1]);
        }
        countType=listType.size();
        if(StringUtils.isNotBlank(typeId)){//如果归属于某一类型
        	//list=doneConfigInfoService.findByHQL("from TDoneConfigInfo p where removed=? and loginName=? and typeId=? and recordId!=null", new Object[]{"0",loginName,typeId});
        	list=processConfigService.findByHQL("from TProcessConfig p where p.removed=? and p.typeId =?", new Object[]{"0",typeId});
        }
        logger.info("assa");
		return "list";
	}
	/**
	 * 显示可添加的类别
	 * @return
	 * @throws Exception
	 */
	@Action(value="getType",results={@Result(name="getType",location="/doneConfig/userInfo/saveType.jsp")})
	public String getType()throws Exception{
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        if(!loginName.startsWith("ST/"))
        	loginName="ST/"+loginName;
        count=doneConfigInfoService.getType("from TDoneConfigInfo where removed=?  and loginName=?",new Object[]{"0",loginName}).size();
		String hql="from TDoneConfigClassic where removed='0' and id not in(select distinct typeId from TDoneConfigInfo where removed=?  and loginName=?)";//
		//可添加类别集合
		classic=doneConfigInfoService.getType(hql, new Object[]{"0",loginName});
		String rhql="from TDoneConfigClassic where removed='0' and id  in(select distinct typeId from TDoneConfigInfo where removed=? and loginName=?)";
		//String rhql="select t.* from TDoneConfigClassic t left join TDoneConfigInfo p on (t.id=p.typeId) where t.removed='0' and p.removed=? and p.loginName=?  order by p.orders";
		reverseclassic=doneConfigInfoService.getType(rhql, new Object[]{"0",loginName});
		return "getType";
	}
	@Action(value="addinfo",results={@Result(name="addinfo",type="redirect",location="/doneConfig/userInfo/list.action")})
	public String addinfo()throws Exception{
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        //start 获取用户信息（账号方面）
        String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginName12=StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("t_login_name"));
        String loginNames = "";
        Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
        logger.info(loginNames);
        //end
        if(!loginName.startsWith("ST/"))
        	loginName="ST/"+loginName;
        TDoneConfigClassic configClassic;
        TDoneConfigInfo configInfo;
        List<TProcessConfig> process=new ArrayList<TProcessConfig>();
        
		for(int i=0;i<chk.size();i++){//此处当前只添加loginName 的记录
			//获取当前账号所属类型的最大orders
	        String hql="select max(orders) from TDoneConfigInfo t where t.removed='0' and t.loginName=?";
	        String orders=doneConfigInfoService.getMaxOrders(hql, new Object[]{loginName});
	        if(Integer.valueOf(orders)>=0)
	        	orders=Integer.valueOf(orders)+1+"";
	        else
	        	orders="0";
			configClassic=doneConfigClassicService.findById(chk.get(i));
			process=processConfigService.findByHQL("from TProcessConfig where removed=? and typeId=?", new Object[]{"0",configClassic.getId()});
			configInfo=new TDoneConfigInfo();
			configInfo.setLoginName(loginName);
			configInfo.setTypeId(configClassic.getId());
			configInfo.setTypeName(configClassic.getName());
			configInfo.setOrders(orders);
			doneConfigInfoService.save(configInfo);
			for(int j=0;j<process.size();j++){
				configInfo=new TDoneConfigInfo();
				configInfo.setLoginName(loginName);
				configInfo.setRecordId(process.get(j).getId());
				configInfo.setRecordName(process.get(j).getName());
				configInfo.setTypeId(configClassic.getId());
				configInfo.setTypeName(configClassic.getName());
				configInfo.setOrders(orders);
				doneConfigInfoService.save(configInfo);
			}
		}
		return "addinfo";
	}
	/**
	 * 删除tdoneconfiginfo中的记录
	 * @return
	 * @throws Exception
	 */
	@Action(value="deletes",results={@Result(name="deletes",type="redirectAction",location="list.action",params={"typeId","%{typeId}"})})
	public String deletes()throws Exception{
		logger.info("sdsd");
		if(StringUtils.isNotBlank(id)){//单条记录删除
			logger.info(id);
			info=doneConfigInfoService.findById(id);
			doneConfigInfoService.delete(id);
			typeId=info.getTypeId();
		}else{//多条记录删除
			info=doneConfigInfoService.findById(chk.get(0));
			doneConfigInfoService.delete(chk.toArray(new String[chk.size()]));
			typeId=info.getTypeId();
		}
		
		
		return "deletes";
	}
	/**
	 * 将未添加到当前用户的记录添加到当前用户的相应类别下（查找）
	 * @return
	 * @throws Exception
	 */
	@Action(value="addList",results={@Result(name="addList",location="/doneConfig/userInfo/addList.jsp")})
    public String addList()throws Exception{
    	/**select * from t_process_config p where p.type_id ='B465DB906E3F4C37B7D8684CDADB8D19' and p.removed='0' and p.id not in 
		(select i.record_id from t_doneconfig_info i where i.type_id='B465DB906E3F4C37B7D8684CDADB8D19' 
				and i.login_name='ST/G001000001612549' and i.removed='0' and i.record_id is not null)*/
		logger.info(typeId);
		doneConfigClassic=doneConfigClassicService.findById(typeId);//显示当前类型
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        if(!loginName.startsWith("ST/"))
        	loginName="ST/"+loginName;
		//查询当前类型的可操作记录
		String hql="from TProcessConfig p where p.typeId =? and p.removed='0' and p.id not in"+ 
		" (select i.recordId from TDoneConfigInfo i where i.typeId=? "+
		" and i.loginName=? and i.removed='0' and i.recordId is not null)";
		listRecorder=processConfigService.findByHQL(hql, new Object[]{typeId,typeId,loginName});
		logger.info("asdas");
    	return "addList";
    }
	/**
	 * 将未添加到当前用户的记录添加到当前用户的相应类别下（添加）
	 * @return
	 */
	@Action(value="addRecord",results={@Result(name="addRecord",type="redirectAction",location="list.action",params={"typeId","%{typeId}"})})
	public String addRecord()throws Exception{
		doneConfigClassic=doneConfigClassicService.findById(id);//显示当前类型
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        if(!loginName.startsWith("ST/"))
        	loginName="ST/"+loginName;
        TProcessConfig process=new TProcessConfig();
        //判断用户在当前类型中是否有记录，如果有则orders取当前值，否则取最大值+1
        HashMap param = new HashMap();
        param.put("typeId", id);
        param.put("loginName", loginName);
        String hql="select orders from TDoneConfigInfo t where t.removed='0' and t.loginName=? and t.recordId is null and t.typeId=?";
        String orders=doneConfigInfoService.getMaxOrders(hql, new Object[]{loginName,id});
        
        TDoneConfigInfo configInfo;
        for(int i=0;i<chk.size();i++){
        	process=processConfigService.findById(chk.get(i));
        	configInfo=new TDoneConfigInfo();
			configInfo.setLoginName(loginName);
			configInfo.setRecordId(process.getId());
			configInfo.setRecordName(process.getName());
			configInfo.setTypeId(doneConfigClassic.getId());
			configInfo.setTypeName(doneConfigClassic.getName());
			configInfo.setOrders(orders);
			doneConfigInfoService.save(configInfo);
        }
        typeId=id;
		return "addRecord";
	}
	/**
	 * 拖拽保存
	 * @return
	 * @throws Exception
	 */
	@Action(value="saveType",results={@Result(name="saveType",type="redirectAction",location="list.action")})
	public String saveType()throws Exception{
		logger.info("sd");
		//String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        // 获取当前用户所有账号
        
        //String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("loginName"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		//logger.info(loginName);
        logger.info(loginNames);//所有16位工号
        loginNames+=","+ologinName;
        String[] names=loginNames.split(",");
        for(String loginName:names){//所有工号
        	if(loginName.startsWith("'")){//'loginName' 如果账号在引号内部
        		loginName=loginName.substring(1);
        	}
        	if(loginName.endsWith("'")){
        		loginName=loginName.substring(0, loginName.length()-1);
        	}
        if(!loginName.startsWith("ST/"))
        	loginName="ST/"+loginName;
        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("loginName", loginName);
        param.put("typeId", chk);
		//先删除该账号下要删除的数据
		String hql="update TDoneConfigInfo t set t.removed='1'  "//, t.loginName=:loginName
				+"where typeId in (select p.typeId from TDoneConfigInfo p where p.removed='0' and p.loginName=:loginName)";
				
		if(chk!=null)
			hql+=" and typeId not in (:typeId)";
		doneConfigInfoService.deleteByHql(hql, param);
		// 修改当前类型记录的orders
		//获取当前类型
		List<TDoneConfigInfo> infoList=doneConfigInfoService.findByHQL("from TDoneConfigInfo where removed='0' and loginName=? and recordId is null order by orders", new Object[]{loginName});
		TDoneConfigInfo infoU;
		//String hqlU="";
		//HashMap paramU; 
		for(int i=0;i<infoList.size();i++){//将当前记录的orders调整//为什么所有的orders都变成一个值
			infoU=infoList.get(i);
			infoU.setOrders(i+"");
			HashMap paramU=new HashMap();
			paramU.put("removed", "0");
			paramU.put("loginName", loginName);
			paramU.put("typeId", infoU.getTypeId());
			paramU.put("orders", String.valueOf(i));
			String hqlU="update TDoneConfigInfo t set t.orders=:orders where t.removed='0' and t.loginName=:loginName and t.typeId=:typeId ";
			logger.info(String.valueOf(i));
			doneConfigInfoService.deleteByHql(hqlU, paramU);
		}
		
		//再找出要添加的类型添加
		List<String> typeIdList=new ArrayList<String>();//存放添加的类型主键
		int mark=0;
		if(infoList.size()>0){
			for(String type:chk){
				mark=0;
				for(int i=0;i<infoList.size();i++){
					if(type.equals(infoList.get(i).getTypeId()))
						mark=1;
				}
				if(mark==0)//
					typeIdList.add(type);
			}
		}else{
			typeIdList=chk;
		}
		//获取当前账号的类型主键
		TDoneConfigClassic configClassic;
        TDoneConfigInfo configInfo;
        List<TProcessConfig> process=new ArrayList<TProcessConfig>();
        if(typeIdList!=null)
		for(int i=0;i<typeIdList.size();i++){//此处当前只添加loginName 的记录
			//获取当前账号所属类型的最大orders
	        String hqladd="select max(orders) from TDoneConfigInfo t where t.removed='0' and t.loginName=?";
	        String orders=doneConfigInfoService.getMaxOrders(hqladd, new Object[]{loginName});
	        if(Integer.valueOf(orders)>=0)
	        	orders=Integer.valueOf(orders)+1+"";
	        else
	        	orders="0";
			configClassic=doneConfigClassicService.findById(typeIdList.get(i));
			process=processConfigService.findByHQL("from TProcessConfig where removed=? and typeId=?", new Object[]{"0",configClassic.getId()});
			configInfo=new TDoneConfigInfo();
			configInfo.setLoginName(loginName);
			configInfo.setTypeId(configClassic.getId());
			//configInfo.setTypeName(configClassic.getName());
			configInfo.setOrders(orders);
			doneConfigInfoService.save(configInfo);
			/*for(int j=0;j<process.size();j++){//添加信息process
				configInfo=new TDoneConfigInfo();
				configInfo.setLoginName(loginName);
				configInfo.setRecordId(process.get(j).getId());
				configInfo.setRecordName(process.get(j).getName());
				configInfo.setTypeId(configClassic.getId());
				configInfo.setTypeName(configClassic.getName());
				configInfo.setOrders(orders);
				doneConfigInfoService.save(configInfo);
			}*/
		}}
		return "saveType";
	}
	
	public List<TProcessConfig> getList() {
		return list;
	}
	public void setList(List<TProcessConfig> list) {
		this.list = list;
	}
	public List<Object[]> getListType() {
		return listType;
	}
	public void setListType(List<Object[]> listType) {
		this.listType = listType;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Integer getCountType() {
		return countType;
	}
	public void setCountType(Integer countType) {
		this.countType = countType;
	}
	public List<TDoneConfigClassic> getClassic() {
		return classic;
	}
	public void setClassic(List<TDoneConfigClassic> classic) {
		this.classic = classic;
	}
	public List<String> getChk() {
		return chk;
	}
	public void setChk(List<String> chk) {
		this.chk = chk;
	}
	protected HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;
	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		this.servletResponse=servletResponse;
	}
	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		// TODO Auto-generated method stub
		this.servletRequest=servletRequest;
	}
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TDoneConfigClassic getDoneConfigClassic() {
		return doneConfigClassic;
	}
	public void setDoneConfigClassic(TDoneConfigClassic doneConfigClassic) {
		this.doneConfigClassic = doneConfigClassic;
	}
	public List<TProcessConfig> getListRecorder() {
		return listRecorder;
	}
	public void setListRecorder(List<TProcessConfig> listRecorder) {
		this.listRecorder = listRecorder;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<TDoneConfigClassic> getReverseclassic() {
		return reverseclassic;
	}
	public void setReverseclassic(List<TDoneConfigClassic> reverseclassic) {
		this.reverseclassic = reverseclassic;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	
}
