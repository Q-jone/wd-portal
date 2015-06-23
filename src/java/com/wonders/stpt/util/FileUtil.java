package com.wonders.stpt.util;

import jxl.JXLException;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileUtil {
	protected static final Log logger = LogFactory.getLog(FileUtil.class);
	
	public static boolean moveFile(String srcFile, String destPath){
		boolean r = false;
        File file = new File(srcFile);
        if(file.exists()&&file.isFile()){
        	File dir = new File(destPath);
        	if(!dir.exists()){
        		dir.mkdirs();
        	}
        	r = file.renameTo(new File(dir, file.getName()));
        }
        return r;
    }

	public static void moveDirFiles(String srcDirStr, String destDirStr,boolean isDeleteSrcDir){
		File srcDir = new File(srcDirStr);
		if(srcDir.exists()&&srcDir.isDirectory()){
			File[] files = srcDir.listFiles();
			if(files!=null&&files.length>0){
				File destDir = new File(destDirStr);
				if(!destDir.exists()){
					destDir.mkdirs();
				}
				for(int i=0;i<files.length;i++){
					File file = files[i];
					file.renameTo(new File(destDir, file.getName()));
				}
			}
			if(isDeleteSrcDir){
				srcDir.delete();
			}
		}
    }

	/**
	 * 生成excel数据流

	 * @param os
	 * @param title
	 * @param head
	 * @param data
	 * @throws IOException
	 * @throws JXLException
	 */
	@SuppressWarnings("unchecked")
	public static void createXls(OutputStream os, String title, List head, List data) throws IOException, JXLException {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet sheet = wwb.createSheet("sheet1", 0);
		
		// 合并标题单元格

		sheet.mergeCells(0, 0, head.size()-1, 0);
		// 设置标题行高
		sheet.setRowView(0, 30);
		sheet.setRowView(1, 18);
		
		// title
		WritableFont wfTitle = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD);
		WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
		wcfTitle.setAlignment(Alignment.CENTRE);
		Label labTitle = new Label(0, 0, title, wcfTitle);
		sheet.addCell(labTitle);
		
		// head
		WritableFont wfHead = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
		WritableCellFormat wcfHead = new WritableCellFormat(wfHead);
		for (int i=0; i<head.size(); i++) {
			Label labHead = new Label(i, 1, (String)head.get(i), wcfHead);
			sheet.addCell(labHead);
		}
		
		// data
		if (data != null && data.size() > 0) {
			if (data.get(0) instanceof Map) {
				// list中是Map
				for (int i=0; i<data.size(); i++) {
					Map m = (Map) data.get(i);
					Set s = m.keySet();
					Iterator itr = s.iterator();
					for (int j=0; itr.hasNext(); j++) {
						Object key = itr.next();
						try {
							Label labData = new Label(j, i+2, m.get(key).toString());
							sheet.addCell(labData);
						} catch (NullPointerException e){}
					}
				}
			} else if (data.get(0) instanceof List) {
				// list中是list
				for (int i=0; i<data.size(); i++) {
					List lst = (List) data.get(i);
					for (int j=0; j<lst.size(); j++) {
						try {
							Label labData = new Label(j, i+2, lst.get(j).toString());
							sheet.addCell(labData);
						} catch (NullPointerException e){}
					}
				}
			} else if (data.get(0) instanceof Object[]) {
				// list中是数组
				for (int i=0; i<data.size(); i++) {
					Object[] obj = (Object[]) data.get(i);
					for (int j=0; j<obj.length; j++) {
						try {
							Label labData = new Label(j, i+2, obj[j].toString());
							//System.out.println(obj[j]);
							sheet.addCell(labData);
						} catch (NullPointerException e){}
					}
				}
			} else {
				logger.warn("行包含的数据不能处理");
			}

		}
		
		wwb.write();
		wwb.close();
	}

	//...
	public static void main(String[] args) throws Exception {
		/*
		String title = "test导出数据";
		List head = new ArrayList();
		head.add("姓名");
		head.add("编号");
		List data = new ArrayList();
		/*List data1 = new ArrayList();
		data1.add("张三");
		data1.add("0100");
		data.add(data1);
		List data2 = new ArrayList();
		data2.add("李四");
		data2.add("0101");
		data.add(data2);*/
		/*Map m1 = new HashMap();
		m1.put("name", "张三");
		m1.put("code", "0100");
		Map m2 = new HashMap();
		m2.put("name", "李四");
		m2.put("code", "0102");
		data.add(m1);data.add(m2);*/
		/*
		Object[] o1 = {"张三", new Integer(10)};
		Object[] o2 = {"李四", new Integer(20)};
		data.add(o1); data.add(o2);
		
		OutputStream os = new FileOutputStream(new File("xx.xls"));
		createXls(os, title, head, data);
		os.close();
		System.out.println("done");
		*/
        String urls = "http://10.1.48.30/portal/404.jsp";
        URL url = null;
        String result = "";
        HttpURLConnection http = null;
        try {
            url = new URL(urls);
            http = (HttpURLConnection) url.openConnection();
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(50000);
            http.setReadTimeout(50000);
            http.setRequestMethod("GET");
            http.setRequestProperty("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
            http.setRequestProperty("Accept-Encoding", "gzip");
            http.setRequestProperty("Referer", "http://dmm.hk/");
            String param = "zs=大声点撒";
//            http.getOutputStream().write(param.getBytes());
//            http.getOutputStream().flush();
//            http.getOutputStream().close();

            OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            osw.write(param);
            osw.flush();
            osw.close();
            System.out.println("getResponseCode====="+http.getResponseCode());
            if (http.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += "\r\n"+inputLine;
                }
                in.close();
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("err");
        } finally {
            if (http != null)
                http.disconnect();
        }
    }
}
