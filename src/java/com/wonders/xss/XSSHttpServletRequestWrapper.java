/**   
* @Title: XSSHttpServletRequestWrapper.java 
* @Package com.wonders.xss 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年7月2日 上午9:28:36 
* @version V1.0   
*/
package com.wonders.xss;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/** 
 * @ClassName: XSSHttpServletRequestWrapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年7月2日 上午9:28:36 
 *  
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper{
	public XSSHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);                                                                    
    }  
                                                                
     /** 
     * Override the original getParameter() method , 
     * so that it can filter all the parameter name and parameter value 
     * then use replace the special character that may cause XSS attack 
     */  
    @Override  
    public String getParameter(String name) {        
    	// System.out.println("The original name received from getParameter() is:"+name);           
        String value = super.getParameter(encodeXSS(name));                                                             
        //the following sentences will be replaced by logging sentence in actual project     
        //System.out.println("The original value received from getParameter() is:"+value);                                                   
        if (value != null) {  
            value = encodeXSS(value);  
        }  
                                                               
        //the following sentences will be replaced by logging sentence in actual project     
        //System.out.println("After handling XSS ,the actual value is:"+value);  
        //System.out.println();                                                           
        return value;  
    }  
          
    
    
    @Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		Map<String, String[]> paramMap = super.getParameterMap();
	    Set<String> keySet = paramMap.keySet();
	    for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
	      String key = (String) iterator.next();
	      String[] str = paramMap.get(key);
				for(int i=0; i<str.length; i++) {
					str[i] = encodeXSS(str[i]);
	//这里可以对页面传入的所有值进行过滤了，你想怎么处理就怎么处理。比如对出入的值进行html危险字符过滤
	   }
	    }
	    return paramMap ;
	}

	/** 
     * Override the original getHeader() method , 
     * so that it can filter all the parameter name and parameter value 
     * then use replace the special character that may cause XSS attack 
     */  
    @Override  
    public String getHeader(String name) {                                                           
        String value = super.getHeader(encodeXSS(name));                                                               
        //the following sentences will be replaced by logging sentence in actual project     
        //System.out.println("The original value received from getHeader() is:"+value);                                                               
        if (value != null) {  
            value = encodeXSS(value);  
        }  
                                                            
        //the following sentences will be replaced by logging sentence in actual project     
       // System.out.println("After handling XSS ,the actual value is:"+value);  
       // System.out.println();  
                                                           
        return value;  
    }  
                                                                                                                                                                                                 
    /** 
     * replace all the characters that may cause XSS attack from half-width character 
     * to full-width character 
     *  
     * @param s 
     * @return 
     */  
    private String encodeXSS(String s) {  
        if (s == null || "".equals(s)) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder(s.length() + 16);  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
                                                              
            //handle the '<' and '>' which can be used for constructing <script> and </script>  
            case '>':  
                sb.append('＞');  
                break;  
            case '<':  
                sb.append('＜');  
                break;  
                                                                 
            //since the html can support the characters using $#number format  
            //so here also need to escape '#','&' and quote symbol  
            case '\'':  
                sb.append('‘');  
                break;  
            case '\"':  
                sb.append('“');  
                break;  
            case '&':  
                sb.append('＆');  
                break;  
            case '\\':  
                sb.append('＼');  
                break;  
            case '#':  
                sb.append('＃');  
                break;                                                                                                                                                                                                      
            //if not the special characters ,then output it directly     
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    }  
}
