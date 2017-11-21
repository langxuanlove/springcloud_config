package com.key.api.util;

// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CxfUtil.java

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class CxfUtil {

	private static Map<String, Client> client = new HashMap<String, Client>();

	/* 私有构造方法，防止被实例化 */
	private CxfUtil(String psWsUrl) {
		JaxWsDynamicClientFactory _dcf = JaxWsDynamicClientFactory.newInstance();
		client.put(psWsUrl, _dcf.createClient(psWsUrl));

		HTTPConduit conduit = (HTTPConduit) getClinet(psWsUrl).getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setAllowChunking(false);
		policy.setConnectionTimeout(3000);
		policy.setReceiveTimeout(5000);
		conduit.setClient(policy);
	}
	public static String callService(String psWsUrl, String method, Object arg[]) {
		if(null == client || null == client.get(psWsUrl)){
			new CxfUtil(psWsUrl);
		}
		Client _client = client.get(psWsUrl);
		Object res[] = (Object[]) null;
		try {
			res = _client.invoke(method, arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String) res[0];
	}
	public static Client getClinet(String psWsUrl){
		return client.get(psWsUrl);
	}	
	public static void main(String args[]) throws Exception {
//	    String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SNID\":\"20140113140645068\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"fase\"},\"GII_IBD\": {\"PAGEINDEX\":\"\", \"PAGESIZE\":\"\", \"ACTION\":\"\",\"ISCOUNT\":\"0\",\"REQUEST\":{\"CLOUD_ID\":\"IBD104\",\"USER_ID\":\"\",\"MANAGER_ID\":\"\",\"INDUSTRY_ID\":\"\",\"CLOUD_TYPE\":\"\",\"APPLYMODE\":\"\",\"STATUS\":\"0\",\"KEYWORDS\":\"\"}}}";
//	   System.out.println(callService("http://127.0.0.1:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_CLOUD_GetCloudBySearch",new Object[]{param1}));
//		String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20151029163248775\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"IDENTIFIER\": \"902B34D9050F2015-10-29 16:33:102aae0f6e3ad44b54a39e4caaa060de18\",\"USER_ID\": \"26a030e47451452abb5508bfb676b1fa\",\"CLOUD_ID\":\"38cc8c6da28e49b7372f36de73dff247\",\"GZIP\": \"true\"}, \"GII_IBD\": {\"PAGEINDEX\": \"1\",\"PAGESIZE\":\"10240\",\"IS_COUNT\":\"0\",\"REQUEST\":{\"USERID\": \"26a030e47451452abb5508bfb676b1fa\",\"CLOUDID\": \"38cc8c6da28e49b7372f36de73dff247\",\"ISDOWNLOAD\":\"0\"}}}";
//		System.out.println(callService("http://192.168.4.58:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppByUserIdAndCloudId",new Object[]{param1}));
//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"false\"},"
//				+ "\"GII_IBD\": {\"OUTIP\":\"192.168.1.50\",\"INIP\":\"192.168.1.50\"}}";
//		System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_CLOUD_GetIPInfoForSelfCloud",new Object[]{_sIbdJson}));		

//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20140113140645068\",\"SIGN\":   [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"},{\"IDENTIFIER\": \"\",\"USER_ID\": \"1000c5bc41c807f4dfb968b3c0cbd728549\",\"CLOUDID\":\"\"}],\"GZIP\": \"false\"},\"GII_IBD\":  {\"USER_NAME\": \"admin\",\"USER_PASSWORD\": \"C0C45FF6AE3D4633B9F3346179AAB3368B92F8C6EDDD63A71AB23F2097D707954495AD57F1086064\",\"MAC\": \"00-25-64-C2-5F-52\"}}";
//		System.out.println(callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_LOGIN",new Object[]{_sIbdJson}));//GII_USER_LOGIN
//	String _jSon="{\"GII_HEAD\":{\"VERSION\": \"PC2.0\", \"SNID\": \"20140113140645068\",\"SIGN\":[{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"fale\"},\"GII_IBD\": {\"PAGEINDEX\": \"1\",\"PAGESIZE\": \"512\",\"ACTION\": \"DETAIL\"      ,\"REQUEST\": {\"CLOUD_ID\": \"\",\"MANAGER_ID\": \"\",\"INDUSTRY_ID\": \"\",\"CLOUD_TYPE\": \"2\",      \"APPLYMODE\": \"\",\"STATUS\": \"0\",\"KEYWORDS\": \"\"}}}";
//	System.out.println(callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_CLOUD_GetCloudBySearch",new Object[]{_jSon}));

//	String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"USER_NAME\": \"admin\",\"USER_PASSWORD\": \"e10adc3949ba59abbe56e057f20f883e\",\"MAC\":\"111E5F1E0577\",\"DATE\":\"2015-04-22 09:40:30\"}}";
//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"REQUEST\":{\"CLOUD_ID\":\"\",\"USER_ID\":\"\",\"MANAGER_ID\":\"\",\"INDUSTRY_ID\":\"\",\"CLOUD_TYPE\":\"0,1\",\"APPLYMODE\":\"\",\"STATUS\":\"0\",\"KEYWORDS\":\"\"},\"PAGEINDEX\":\"1\",\"PAGESIZE\":\"20\",\"ACTION\":\"0\",\"ISCOUNT\":\"\"}}";
//		System.out.println(callService("http://192.168.1.164:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_CLOUD_GetCloudBySearch",new Object[]{_sIbdJson}));

		
//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"USER_NAME\": \"admin\",\"USER_PASSWORD\": \"e10adc3949ba59abbe56e057f20f883e\",\"MAC\":\"111E5F1E0577\",\"DATE\":\"2015-04-22 09:40:30\"}}";
//		String _sIbdJson = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \"f42a7a6b9338440bb01c26a5ca9a1cb9\"}]";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Role_GetRoleBaseByOrganType",new Object[]{_sIbdJson}));
//		String _sIbdJson = "[{\"IOM_Version\": \"Web3.0\"},{\"organType\": \"0\",\"serviceId\": \"f42a7a6b9338440bb01c26a5ca9a1cb9\"}]";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Role_GetRoleChildrenByOrganType",new Object[]{_sIbdJson}));
//		for(int i=0;i<10;i++){
//			System.out.println(UUIDUtil.getUUID());
//		}
//		String param1 = "[{\"IOM_Version\": \"Web3.0\"},{\"organId\":\"1fd03d1a0a1a46e9bcb444085cf6ced1\",\"pageSize\":\"20\",\"pageIndex\":\"1\",\"searchValue\":\"gfae\",\"serviceId\": \"38cc8c6da28e49b7372f36de73dff247\"}]";
//		System.out.println(callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Organ_GetMemberListByOrganId",new Object[]{param1}));

//		 String server2 ="http://192.168.1.27:8080/Gnet_Ibd_Iom/services/IOM_CommonRest/IOM_Organ_GetMemberListByOrganId";
//		    System.out.println(HttpUtil.sendPostRequest(server2, param1,false));
		
		
		//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"REQUEST\":["
//				+ "{\"CLOUD_ID\":\"PublicCloudId\",\"MYSQL_URL\":\"jdbc:mysql://192.168.1.164:3306/gnet_ibd_iom_PublicCloudId\",\"DB_LOGNAME\":\"root\",\"DB_PWD\":\"123456\"},"
//				+ "{\"CLOUD_ID\":\"ceshi\",\"MYSQL_URL\":\"jdbc:mysql://192.168.1.164:3306/gnet_ibd_iom_ceshi\",\"DB_LOGNAME\":\"root\",\"DB_PWD\":\"123456\"}]}}";
//		String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"ACTION\":\"PWD\",\"USER_ID\":\"4\",\"NEW_PWD\":\"670b14728ad9902aecba32e22fa4f6bd\"}}";
		
					
//		String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"IOS2.0\",\"SNID\": \"423424234423\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\" }],\"GZIP\": \"true\"}, \"GII_IBD\": {\"LOCATION_ID\": \"\",\"LOCATION_NAME\": \"\",\"PRE_LOCATION_ID\":\"\"}}";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_SearchLocation",new Object[]{_sIbdJson}));

		
		
//			String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"PC3.0\", \"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"IDENTIFIER\": \"902B34D9051D2015-09-18 14:34:074df6e89ccc744d899fc684a3dc01284a\",\"USER_ID\": \"31a84a949b7647c19ef6dc590ff88c5f\",\"CLOUD_ID\":\"38cc8c6da28e49b7372f36de73dff247\",\"GZIP\": \"true\"},\"GII_IBD\": {\"REQUEST\":{\"USERID\":\"31a84a949b7647c19ef6dc590ff88c5f\",\"PARENT_ID\":\"\",\"FOLDERNAME\":\"新建文件夹\",       \"FOLDER_ID\":\"\",\"ISWEB\":\"0\"}}}";
//			System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_CreateOrUpdateFolder",new Object[]{_sIbdJson}));
			
			
//			String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20150924225919287\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"IDENTIFIER\": \"902B34D9051D2015-09-24 22:59:32121ffe4fb5b24388a5183723ec15f21a\",\"USER_ID\": \"44\",\"CLOUD_ID\":\"3b860f8052bb493285387891cfe63cf5\",\"GZIP\": \"true\"},       \"GII_IBD\": {\"PAGEINDEX\": \"1\",\"PAGESIZE\":\"10240\",\"IS_COUNT\":\"0\",\"REQUEST\":{\"USERID\": \"44\",\"CLOUDID\": \"3b860f8052bb493285387891cfe63cf5\",\"ISDOWNLOAD\":\"1\"}}}";
//			System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppByUserIdAndCloudId",new Object[]{_sIbdJson}));
			
			
//			String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"PC3.0\", \"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"IDENTIFIER\": \"902B34D9051D2015-09-18 16:39:18948743b57ae84d60b7c01b8497c7712c\",\"USER_ID\": \"31a84a949b7647c19ef6dc590ff88c5f\",\"CLOUD_ID\":\"38cc8c6da28e49b7372f36de73dff247\",       \"GZIP\": \"true\"},\"GII_IBD\":{\"REQUEST\":{\"APPID\": \"72f15b49b7c4477cb41fdefd2fbfc933\",\"USEID\": \"72f15b49b7c4477cb41fdefd2fbfc933\",\"USERID\":\"31a84a949b7647c19ef6dc590ff88c5f\",\"TYPE\":\"0\",\"ISWEB\":\"0\"}}}";
//			System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_InstallOrUnInstallApp",new Object[]{_sIbdJson}));
			
			

//		String _s = UUIDUtil.getUUID();
//		for(int i=0;i<5;i++){
//			System.out.println(UUIDUtil.getUUID());
//		}

//		String _sIbdJson="[{\"Version\":\"WB1.0\",\"SNID\":\"20120309195611068\",\"Sign\":\"da32rr43re\"},{\"UserID\":\"30a678bc5b9141e489a0ad457a40f101\"}]";
//		System.out.println(callService("http://192.168.4.58:8080/Gnet_Ibd_Iom/services/Ibd_CompatService?wsdl","GII_USER_GetUserBinfo",new Object[]{_sIbdJson}));
		

//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"2015 5 718 151795\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"},{\"IDENTIFIER\": \"082E5F1E05772015-05-07 18:03:15eadd6716b97c4240b6af93283158d4df\",\"USER_ID\": \"1000c5bc41c807f4dfb968b3c0cbd728549\",\"CLOUD_ID\":\"IBD104\"}],\"GZIP\": \"true\"},						  \"GII_IBD\": {\"PAGEINDEX\": \"1\",\"PAGESIZE\":\"10240\",\"IS_COUNT\":\"0\",\"REQUEST\":{\"USERID\": \"1000c5bc41c807f4dfb968b3c0cbd728549\",\"CLOUDID\": \"IBD104\",\"ISDOWNLOAD\":\"0\"}}}";
//		System.out.println(callService("http://192.168.4.80:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppByUserIdAndCloudId",new Object[]{_sIbdJson}));
	
//      String _sJson="{\"GII_HEAD\":{\"GZIP\":\"false\",\"SIGN\":[{\"POSITION/k\":\"\",\"RANDOMCODE/v\":\"\"}],\"SNID\":\"2014102810543913\",\"VERSION\":\"PC3.0\"},\"GII_IBD\":{\"ISCOUNT\":\"0\",\"PAGEINDEX\":\"\",\"PAGESIZE\":\"\",\"REQUEST\":{\"EXACT\":{\"USER_NAME\":\"\",\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USERID\":\"\"},\"VAGUE\":{\"DEFAULT\":\"邢士强\"}}}}";
      
//		String _sJson="{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SNID\":\"8d291041e45ff3f\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode\": \"\"}],\"GZIP\": \"fase\"},\"GII_IBD\": {\"PAGEINDEX\":\"1\",\"PAGESIZE\":\"10\",\"ISCOUNT\":\"0\",\"REQUEST\":{\"EXACT\":{\"USER_NAME\":\"\",\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USERID\":\"\"}, \"VAGUE\" : {\"TERM\":{\"USER_TRUENAME\":{\"SIGN\":\"T\",\"VALUE\":\"邢士强\"}}}}}}";
//		System.out.println(
//	    		callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl", "GII_USER_SearchUser",  new Object[] {_sJson} )
//		);
//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"USER_NAME\": \"admin\",\"USER_PASSWORD\": \"e10adc3949ba59abbe56e057f20f883e\"}}";
//		System.out.println(callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_LOGIN",new Object[]{_sIbdJson}));	
		
		
//		 String _sJson="{\"GII_HEAD\":{\"GZIP\":\"false\",\"SIGN\":[{\"POSITION/k\":\"\",\"RANDOMCODE/v\":\"\"}],\"SNID\":\"2014102810543913\",\"VERSION\":\"PC3.0\",\"CLOUDID\":\"38cc8c6da28e49b7372f36de73dff247\"},\"GII_IBD\":{\"ISCOUNT\":\"0\",\"PAGEINDEX\":\"\",\"PAGESIZE\":\"\",\"REQUEST\":{\"EXACT\":{\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USER_NAME\":\"\"},\"VAGUE\":{\"DEFAULT\":\"adm\",\"GROUPID\":\"38cc8c6da28e49b7372f36de73dff247\"}}}}";
//	      System.out.println(
//		    		callService("http://192.168.4.58:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl", "GII_USER_SearchUser",  new Object[] {_sJson} )
//			);
//	      String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SNID\":\"8d25c621a01479f\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"fase\"},\"GII_IBD\": {\"PAGEINDEX\":\"\", \"PAGESIZE\":\"\", \"ISCOUNT\":\"0\",\"REQUEST\":{\"EXACT\":{\"USER_NAME\":\"\",\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USERID\":\"10003\"}}}}";
//	      System.out.println(callService("http://192.168.4.54:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_SearchUser",new Object[]{param1}));
	
//		 String param1 = "{\"GII_HEAD\":{\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode\":\"\"}],\"GZIP\": \"true\"},\"GII_IBD\": {\"APPBELONG\": \"1\",\"USEID\": \"9797f04c1f5e416c93294a20733a048f\"}}";
//	      System.out.println(callService("http://192.168.4.58:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_getAppDetailInfoByUseId",new Object[]{param1}));
	
//	    String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SNID\":\"20140113140645068\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"fase\"},\"GII_IBD\": {\"PAGEINDEX\":\"\", \"PAGESIZE\":\"\", \"ACTION\":\"\",\"ISCOUNT\":\"0\",\"REQUEST\":{\"CLOUD_ID\":\"IBD104\",\"USER_ID\":\"\",\"MANAGER_ID\":\"\",\"INDUSTRY_ID\":\"\",\"CLOUD_TYPE\":\"\",\"APPLYMODE\":\"\",\"STATUS\":\"0\",\"KEYWORDS\":\"\"}}}";
//	   System.out.println(callService("http://127.0.0.1:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_CLOUD_GetCloudBySearch",new Object[]{param1}));
	
//		 String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SNID\":\"20140113140645068\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"fase\"},\"GII_IBD\": {\"PAGEINDEX\":\"\", \"PAGESIZE\":\"\", \"ACTION\":\"DETAIL\",\"ISCOUNT\":\"0\",\"REQUEST\":{\"USERID\":\"1000c5bc41c807f4dfb968b3c0cbd728549\",\"STATUS\":\"0\"}}}";
//		System.out.println(callService("http://127.0.0.1:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_GetCloudInfoByUserId",new Object[]{param1}));
		
		
//		String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"PC3.0\", \"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"IDENTIFIER\": \"\",\"USER_ID\": \"\",\"CLOUD_ID\":\"\",\"GZIP\": \"true\"}}";
//		System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_SYS_GetIBDVersion",new Object[]{_sIbdJson}));
		
		
//		String _sIbdJson="{\"GII_IBD\":{\"USER_NAME\":\"yangxuanwei@keytec.com.cn\",\"USER_PASSWORD\":\"e10adc3949ba59abbe56e057f20f883e\"},\"GII_HEAD\":{\"SIGN\":[{\"position/k\":\"\"},{\"RandomCode/v\":\"\"}],\"GZIP\":\"false\",\"VERSION\":\"IO1.0\",\"SNID\":\"20140113140645068\"}}";
		/*String str = "[{\"IOM_Version\": \"Web3.0\"},{\"organId\": \""
			    + "a8ff3ac2f0704188839e3f0f698d560b"
			    + "\",\"pageSize\":\"10000\",\"pageIndex\":\"1\",\"serviceId\": \""
			    + "IBD104" + "\"}]";
		System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Organ_GetMemberListByOrganId",new Object[]{str}));
		*/
		/*String _sIbdJson="{\"GII_HEAD\":{\"GZIP\":\"false\",\"SIGN\":[{\"POSITION/k\":\"\",\"RANDOMCODE/v\":\"\"}],\"SNID\":\"2015092902271014\",\"VERSION\":\"PC1.0\"},\"GII_IBD\":{\"ISCOUNT\":\"0\",\"PAGEINDEX\":\"1\",\"PAGESIZE\":\"10000\",\"REQUEST\":{\"EXACT\":{\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USERID\":\"8c045a3d420348578f8d2d842f7254e2,d8ca4c61da7b436e840f75b93e546bac,66eb6b956a7e42b2a83c385fabbff224,b3d39f4c6683488ca2b4fd22bfd368ce,c22250b962eb4e29931b913345729dd6,f768914ef6c849539c68b61f83782c87,200864,202069,200575,200866\",\"USER_NAME\":\"\"},\"VAGUE\":{\"DEFAULT\":\"\",\"GROUPID\":\"IBD104\"}}}}";
		System.out.println(callService("http://192.168.4.58:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_SearchUser",new Object[]{_sIbdJson}));
		*/
//		String _sIbdJson="{\"GII_HEAD\": {\"VERSION\": \"PC2.0\", \"SNID\": \"201509131630123456\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"fase\",\"CLOUD_ID\":\"38cc8c6da28e49b7372f36de73dff247\",\"USER_ID\":\"null\"},\"GII_IBD\": {\"CLOUDID\":\"a0c1b80e482e447ebe9bbd11aafdaa93\",\"USER_ID\":\"null\",\"SEARCHNAME\":\"\",\"PAGEINDEX\":\"\",\"PAGESIZE\":\"\"}}";
//		System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_ICM_GetJoinCloudListById",new Object[]{_sIbdJson}));
		
		
//		String param = "{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"position/k\": \"\""
//				+ "}," + "{\"RandomCode\": \"\"},],\"GZIP\": \"false\"},\"GII_IBD\": {\"PAGEINDEX\":\"\",\"PAGESIZE\":\"\",\"ISCOUNT\":\"\",\"REQUEST\":{\"EXACT\":{\"USER_NAME\":\"\",\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USERID\":\"00044d3a283b48338ab8c2d8a4372865\"},\"VAGUE\":{\"GROUPID\":\"\",\"DEFAULT\":\"\"}}}}";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_SearchUser",new Object[]{param}));
		
//		String param = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\",\"SNID\": \"20151012183823 74\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"},{\"IDENTIFIER\": \"\"}],\"GZIP\": \"true\",\"USER_ID\": \"26a030e47451452abb5508bfb676b1fa\",\"CLOUD_ID\":\"IBD104\"},						  \"GII_IBD\": {\"PAGEINDEX\": \"1\",\"PAGESIZE\":\"10240\",\"IS_COUNT\":\"0\",\"REQUEST\":{\"USERID\": \"26a030e47451452abb5508bfb676b1fa\",\"CLOUDID\": \"IBD104\",\"ISDOWNLOAD\":\"0\"}}}";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppByUserIdAndCloudId",new Object[]{param}));
		
		
	
//		String param = "{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20151013963944\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"},{\"IDENTIFIER\": \"\",\"USER_ID\": \"26a030e47451452abb5508bfb676b1fa\",\"CLOUD_ID\":\"IBD104\"}],\"GZIP\": \"true\"},						  \"GII_IBD\": {\"PAGEINDEX\": \"1\",\"PAGESIZE\":\"10240\",\"IS_COUNT\":\"0\",\"REQUEST\":{\"USERID\": \"26a030e47451452abb5508bfb676b1fa\",\"CLOUDID\": \"IBD104\",\"ISDOWNLOAD\":\"0\"}}}";
//		System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppByUserIdAndCloudId",new Object[]{param}));
//		String param = "{\"GII_HEAD\": {\"VERSION\": \"PC1.0\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"SNID\": \"\",\"GZIP\": \"false\"},\"GII_IBD\": {\"REQUEST\":[{\"USER_NAME\":\"haoyujing02\",  \"USER_PASSWORD\":\"e10adc3949ba59abbe56e057f20f883e\",\"ISETPUSER\":\"1\",\"SHORT_NAME\":\"\", \"USER_TRUENAME\":\"郝钰晶测试用02\",\"USER_NICKNAME\":\"\",\"USER_SEX\":\"\",\"USER_BIRTHDAY\":\"\",\"LOCATION_ID\":\"\",\"USERHEAD_URL\":\"\",\"BLOOD\":\"\",\"ADDRESS\":\"\",\"QQ\":\"\",\"EMAIL\":\"\",\"TEL\":\"\",\"MOBIL\":\"\",\"FAX\":\"\"}]}}";
//		System.out.println(callService("http://192.168.4.58:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_AddUser",new Object[]{param}));
		
		
//		String param1 = "{\"GII_HEAD\":{\"VERSION\":\"PC2.0\",\"SNID\":\"20151015141731489\",\"SIGN\":[{\"k\":\"\"},{\"v\":\"\"}],\"GZIP\":\"true\",\"CLOUD_ID\":\"38cc8c6da28e49b7372f36de73dff247\"},\"GII_IBD\":{\"REQUEST\":{\"USEID\":\"\",\"APPID\":\"63e4eb98fac348f2b9df601702eb0870\"}}}";
//		System.out.println(callService("http://192.168.1.164:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppByCloudIdAndAppIdOrUseId",new Object[]{param1}));
//		String param1 = "{\"GII_IBD\":{\"MAC\":\"D02AB94A7\",\"USEDTYPE\":\"phone\",\"OPEN_ID\":\"63371B0533167B79BD159CC0B823B1A7\",\"DATE\":\"201505211014\",\"OAUTH_TYPE\":\"QQ\"},\"GII_HEAD\":{\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode\": \"\"}],\"GZIP\":\"true\",\"VERSION\":\"iPhone1.5.3\",\"SNID\":\"201505211014\"}}";
//		System.out.println(callService("http://192.168.1.164:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_LOGIN",new Object[]{param1}));
		
		
//		String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"k\": \"\"},{\"v\": \"\"}],\"GZIP\": \"true\",\"CLOUD_ID\": \"eb065eb99c804f789510696e6c553cc4\"},\"GII_IBD\": {\"REQUEST\": {\"APP_ID\": \"4688db872d5941508be6cae5f34b6e59\",\"USE_ID\": \"3346e87ff7494508a571b754d5b1ee4b\",\"USER_ID\": \"209cac06029e4624b68698fa68a670f0\",\"CLOUD_ID\": \"eb065eb99c804f789510696e6c553cc4\"}}}";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_APP_GetAppModule",new Object[]{param1}));
		
//	
//		String _sIbdJson = "[{\"IOM_Version\": \"Web3.0\"},{\"userId\":\"e8d4e46e022a4a8e959f296c64f0847d\",\"serviceId\":\"dfe093c2aec84a0a9357b8259de2de10\",\"positionType\":\"0\"}]";
//		System.out.println(callService("http://114.112.90.40:9360/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Role_GetRoleListByOrganId",new Object[]{_sIbdJson}));
		
		
		
//		String json = "[{\"action\":0},{\"serviceId\":\"\", \"organList\":[{\"\":\"\"},{\"\":\"\"}]}]";
//		
//		String service = callService("http://192.168.1.43:8080/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Organ_AddOrUpdateOrganList", new Object[]{json});
//		System.out.println(service);
		
		
		
//		String param1 = "{\"GII_HEAD\": {\"VERSION\": \"PC3.0\", \"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITION/k\": \"\"},{\"RANDOMCODE/v\": \"\"}],\"IDENTIFIER\": \"\",\"USER_ID\": \"\",\"CLOUD_ID\":\"\",\"GZIP\": \"true\"}}";
//		System.out.println(callService("http://192.168.1.50:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_SYS_GetIBDVersion",new Object[]{param1}));
		
		
//		String _sIbdJson = "{\"GII_HEAD\": {\"VERSION\": \"PC2.0\",\"SNID\": \"20140113140645068\",\"SIGN\": [{\"POSITIONk\": \"\"},"
//				+ "{\"RANDOMCODEv\": \"\"}],\"GZIP\": \"false\"},\"GII_IBD\": {\"USER_NAME\": \"dsf@test.com\",\"USER_PASSWORD\": \"e10adc3949ba59abbe56e057f20f883e\"}}";
//	    String server = "http://114.112.90.40:9360/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_USER_LOGIN/";
	    
//	    String _sIbdJson ="{\"GII_HEAD\":{\"GZIP\":true,\"SNID\":1445998921289,\"VERSION\":\"PC2.0\"},\"GII_IBD\":{\"REQUEST\":{\"EXACT\":{\"ISETPUSER\":\"\",\"STATUS\":\"\",\"USERID\":\"209cac06029e4624b68698fa68a670f0\",\"USER_NAME\":\"\"}}}}";
//	    String server ="http://114.112.90.40:9360/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_USER_SearchUser";
//	    System.out.println(HttpUtil.sendPostRequest(server, _sIbdJson,false));
		
//		*****************2493ms**************
//		long _long1=System.currentTimeMillis();
//		String _sJson="{\"GII_HEAD\":{\"GZIP\":\"false\",\"SIGN\":[{\"POSITION/k\":\"\",\"RANDOMCODE/v\":\"\"}],\"SNID\":\"2015112410445412\",\"VERSION\":\"PC1.0\"},\"GII_IBD\":{\"ISCOUNT\":\"0\",\"PAGEINDEX\":\"\",\"PAGESIZE\":\"\",\"REQUEST\":{\"EXACT\":{\"ISETPUSER\":\"1\",\"STATUS\":\"0\",\"USERID\":\"a28cee84f48b4ed0a898b842102aee19,6b91a1b697284265a42b7705694643e2,5f733248bac14e08a16921024abfdce7,03abb566baea44499e1390092b25ef9a,48efc925251f44daaf94f4526f39c3f4,33600c75976c4c8a946b687f0ba9bac4,5bf15fd963264faa9b0b2c96821b884c,7c8fc0f5d56f4467a3fe18410011390c,470018065f924f3eb9675ccc033a78f7,301dde793fc54a79b0d40e2749757192,230dc1d57cce47a0abba3a67256caaea,122e26eb5c524350a06841e7f1a066a3,93f1f18ac58742fd8bfd4d43691e40e7,b08da6a1e26648719b16e246caf9fa24,c1d18786417947fdb3a4d11736744abf,336e4f23b99543bc9869f62f6645b2d6,b85841d028814d32ae1edeced0adc6ba,52ce8d5cfaef4543ad5b252715aa4890,8677fe19fcbc42fb8c69395e1326d2a6,0128fc41535b4307abe053f106b50d2c,ccc4b0811f334df686f1cadc9f5c032a,934e8fc584de4603829efd62e0144af0,c1b7a5b5772549b0a94a6f6e4831460e,f7a97927aa094275ad62d70ab0be5b38,d309a223618549268efaee96382d7975,5d909ddce60e4907a3180ef1744483aa,4349ab344e8b4f5399daa9bb604f1a7c,abcaeaadffce40ac9d3868d2a7f4d787,d4ac118110dc4a11939530eb61b7df2c,5bdac823b0504f678276c3951de74054,2c82d0f048864dafbc4f02d3bc8bad16,46ed7c4ea85b4f7d8cd8abe0ca37c4d7,5ce3a41160fe4f7a9eb441e336b25572,272249ac321641ecb7d65f8100a74f20,680d0041e2c74cf99f779e2c03119ebb,null,82790f8cfc0f4f90b37cbe078493b9b6,null,null,a0f68c042fa74c06b52ac88e5161be92,d2e870d650664a18bab0d6b60845cc65,ec3397c802574882aa16e22150d516bf,122c95b04cd24d2693bc068ceac1deb1,f1c083bf0cae4e39a488e1164b042b12,102878,103104,115977,103139,106276,109590,106524,106923,102855,102351,117104,106484,105556,104506,102245,110017,109176,108847,101913,115313,120078,105339,102312,104943,106946,104893,107435,110223,114039,107635,104054,107943,103910,114140,103763,103330,114391,101071,107715,105806,112372,101947,101171,105623,114666,102285,115842,105816,119715,119354,102368,102138,103272,102935,110972,105592,100753,104633,116882,116937,101406,104981,101206,114957,109207,105078,108604,117368,106046,102218,110857,101589,119327,100261,107562,110413,103842,100858,122309,104292,119814,109666,115513,104816,103440,104215,106219,105154,108573,109457,106366,109474,106480,114904,103203,109536,109506,112408,108799,104424,104203,101409,119779,114523,110621,119765,103686,101848,106675,119885,102079,120111,105474,100107,102020,109452,103942,108456,119229,102247,110000,117804,107111,103342,105983,108950,120887,101351,121995,108644,106388,110762,104071,100812,100081,106739,103466,110458,101033,110981,109192,109809,110539,105524,109988,101855,108167,114519,120027,115675\",\"USER_NAME\":\"\"},\"VAGUE\":{\"DEFAULT\":\"\",\"GROUPID\":\"38cc8c6da28e49b7372f36de73dff247\"}}}}";
//		  String server ="http://192.168.15.131:8080/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_USER_SearchUser";
//		    System.out.println(HttpUtil.sendPostRequest(server, _sJson,false));
//		    long _long2=System.currentTimeMillis();
//		    System.out.println(_long2-_long1+"ms");
//		    ************************1212ms*************
//		    String _sJson2="{\"GII_HEAD\":{\"VERSION\": \"PC2.0\",\"SNID\": \"1446536755\",\"SIGN\": [{\"position/k\": \"\"},{\"RandomCode/v\": \"\"}],\"GZIP\": \"fase\"},\"GII_IBD\": {\"PAGEINDEX\":\"\",\"PAGESIZE\":\"\",\"ISCOUNT\":\"\",\"SEARCHVALUE\":\"\",\"USERID\":\"a28cee84f48b4ed0a898b842102aee19,6b91a1b697284265a42b7705694643e2,5f733248bac14e08a16921024abfdce7,03abb566baea44499e1390092b25ef9a,48efc925251f44daaf94f4526f39c3f4,33600c75976c4c8a946b687f0ba9bac4,5bf15fd963264faa9b0b2c96821b884c,7c8fc0f5d56f4467a3fe18410011390c,470018065f924f3eb9675ccc033a78f7,301dde793fc54a79b0d40e2749757192,230dc1d57cce47a0abba3a67256caaea,122e26eb5c524350a06841e7f1a066a3,93f1f18ac58742fd8bfd4d43691e40e7,b08da6a1e26648719b16e246caf9fa24,c1d18786417947fdb3a4d11736744abf,336e4f23b99543bc9869f62f6645b2d6,b85841d028814d32ae1edeced0adc6ba,52ce8d5cfaef4543ad5b252715aa4890,8677fe19fcbc42fb8c69395e1326d2a6,0128fc41535b4307abe053f106b50d2c,ccc4b0811f334df686f1cadc9f5c032a,934e8fc584de4603829efd62e0144af0,c1b7a5b5772549b0a94a6f6e4831460e,f7a97927aa094275ad62d70ab0be5b38,d309a223618549268efaee96382d7975,5d909ddce60e4907a3180ef1744483aa,4349ab344e8b4f5399daa9bb604f1a7c,abcaeaadffce40ac9d3868d2a7f4d787,d4ac118110dc4a11939530eb61b7df2c,5bdac823b0504f678276c3951de74054,2c82d0f048864dafbc4f02d3bc8bad16,46ed7c4ea85b4f7d8cd8abe0ca37c4d7,5ce3a41160fe4f7a9eb441e336b25572,272249ac321641ecb7d65f8100a74f20,680d0041e2c74cf99f779e2c03119ebb,null,82790f8cfc0f4f90b37cbe078493b9b6,null,null,a0f68c042fa74c06b52ac88e5161be92,d2e870d650664a18bab0d6b60845cc65,ec3397c802574882aa16e22150d516bf,122c95b04cd24d2693bc068ceac1deb1,f1c083bf0cae4e39a488e1164b042b12,102878,103104,115977,103139,106276,109590,106524,106923,102855,102351,117104,106484,105556,104506,102245,110017,109176,108847,101913,115313,120078,105339,102312,104943,106946,104893,107435,110223,114039,107635,104054,107943,103910,114140,103763,103330,114391,101071,107715,105806,112372,101947,101171,105623,114666,102285,115842,105816,119715,119354,102368,102138,103272,102935,110972,105592,100753,104633,116882,116937,101406,104981,101206,114957,109207,105078,108604,117368,106046,102218,110857,101589,119327,100261,107562,110413,103842,100858,122309,104292,119814,109666,115513,104816,103440,104215,106219,105154,108573,109457,106366,109474,106480,114904,103203,109536,109506,112408,108799,104424,104203,101409,119779,114523,110621,119765,103686,101848,106675,119885,102079,120111,105474,100107,102020,109452,103942,108456,119229,102247,110000,117804,107111,103342,105983,108950,120887,101351,121995,108644,106388,110762,104071,100812,100081,106739,103466,110458,101033,110981,109192,109809,110539,105524,109988,101855,108167,114519,120027,115675\",\"CLOUD_ID\":\"38cc8c6da28e49b7372f36de73dff247\"}}";
//		    long _long3=System.currentTimeMillis();
//		    String server2 ="http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonRest/GII_USER_GetUsersBySearch";
//			    System.out.println(HttpUtil.sendPostRequest(server2, _sJson2,false));
//			    long _long4=System.currentTimeMillis();
//			    System.out.println(_long4-_long3+"ms");
		
		//String param1 = "";
//		long _long1=System.currentTimeMillis();
//		System.out.println(callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_SearchUser",new Object[]{_sJson}));
//		 long _long2=System.currentTimeMillis();
//		 System.out.println(_long2-_long1+"ms*******************");
		 
//			long _long3=System.currentTimeMillis();
//			System.out.println(callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/Ibd_CommonService?wsdl","GII_USER_GetUsersBySearch",new Object[]{_sJson2}));
//			 long _long4=System.currentTimeMillis();
//			 System.out.println(_long4-_long3+"ms");
		
//		String json = "[{\"IOM_Version\": \"Web3.0\"},{\"positionId\": \"b900aff998b34299a26851042d5660f1\",\"pageSize\":\"10000\",\"pageIndex\":\"1\",\"searchValue\": \"\",\"serviceId\": \"38cc8c6da28e49b7372f36de73dff247\"}]";	
//		String service = callService("http://192.168.1.27:8080/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Position_GetMemberListByPositionId", new Object[]{json});
//		System.out.println(service);
		
		String json = "[{\"IOM_Version\": \"Web3.0\"},{\"positionId\": \"0b0ada782d1545e2b488b3dc074e8d87\",\"pageSize\":\"10000\",\"pageIndex\":\"1\",\"searchValue\": \"\",\"serviceId\": \"c0ed35628e6b4abba54f55591d2ced42\"}]";	
		String service = callService("http://192.168.4.80/Gnet_Ibd_Iom/services/IOM_CommonService?wsdl","IOM_Position_GetMemberListByPositionId", new Object[]{json});
		System.out.println(service);
		 
	}
}
