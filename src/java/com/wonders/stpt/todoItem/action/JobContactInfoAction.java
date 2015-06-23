/**
 * 
 */
package com.wonders.stpt.todoItem.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.wonders.stpt.todoItem.util.JobContactUtil;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: TodoItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/jobContact")
@Controller("jobContactInfoAction")
@Scope("prototype")
public class JobContactInfoAction extends AbstractParamAction{

	private static final long serialVersionUID = 1L;
	
	public static String items_split = "&&";
	public static String values_split = "##";
	
	/**工作联系单列表简单信息ACTION
	 * @return
	 */
	@Action(value="listJobInfo")
	public String ListJobInfo(){
		String ret = "";
		String groupids = StringUtil.getNotNullValueString(this.servletRequest.getParameter("groupids"));
		String ptypes = StringUtil.getNotNullValueString(this.servletRequest.getParameter("ptypes"));
		String dbxtypes = StringUtil.getNotNullValueString(this.servletRequest.getParameter("dbxtypes"));
		String loginName = StringUtil.getNotNullValueString(this.servletRequest.getParameter("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("deptId"));

		if(loginName.length()==0||deptId.length()==0||groupids.length()==0) return null;
		
		String[] group = groupids.split(",");
		String[] type = ptypes.split(",");
		String[] dbxtype = dbxtypes.split(",");
		
		for(int i=0;i<group.length;i++){
			String gp = StringUtil.getNotNullValueString(group[i]);
			String tp = StringUtil.getNotNullValueString(type[i]);
			String dtp = StringUtil.getNotNullValueString(dbxtype[i]);
			
			//int mapProcessNum = 0;
			//int mapDyxNum = 0;
			
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
			if("process".equals(dtp)){
				list = JobContactUtil.getListProcessInfo(gp, loginName, deptId, tp," order by to_number(pincident)");
			}else if("dyx".equals(dtp)){
				list1 = JobContactUtil.getListDyxInfo(gp, loginName, deptId, tp," order by to_number(pincident)");
			}else if("all".equals(dtp)){
				list = JobContactUtil.getListProcessInfo(gp, loginName, deptId, "3"," order by to_number(pincident)");
				list1 = JobContactUtil.getListDyxInfo(gp, loginName, deptId, "1"," order by to_number(pincident)");
			}
			String theme = "";
			String stepLabel = "";
			List<String> deptList = new ArrayList<String>();
			Map<String,String> steplabelList = new HashMap<String,String>();
			String memo = "";
			
			for(int j=0;j<list.size();j++){
				Map<String,String> tmp = list.get(j);
				theme = tmp.get("theme");
				if(!deptList.contains(tmp.get("mainUnit")))
					deptList.add(tmp.get("mainUnit"));
				steplabelList.put(tmp.get("steplabel"), "");
				stepLabel = tmp.get("steplabel");
			}
			
			for(int j=0;j<list1.size();j++){
				Map<String,String> tmp = list1.get(j);
				theme = tmp.get("theme");
				
				if(!deptList.contains(tmp.get("mainUnit")))
					deptList.add(tmp.get("mainUnit"));
				
				steplabelList.put(tmp.get("steplabel"), "");
				
				stepLabel = tmp.get("steplabel");
			}
			
			String deptNames = JobContactUtil.listToStringBySplit(deptList, "，");

			if(steplabelList.size()>1) stepLabel = "";
			
			if("process".equals(dtp)){
				if("1".equals(tp)){
					if(list.size()>0) memo = "本记录中有  <font color=red><b>"+list.size()+"</b></font> 条待办事项";
				}
			}else if("dyx".equals(dtp)){
				if("0".equals(tp)){
					if(list1.size()>0)  memo = "本记录中有 <font color=red><b>"+list1.size()+"</b></font> 条待阅事项";
				}
			}else if("all".equals(dtp)){
				//if(list.size()>0&&list1.size()>0) memo = memo +"本记录中有";
				if(list.size()>0) memo = "本记录中有 <font color=red><b>"+list.size()+"</b></font> 条已办事项";
				//if(list.size()>0&&list1.size()>0) memo = memo+"、";
				if(list.size()==0&&list1.size()>0) memo = " 本记录中有 <font color=red><b>"+list1.size()+"</b></font> 条已阅事项";
			}
			
			
			ret = ret +gp + values_split + (list.size()+list1.size()) + values_split + ""+deptNames+"："+theme + values_split +stepLabel+values_split+memo+values_split+dtp+items_split;
		}
		
		if(ret.endsWith(items_split)) ret = ret.substring(0,ret.length()-items_split.length());
		ActionWriter aw = new ActionWriter(this.servletResponse);
		aw.writeAjax(ret);

		return null;
	}
	
	/**工作联系单列表弹出层信息ACTION
	 * @return
	 */
	@Action(value="listHoverJobInfo")
	public String ListHoverJobInfo(){
		String ret = "";
		String groupId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("groupId"));
		String ptype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("ptype"));
		String dbxtype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("dbxtype"));
		String name = StringUtil.getNotNullValueString(this.servletRequest.getParameter("name"));
		String loginName = StringUtil.getNotNullValueString(this.servletRequest.getParameter("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("deptId"));
		
		if(loginName.length()==0||deptId.length()==0||groupId.length()==0) return null;
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		if("process".equals(dbxtype)){
			list = JobContactUtil.getListProcessInfo(groupId, loginName, deptId, ptype," order by to_number(pincident)");
		}else if("dyx".equals(dbxtype)){
			list = JobContactUtil.getListDyxInfo(groupId, loginName, deptId, ptype," order by to_number(pincident)");
		}else if("all".equals(dbxtype)){
			list = JobContactUtil.getListProcessInfo(groupId, loginName, deptId, "3"," order by to_number(pincident)");
			list.addAll(JobContactUtil.getListDyxInfo(groupId, loginName, deptId, "1"," order by to_number(pincident)"));
		}
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='hoverTable'>");
		sb.append("<tr>");
		
		if("process".equals(dbxtype)){
			sb.append("<td class='title'>主题</td>");
			sb.append("<td class='title'>部门</td>");
			if("1".equals(ptype)){
				sb.append("<td class='title'>步骤</td>");
				sb.append("<td class='title'>当前操作人</td>");
			}
		}else if("dyx".equals(dbxtype)){
			sb.append("<td class='title'>主题</td>");
			sb.append("<td class='title'>部门</td>");
			if("0".equals(ptype)){
				sb.append("<td class='title'>步骤</td>");
				sb.append("<td class='title'>当前操作人</td>");
			}
		}else if("all".equals(dbxtype)){
			sb.append("<td class='title'>主题</td>");
			sb.append("<td class='title'>部门</td>");
			sb.append("<td class='title'>事件跟踪</td>");
		}
		
		sb.append("</tr>");
		
		Map<String,String> filterDeptMap = new HashMap<String,String>();
		for(int j=0;j<list.size();j++){
			Map<String,String> tmp = list.get(j);
			
			if("process".equals(dbxtype)){
				sb.append("<tr>");
				sb.append("<td>"+tmp.get("theme")+"</td>");
				sb.append("<td>"+tmp.get("mainUnit")+"</td>");
				if("1".equals(ptype)){
					sb.append("<td>"+tmp.get("steplabel")+"</td>");
					sb.append("<td>"+name+"</td>");
				}
			}else if("dyx".equals(dbxtype)){
				sb.append("<tr>");
				sb.append("<td>"+tmp.get("theme")+"</td>");
				sb.append("<td>"+tmp.get("mainUnit")+"</td>");
				if("0".equals(ptype)){
					sb.append("<td>"+tmp.get("steplabel")+"</td>");
					sb.append("<td>"+name+"</td>");
				}
			}else if("all".equals(dbxtype)){
				if(!filterDeptMap.containsKey(tmp.get("mainUnitId"))){
					sb.append("<tr>");
					sb.append("<td>"+tmp.get("theme")+"</td>");
					sb.append("<td>"+tmp.get("mainUnit")+"</td>");
					sb.append("<td>"+"&nbsp;"+"</td>");
					filterDeptMap.put(tmp.get("mainUnitId"), "");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		ret = sb.toString();
		ActionWriter aw = new ActionWriter(this.servletResponse);
		aw.writeAjax(ret);

		return null;
	}

	
	/**工作联系单列表催办弹出层信息ACTION
	 * @return
	 */
	@Action(value="listHoverCuiBanJobInfo")
	public String ListHoverCuiBanJobInfo(){
		String ret = "";
		String groupId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("groupId"));
		String ptype = StringUtil.getNotNullValueString(this.servletRequest.getParameter("ptype"));
		String loginName = StringUtil.getNotNullValueString(this.servletRequest.getParameter("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("deptId"));
		
		if(loginName.length()==0||deptId.length()==0||groupId.length()==0) return null;
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		list = JobContactUtil.getListProcessInfo(groupId, loginName, deptId, ptype," order by to_number(pincident)");
		Map<String,Map<String,String>> jobInfoMap = new HashMap<String,Map<String,String>>();
		List<String> inciList = new ArrayList<String>();
		for(int j=0;j<list.size();j++){
			Map<String,String> tmp = list.get(j);
			String inci = tmp.get("pincident");
			if(!inciList.contains(inci)){
				inciList.add(inci);
				jobInfoMap.put(inci, tmp);
			}
		}
		
		List<String> inciListFilter = JobContactUtil.jobContactCuibanInfo(inciList); 
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='hoverTable'>");
		sb.append("<tr>");
		sb.append("<td class='title'>部门</td>");
		sb.append("<td class='title'>催办</td>");
		sb.append("</tr>");
		

		for(int j=0;j<inciListFilter.size();j++){
			String inci = inciListFilter.get(j);
			
			Map<String,String> tmp = jobInfoMap.get(inci);

			sb.append("<tr>");
			sb.append("<td>"+tmp.get("mainUnit")+"</td>");
			sb.append("<td>"+"<a href='' onclick=\"return cuibanNew('"+inci+"');\"><img src='../css/default/images/p_task_exp.gif'/></a>"+"</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		ret = sb.toString();

		ActionWriter aw = new ActionWriter(this.servletResponse);
		aw.writeAjax(ret);

		return null;
	}
	
	
}
