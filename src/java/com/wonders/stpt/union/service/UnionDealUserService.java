package com.wonders.stpt.union.service;

import java.util.List;

import com.wonders.stpt.union.entity.bo.UnionDealUser;

public interface UnionDealUserService {
	
	public UnionDealUser find(String id);
	
	public List<UnionDealUser> findByStatusAndStage(int status, int stage);

}
