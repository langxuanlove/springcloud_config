package com.key.api.ws.impl;


import java.util.ArrayList;
import java.util.List;

/**
 * 当前线程中切换数据源
 * 
 * @author Kui
 *
 */

public class DynamicDataSourceContextHolder {
	
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSource当前是否存在
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
