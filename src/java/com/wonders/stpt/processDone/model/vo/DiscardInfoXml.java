/**   
* @Title: DiscardInfoXml.java 
* @Package com.wonders.task.workflowDiscardExternal.model.xml 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年7月1日 下午2:35:31 
* @version V1.0   
*/
package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @ClassName: DiscardInfoXml 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年7月1日 下午2:35:31 
 *  
 */

@XmlRootElement(name = "root")
public class DiscardInfoXml {
	
	@XmlElement(name = "basicData")
	public DiscardVo mainBo;
	
}
