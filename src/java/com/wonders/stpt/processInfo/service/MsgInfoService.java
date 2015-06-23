package com.wonders.stpt.processInfo.service;

import java.util.List;


import com.wonders.stpt.processInfo.model.MsgCountVo;

public interface MsgInfoService {
	public List<MsgCountVo> query(String pname,String pincident, String orders);
}
