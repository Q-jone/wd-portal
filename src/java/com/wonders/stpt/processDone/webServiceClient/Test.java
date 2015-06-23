package com.wonders.stpt.processDone.webServiceClient;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Test {

	/**
	 * @param args
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws DocumentException {
		// TODO Auto-generated method stub
		DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		SAXReader reader = new SAXReader();   
        Document   document = reader.read(new File("D://greataXml/greataProjectPlan.xml"));   
        //System.out.println(document.asXML());
		String returnXml = delegate.setDataInfo("greata", getMD5("greata2013!"), "2014-06-05", document.asXML());
		System.out.println(returnXml);
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
	
	// 将传递进来的字节数组转换成十六进制的字符串形式并返回  
    private static String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    }  

}
