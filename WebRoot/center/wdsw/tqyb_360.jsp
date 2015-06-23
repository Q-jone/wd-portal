<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%!


public String getHtml() throws Exception {
	//System.setProperty("http.proxyHost", "isaserver");System.setProperty("http.proxyPort", "80");
	java.net.URL url = new java.net.URL("http://tq.360.cn");
	java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
	if (conn.getResponseCode() == 200) {
		java.io.InputStream is = (java.io.InputStream) conn.getContent();
		java.io.ByteArrayOutputStream baos = 
			new java.io.ByteArrayOutputStream();
		
		int buffer = 1024;
		byte[] b = new byte[buffer];
		int n = 0;
		while ((n = is.read(b, 0, buffer)) > 0) {
			baos.write(b, 0, n);
		}
		String s = new String(baos.toByteArray(), "UTF-8");
		is.close();
		baos.close();
		return s;
	}
	return "";
}

%><% out.println(getHtml()); %>