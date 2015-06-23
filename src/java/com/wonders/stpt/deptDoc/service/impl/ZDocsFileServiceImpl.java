package com.wonders.stpt.deptDoc.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.wonders.stpt.attachOld.service.FjshServiceOld;
import com.wonders.stpt.deptDoc.constants.DeptDocConstants;
import com.wonders.stpt.deptDoc.dao.DocsTDao;
import com.wonders.stpt.deptDoc.model.bo.ZDocsFile;
import com.wonders.stpt.deptDoc.model.vo.ZDocsFileListVo;
import com.wonders.stpt.deptDoc.service.ZDocsFileService;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.validFile.util.ValidFileUtil;

@Repository("zdocsFileService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ZDocsFileServiceImpl implements ZDocsFileService{
	
	public ZDocsFile findById(String fileId){
		return this.dao.find(fileId, ZDocsFile.class);
	}
	
	public List<ZDocsFile> findByCatalogId(String catalogId){
		return this.dao.findByHql("from ZDocsFile where parentSid = ? ", new Object[]{catalogId});
	}
	
	@Override
	public int removeById(String id) {
		// TODO Auto-generated method stub
		return dao.excuteHQLUpdate("update ZDocsFile set state = '0' where sid = ?", new Object[]{id});
	}
	
	public void uploadDocs(String attachGroup, String catalogId, String flag,String fileName, String keyword, String cUserId,String cUserName){
		//List<AttachFileOld> attachFiles = this.fjshService.findFilesByGroupId(attachGroup);
		//for(AttachFileOld attach : attachFiles){
			ZDocsFile newZf = new ZDocsFile();
			newZf.setParentSid(catalogId);
			newZf.setFlag(flag);
			newZf.setKeyword(keyword);
			newZf.setFilePath(DeptDocConstants.ATTACH_URL_DOWNLOAD+attachGroup);
			//newZf.setFileName(attach.getFileName());
			newZf.setFileName(fileName);
			newZf.setFileSize("");
			newZf.setExtName("");
			Date now = new Date();
			newZf.setCreateDate(now);
			newZf.setUpdateDate(now);
			newZf.setCreateUser("ST/"+cUserId);
			newZf.setCreateUserName(cUserName);
			newZf.setUpdateUser("ST/"+cUserId);
			newZf.setUpdateUserName(cUserName);
			newZf.setRefId(attachGroup);
			this.dao.save(newZf);
		//}
	}
	
	public void copyToFolders(String fileIds, String destDirIds, String cUserId,String cUserName,boolean move){
		List<ZDocsFile> zfiles = this.findByIds(fileIds.split(","));
		String[] destDirArr = destDirIds.split(",");
		for(ZDocsFile zf : zfiles){
			for(String destDir : destDirArr){
				ZDocsFile newZf = new ZDocsFile();
				newZf.setParentSid(destDir);
				newZf.setKeyword(zf.getKeyword());
				newZf.setFilePath(zf.getFilePath());
				newZf.setFileName(zf.getFileName());
				newZf.setFileSize(zf.getFileSize());
				newZf.setExtName(zf.getExtName());
				Date now = new Date();
				newZf.setCreateDate(now);
				newZf.setUpdateDate(now);
				newZf.setCreateUser("ST/"+cUserId);
				newZf.setCreateUserName(cUserName);
				newZf.setUpdateUser("ST/"+cUserId);
				newZf.setUpdateUserName(cUserName);
				newZf.setFlag(zf.getFlag());
				newZf.setRefId(zf.getRefId());
				this.dao.save(newZf);				
			}
			if(move){
				zf.setState("0");
				this.dao.save(zf);
			}
		}
	}
	
	@Override
	public int updateDocInfo(String id, String fileName,String keyword, String uUserId, String uUserName) {
		ZDocsFile zf = this.findById(id);
		zf.setFileName(fileName);
		zf.setKeyword(keyword);
		zf.setUpdateDate(new Date());
		zf.setUpdateUser("ST/"+uUserId);
		zf.setUpdateUserName(uUserName);
		this.dao.save(zf);
		// TODO Auto-generated method stub
		return 1;
	}
	
	private List<ZDocsFile> findByIds(final String[] ids){
        return dao.getHibernateTemplate().execute(new HibernateCallback<List<ZDocsFile>>() {  
            public List<ZDocsFile> doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createQuery(" from ZDocsFile f where f.id in (:ids) ");
                q.setParameterList("ids", ids);
                return q.list();
            }  
        });  
	}
	
	public PageResultSet<Map<String,Object>> list(ZDocsFileListVo vo){
		Map<String,Object> map = ValidFileUtil.generateFilterMap(vo);
		List<Object> src = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		String order = " order by update_date desc ";
		if(vo.leader || DeptDocConstants.CATELOG_DOC_SHARE.equals(vo.getFolder().getFlag())){
			sql.append("select * from Z_DOCS_FILE t where state = '1' ");
		}else{
			sql.append("select distinct t.* from Z_DOCS_FILE t,z_docs_right r  where  t.sid=r.rightid and r.type='1' and t.state='1' and r.empid ='");
			sql.append(vo.getLoginName());
			sql.append("' ");
		}
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if(entry.getKey().indexOf("_equal") > 0){
				sql.append(" and t."+entry.getKey().replace("_equal", "")+" = ? ");
				src.add(entry.getValue());
			}else if(entry.getKey().indexOf("_like") > 0){
				sql.append(" and t."+entry.getKey().
						replace("_like", "")
						+" like ? escape '/'");
				src.add("%"+entry.getValue().toString().replaceAll("%","/%").replaceAll("_","/_")+"%");
			}else if(entry.getKey().indexOf("_startDate") > 0){
					sql.append(" and t."+entry.getKey().replace("_startDate", "")+" >= to_date(?,'yyyy-MM-dd') ");
					src.add(entry.getValue());
			}else if(entry.getKey().indexOf("_endDate") > 0){
				sql.append(" and t."+entry.getKey().replace("_endDate", "")+" <= to_date(?,'yyyy-MM-dd HH24:MI:SS') ");
				src.add(entry.getValue()+" 23:59:59");
			}else if("order".equals(entry.getKey())){
				order = " order by t." + entry.getValue();
			}
		}
		sql.append(order);
		PageResultSet<Map<String,Object>> result = new PageResultSet<Map<String,Object>>();
		Query query = this.dao.createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for(int i=0;i<src.size();i++){
			query.setParameter(i, src.get(i));
		}
		int totalRows = query.list().size();
		PageInfo pageinfo = new PageInfo(totalRows, ValidFileUtil.getPageSize(vo.pageSize), ValidFileUtil.getPage(vo.page));	
		query.setFirstResult(pageinfo.getBeginIndex());
		query.setMaxResults(pageinfo.getPageSize());
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		result.setList(list);
		result.setPageInfo(pageinfo);
		return result;
	}
	
	private FjshServiceOld fjshService;
	public FjshServiceOld getFjshServiceOld() {
		return fjshService;
	}
	
	@Autowired(required=false)
	public void setFjshServiceOld(@Qualifier("fjshServiceOld")FjshServiceOld fjshService) {
		this.fjshService = fjshService;
	}
	
	private DocsTDao<ZDocsFile> dao;
	public DocsTDao<ZDocsFile> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("docsTDao")DocsTDao<ZDocsFile> dao) {
		this.dao = dao;
	}
}
