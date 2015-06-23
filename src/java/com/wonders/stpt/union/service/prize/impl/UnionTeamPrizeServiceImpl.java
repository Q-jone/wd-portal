package com.wonders.stpt.union.service.prize.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.prize.UnionTeamPrizeService;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

@Repository("unionTeamPrizeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionTeamPrizeServiceImpl implements UnionTeamPrizeService{
	
	private UnionTDao<UnionTeamPrize> dao;
	public UnionTDao<UnionTeamPrize> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionTeamPrize> dao) {
		this.dao = dao;
	}

	@Override
	public UnionTeamPrize find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionTeamPrize.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionTeamPrize save(UnionTeamPrize bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			bo = dao.update(bo);
		}else{
			bo = dao.save(bo);
		}
		
		return bo;
	}
	
	public List<UnionTeamPrize> findByMatchId(String matchId){
		String hql = "from UnionTeamPrize pp left join fetch pp.prize p where pp.removed = 0 and p.matchId = ? order by pp.deptId";
		return this.dao.findByHql(hql, new Object[]{matchId});
	}
	
	public List<UnionTeamPrize> findByDeptOfMatch(String matchId, String deptId){
		String hql = "from UnionTeamPrize pp left join fetch pp.prize p where pp.removed = 0 and p.matchId = ? and pp.deptId = ? order by pp.rejected desc,pp.uUser,pp.uTime desc";
		return this.dao.findByHql(hql, new Object[]{matchId,deptId});
	}
	
	public List<UnionTeamPrize> findByApplyId(String applyId){
		String hql = "from UnionTeamPrize pp left join fetch pp.prize p where pp.removed = 0 and pp.applyId = ? order by pp.rejected desc,pp.uTime desc";
		return this.dao.findByHql(hql, new Object[]{applyId});
	}
	
	public int findByPrizeAndDeptId(String prizeId,String deptId){
		String hql = "select count(p.id) from UnionTeamPrize p where p.removed = 0 and p.prizeId = '"+prizeId+"' and p.deptId = '"+deptId+"'";
		return this.dao.countByHql(hql);
	}	
	
	public List<UnionTeamPrize> findByThemeId(String themeId){
		String hql = "from UnionTeamPrize pp left join fetch pp.prize p left join fetch p.match m where pp.removed = 0 and m.themeId = ? order by pp.deptId";
		return this.dao.findByHql(hql, new Object[]{themeId});
	}
	
	@Override
	public int logicDelete(String id){
		return this.dao.excuteHQLUpdate("update UnionTeamPrize set removed = 1 where id = ?", new Object[]{id});
	}
	
	public void updateRejected(String applyId, String rejected){
		this.dao.excuteHQLUpdate("update UnionTeamPrize set rejected = ? where applyId = ? and removed = 0", new Object[]{rejected,applyId});
	}
	
	public void eraseModified(String applyId){
		this.dao.excuteHQLUpdate("update UnionTeamPrize set modified = '0' where removed = 0 and modified = '1' and applyId = ?", new Object[]{applyId});
	}
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds){
		String ids = params.getTeamPrizeIds();
		if(StringUtils.isNotEmpty(ids)){
			String[] idArray = ids.split(",");
			List<UnionTeamPrize> toUpdateList = new ArrayList<UnionTeamPrize>();
			
			for(String id : idArray){
				UnionTeamPrize teamPrize = this.find(id);
				teamPrize.setRejected("1");
				toUpdateList.add(teamPrize);
				
				applyIds.add(teamPrize.getApplyId());
			}
			
			this.dao.saveOrUpdateAll(toUpdateList);			
		}
	}
	
	public List<UnionTeamPrize> findRejectedByApplyId(String applyId){
		String hql = "from UnionTeamPrize pp left join fetch pp.prize p where pp.removed = 0 and pp.applyId = ? and pp.rejected = '1'";
		return this.dao.findByHql(hql, new Object[]{applyId});
	}
	
	public String readExcel(List<File> files,List<String> fileuploadFileName,UnionFlowInfo params){
		UnionApplyMatch applyMatch = this.applyMatchService.find(params.getApplyId());
		String now = DateUtil.getNowTime();
		
		String result = "";
		boolean hasError = false;
		List<UnionTeamPrize> list = new ArrayList<UnionTeamPrize>();
		List<String> tmp = new ArrayList<String>();
        try {
        	for(int index = 0;index < files.size();index++){
        		File file = files.get(index);
	            Workbook book = Workbook.getWorkbook(file);
	            //get a Sheet object.    
	            Sheet[] sheets = book.getSheets();
	            //get 1st-Column,1st-Row content.  
	            for(Sheet sheet:sheets){
	            	for(int i=1;i<sheet.getRows();i++){	
	            		String errorInfo = "";
	            		UnionTeamPrize o = new UnionTeamPrize();
	            		Cell[] cells = sheet.getRow(i);  
		           		if(cells!=null && cells.length>=5){
		           			String name = StringUtil.getNotNullValueString(cells[0].getContents());
		           			if(!"".equals(name)){
		           				if(name.length() <= 200){
		           					o.setName(name);
		           				}else{
			           				errorInfo +="集体名称限200字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="集体名称不能为空，";
		           				hasError = true;
		           			}
		           			String persons = StringUtil.getNotNullValueString(cells[1].getContents());
		           			if(!"".equals(persons)){
		           				if(persons.length() <= 10){
		           			        Pattern p = Pattern.compile("^\\d+$");
		           			        if(p.matcher(persons).matches()){
		           			        	o.setPersons(Integer.valueOf(persons));
		           			        }else{
				           				errorInfo +="人数必须是整数，";
				           				hasError = true;		           			        	
		           			        }
		           				}else{
			           				errorInfo +="人数限10位以内整数，";
			           				hasError = true;
		           				}
		           			}else{
		           				errorInfo +="人数不能为空，";
		           				hasError = true;
		           			}		    
		           			String responsibilePerson = StringUtil.getNotNullValueString(cells[2].getContents());
		           			if(!"".equals(responsibilePerson)){
		           				if(responsibilePerson.length() <= 200){
		           					o.setResponsibilePerson(responsibilePerson);
		           				}else{
			           				errorInfo +="责任人限200字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="责任人不能为空，";
		           				hasError = true;		           				
		           			}
		           			String telephone = StringUtil.getNotNullValueString(cells[3].getContents());
		           			if(!"".equals(telephone)){
		           				if(telephone.length() <= 200){
		           					o.setTelephone(telephone);
		           				}else{
			           				errorInfo +="联系电话限200字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="联系电话不能为空，";
		           				hasError = true;		           				
		           			}
		           			String introduct = StringUtil.getNotNullValueString(cells[4].getContents());
		           			if(!"".equals(introduct)){
		           				if(introduct.length() <= 350){
		           					o.setIntroduct(introduct);
		           				}else{
			           				errorInfo +="简要事迹限350字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="简要事迹不能为空，";
		           				hasError = true;		           				
		           			}		           					           			
		            	}else{
		            		errorInfo +="字段没有全部填写，";
		            		hasError = true;
		            	}
		           		if(!"".equals(errorInfo)){
		           			result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;sheet-"+sheet.getName()+"：第"+(i+1)+"行的"+errorInfo+"<br>";		           			
		           		}else{
		           			o.setApplyId(params.getApplyId());
		           			o.setPrizeId(params.getPrizeId());
		           			o.setcUser(params.getLoginName());
		           			o.setuUser(params.getLoginName());
		           			o.setcUserName(params.getUserName());
		           			o.setcTime(now);
		           			o.setuTime(now);
		           			o.setDeptId(applyMatch.getDeptId());
		           			o.setDeptName(applyMatch.getDeptName());
		           			list.add(o);		           			
		           		}
	            	}
	            } 
	            book.close(); 	   
	        	if(hasError){
	        		result = fileuploadFileName.get(index) + " 导入失败：<br>" + result + "<br>";
	        	}else{
	        		int left = prizeService.quantityLeft(params.getPrizeId(), applyMatch.getDeptId());
	        		if(list.size() <= left){
		        		result = fileuploadFileName.get(index) + " 导入成功。<br>";
		        		this.dao.saveOrUpdateAll(list);	        			
	        		}else{
	        			result = fileuploadFileName.get(index) + " 导入失败：该文件共包含"+list.size()+"个奖项，超出了实际剩余名额"+left+"个。<br>";
	        		}
	        	}	            
        	}
        } catch (Exception e) {   
            e.printStackTrace();   
        }
        return result;
	}
	
	private UnionApplyMatchService applyMatchService;
	public UnionApplyMatchService getApplyMatchService() {
		return applyMatchService;
	}
	@Autowired(required=false)
	public void setApplyMatchService(@Qualifier("unionApplyMatchService")UnionApplyMatchService applyMatchService) {
		this.applyMatchService = applyMatchService;
	}
	
	private UnionPrizeService prizeService;
	public UnionPrizeService getPrizeService() {
		return prizeService;
	}
	@Autowired(required=false)
	public void setPrizeService(@Qualifier("unionPrizeService")UnionPrizeService prizeService) {
		this.prizeService = prizeService;
	}
}
