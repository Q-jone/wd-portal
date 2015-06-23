package com.wonders.stpt.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

/**
 * 为Spring管理之外的类提供由Spring管理的类的实例
 * 配置文件为BackgroundContext.xml
 * @author zzg
 *
 */
public class SpringBeanUtil implements ApplicationContextAware {
	protected final static Log logger = LogFactory.getLog(SpringBeanUtil.class);
	
	private static ApplicationContext ctx = null;
	
	private static Map<String, Properties> propMap = new HashMap<String, Properties>(0);
	
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		SpringBeanUtil.ctx = ctx;
	}
	
	public static Object getBean(String prop) {
		Object obj = ctx.getBean(prop);
		if (logger.isDebugEnabled()) {
			logger.debug("property=[" + prop + "],object=[" + obj + "]");
		}
		return obj;
	}

	//读取配置文件
	//Properties pt=SpringBeanUtil.getProperties("classpath:file.properties");//取properties
	//String fileDown = pt.getProperty("fileDown");//取properties中的值
	public static Properties getProperties(String filepath) {
		//if (propMap.containsKey(filepath)) return propMap.get(filepath);
		
		Resource resource = ctx.getResource(filepath);
		Properties prop = new Properties();
		try {
			prop.load(resource.getInputStream());
			propMap.put(filepath, prop);
			return prop;
		} catch (IOException e) {
			logger.error("can not find the resource file:[" + filepath + "]", e);
			return null;
		}
	}

	public static DataSource getDataSource(String dataSource) {
		if(dataSource==null||"".equals(dataSource)){
			return (DataSource) getBean("datasource");
		}
		return (DataSource) getBean(dataSource);
	}
	
	public static SessionFactory getSessionFactory() {
		return (SessionFactory) getBean("sessionFactory");
	}
}
