/*   
 * Copyright (c) 2014-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */ 
package com.key.api.util; 

public class Constants {
	
	//5个默认管理角色的id
	public static final String TECH_MANAGER_KEY = "techmanager";
	public static final String BUSINESS_MANAGER_KEY = "businessmanager";
	public static final String MARKET_MANAGER_KEY = "marketmanager";
	public static final String ADMINISTRATIVE_MANAGER_KEY = "administrativemanager";
	public static final String OTHER_MANAGER_KEY = "othermanager";
	
	public static final String CLOUD_KEY = "IOM.groupKey";
	
	public static final String USER_KEY = "IOM.userKey";
	
	public static final String CLOUD_MANAGER_KEY = "IOM.cloudCreator";
	
	public static final String ALLOW_MOREORGAN = "IOM.isAllowMoreOrgan";
	
	//排序字段初始化时的差值
	public static double SORT_DIFF = 10;
	
	public static String DS_TYPE = "dbcp";

    public static boolean HAS_QUENE = false;
    public static int QUENE_MAX = 1000;

    public static boolean HAS_OAUTH = false;

    public static String OAUTH_CODE_ERROR = "900";
    public static String QUENE_CODE_ERROR = "800";

    public static String MYSQL = "msyql";

    public static final String JSON_HEAD = "GII_Head";// JSON传出头
    public static final String[] HEAD_REQUEST = {"version", "snid", "sign" };// 请求头信息
    public static final String[] HEAD_RESPONSE = { "SNID", "RESCODE","MEMO" , "SIGN"};// 相应头信息
    public static final int SYSTEM_IMG = 0;// 系统图片
    public static final int LOCAL_IMG = 1;// 本地图片
    public static final int NETWORK_CONNECTION=2;// 网络连接
    public static final String paramPath = "config/global.properties";
    
	public static final String RESPONSE_HEAD="GII_HEAD";
	public static final String HEAD_VERSION="VERSION";
	public static final String HEAD_SNID="SNID";
	public static final String HEAD_SIGN="SIGN";
	public static final String HEAD_RESCODE="RESCODE";
	public static final String HEAD_MSG="MSG";
	public static final String HEAD_INFO="INFO";
	public static final String HEAD_GZIP="GZIP";
	public static final String RESPONSE_GII_IBD="GII_IBD";
	
	public static final String REQUEST_VERSION="VERSION";
	public static final String REQUEST_HEAD="GII_HEAD";
	public static final String REQUEST_SNID="SNID";
	public static final String REQUEST_SIGN="SIGN";
	public static final String REQUEST_POS="POSITION/k";
	public static final String REQUEST_RAND="RANDOMCODE/v";
	public static final String REQUEST_GZIP="GZIP";
	public static final String REQUEST_GII_IBD="GII_IBD";
	
    public static final String IOM_HEAD="IOM_Head";
	public static final String IOM_HEAD_RESULT="result";
	public static final String IOM_HEAD_MESSAGE="message";
	public static final String IOM_LIST="IOM_List";

	public static final Object REQUEST_HEAD2 = null;
	
	public static final String IDENTIFIER_KEY="IDENTIFIER";
	public static final String USERID_KEY="USER_ID";
	public static final String CLOUDID_KEY="CLOUD_ID";
	
	public static final String USER_LOGIN="LOGIN";
    public static final String USER_LOGOFF="LOGOFF";
    
    /**
     * 公有云ID标示 
     */
    public static String PUBLIC_CLOUD_ID = "38cc8c6da28e49b7372f36de73dff247";//公有云ID
    public static String PUBLIC_USER_ID = "1000c5bc41c807f4dfb968b3c0cbd728549";//公有云管理员ID
    public static String SYS_USER_ID = "1000000000000000000000000000000000";//系统用户的ID
    
	public static final String HTTPHEAD_WS="http://";
	
	public static final String RESOURCE_APP="app";
	public static final String RESOURCE_CLOUD="cloud";
	public static final String RESOURCE_USER="user";
	public static final String RESOURCE_SYS="sys";
	public static final String RESOURCE_ICM="icm";
	
	//spring中的bean标识
	public static final String RESOURCE_SERVICE = "resourceService";
	public static final String IBD_WEB_SERVICE = "ibdWebService";
	
}