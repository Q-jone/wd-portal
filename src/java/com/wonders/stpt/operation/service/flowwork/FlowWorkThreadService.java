package com.wonders.stpt.operation.service.flowwork;

import com.wonders.stpt.beforeBuild.model.bo.ShortMsg;
import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.bo.FlowWorkProcess;
import com.wonders.stpt.operation.entity.bo.FlowWorkThread;
import com.wonders.stpt.operation.entity.bo.OpDictionary;
import com.wonders.stpt.operation.entity.bo.OpDocSend;
import com.wonders.stpt.operation.entity.vo.AttMainVo;
import com.wonders.stpt.operation.entity.vo.OpSendXmlVo;
import com.wonders.stpt.operation.service.OpDictionaryService;
import com.wonders.stpt.operation.service.OpDocSendService;
import com.wonders.stpt.operation.util.FlowWorkContents;
import com.wonders.stpt.operation.util.HttpRequestHelper;
import com.wonders.stpt.processDone.util.WebServiceFunc;
import com.wonders.stpt.todoItem.util.ConfigUtil;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;
import net.sf.json.JSONObject;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("flowWorkThreadService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class FlowWorkThreadService {
	
	private static final String urlAddTask = ConfigUtil.getValueByKey("config.properties", "urlAddTask");
	private static final String urlUpdateTask = ConfigUtil.getValueByKey("config.properties", "urlUpdateTask");
	private static final String urlDealFlow = ConfigUtil.getValueByKey("config.properties", "urlDealFlow");
	private static final String urlPassSign = ConfigUtil.getValueByKey("config.properties", "urlPassSign");
	private static final String urlPassAdd = ConfigUtil.getValueByKey("config.properties", "urlPassAdd");

    private OpTDao<FlowWorkThread> newDao;
    public OpTDao<FlowWorkThread> getNewDao() {
        return newDao;
    }
    @Autowired(required=false)
    public void setNewDao(@Qualifier("opTNewDao")OpTDao<FlowWorkThread> newDao) {
        this.newDao = newDao;
    }

	private OpTDao<FlowWorkThread> dao;
	public OpTDao<FlowWorkThread> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("opTDao")OpTDao<FlowWorkThread> dao) {
		this.dao = dao;
	}
	private OpTDao<ShortMsg> msgDao;
	public OpTDao<ShortMsg> getMsgDao() {
		return msgDao;
	}
	@Autowired(required=false)
	public void setMsgDao(@Qualifier("opTDao")OpTDao<ShortMsg> msgDao) {
		this.msgDao = msgDao;
	}
	
	public String saveThreadAndDoNext(FlowWorkThread fThread,HttpServletRequest request){
		
		String taskUid = fThread.getTaskUid();
		if(taskUid != null && !"".equals(taskUid)){
			HttpRequestHelper.portalService(taskUid, urlUpdateTask);
		}
		
		this.save(fThread);
		int oindex = fThread.getOrderIndex();
		List<Long> ids = new ArrayList<Long>();
		String hander = "";
		String stage = "";
		List<FlowWorkThread> fThreads =null;
		FlowWorkProcess flowProcess = null;
		
		String handlerId = request.getParameter("handlerId");
		String handlerName = request.getParameter("handlerName");
		
		switch(fThread.getOperationType().intValue()){
		case 0://通过
			fThreads = this.findByFlowUidAndIndexAll(fThread.getFlowUid(), oindex,oindex+1);
			boolean isnext = true;
			for(FlowWorkThread f:fThreads){
				if(f.getOrderIndex()==oindex){
					isnext=false;
					break;
				}else{
					ids.add(f.getId());
					hander+=f.getUserName();
				}
			}
			if(isnext){
				if(fThreads!=null&&fThreads.size()>0){
					for(Long id: ids){
						String addTaskResult = callAddTaskService(id,handlerId,handlerName);
						if(!"".equals(addTaskResult) && !"error".equals(addTaskResult)){
							this.updateTaskUid(id, addTaskResult);
						}
					}
					stage = fThreads.get(0).getNodeName();
					this.updateStateAndsTime(1, fThread.getEndTime(), ids);

                    //发送短信给指定人员  部门领导 套头人员
                    //套头人员 //部门领导
                    if(("运营发文套头".equals(fThreads.get(0).getNodeName()))
                            ||
                            ("运营发文审批".equals(fThreads.get(0).getNodeName()) && fThread.getDefOrderIndex() == 0)){
                        OpDocSend opDocSend = opDocSendService.findByFlowUid(fThread.getFlowUid());
                        List<String> sendList = new ArrayList<String>();
                        sendList.add(fThreads.get(0).getUserId());
                        this.sendMsg(sendList,"您有一条待办事项：运营发文-"+opDocSend.getDocTitle()+", 请关注！");
                    }


					this.flowWorkProcessService.updateProcess(fThread.getFlowUid(),hander+FlowWorkContents.FLOW_HANDER_ING,stage, 1,DateUtil.getNowTime());
				}else{//流程结束
					hander = fThread.getUserName()+FlowWorkContents.FLOW_HANDER_END; 
					
					flowProcess = flowWorkProcessService.findFlowProcessByFlowUid(fThread.getFlowUid());
					flowWorkProcessService.updateProcess(fThread.getFlowUid(),hander,2,fThread.getEndTime(),DateUtil.getNowTime());
					
					doFinish(fThread.getFlowUid());
				}
			}
			break;
		case 2:
			break;
		case 3:
			//流程否决
			hander = fThread.getUserName()+FlowWorkContents.FLOW_HANDER_ABORT; 
			
			flowProcess = flowWorkProcessService.findFlowProcessByFlowUid(fThread.getFlowUid());
			flowWorkProcessService.updateProcess(fThread.getFlowUid(),hander,3,fThread.getEndTime(),DateUtil.getNowTime());
			this.deleteByFlowUidAndOrderIndex(fThread.getFlowUid(), oindex, fThread.getId());
				
			break;
		case 4:
			//经分管领导审核同意
			hander = fThread.getUserName()+FlowWorkContents.FLOW_HANDER_AGREED_BY_LEADER; 
			
			flowProcess = flowWorkProcessService.findFlowProcessByFlowUid(fThread.getFlowUid());
			flowWorkProcessService.updateProcess(fThread.getFlowUid(),hander,4,fThread.getEndTime(),DateUtil.getNowTime());
			
			doFinish(fThread.getFlowUid());			
			break;
		case 5://退回发起人  当前循环的  发起人位置
			
			FlowWorkThread f = findStartByFlowUidAndLoopIndex(fThread.getFlowUid(),fThread.getLoopIndex());
			Long maxLoopIndex = this.findMaxLoopIndex(fThread.getFlowUid());
			if(f != null){
				this.deleteByFlowUidAndOrderIndex(fThread.getFlowUid(), oindex, fThread.getId());
				
				FlowWorkThread fnew = new FlowWorkThread();	
				fnew.setUserId(f.getUserId());
				fnew.setUserName(f.getUserName());		
				fnew.setFlowUid(f.getFlowUid());
				fnew.setNodeName(f.getNodeName());
				fnew.setDefOrderIndex(f.getDefOrderIndex());
				fnew.setAltAuthCode(f.getAltAuthCode());
				fnew.setOperate(f.getOperate());		
				fnew.setState(1);
				fnew.setStartTime(fThread.getEndTime());
				//add task
				String addTaskResult = callAddTaskService(fnew);
				if(!"".equals(addTaskResult) && !"error".equals(addTaskResult)){
					fnew.setTaskUid(addTaskResult);  //关联待办项KEY
				}
				fnew.setOrderIndex(1+oindex);
				fnew.setLoopIndex(maxLoopIndex+1l);
				this.save(fnew);
				
				flowWorkProcessService.updateProcess(fnew.getFlowUid(),fnew.getUserName()+FlowWorkContents.FLOW_HANDER_ING,fnew.getNodeName(),1,DateUtil.getNowTime());
			}
			break;
			
			
/*			fThreads = this.findByFlowUidAndLoopIndex(fThread.getFlowUid(),fThread.getLoopIndex(),fThread.getOrderIndex());
			Long maxLoopIndex = this.findMaxLoopIndex(fThread.getFlowUid());
			List<FlowWorkThread> fadds = new ArrayList<FlowWorkThread>();
			
			int i=1;
			int lastindex=0;
			
			int backIndex=0;
			
			if(fThreads.size()>0){
				backIndex=fThreads.get(0).getOrderIndex();
			}
			
			
			for(FlowWorkThread f:fThreads){
				
				FlowWorkThread fnew =null;
				if(f.getState()==2){//完成的节点
					fnew = new FlowWorkThread();
					if(StringUtil.isNull(f.getAltAuthCode())){
						fnew.setUserId(f.getUserId());
						fnew.setUserName(f.getUserName());						
					}else{
						fnew.setUserId(null);
						fnew.setUserName(null);		
					}
					fnew.setFlowUid(f.getFlowUid());
					fnew.setNodeName(f.getNodeName());
					fnew.setDefOrderIndex(f.getDefOrderIndex());
					fnew.setAltAuthCode(f.getAltAuthCode());
					fnew.setOperate(f.getOperate());
					fadds.add(fnew);
				}else{
					fnew = f;
					f.setStartTime(null);
				}
				fnew.setState(0);
				
				if(backIndex==f.getOrderIndex()){//发起人启动流程
					fnew.setState(1);
					fnew.setStartTime(fThread.getEndTime());
					//add task
					String addTaskResult = callAddTaskService(fnew);
					if(!"".equals(addTaskResult) && !"error".equals(addTaskResult)){
						fnew.setTaskUid(addTaskResult);  //关联待办项KEY
					}					
					flowWorkProcessService.updateProcess(fnew.getFlowUid(),fnew.getUserName()+FlowWorkContents.FLOW_HANDER_ING,fnew.getNodeName(),1,DateUtil.getNowTime());
				}
				if(f.getOrderIndex()!=lastindex){
					lastindex = f.getOrderIndex();
					i++;
				}
				
				fnew.setOrderIndex(i+oindex);
				fnew.setLoopIndex(maxLoopIndex+1l);
				fadds.add(fnew);
			}
			
			this.updateOrderindex(oindex,i, fThread.getFlowUid());
			//this.clearSelectedUserOnRollback(fThread.getOrderIndex(), fThread.getFlowUid());
			this.saveOrUpdateAll(fadds);
			break;*/
		}
		
		
		return "ok";
	}

    /**
     * 指定发送短信
     * @param registerList
     * @param message
     */
    private void sendMsg(List<String> registerList,String message){
        Map<String,String> userMap = this.dao.getUserMobile(registerList);
        String mobile = null;
        if(userMap != null && userMap.size() > 0){
            for(String user : registerList){
                mobile = userMap.get(user);
                if(mobile != null && mobile.length() > 0){
                    ShortMsg msg = new ShortMsg();
                    msg.setMobile(mobile);
                    msg.setStatus("0");
                    msg.setContent(message);
                    this.msgDao.save(msg);
                }
            }
        }
    }

    /**
     * 获取xml
     * @param bo
     * @return
     */
    private String getXML(OpDocSend opDocSend){
        //构造xml Map
        OpSendXmlVo xml = new OpSendXmlVo();
        xml.setId(opDocSend.getId());
        xml.setFileCode(opDocSend.getFileCode());
        xml.setFileType(opDocSend.getFileTypeDic()!=null?opDocSend.getFileTypeDic().getName():""
                + opDocSend.getFileTypeSubDic()!=null?"-"+opDocSend.getFileTypeSubDic().getName():"");
        xml.setDocTitle(opDocSend.getDocTitle());
        xml.setSendMainW(opDocSend.getSendMainW());
        xml.setSendOutW(opDocSend.getSendOutW());
        xml.setSendLineW(opDocSend.getSendLineW());
        xml.setApplyDate(opDocSend.getApplyDate());
        xml.setPubDate(opDocSend.getPubDate());
        xml.setValidDate(opDocSend.getValidDate());
        xml.setPassDate(opDocSend.getPassDate());
        AttMainVo avo = new AttMainVo();
        avo.setAttachFileList(this.dao.getAttach(opDocSend.getContentAttMain()));
        xml.setAttMain(avo);
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context1 = JAXBContext.newInstance(OpSendXmlVo.class);
            Marshaller m = context1.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(xml, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 格式化XML
     */
    private String formatXml(Document doc){
        StringWriter out = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();

        format.setEncoding("UTF-8"); // 设置XML文档的编码类型
        format.setIndent(true);      // 设置是否缩进
        format.setIndent("   ");     // 以空格方式实现缩进
        format.setNewlines(true);    // 设置是否换行
        XMLWriter xw = new XMLWriter (out, format);
        try {
            //xw.setEscapeText(false);
            xw.write(doc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                xw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toString();
    }

    /**
     * 构造ws
     * @param result
     * @param xml
     * @param type
     */
    private void getWsResult(List<Map<String,String>> result,String xml,String type){
        Document doc = null;
        Document docNew = null;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            doc = DocumentHelper.parseText(xml);
            docNew = DocumentHelper.createDocument();
            Element rootElemtnt = docNew.addElement("root");
            rootElemtnt.addAttribute("date",date);
            rootElemtnt.addAttribute("type",type);
            Element datas = rootElemtnt.addElement("Datas");
            Node DatasNode=doc.selectSingleNode("BasicData");
            datas.add((Node)DatasNode.clone());
            Map<String,String> map = new HashMap<String, String>();
            map.put("date",date);
            map.put("content",this.formatXml(docNew));
            result.add(map);

        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	private void doFinish(String flowUid){
		OpDocSend opDocSend = opDocSendService.findByFlowUid(flowUid);

        String xml = this.getXML(opDocSend);
        List<Map<String,String>> wsResult = new ArrayList<Map<String, String>>();

        //发送短信至发起人
        List<String> registerList = new ArrayList<String>();
        registerList.add(opDocSend.getcUser());
        this.sendMsg(registerList,"您有一条运营发文："+opDocSend.getDocTitle()+" 已完成流转，请关注");

		String ids = "";
		String sendMainId = opDocSend.getSendMainId();
		String sendLineId = opDocSend.getSendLineId();
		if(!StringUtil.isNull(sendMainId)){
			ids += sendMainId;
		}
		if(sendLineId != null){
			ids += ","+sendLineId;
		}		
		
		List<OpDictionary> units = this.opDictionaryService.findByIds(ids);
		Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
		for(OpDictionary unit : units){
			if(!map.containsKey(unit.getCode())){
				Map<String,String> tmp = new HashMap<String,String>();
				if(!"ST_DEPT".equals(unit.getTypecode())){
					tmp.put("to", unit.getDescription());
					tmp.put("cc", unit.getName());
				}else{
					tmp.put("to", unit.getName());
					tmp.put("cc", "");
				}
				map.put(unit.getCode(), tmp);
			}else{
				Map<String,String> tmp = map.get(unit.getCode());
				if(!"ST_DEPT".equals(unit.getTypecode())){
					String cc = tmp.get("cc");
					tmp.put("cc", "".equals(cc)?unit.getName() : cc+"、"+unit.getName());
				}
			}
		}
		
		String toLeaderDepts = "";
		List<OpDictionary> toLeaders = opDictionaryService.findByType("TO_LEADER");
		if(toLeaders != null && toLeaders.size() > 0){
			toLeaderDepts = toLeaders.get(0).getName();
		}
		
		Set<String> depts = map.keySet();
		String sentDepts = "";
        //获取对应wstable
        Map<String,String> wsMap = this.newDao.getWsUser();
        for (String deptId : depts) {
        	Map<String,String> receive = map.get(deptId);
        	String to = receive.get("to");
        	String cc = receive.get("cc");
        	String ccTodo = (!"".equals(cc) ? "(请传阅"+cc+")" : "");
        	String ccRemark = (!"".equals(cc) ? "请传阅"+cc : "");
        	
        	String loginName = "";
        	String url = "";
        	ShortMsg msg = null;
        	String mobiles = "";
        	if(toLeaderDepts.indexOf(deptId) < 0){
        		Object[] receiver = this.opDictionaryService.getReceiver(deptId);
        		if(receiver != null){
        			loginName = (String)receiver[0];       
        			mobiles = (String)receiver[3];
        		}
        		url = urlPassSign;

        		if("2551".equals(deptId)){ //hardcode 建管中心 收发员办理
        			url = urlPassAdd;
        		}

                //发送webservice
                if(wsMap.size() > 0 && wsMap.containsKey(deptId)){
                    this.getWsResult(wsResult,xml,wsMap.get(deptId));
                }

        	}else{
        		Object[] leader = this.opDictionaryService.getLeader(deptId);
        		if(leader != null){
        			loginName = (String)leader[0];        			
        		}
        		url = urlPassAdd;
        	}
        	
        	try {
				String modelName = URLEncoder.encode("运营发文传阅","UTF-8");
				String title = URLEncoder.encode(opDocSend.getDocTitle(),"UTF-8");
				String remark = URLEncoder.encode(ccRemark,"UTF-8");
				url += "?modelName="+modelName+"&attach="+opDocSend.getContentAttMain()+"&title="+title+"&mainId="+opDocSend.getId()+"&mainTable=OP_DOC_SEND&remark="+remark+"&taskuser=ST/"+loginName;
				
				Map param = new HashMap();
				param.put("app", "OP_DOCSEND");
				param.put("type", "0");
				param.put("occurTime", DateUtil.getNowTime());
				param.put("title", "运营发文部门内部传阅-"+to+ccTodo);
				param.put("loginName", "ST/"+loginName);
				param.put("status", "0");
				param.put("removed", "0");
				param.put("typename", "运营发文");
				param.put("url", "");
				param.put("pname", "运营发文");
				param.put("pincident", "1");
				param.put("cname", "子流程实例号");
				param.put("cincident", "1");
				param.put("stepName", "运营发文传阅");
				param.put("initiator", "ST/"+loginName);
				String todoId = HttpRequestHelper.portalService(JSONObject.fromObject(param).toString(), urlAddTask);
				this.dao.excuteHQLUpdate("update TodoItem set url = ? where id = ?", new Object[]{url+"&todoId="+todoId,todoId});
				
				sentDepts += ","+deptId;
				
        		if(!StringUtil.isNull(mobiles)){
        			String[] mobileArray = mobiles.split(",");
        			for(String mobile : mobileArray){
            			msg = new ShortMsg();
    					msg.setMobile(mobile);
    					msg.setStatus("0");
    					msg.setContent("您有一条运营发文通知："+opDocSend.getDocTitle()+" 请查看");
    					this.msgDao.save(msg);        				
        			}
        		}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        }
        
        if(sentDepts.length() > 0){
        	sentDepts = sentDepts.substring(1);
        	int passCnt = sentDepts.split(",").length;
        	this.dao.excuteHQLUpdate("update OpDocSend set sentDepts = ?,passCnt = ?,passDate = ? where id = ?", new Object[]{sentDepts,passCnt,DateUtil.getNowDate(),opDocSend.getId()});
        }

        //webservice
        WebServiceFunc ws = new WebServiceFunc();
        List<String> resultList = ws.setDataInfo(wsResult);

	}
	
	private List<FlowWorkThread> findByFlowUidAndIndexAll(String flowUid,int orderindex,int nextorderindex){
		return this.dao.findByHql(" from FlowWorkThread f where  f.flowUid= ? and (f.orderIndex = ? or f.orderIndex= ?) and (f.state=0 or f.state=1) ", new Object[]{flowUid,orderindex,nextorderindex});
	}

	public FlowWorkThread save(FlowWorkThread bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	private int updateTaskUid(Long id,String taskUid){
		return this.dao.excuteHQLUpdate(" update FlowWorkThread a set a.taskUid = ? where a.id = ? ", new Object[]{taskUid,id});
	}
	
	private int updateStateAndsTime(final int state,final String time,final List<Long> ids){
        return dao.getHibernateTemplate().execute(new HibernateCallback<Integer>() {  
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createQuery(" update FlowWorkThread a set a.state = :state, a.startTime = :startTime where a.id in (:ids) ");
                q.setParameter("state", state);
                q.setParameter("startTime", time);
                q.setParameterList("ids", ids);
                return q.executeUpdate();  
            }  
        });  
	}
	
	private int updateStateAndsHandler(final List<Long> ids,final String handlerId,final String handlerName){
        return dao.getHibernateTemplate().execute(new HibernateCallback<Integer>() {  
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createQuery(" update FlowWorkThread a set a.userId = :userId, a.userName = :userName where a.id in (:ids) ");
                q.setParameter("userId", handlerId);
                q.setParameter("userName", handlerName);
                q.setParameterList("ids", ids);
                return q.executeUpdate();  
            }  
        });  
	}
	
	private int deleteByFlowUidAndOrderIndex(String flowUid,int orderIndex,Long threadId){
		return dao.excuteHQLUpdate(" delete from FlowWorkThread f where  f.flowUid=? and f.orderIndex>=? and f.id !=?", new Object[]{flowUid,orderIndex,threadId});
	}
	
/*	private List<FlowWorkThread> findByFlowUidAndLoopIndex(String flowUid,Long loopIndex,int orderIndex){
		return dao.findByHql(" from FlowWorkThread f where  f.flowUid=? and f.loopIndex=? and f.orderIndex<=?  order by f.orderIndex asc, f.id asc  ", new Object[]{flowUid,loopIndex,orderIndex});
	}*/
	
	private FlowWorkThread findStartByFlowUidAndLoopIndex(String flowUid,Long loopIndex){
		List<FlowWorkThread> list = dao.findByHql(" from FlowWorkThread f where  f.flowUid=? and f.loopIndex=? and f.defOrderIndex=0", new Object[]{flowUid,loopIndex});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	private int updateOrderindex(int index,int addNum ,String flowUid){
		return dao.excuteHQLUpdate(" update FlowWorkThread a set a.orderIndex = a.orderIndex+? where a.orderIndex>? and a.flowUid=? ", new Object[]{addNum,index,flowUid});
	}
	
	private int updateHandler(String flowUid,int orderIndex,String handlerId,String handlerName){
		return dao.excuteHQLUpdate(" update FlowWorkThread a set a.userId = ? and a.userName = ? where a.orderIndex = ?+1 and a.flowUid=? ", new Object[]{handlerId,handlerName,orderIndex,flowUid});
	}
	
	private Long findMaxLoopIndex(String flowUid){
		Query query = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select max(f.loopIndex) from FlowWorkThread f where  f.flowUid='"+flowUid+"'");
		return Long.valueOf(query.uniqueResult().toString());
	}
	
	public FlowWorkThread findOngoingThread(String flowUid,String userId){
		List<FlowWorkThread> list = this.dao.findByHql("from FlowWorkThread f where f.flowUid=? and f.userId like ? and f.state = 1", new Object[]{flowUid,userId+"%"});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public FlowWorkThread findThreadByOrderIndex(String flowUid,int orderIndex){
		List<FlowWorkThread> list = this.dao.findByHql("from FlowWorkThread f where f.flowUid=? and f.orderIndex = ?", new Object[]{flowUid,orderIndex});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public List<FlowWorkThread> findThreadsByFlowUid(String flowUid){
		return this.dao.findByHql("from FlowWorkThread f where f.state = 2 and f.flowUid=? order by f.orderIndex desc", new Object[]{flowUid});
	}
	
	private void saveOrUpdateAll(List<FlowWorkThread> threads){
		this.dao.saveOrUpdateAll(threads);
	}
	
	public FlowWorkThread find(Long id) {
		// TODO Auto-generated method stub
		return dao.find(id,FlowWorkThread.class);
	}
	private String callAddTaskService(Long threadId){
		FlowWorkThread thread = this.find(threadId);
		return this.callAddTaskService(thread);
	}
	
	private String callAddTaskService(Long threadId, String handlerId, String handlerName){
		FlowWorkThread thread = this.find(threadId);
		if(!StringUtil.isNull(handlerId)){
			thread.setUserId(handlerId);
			thread.setUserName(handlerName);
		}
		return this.callAddTaskService(thread);
	}
	
	private String callAddTaskService(FlowWorkThread thread){
		Map p = this.getTaskParam(thread);
		String pJson = JSONObject.fromObject(p).toString();
		String result = HttpRequestHelper.portalService(pJson, urlAddTask);
		if("".equals(result) || "error".equals(result)){
			System.out.println("Failed to add task !");
		}
		return result;
	}
	
	/**
	 * 调用待办接口传递参数
	 * @param threadId
	 * @return
	 */
	private Map getTaskParam(FlowWorkThread thread){
		Map param = null;
		
		OpDocSend opDocSend = opDocSendService.findByFlowUid(thread.getFlowUid());
		if(thread != null && opDocSend != null){
			param = new HashMap();
			param.put("app", "OP_DOCSEND");
			param.put("type", "0");
			param.put("occurTime", DateUtil.getNowTime());
			param.put("title", "运营发文-" + opDocSend.getDocTitle());
			param.put("loginName", "ST/"+thread.getUserId());
			param.put("status", "0");
			param.put("removed", "0");
			param.put("typename", "运营发文");
			try {
				param.put("url", URLEncoder.encode(urlDealFlow + "?id="+opDocSend.getId(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			param.put("pname", "运营发文");
			param.put("pincident", "1");
			param.put("cname", "子流程实例号");
			param.put("cincident", "1");
			param.put("stepName", thread.getNodeName());
			param.put("initiator", "ST/"+thread.getUserId());
		}
		return param;
	}
	
	private FlowWorkProcessService flowWorkProcessService;
	public FlowWorkProcessService getFlowWorkProcessService() {
		return flowWorkProcessService;
	}
	@Autowired(required=false)
	public void setFlowWorkProcessService(@Qualifier("flowWorkProcessService")FlowWorkProcessService flowWorkProcessService) {
		this.flowWorkProcessService = flowWorkProcessService;
	}
	
	private OpDocSendService opDocSendService;
	public OpDocSendService getOpDocSendService() {
		return opDocSendService;
	}
	@Autowired(required=false)
	public void setOpDocSendService(@Qualifier("opDocSendService")OpDocSendService opDocSendService) {
		this.opDocSendService = opDocSendService;
	}
	
	private OpDictionaryService opDictionaryService;
	public OpDictionaryService getOpDictionaryService() {
		return opDictionaryService;
	}
	@Autowired(required=false)
	public void setOpDictionaryService(@Qualifier("opDictionaryService")OpDictionaryService opDictionaryService) {
		this.opDictionaryService = opDictionaryService;
	}

    public static void main(String[] args){
        OpSendXmlVo vo = new OpSendXmlVo();
        vo.setDocTitle("");
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context1 = JAXBContext.newInstance(OpSendXmlVo.class);
            Marshaller m = context1.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(vo, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(sw.toString());
    }
}
