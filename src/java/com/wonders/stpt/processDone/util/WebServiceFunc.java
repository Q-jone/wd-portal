package com.wonders.stpt.processDone.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.processDone.webServiceClient.DataImportAPIDelegate;
import com.wonders.stpt.processDone.webServiceClient.DataImportAPIService;

public class WebServiceFunc {
	private String userName="wonder";
	private String pwd="wonder2013!";
	
	public List<String> setDataInfo(List<Map<String,String>> list){
		DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		String result = "";
		List<String> resultList = new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				result = delegate.setDataInfo(userName, getMD5(pwd), list.get(i).get("date"), list.get(i).get("content")); 
				resultList.add(result);
			}
		}
		return resultList;
	}
	
	private String getMD5(String value) {  
        String result = null;  
        try{  
            byte[] valueByte = value.getBytes();  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(valueByte);  
            result = toHex(md.digest());  
        }catch(NoSuchAlgorithmException e1){  
            e1.printStackTrace();  
        }  
        return result;  
    }  
    // 将传递进来的字节数组转换成十六进制的字符串形式并返回  
    private String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    }  
    
    public List<String> setDataInfo(List<Map<String,String>> list,String userName,String pwd){
		DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		String result = "";
		List<String> resultList = new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				result = delegate.setDataInfo(userName, getMD5(pwd), list.get(i).get("date"), list.get(i).get("content")); 
				resultList.add(result);
			}
		}
		return resultList;
	}
}
