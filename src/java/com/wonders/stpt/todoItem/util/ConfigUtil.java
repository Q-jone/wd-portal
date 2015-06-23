/**   
* @Title: ConfigUtil.java 
* @Package com.wonders.stpt.todoItem.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年7月4日 上午11:23:28 
* @version V1.0   
*/
package com.wonders.stpt.todoItem.util;

import java.io.InputStream;
import java.util.Properties;

/** 
 * @ClassName: ConfigUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年7月4日 上午11:23:28 
 *  
 */
public class ConfigUtil {
	private static Properties p = null;

	public synchronized static void initP(String propertyName) throws Exception {
		if (p == null) {
			p = new Properties();

			InputStream inputstream = ConfigUtil.class.getClassLoader()
					.getResourceAsStream(propertyName);
			if (inputstream == null) {
				throw new Exception("inputstream " + propertyName
						+ " open null");
			}
			p.load(inputstream);
			inputstream.close();
			inputstream = null;
		}
	}

	public static String getValueByKey(String propertyName, String key) {
		String result = "";
		try {
			initP(propertyName);
			result = p.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] s) {
		// System.out.println(PWSProperties.getValueByKey("ws_split_chars"));
	}
}
