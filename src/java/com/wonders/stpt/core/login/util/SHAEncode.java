/**
 * 
 */
package com.wonders.stpt.core.login.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/** 
 * @ClassName: SHAEncode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-19 下午02:00:50 
 *  
 */
public class SHAEncode {
	public static String encodeInternal(String input) {
		if (true) {
			return DigestUtils.shaHex(input);
		}
		byte[] encoded = Base64.encodeBase64(DigestUtils.sha(input));
		return new String(encoded);
	}
	
	public static void main(String[] args){
		System.out.println(encodeInternal("403411"));
	}

}
