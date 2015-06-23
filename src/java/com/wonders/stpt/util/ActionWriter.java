package com.wonders.stpt.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wonders.stpt.todoItem.model.bo.TodoItem;

public class ActionWriter {
	private HttpServletResponse response;
	private Gson gsonAnnotation = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	private Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
	
	public ActionWriter(HttpServletResponse response){
		this.response = response;
	}
	
	/**JSON输出(ajax)
	 * @param response
	 * @param obj
	 */
	public void writeJsonWithAnnotation(Object obj){
		if(response==null) return;
		Writer w = null;
		
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			w = response.getWriter();
			
			String ret = gsonAnnotation.toJson(obj);	
			w.write(ret);		
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(w!=null){
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void writeJson(Object obj){
		if(response==null) return;
		Writer w = null;
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");	
			w = response.getWriter();
			String ret = gson.toJson(obj);	
			//System.out.println(ret);
			w.write(ret);		
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(w!=null){
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**字符串输出(ajax)
	 * @param response
	 * @param str
	 */
	public void writeAjax(String str){
		if(response==null) return;
		Writer w = null;
		
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			w = response.getWriter();
			
			w.write(str);
			
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(w!=null){
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void writeJpeg(BufferedInputStream bis){
		if(response==null) return;
		OutputStream output = null;
		
		try {
			response.setContentType("image/jpeg");
	        response.setHeader("Pragma","No-cache");
	        response.setHeader("Cache-Control","no-cache");
	        response.setDateHeader("Expires", 0);
			
	        output = response.getOutputStream();
	        byte[] bytes = new byte[100];
	        int len;
	        while ((len = bis.read(bytes)) > 0) {
	        	output.write(bytes, 0, len);
	        }
	        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("sss.sss"),"utf-8"));
	        PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream("d"),"UTF-8"));
	        Scanner sca = new Scanner("ddd");
	        sca.next();
	        bis.close();
	        output.flush();
	        output.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(output!=null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

//	public static void main(String[] args) throws IOException{
//        File file = new File("d:" + File.separator + "aaa.java");
//        File zipFile = new File("d:" + File.separator + "hello.zip");
//        InputStream input = new FileInputStream(file);
//        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(
//                zipFile));
//        zipOut.putNextEntry(new ZipEntry(file.getName()));
//        // 设置注释
//        zipOut.setComment("hello");
//        int temp = 0;
//        while((temp = input.read()) != -1){
//            zipOut.write(temp);
//        }
//        input.close();
//        zipOut.close();
//    }
	
//	 public static void main(String[] args) throws IOException{
//	        File file = new File("d:" + File.separator + "hello.zip");
//	        File outFile = new File("d:" + File.separator + "aaaaa.txt");
//	        ZipFile zipFile = new ZipFile(file);
//	        ZipEntry entry = zipFile.getEntry("aaa.java");
//	        InputStream input = zipFile.getInputStream(entry);
//	        OutputStream output = new FileOutputStream(outFile);
//	        int temp = 0;
//	        while((temp = input.read()) != -1){
//	            output.write(temp);
//	        }
//	        input.close();
//	        output.close();
//	    }
	
	 public static void main(String[] args) throws IOException{
		 File img = new File("E:/Users/zhoushun/Downloads/fringe2.jpg");
		 System.out.println("系统默认编码为：" + System.getProperty("file.encoding"));
		 BufferedImage bi = (BufferedImage)ImageIO.read(img);  
		 BufferedImage bbb =  bi.getSubimage(0, 0, 1600, 900);
		 File f = new File("E:/Users/zhoushun/Downloads/zs1.jpg");
		 ImageIO.write(bbb, "jpg", f);
		 System.out.println("a"+"\n"+"b");
//	        File file = new File("d:" + File.separator + "hello.zip");
//	        File outFile = null;
//	        ZipFile zipFile = new ZipFile(file);
//	        ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
//	        ZipEntry entry = null;
//	        InputStream input = null;
//	        OutputStream output = null;
//	        while((entry = zipInput.getNextEntry()) != null){
//	            System.out.println("解压缩" + entry.getName() + "文件");
//	            outFile = new File("d:" + File.separator + entry.getName());
//	            if(!outFile.getParentFile().exists()){
//	            	System.out.println(outFile.getParentFile().getAbsolutePath());
//	                outFile.getParentFile().mkdir();
//	            }
//	            if(!outFile.exists()){
//	                outFile.createNewFile();
//	            }
//	            input = zipFile.getInputStream(entry);
//	            output = new FileOutputStream(outFile);
//	            int temp = 0;
//	            while((temp = input.read()) != -1){
//	                output.write(temp);
//	            }
//	            input.close();
//	            output.close();
//	        }
		 System.out.println(InetAddress.getLocalHost().toString());
		 System.out.println(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.SIMPLIFIED_CHINESE).format(new java.util.Date()));
		 int cPageNo = 1;
			try{
				cPageNo = Integer.parseInt("222d");
				if(cPageNo<=0) cPageNo=1;
			}catch(Exception e){
				cPageNo = -999;
				//e.printStackTrace();
			}
			
			System.out.println(0%10);
			
			Gson gson = new Gson();
			TodoItem t = new TodoItem();
			t.setData("<font color=\"red\">");
			String result = gson.toJson(t).replace("\\u003c", "<").replace("\\u003e", ">").replace("\\u003d", "=");
			System.out.println(result);
//			TodoItem tt = new TodoItem();
//			tt = gson.fromJson(result, tt.getClass());
//			System.out.println(tt.getData());
//			System.out.println("1:2".split("\\:")[1]);
//			
//			Pattern pattern = Pattern.compile("^[0-2]\\d\\:\\d{2}$");
//			Matcher matcher = pattern.matcher("13:22");
//			System.out.println(matcher.matches());
//			
//			System.out.println(gson.toJson(""));
//			Map<String,String> mm = new HashMap<String,String>();
//			System.out.println(mm.get("dasdsa"));
//			URL url = new URL("http://www.163.com");
//			FileUtils.copyURLToFile(url, new File("D:/zszszs.html"));
			
	 }
}
