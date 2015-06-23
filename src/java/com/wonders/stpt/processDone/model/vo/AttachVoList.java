/**   
* @Title: AttachVoList.java 
* @Package com.wonders.stpt.processDone.model.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年7月6日 上午10:35:07 
* @version V1.0   
*/
package com.wonders.stpt.processDone.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.wonders.stpt.processDone.model.bo.AttachFile2;

/** 
 * @ClassName: AttachVoList 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年7月6日 上午10:35:07 
 *  
 */

public class AttachVoList {
	@XmlElement(name="attach")
    public List<AttachFile2> attachFileList;
}
