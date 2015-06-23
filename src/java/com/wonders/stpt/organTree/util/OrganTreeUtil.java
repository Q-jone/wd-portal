package com.wonders.stpt.organTree.util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wonders.stpt.organTree.service.TorganRelationService;

@Component("organTreeUtil")
public class OrganTreeUtil {
	private static HashMap<String,String> receiversMap = null;
	private static HashMap<String,String> configersMap = null;
	private static TorganRelationService torganRelationService;
	public static TorganRelationService getTorganRelationService() {
		return torganRelationService;
	}
	@Autowired(required=false)
	public void setTorganRelationService(
			@Qualifier("torganRelationService")TorganRelationService torganRelationService) {
		OrganTreeUtil.torganRelationService = torganRelationService;
	}
	
	static {
		init();
	}
	
	public static void init(){
		if(receiversMap==null){
			receiversMap = new HashMap<String, String>();
			//receiversMap = (HashMap<String, String>) torganRelationService.getReceivers();
		}
		if(configersMap==null){
			configersMap = new HashMap<String, String>();
			//configersMap = (HashMap<String, String>) torganRelationService.getConfigers();
		}
	}
	
	public static HashMap<String,String> getReceiversMap(){
		synchronized(receiversMap){
			if(receiversMap==null||receiversMap.size()==0){
				receiversMap = (HashMap<String, String>) torganRelationService.getReceivers();
			}
		}
		return receiversMap;
	}
	
	public static HashMap<String,String> getConfigersMap(){
		synchronized(configersMap){
			if(configersMap==null||configersMap.size()==0){
				configersMap = (HashMap<String, String>) torganRelationService.getConfigers();
			}
		}
		return configersMap;
	}
	
	public static void refresh(){
		receiversMap = null;
		configersMap = null;
	}
	
}
