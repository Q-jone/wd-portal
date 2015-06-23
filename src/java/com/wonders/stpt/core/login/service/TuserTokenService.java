/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.core.login.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.core.login.entity.bo.TuserToken;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface TuserTokenService {
	/**
	 * 删除实体对象
	 * 
	 * @param tuserToken
	 */
	public void deleteTuserToken(TuserToken tuserToken);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public TuserToken findTuserTokenById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param tuserToken
	 */
	public void addTuserToken(TuserToken tuserToken);

	/**
	 * 更新数据到数据库
	 * 
	 * @param tuserToken
	 *            实体
	 */
	public void updateTuserToken(TuserToken tuserToken);

	
	public List<TuserToken> findTuserTokenByIdRemoved(String id);
}
