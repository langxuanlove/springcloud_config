package com.key.api.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * @author sam
 * 
 * @version v0.1
 * 
 *          Created on 2013-06-24
 * 
 *          Revision History: Date Reviser Description ---- -------
 *          ----------------------------------------------------
 * 
 *          Description:java对象与json字符的相互转换
 */
public class JsonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	/**
	 * 反序列化配置组件
	 * @auther Aaron
	 * 
	 * @return
	 */
	public static Feature[] getFeatures() {
		
		//忽略不匹配的字段，将字符串值初始化为空字符串
		Feature[] features = {Feature.IgnoreNotMatch, Feature.InitStringFieldAsEmpty};
		return features;
	}


	/**
	 * Description:
	 * 
	 * @param rescode
	 *            请求的状态{“fail”，“success”}
	 * @param memo
	 *            请求的返回信息
	 * @param obj
	 *            返回的数据
	 * @return
	 */
	public static Map<String, Object> iomResInfo(String rescode, String memo, Object obj) {
		Map<String, Object> _mapHeadValue = new HashMap<>();
		List<Map<String, Object>> _lst = new ArrayList<>();
		Map<String, Object> _map = new HashMap<>();
		_map.put(Constants.IOM_HEAD_RESULT, rescode);
		_map.put(Constants.IOM_HEAD_MESSAGE, memo);
		_lst.add(_map);
		_mapHeadValue.put(Constants.IOM_HEAD, _lst);
		if (obj != null) {
			_mapHeadValue.put(Constants.IOM_LIST, obj);
		} else {
			List _lstArr = new ArrayList();
			_mapHeadValue.put(Constants.IOM_LIST, _lstArr);
		}
		return _mapHeadValue;
	}
	
	/**
	 * @param psJson
	 *            需要转换的json字符串
	 * @return 转换后的对象
	 */
	@SuppressWarnings("rawtypes")
	public static final List parseArray(String psJson) {
		return JSON.parseArray(psJson);
	}
	/**
	 * @param pobj
	 *            需要转换的java对象
	 * @return 转换后的对象
	 */
	public static String toJSONString(Object pobj, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if (null != value) {
					if (value instanceof java.util.Date) {
						return String.format("%1$tF %1tT", value);
					}
					return value;
				} else {
					return "";
				}
			}
		});
		serializer.write(pobj);
		s = out.toString();
		out.close();
		return s;
	}
	/**
	 * iom出错时返回错误信息
	 * @auther aaron
	 * 
	 * @param memo
	 * @param object
	 * @return
	 */
	public static String iomFail(String memo, Object object) {
		
		return JsonUtil.toJSONString(JsonUtil.iomResInfo("Fail", memo, object));
	}
}
