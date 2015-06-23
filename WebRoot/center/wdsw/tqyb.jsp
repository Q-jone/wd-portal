<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%!
protected static String BASE_WEATHER_URL		= "http://weather.china.com.cn";

protected static String WEATHER_URL 			= BASE_WEATHER_URL + "/city/58362_zx.html";

protected static String WEATHER_HTML_CHARSET 	= "UTF-8";

/* servletContext中保存天气预报的key */
protected static String CACHE_HTML_KEY 			= "cache.tqyb.html";

protected static String CACHE_DT_KEY 			= "cache.tqyb.dt";

public String getHtml(JspWriter out) throws Exception {
	//System.setProperty("http.proxyHost", "isaserver");System.setProperty("http.proxyPort", "80");
	java.net.URL url = new java.net.URL("http://weather.china.com.cn/city/58362_zx.html");
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
		String s = new String(baos.toByteArray(), WEATHER_HTML_CHARSET);
		is.close();
		baos.close();
		return s;
	}
	return "";
}

public void cacheHtml(ServletContext ctx, String html) {
	ctx.setAttribute(CACHE_HTML_KEY, html);
	ctx.setAttribute(CACHE_DT_KEY, new Long(System.currentTimeMillis()));
}

public void showHtml(ServletContext ctx, JspWriter out) throws Exception {
	Long cachedTimestamp = (Long)ctx.getAttribute(CACHE_DT_KEY);
	String html = "";
	long millis = 10 * 60 * 1000;  // 缓存10分钟 ，超过重新取
	if (cachedTimestamp == null)
		cachedTimestamp = new Long(0);
	if (System.currentTimeMillis() - cachedTimestamp.longValue() > millis) {
		try {
			html = getHtml(out);
			if (!"".equals(html)) {
				//html = html.replaceAll("</title>", "</title>\r\n<base href=\"" + BASE_WEATHER_URL + "\">");//
				html = html.replaceAll("background=\"http://images.china.cn/images1/ch/resource/t_2.jpg\"", "");
				html = html.replaceAll("width=\"243\"", "width=\"220\"");
				html = html.replaceAll("width=\"86\"", "width=\"64\"");
				html = html.replaceAll("width=\"16\"", "width=\"0\"");
				html = html.replaceAll("/weathericons", "/portal/weathericons");
				html = html.replaceAll("/city/58362_full.html", "");//http://weather.china.com.cn/city/58362_full
				html = html.replaceAll("详细预报", "");
				//html = html.replaceAll("background-color: #D7F3FF;", "background-color: #E4EDF3;");
				//System.out.println("get html ok, cache it.");
				cacheHtml(ctx, html);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	if ("".equals(html)) {
		//System.out.println("get html from cache.");
		html = (String) ctx.getAttribute(CACHE_HTML_KEY);
	}
	out.print(html);
}
%><% showHtml(application, out); %>