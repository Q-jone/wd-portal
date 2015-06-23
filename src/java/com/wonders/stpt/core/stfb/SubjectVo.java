/**
 * 
 */
package com.wonders.stpt.core.stfb;

/**
 * @ClassName: SubjectVo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhoushun
 * @date 2012-3-9 下午11:08:02
 * 
 */
public class SubjectVo {
	private int id = 0;// 栏目记录号

	private String name = "";// 栏目名称
	private int parentid = 0;// 上级栏目id
	private String url = "";// 超链接

	private String dir = "";// 目录名称
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

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}


}
