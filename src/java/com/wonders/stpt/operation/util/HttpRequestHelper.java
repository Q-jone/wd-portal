package com.wonders.stpt.operation.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
/**
 * 
 */


/** 
 * @ClassName: Msg 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-7 上午9:56:56 
 *  
 */
public class HttpRequestHelper {
	

	/**调用portal外部接口方法
	 * @param method
	 * @param paramsXml
	 * @return
	 */
	public static String portalService(String mobile,String urls) {
		
		String result = "";
		//String msg  = "您好！机关工会为您办理了“上海市退休职工住院补充医疗互助保障”，费用由集团统筹。具体保障内容等有关说明已通过信函、快递等形式寄给您，请注意查收。另，值此端午佳节来临之际，祝您节目快乐，身体健康，阖家幸福！申通地铁集团机关工会";
		//String msg  = "周四在文三路马腾路东部软件园，创新大厦B座1F，进门左手边，交通物流公共信息平台。杭州东站下车的话出租车或者179路（到站：文三路口）。城站就打车吧。";
		try {
			
			//String urls = "http://211.136.163.68:8000/httpserver?enterpriseid=00323&accountid=666&pswd=4Y3j78z2&mobs="+mobile+"&msg="+msg;
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
				// http.setRequestProperty("Content-Type",
				// "text/xml; charset=UTF-8");
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.connect();
				String param = "&data="+ mobile;  
				  
				    OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");  
				    osw.write(param);  
				    osw.flush();  
				    osw.close();  
				  
				    
				if (http.getResponseCode() == 200) {
					BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						result += inputLine;
					}
					in.close();
					//result = "["+result+"]";
				}
			} catch (Exception e) {
				System.out.println("err");
				e.printStackTrace();
			} finally {
				if (http != null) http.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
		
	
	public static void main(String[] args) throws IOException{
/*		String s = "http://10.1.48.16:8080/workflow/send-tDocSend/toFormPage.action?modelName=%E6%96%B0%E5%8F%91%E6%96%87%E6%B5%81%E7%A8%8B&incidentNo=65&processName=%E6%96%B0%E5%8F%91%E6%96%87%E6%B5%81%E7%A8%8B&pinstanceId=65&taskUserName=ST/G001000001612549&stepName=%E5%8F%91%E6%96%87%E9%80%9A%E7%9F%A5&taskId=12261064757e7498937e6b29ea80ca&taskuser=ST/G001000001612549&codeId=13";
		String ms = "{\"app\": \"standardWork\",\"type\": 0,"
			+ "\"occurTime\": \"2013-11-14 11:22:02\",\"title\": \"-------流程标题-------\","
			+ "\"loginName\": \"ST/G01008000311\",\"status\": 0,\"removed\": 0,"
			+ " \"typename\": \"流程名称11\","
			+ "\"url\": \""+URLEncoder.encode(s,"UTF-8")+"\","
			+ "\"pname\": \"主流程名称\",\"pincident\": 1,"
			+ "\"cname\": \"子流程实例号\",\"cincident\": 1,"
			+ "\"stepName\": \"当前步骤\","
			+ "\"initiator\": \"ST/G01008000311\"}";
		String id = "8a81a97c441bba8c01441bbf2bde0002";
		List<String> list = new ArrayList<String>();
		//String[] mmm = ms.split(",");
		String url = "http://10.1.14.20:8088/workflowController/service/todo/addTask";
		System.out.println(portalService(ms,url));
		
		String g = " 的发顺丰     发的是    发的   ";
		System.out.println(g);
		System.out.println(g.replaceAll("\\s+", ""));
		
		%E8%BF%90%E8%90%A5%E5%8F%91%E6%96%87%E4%BC%A0%E9%98%85
		
		%E4%B8%80%E5%8F%B7%E7%BA%BF
		*/
		
		
/*		Map param = new HashMap();
		param.put("app", "OP_DOCSEND");
		param.put("type", "0");
		param.put("occurTime", "2014-08-15 14:22:02");
		param.put("title", "运营发文-test-传阅");
		param.put("loginName", "ST/G001000000282516");
		param.put("status", "0");
		param.put("removed", "0");
		param.put("typename", "运营发文");
		param.put("url", URLEncoder.encode("http://10.1.48.16:8080/workflow/dept-passMain/add.action?modelName=%E8%BF%90%E8%90%A5%E5%8F%91%E6%96%87%E4%BC%A0%E9%98%85&attach={FB94D0AD-7D98-DDAC-805E-391F05C34620}&title=%E8%BF%90%E8%90%A5%E5%8F%91%E6%96%87%E4%BC%A0%E9%98%85&mainId=8a818e9447cf1c180147d79d740d05df&mainTable=OP_DOC_SEND&todoId=8a81b0e544c5eef50144d305b1c30145&remark=%E4%B8%80%E5%8F%B7%E7%BA%BF&taskuser=ST/G001000000282516", "UTF-8"));
		param.put("pname", "运营发文");
		param.put("pincident", "1");
		param.put("cname", "子流程实例号");
		param.put("cincident", "1");
		param.put("stepName", "运营发文传阅");
		param.put("initiator", "ST/G001000000282516");
		System.out.println(portalService(JSONObject.fromObject(param).toString(), "http://10.1.14.20:8088/workflowController/service/todo/addTask"));*/
		
		
		Map param = new HashMap();
		param.put("app", "OP_DOCSEND");
		param.put("type", "0");
		param.put("occurTime", "2014-08-15 14:38:02");
		param.put("title", "运营发文-收发员-传阅");
		param.put("loginName", "ST/G010138000022517");
		param.put("status", "0");
		param.put("removed", "0");
		param.put("typename", "运营发文");
		param.put("url", URLEncoder.encode("http://10.1.48.16:8080/workflow/dept-passMain/sign.action?modelName=%E8%BF%90%E8%90%A5%E5%8F%91%E6%96%87%E4%BC%A0%E9%98%85&attach={FB94D0AD-7D98-DDAC-805E-391F05C34620}&title=%E8%BF%90%E8%90%A5%E5%8F%91%E6%96%87%E4%BC%A0%E9%98%85&mainId=8a818e9447cf1c180147d79d740d05df&mainTable=OP_DOC_SEND&todoId=8a81b0e544c5eef50144d305b1c30145&remark=%E4%B8%80%E5%8F%B7%E7%BA%BF&taskuser=ST/G010138000022517", "UTF-8"));
		param.put("pname", "运营发文");
		param.put("pincident", "1");
		param.put("cname", "子流程实例号");
		param.put("cincident", "1");
		param.put("stepName", "运营发文传阅");
		param.put("initiator", "ST/G010138000022517");
		System.out.println(portalService(JSONObject.fromObject(param).toString(), "http://10.1.14.20:8088/workflowController/service/todo/addTask"));
	}
}
