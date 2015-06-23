package com.wonders.stpt.union.service.prize.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.DateCell;
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
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.prize.UnionPersonalPrizeService;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

@Repository("unionPersonalPrizeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionPersonalPrizeServiceImpl implements UnionPersonalPrizeService{
	
	private List<String> genderDics = Arrays.asList(new String[]{"男","女"});
	private List<String> politicalDics = Arrays.asList(new String[]{"中共党员","中共预备党员","民主党派","共青团员","群众"});
	
	private UnionTDao<UnionPersonalPrize> dao;
	public UnionTDao<UnionPersonalPrize> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionPersonalPrize> dao) {
		this.dao = dao;
	}

	@Override
	public UnionPersonalPrize find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionPersonalPrize.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionPersonalPrize save(UnionPersonalPrize bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			bo = dao.update(bo);
		}else{
			bo = dao.save(bo);
		}
		
		return bo;
	}
	
	public List<UnionPersonalPrize> findByMatchId(String matchId){
		String hql = "from UnionPersonalPrize pp left join fetch pp.prize p where pp.removed = 0 and p.matchId = ? order by pp.deptId";
		return this.dao.findByHql(hql, new Object[]{matchId});
	}
	
	public List<UnionPersonalPrize> findByThemeId(String themeId){
		String hql = "from UnionPersonalPrize pp left join fetch pp.prize p left join fetch p.match m where pp.removed = 0 and m.themeId = ?  order by pp.deptId";
		return this.dao.findByHql(hql, new Object[]{themeId});
	}
	
	public List<UnionPersonalPrize> findByDeptOfMatch(String matchId, String deptId){
		String hql = "from UnionPersonalPrize pp left join fetch pp.prize p where pp.removed = 0 and p.matchId = ? and pp.deptId = ? order by pp.rejected desc,pp.uUser,pp.uTime desc";
		return this.dao.findByHql(hql, new Object[]{matchId,deptId});
	}

	public List<UnionPersonalPrize> findByApplyId(String applyId){
		String hql = "from UnionPersonalPrize pp left join fetch pp.prize p where pp.removed = 0 and pp.applyId = ? order by pp.rejected desc,pp.uTime desc";
		return this.dao.findByHql(hql, new Object[]{applyId});
	}
	
	public List<UnionPersonalPrize> findRejectedByApplyId(String applyId){
		String hql = "from UnionPersonalPrize pp left join fetch pp.prize p where pp.removed = 0 and pp.applyId = ? and pp.rejected = '1'";
		return this.dao.findByHql(hql, new Object[]{applyId});
	}
	
	public int findByPrizeAndDeptId(String prizeId,String deptId){
		String hql = "select count(p.id) from UnionPersonalPrize p where p.removed = 0 and p.prizeId = '"+prizeId+"' and p.deptId = '"+deptId+"'";
		return this.dao.countByHql(hql);
	}	
	
	@Override
	public int logicDelete(String id){
		return this.dao.excuteHQLUpdate("update UnionPersonalPrize set removed = 1 where id = ?", new Object[]{id});
	}
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds){
		String ids = params.getPersonalPrizeIds();
		if(StringUtils.isNotEmpty(ids)){
			String[] idArray = ids.split(",");
			List<UnionPersonalPrize> toUpdateList = new ArrayList<UnionPersonalPrize>();
			
			for(String id : idArray){
				UnionPersonalPrize personalPrize = this.find(id);
				personalPrize.setRejected("1");
				toUpdateList.add(personalPrize);
				
				applyIds.add(personalPrize.getApplyId());
			}
			
			this.dao.saveOrUpdateAll(toUpdateList);			
		}
	}
	
	public String readExcel(List<File> files,List<String> fileuploadFileName,UnionFlowInfo params){
		UnionApplyMatch applyMatch = this.applyMatchService.find(params.getApplyId());
		String now = DateUtil.getNowTime();
		
		String result = "";
		boolean hasError = false;
		List<UnionPersonalPrize> list = new ArrayList<UnionPersonalPrize>();
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
	            		UnionPersonalPrize o = new UnionPersonalPrize();
	            		Cell[] cells = sheet.getRow(i);  
		           		if(cells!=null && cells.length>=7){
		           			String name = StringUtil.getNotNullValueString(cells[0].getContents());
		           			if(!"".equals(name)){
		           				if(name.length() <= 200){
		           					o.setName(name);
		           				}else{
			           				errorInfo +="姓名限200字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="姓名不能为空，";
		           				hasError = true;
		           			}
		           			String gender = StringUtil.getNotNullValueString(cells[1].getContents());
		           			if(!"".equals(gender)){
		           				if(gender.length() <= 2){
		           					if(!genderDics.contains(gender)){
		           						errorInfo +="性别必须为："+genderDics;
		           						hasError = true;	
		           					}else{
		           						o.setGender(gender);		           						
		           					}
		           				}else{
			           				errorInfo +="性别限2字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="性别不能为空，";
		           				hasError = true;
		           			}		    
		           			if(cells[2] != null){
			           			if(cells[2] instanceof DateCell){
			           				DateCell dateCell = (DateCell)cells[2];
				           			String brithday = DateUtil.getDateStr(dateCell.getDate(), DateUtil.DATE_FORMAT);
				           			o.setBrithday(brithday);		           				
			           			}else{
			           				errorInfo +="生日必须为日期格式，";
			           				hasError = true;		           				
			           			}		           				
		           			}else{
		           				errorInfo +="生日不能为空，";
		           				hasError = true;		           				
		           			}
		           			String political = StringUtil.getNotNullValueString(cells[3].getContents());
		           			if(!"".equals(political)){
		           				if(political.length() <= 50){
		           					if(!politicalDics.contains(political)){
		           						errorInfo +="政治面貌必须为："+politicalDics;
		           						hasError = true;	
		           					}else{
		           						o.setPolitical(political);		           						
		           					}
		           				}else{
			           				errorInfo +="政治面貌限50字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="政治面貌不能为空，";
		           				hasError = true;		           				
		           			}
		           			String position = StringUtil.getNotNullValueString(cells[4].getContents());
		           			if(!"".equals(position)){
		           				if(position.length() <= 100){
		           					o.setPosition(position);
		           				}else{
			           				errorInfo +="职务限100字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="职务不能为空，";
		           				hasError = true;		           				
		           			}		        
		           			String prizeInfo = StringUtil.getNotNullValueString(cells[5].getContents());
		           			if(!"".equals(prizeInfo)){
		           				if(prizeInfo.length() <= 300){
		           					o.setPrizeInfo(prizeInfo);
		           				}else{
			           				errorInfo +="曾获荣誉限300字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="曾获荣誉不能为空，";
		           				hasError = true;		           				
		           			}		           			
		           			String introduct = StringUtil.getNotNullValueString(cells[6].getContents());
		           			if(!"".equals(introduct)){
		           				if(introduct.length() <= 400){
		           					o.setIntroduct(introduct);
		           				}else{
			           				errorInfo +="主要事迹限400字，";
			           				hasError = true;		           					
		           				}
		           			}else{
		           				errorInfo +="主要事迹不能为空，";
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
	
	public void updateRejected(String applyId, String rejected){
		this.dao.excuteHQLUpdate("update UnionPersonalPrize set rejected = ? where applyId = ? and removed = 0", new Object[]{rejected,applyId});
	}
	
	public void eraseModified(String applyId){
		this.dao.excuteHQLUpdate("update UnionPersonalPrize set modified = '0' where removed = 0 and modified = '1' and applyId = ?", new Object[]{applyId});
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
