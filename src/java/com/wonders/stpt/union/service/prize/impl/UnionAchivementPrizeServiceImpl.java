package com.wonders.stpt.union.service.prize.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.prize.UnionAchivementPrizeService;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

@Repository("unionAchivementPrizeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionAchivementPrizeServiceImpl implements UnionAchivementPrizeService{
	
	private List<String> moduleDics = Arrays.asList(new String[]{"安全保障","服务品牌","团队建设"});
	
	private UnionTDao<UnionAchivementPrize> dao;
	public UnionTDao<UnionAchivementPrize> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionAchivementPrize> dao) {
		this.dao = dao;
	}

	@Override
	public UnionAchivementPrize find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionAchivementPrize.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionAchivementPrize save(UnionAchivementPrize bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			bo = dao.update(bo);
		}else{
			bo = dao.save(bo);
		}
		
		return bo;
	}
	
	public List<UnionAchivementPrize> findByMatchId(String matchId){
		String hql = "from UnionAchivementPrize pp left join fetch pp.prize p where pp.removed = 0 and p.matchId = ? order by pp.deptId";
		return this.dao.findByHql(hql, new Object[]{matchId});
	}
	
	public List<UnionAchivementPrize> findByDeptOfMatch(String matchId, String deptId){
		String hql = "from UnionAchivementPrize pp left join fetch pp.prize p where pp.removed = 0 and p.matchId = ? and pp.deptId = ? order by pp.rejected desc,pp.uUser,pp.uTime desc";
		return this.dao.findByHql(hql, new Object[]{matchId,deptId});
	}
	
	public List<UnionAchivementPrize> findByApplyId(String applyId){
		String hql = "from UnionAchivementPrize pp left join fetch pp.prize p where pp.removed = 0 and pp.applyId = ? order by pp.rejected desc,pp.uTime desc";
		return this.dao.findByHql(hql, new Object[]{applyId});
	}
	
	public int findByPrizeAndDeptId(String prizeId,String deptId){
		String hql = "select count(p.id) from UnionAchivementPrize p where p.removed = 0 and p.prizeId = '"+prizeId+"' and p.deptId = '"+deptId+"'";
		return this.dao.countByHql(hql);
	}	
	
	public List<UnionAchivementPrize> findByThemeId(String themeId){
		String hql = "from UnionAchivementPrize pp left join fetch pp.prize p left join fetch p.match m where pp.removed = 0 and m.themeId = ? order by pp.deptId";
		return this.dao.findByHql(hql, new Object[]{themeId});
	}
	
	@Override
	public int logicDelete(String id){
		return this.dao.excuteHQLUpdate("update UnionAchivementPrize set removed = 1 where id = ?", new Object[]{id});
	}
	
	public void updateRejected(String applyId, String rejected){
		this.dao.excuteHQLUpdate("update UnionAchivementPrize set rejected = ? where applyId = ? and removed = 0", new Object[]{rejected,applyId});
	}
	
	public void eraseModified(String applyId){
		this.dao.excuteHQLUpdate("update UnionAchivementPrize set modified = '0' where removed = 0 and modified = '1' and applyId = ?", new Object[]{applyId});
	}
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds){
		String ids = params.getAchivementPrizeIds();
		if(StringUtils.isNotEmpty(ids)){
			String[] idArray = ids.split(",");
			List<UnionAchivementPrize> toUpdateList = new ArrayList<UnionAchivementPrize>();
			
			for(String id : idArray){
				UnionAchivementPrize achivementPrize = this.find(id);
				achivementPrize.setRejected("1");
				toUpdateList.add(achivementPrize);
				
				applyIds.add(achivementPrize.getApplyId());
			}
			
			this.dao.saveOrUpdateAll(toUpdateList);			
		}
	}
	
	public List<UnionAchivementPrize> findRejectedByApplyId(String applyId){
		String hql = "from UnionAchivementPrize pp left join fetch pp.prize p where pp.removed = 0 and pp.applyId = ? and pp.rejected = '1'";
		return this.dao.findByHql(hql, new Object[]{applyId});
	}
	
	public String readExcel(List<File> files,List<String> fileuploadFileName,UnionFlowInfo params){
		UnionApplyMatch applyMatch = this.applyMatchService.find(params.getApplyId());
		String now = DateUtil.getNowTime();
		
		String result = "";
		boolean hasError = false;
		List<UnionAchivementPrize> list = new ArrayList<UnionAchivementPrize>();
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
	            		UnionAchivementPrize o = new UnionAchivementPrize();
	            		Cell[] cells = sheet.getRow(i);  
		           		if(cells!=null && cells.length>=6){
		           			String module = StringUtil.getNotNullValueString(cells[0].getContents());
		           			if(!"".equals(module)){
		           				if(module.length() <= 100){
		           					if(!moduleDics.contains(module)){
		           						errorInfo +="参赛板块必须为："+moduleDics;
		           						hasError = true;	
		           					}else{
		           						o.setModule(module);
		           					}
		           				}else{
			           				errorInfo +="参赛板块限100字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="参赛板块不能为空，";
		           				hasError = true;
		           			}
		           			String prjectName = StringUtil.getNotNullValueString(cells[1].getContents());
		           			if(!"".equals(prjectName)){
		           				if(prjectName.length() <= 200){
		           					o.setPrjectName(prjectName);
		           				}else{
			           				errorInfo +="项目名称限200字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="项目名称不能为空，";
		           				hasError = true;
		           			}		    
		           			String groupName = StringUtil.getNotNullValueString(cells[2].getContents());
		           			if(!"".equals(groupName)){
		           				if(groupName.length() <= 200){
		           					o.setGroupName(groupName);
		           				}else{
			           				errorInfo +="申报集体全称限200字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="申报集体全称不能为空，";
		           				hasError = true;		           				
		           			}
		           			String responsibilePerson = StringUtil.getNotNullValueString(cells[3].getContents());
		           			if(!"".equals(responsibilePerson)){
		           				if(responsibilePerson.length() <= 50){
		           					o.setResponsibilePerson(responsibilePerson);
		           				}else{
			           				errorInfo +="负责人限50字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="负责人不能为空，";
		           				hasError = true;		           				
		           			}
		           			String telephone = StringUtil.getNotNullValueString(cells[4].getContents());
		           			if(!"".equals(telephone)){
		           				if(telephone.length() <= 50){
		           					o.setTelephone(telephone);
		           				}else{
			           				errorInfo +="联系电话限50字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="联系电话不能为空，";
		           				hasError = true;		           				
		           			}		                			
		           			String introduct = StringUtil.getNotNullValueString(cells[5].getContents());
		           			if(!"".equals(introduct)){
		           				if(introduct.length() <= 300){
		           					o.setIntroduct(introduct);
		           				}else{
			           				errorInfo +="项目简介限300字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="项目简介不能为空，";
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
