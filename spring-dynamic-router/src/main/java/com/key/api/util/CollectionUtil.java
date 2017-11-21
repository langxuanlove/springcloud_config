package com.key.api.util;

import java.util.Collection;

/**
 * 检查一些集合属性是否为空
 * 
 * @author Aaron
 * 
 * @version v3.0
 * 
 * Created on 2016年4月27日 下午6:02:45
 * 
 * Revision History:
 * Date   		Reviser		Description
 * 2016年4月27日      
 * ----   		-------   	----------------
 * 
 * Description:
 */
public class CollectionUtil {
	
	/**
	 * 检查一个list是否不为空,包含不为null，集合中的大小大于0
	 * @auther Aaron
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		
		return org.springframework.util.CollectionUtils.isEmpty(collection);
	}
	
}