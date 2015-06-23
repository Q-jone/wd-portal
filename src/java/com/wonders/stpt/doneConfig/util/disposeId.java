package com.wonders.stpt.doneConfig.util;

import java.util.ArrayList;
import java.util.List;

public class disposeId {
	/**
	 * 处理用户所属主键
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List disId(List<String> list)throws Exception{
		ArrayList idList=new ArrayList();
		String id;
		for(int i=0;i<list.size();i++){
			id=list.get(i);
			if(id.indexOf("_")>=0)//用户所属记录id
				idList.add(id.substring(id.indexOf("_")+1, id.length()));
		}
		return idList;
	}
	/**
	 * 类型中默认记录（refid为记录主键）
	 * @param list
	 * @return
	 */
	public List defaultidbytype(List<String> list){
		ArrayList idList=new ArrayList();
		String id;
		for(int i=0;i<list.size();i++){
			id=list.get(i);
			if(id.indexOf("_")<0)//默认记录id
				idList.add(id);
		}
		return idList;
	}
	
	public static void main(String[] args){
		String str="12345_67";
		if(str.indexOf("_")>=0)
			str=str.substring(str.indexOf("_")+1, str.length());
		System.out.println(str);
	}
}
