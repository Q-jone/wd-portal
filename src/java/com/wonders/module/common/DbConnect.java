package com.wonders.module.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.stpt.util.SpringBeanUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class DbConnect {
	protected final Log logger = LogFactory.getLog(getClass());

	boolean flag = false;
	private Connection conn = null;
	static int detailNum = 0;

	public DbConnect() {
	}

	private boolean connectToDb(String dataSource) {
		/*
		 * 从spring容器取得datasource，进而获得connection 以保证整个工程的数据库入口唯一
		 */
		try {
			DataSource ds = SpringBeanUtil.getDataSource(dataSource);
			conn = ds.getConnection();
			flag = true;
		} catch (Exception e) {
			logger.warn("取连接失败", e);
			flag = false;
		}
		return flag;
	}

	public Connection getDbConn(String dataSource) {
		try {
			if (connectToDb(dataSource))
				;
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("error!");
		}
		return conn;
	}

	public void commit() {
		try {
			conn.commit();
		} catch (java.sql.SQLException e) {
			System.out.println("error!");
		}
	}

	public void rollback() {
		try {
			conn.rollback();
		} catch (java.sql.SQLException e) {
			System.out.println("error!");
		}
	}

	public String getNextValue(Connection con, String seqName)
			throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int nextValue = 0;
		String nextStr = null;
		String tempSeq = null;
		try {
			tempSeq = seqName + ".nextval";
			String exSQL = "SELECT " + tempSeq + " FROM DUAL";
			pstmt = con.prepareStatement(exSQL);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				nextValue = rst.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		nextStr = (new Integer(nextValue).toString());
		return nextStr;
	}

	public void release() {
		try {
			conn.close();
		} catch (java.sql.SQLException e) {
			System.out.println("error!");
		}
	}

	public void release(Connection cn) {
		try {
			cn.close();
		} catch (java.sql.SQLException e) {
			System.out.println("error!");
		}
	}

}
