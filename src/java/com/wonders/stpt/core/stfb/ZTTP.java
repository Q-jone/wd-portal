package com.wonders.stpt.core.stfb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wonders.module.common.ExecuteSql;

public class ZTTP {
	public int id;
	public String name;
	public String copyname;
	public String part;
	public String pub_flag;
	public String pic;
	public String create_time;
	public String content;
	public String source;
	public String abstractContent;

	public static List<ZTTP> listZTTP(String sWhere, int num) {
		ExecuteSql dealsql = new ExecuteSql("dataSource3");
		List<ZTTP> ary = new ArrayList<ZTTP>();
		try {
			String sql = "select * from(select * from ZTTP  " + sWhere
					+ ") where rownum<=" + num;
			ResultSet rs = dealsql.ExecuteDemandSql(sql);

			while (rs.next()) {
				ZTTP zttp = new ZTTP();
				zttp.id = rs.getInt("id");
				String namee = rs.getString("name");
				zttp.copyname = namee;
				if(namee.length()>25)   namee = namee.substring(0,25)+"...";
				zttp.name = namee;	
				zttp.part = rs.getString("part");
				zttp.pub_flag = rs.getString("pub_flag");
				zttp.pic = rs.getString("pic");
				zttp.create_time = rs.getString("create_time");
				String contentt = rs.getClob("content").getSubString((long) 1,
						(int) rs.getClob("content").length());
				contentt = StfbPage.splitAndFilterString(contentt,150);
				zttp.content = contentt;
				zttp.source = rs.getString("source");
				String nContent = contentt ;
				if (contentt.length() > 70){	
					nContent = nContent.substring(0, 70) + "...";
				}
				zttp.abstractContent = nContent;
				ary.add(zttp);
			}

			rs.close();
			dealsql.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return ary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getPub_flag() {
		return pub_flag;
	}

	public void setPub_flag(String pub_flag) {
		this.pub_flag = pub_flag;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}

	public String getCopyname() {
		return copyname;
	}

	public void setCopyname(String copyname) {
		this.copyname = copyname;
	}

	
}
