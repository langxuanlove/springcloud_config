package com.key.api.ws.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.key.api.ws.IWsCommon;

import io.swagger.annotations.Api;
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
@WebService(serviceName = "API_WebService", portName = "commonServicePort", endpointInterface = "com.key.api.ws.IWsCommon", targetNamespace = "http://tempuri.org/")
@Component(value = "iWsCommon")
@Api(value="/CommonRest")
public class IWsCommonImpl implements IWsCommon{
	
	@Resource
    public DynamicDataSource dataSource;

	@RequestMapping(value="/Add_DataSource",produces="application/json;charset=UTF-8")
	@ResponseBody
	@Override
	/**
	 * 参数格式例子：
	 * jsonStr={"cloudId":"IBD104","type":"com.alibaba.druid.pool.DruidDataSource"
	 * 		   ,"driver":"com.mysql.jdbc.Driver","url":"jdbc:mysql://192.168.4.57:3306/gnet_ibd_iom_ibd104"
	 *         ,"username":"root","password":"123456"}
	 */
	public String Add_DataSource(String jsonStr) {
		JSONObject param=JSON.parseObject(jsonStr);
		// 解析传过来的数据.
		String names=PropertyUtil.getProperty("custom.datasource.names");
		String cloudId=param.getString("cloudId");
		// 数据写入配置文件中.
		PropertyUtil.setProperty("custom.datasource.names",names+","+cloudId);
		PropertyUtil.setProperty("custom.datasource."+cloudId+".type", param.getString("type"));
		PropertyUtil.setProperty("custom.datasource."+cloudId+".driverClassName", param.getString("driver"));
		PropertyUtil.setProperty("custom.datasource."+cloudId+".url", param.getString("url"));
		PropertyUtil.setProperty("custom.datasource."+cloudId+".username", param.getString("username"));
		PropertyUtil.setProperty("custom.datasource."+cloudId+".password", param.getString("password"));
		// 写入环境变量中.
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("names",PropertyUtil.getProperty("custom.datasource.names"));
		map.put("cloudId",param.getString("cloudId"));
		map.put("type", param.getString("type"));
		map.put("driverClassName", param.getString("driver"));
		map.put("url", param.getString("url"));
		map.put("username", param.getString("username"));
		map.put("password", param.getString("password"));
		// 根据云id创建数据源.
        dataSource.addNewCustomDataSources(map,cloudId);
		return "数据源添加成功！";
	}
	@RequestMapping(value="/Delete_DataSource",produces="application/json;charset=UTF-8")
	@ResponseBody
	@Override
	/**
	  * 参数格式例子：
	 * jsonStr={"cloudId":"IBD104","type":"com.alibaba.druid.pool.DruidDataSource"
	 * 		   ,"driver":"com.mysql.jdbc.Driver","url":"jdbc:mysql://192.168.4.57:3306/gnet_ibd_iom_ibd104"
	 *         ,"username":"root","password":"123456"}
	 */      
	public String Delete_DataSource(String jsonStr) {
		// 解析传过来的数据.
		JSONObject param=JSONObject.parseObject(jsonStr);
		String delCloudId=param.getString("cloudId");
		String names=PropertyUtil.getProperty("custom.datasource.names");
		String storeCloudId="";
		String[] oldCloudIds=names.split(",");
        List<String> oldls = Arrays.asList(oldCloudIds);
        List<String> newls = new ArrayList<String>(oldls);
        if(newls.contains(delCloudId))newls.remove(delCloudId);
        for (int i = 0; i < newls.size(); i++) {
        	storeCloudId+=newls.get(i);
        	if(i!=newls.size()-1){
        		storeCloudId+=",";
        	} 
		}
		// 数据写入配置文件中.
		PropertyUtil.setProperty("custom.datasource.names",storeCloudId);
		// 删除.
		PropertyUtil.deleteKey("custom.datasource."+delCloudId+".type");
		PropertyUtil.deleteKey("custom.datasource."+delCloudId+".driverClassName");
		PropertyUtil.deleteKey("custom.datasource."+delCloudId+".url");
		PropertyUtil.deleteKey("custom.datasource."+delCloudId+".username");
		PropertyUtil.deleteKey("custom.datasource."+delCloudId+".password");
		// 写入环境变量中.
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("names",PropertyUtil.getProperty("custom.datasource.names"));
		map.put("cloudId",delCloudId);
		map.put("type", param.getString("type"));
		map.put("driverClassName",param.getString("driver"));
		map.put("url", param.getString("url"));
		map.put("username",param.getString("username"));
		map.put("password", param.getString("password"));
		// 根据云id创建数据源.
		dataSource.deleteNewCustomDataSources(map,delCloudId);
		return "数据源删除成功！";
	}
}
