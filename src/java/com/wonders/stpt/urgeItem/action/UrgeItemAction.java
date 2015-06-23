/**
 * 
 */
package com.wonders.stpt.urgeItem.action;

import java.util.Calendar;
import java.util.Date;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.urgeItem.model.bo.UrgeItem;
import com.wonders.stpt.urgeItem.service.UrgeItemService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: UrgeItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/urgeItem")
@Controller("urgeItemAction")
@Scope("prototype")
public class UrgeItemAction extends AbstractParamAction implements ModelDriven<UrgeItem>{

	private static final long serialVersionUID = 1L;
	
	private UrgeItem vo = new UrgeItem();
	
	private UrgeItemService service;
	
	
	
	public UrgeItemService getService() {
		return service;
	}

	@Autowired(required=false)
	public void setService(@Qualifier("urgeItemService")UrgeItemService service) {
		this.service = service;
	}

	public Date getAnotherDate(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, num);
		return c.getTime();
		}
	
	@Action(value="urgeDelay")
	public String urgeItemDelay(){
		String flag = "0";
		
		int number = 0;
		try{
			number = Integer.parseInt(vo.getDelayDay());
		}catch(Exception e){
			number = -1;
		}
		
		if(number > 0){
			String urgeDate = DateUtil.getDateStr(getAnotherDate(new Date(),number),"yyyy-MM-dd");
			vo.setDelayDate(urgeDate+" 23:59:59");
			UrgeItem bo = this.service.getBo(vo.getProcess(), vo.getIncident());
			try{
				if(bo == null){
					this.service.save(vo);
				}else{
					//bo.setDelayDate(urgeDate);
					bo.setDelayDay(vo.getDelayDay());
					bo.setDelayPerson(vo.getDelayPerson());
					this.service.update(bo);
				}
				flag = "1";
			}catch(Exception e){
				flag = "0";
			}
		}else if(number == 0){
			
		}else{
			flag = "0";
		}
		ActionWriter aw = new ActionWriter(this.servletResponse);
		aw.writeAjax(flag);
		return null;
	}

	
	@Action(value="urgeCount")
	public String urgeItemCount(){
		String process = StringUtil.getNotNullValueString(this.servletRequest.getParameter("process"));
		String incident = StringUtil.getNotNullValueString(this.servletRequest.getParameter("incident"));
		int count = 0;
		try{
			count = this.service.getCount(process, incident);
		}catch(Exception e){
			count = 0;
		}
		ActionWriter aw = new ActionWriter(this.servletResponse);
		aw.writeAjax(count+"");
		return null;
	}
	
	
	
	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public UrgeItem getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
}
