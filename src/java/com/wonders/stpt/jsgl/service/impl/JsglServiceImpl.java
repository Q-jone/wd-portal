package com.wonders.stpt.jsgl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.jsgl.dao.JsglDao;
import com.wonders.stpt.jsgl.service.JsglService;

@Repository("jsglService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class JsglServiceImpl implements JsglService{
	@Autowired
	private JsglDao jsglDao;
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		return jsglDao.findBySql(sql, src);
	}
}
