package com.wonders.stpt.standardWork.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.standardWork.dao.StandardWorkDao;
import com.wonders.stpt.standardWork.model.bo.StandardWorkType;
import com.wonders.stpt.standardWork.service.StandardWorkService;

@Repository("standardWorkService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class StandardWorkServiceImpl implements StandardWorkService{
	@Autowired
	private StandardWorkDao standardWorkDao;
	
	@SuppressWarnings("unchecked")
	
	@Override
	public List<Object[]> findEventsTypeList(){
		return standardWorkDao.findEventsTypeList();
	}
	
	@Override
	public String findEventsTypeByTree(String openId){
		List<Object[]> list = standardWorkDao.findEventsTypeList();
		List<String> openIdList = getAllOpenId(openId);
		
		String str = "";
		String data_str = "";
		if(list!=null && list.size()>0 && openIdList!=null && openIdList.size()>0){
			str += "{\"data\":[{\"state\":\"open\",";
			for(int i=0;i<list.size();i++){
				data_str = "\"data\":\""+list.get(i)[1]+"\",\"metadata\":{\"id\":\""+list.get(i)[0]+"\",\"name\":\""+list.get(i)[1]+"\",\"parentId\":\""+list.get(i)[2]+"\",\"parentTypeName\":\""+list.get(i)[5]+"\",\"level\":\""+list.get(i)[4]+"\",\"order\":"+list.get(i)[3]+"}";
				
				if(openIdList!=null && openIdList.size()>1){
					for(int j=0;j<openIdList.size();j++){
						if(openIdList.get(j).equals(list.get(i)[0])){
							data_str = "\"state\":\"open\","+data_str;
							break;
						}
					}
				}else{
					if(Integer.valueOf(list.get(i)[4].toString())<=2){//默认展开层数
						data_str = "\"state\":\"open\","+data_str;
					}
				}
				
				if(i==0){//根节点
					str += data_str;
				}else if(String.valueOf(list.get(i)[4]).equals(String.valueOf(list.get(i-1)[4]))){//同级节点
					str += "},{"+data_str;
				}else if(Integer.parseInt(String.valueOf(list.get(i)[4]))>Integer.parseInt(String.valueOf(list.get(i-1)[4]))){//下级节点
					str += ",\"children\":[{"+data_str;
				}else{//上级同辈节点
					for(int j=0;j<(Integer.parseInt(String.valueOf(list.get(i-1)[4]))-Integer.parseInt(String.valueOf(list.get(i)[4])));j++){
						str += "}]";
					}
					str += "},{"+data_str;
				}
			}
			for(int j=0;j<Integer.parseInt(String.valueOf(list.get(list.size()-1)[4]));j++){
				str += "}]";
			}
			str += "}]}";
		}
		
		return str;
	}
	
	public List<Object[]> getFileListByPage(int startRow, int pageSize,String typeId, String searchParam){
		return standardWorkDao.getFileListByPage(startRow, pageSize, typeId, searchParam);
	}
	
	public int getFileListCount(String typeId, String searchParam){
		return standardWorkDao.getFileListCount(typeId, searchParam);
	}
	
	public String getParentNodes(String id){
		String nodes = "";
		List<String> list = getAllOpenId(id);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if(i>0){
					nodes += "\\";
				}
				nodes += standardWorkDao.getTypeNameById(list.get(i));
			}
		}
		return nodes;
	}
	
	/**
	 * 取得所有祖辈节点（包括自己）
	 * @param openId
	 * @return
	 */
	private List<String> getAllOpenId(String openId){
		List<String> openIdList = new ArrayList<String>();
		if(openId!=null&&!"".equals(openId)){
			StandardWorkType bo = (StandardWorkType)standardWorkDao.load(StandardWorkType.class, openId);
			if(bo!=null&&bo.getParentId()!=null){
				if(!"0".equals(bo.getParentId())){
					openIdList = getAllOpenId(bo.getParentId());
				}
				openIdList.add(openId);
			}
		}
		return openIdList;
	}
	
	public String getFilePathById(String id){
		return standardWorkDao.getFilePathById(id);
	}
	
	public Object load(Class entityClass,String id){
		return standardWorkDao.load(entityClass, id);
	}
	
	public List<Object[]> getContentListByPage(int startRow, int pageSize){
		return standardWorkDao.getContentListByPage(startRow, pageSize);
	}
	
	public int getContentListCount(){
		return standardWorkDao.getContentListCount();
	}
}
