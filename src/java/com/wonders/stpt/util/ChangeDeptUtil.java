package com.wonders.stpt.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class ChangeDeptUtil {

	public static boolean portalService(String newUserId,String token){
		boolean suc=false;
		String result = "";
		String urlCa =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("urlCa").trim();
		String serverPath =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("serverPath").trim();
		String apiName =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("apiName").trim();
		String method=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("switchDeptMethod").trim();;
		String secret=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("secret").trim();
		String appName=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caAppName").trim();
		String urls = urlCa+serverPath+"/"+apiName;
		String dataParams="<?xml version=\"1.0\" encoding=\"utf-8\"?><params><newUserId>"+newUserId+"</newUserId></params>";
		String sign=getMD5(appName+token+method+secret);
		String dataType="json";
		String param = "";
		try {

			URL url = null;
			HttpURLConnection http = null;
			
			try {
				url = new URL(urls);
				http = (HttpURLConnection) url.openConnection();
				http.setDoInput(true);
				http.setDoOutput(true);
				http.setUseCaches(false);
				http.setConnectTimeout(50000);
				http.setReadTimeout(50000);
				http.setRequestMethod("POST");
				//http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");			
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.connect();
				param = "&appName=" + appName + "&token=" + token + "&method="+method + "&dataType=" + dataType+"&dataParams=" + dataParams + "&sign=" + sign;;
	         	OutputStreamWriter osw=new OutputStreamWriter(http.getOutputStream(),"utf-8");
	          	osw.write(param);
	          	osw.flush();
	          	osw.close();
	         			
				if (http.getResponseCode() == 200) {
					BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						result += inputLine;
					}
					in.close();
					try {			
						Document doc = DocumentHelper.parseText(result);
							
						Element root = doc.getRootElement();
						if(root.element("code").getTextTrim().equalsIgnoreCase("100")){
							suc=true;
						}
					}catch (Exception e) {
						System.out.println("err");
					} 
				}
			} catch (Exception e) {
				System.out.println("err");
			} finally {
				if (http != null) http.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suc;		
	}
	
	private static String getMD5(String value) {  
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


    private static String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    } 

}
