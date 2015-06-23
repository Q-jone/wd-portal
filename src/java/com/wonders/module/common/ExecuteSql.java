/*
 * 创建日期 2006-4-11
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.wonders.module.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zougao
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class ExecuteSql {
	private DbConnect dbcon;
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;

	public ExecuteSql(String dataSource) {
		dbcon = new DbConnect();
		// dbcon = null;
		conn = dbcon.getDbConn(dataSource);
		// conn = null;
		pstmt = null;
		stmt = null;

	}

	public boolean ExecuteSql(String sql) {
		// DbConnect dbcon = null;
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		// Statement stmt = null;
		boolean res = false;

		try {
			// dbcon = new DbConnect();

			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return res;
	}

	public ResultSet ExecuteDemandSql(String sql) {
		// DbConnect dbcon = null;
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		// Statement stmt = null;
		ResultSet rs = null;
		try {

			// conn = dbcon.getDbConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} // finally {
		// try {
		// if (pstmt != null)
		// pstmt.close();
		// } catch (Exception e) {
		// }
		// try {
		// if (conn != null)
		// conn.close();
		// } catch (Exception e) {
		// }
		// }
		return rs;
	}

	public int ExecuteDeleteSql(String sql) {
		// DbConnect dbcon = null;
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		// Statement stmt = null;
		int result = 0;
		try {
			// dbcon = new DbConnect();

			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public void close() throws SQLException {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.err.println("------*" + e);
		}

	}
}