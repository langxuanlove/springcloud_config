package com.key.api.ws.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;
/**
 * 
 * 动态路由指定数据源
 * @author Kui
 *
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {
	// 主数据源
	private static final String SPRING_SOURCE="spring";
	// 更多数据源
	private static final String CUSTOM_SOURCE="custom";
	// 间隔符号
	private static final String MARK=".datasource.";
	// 常量数组
	private static final String[] paramsSource={"type","driverClassName","url","username","password"};
	// 存储所有的数据源(默认数据源和多个数据源).
	private Map<Object, Object> targetDataSources= new HashMap<Object, Object>();
	// 转换类
	public  ConversionService conversionService = new DefaultConversionService(); 
	// 存储除了type,driverClassName,url,username,password这五个参数以外的参数.例如spring.datasource.minIdle=1等参数.
	public  PropertyValues dataSourcePropertyValues;
	// 定义数据源
	public  DataSource defaultDataSource;
	// 注入标准的Servlet环境变量.
	@Autowired
	private StandardServletEnvironment env;
	
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
	@Override
	public void afterPropertiesSet() {
		this.initDefaultDataSource(env);
		this.initCustomDataSources(env); 
		super.afterPropertiesSet();
	}
    /**
     * 初始化主数据源
     * @param env
     */
    public void initDefaultDataSource(Environment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, SPRING_SOURCE);
        Map<String, Object> dsMap = propertyResolver.getSubProperties(MARK);
        defaultDataSource = buildDataSource(dsMap);
        dataBinder(defaultDataSource, env);
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        this.setDefaultTargetDataSource(defaultDataSource);
    }
    /**
     * 初始化更多数据源
     * @param env
     */
    public void initCustomDataSources(StandardServletEnvironment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, CUSTOM_SOURCE+MARK);
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = buildDataSource(dsMap);
            targetDataSources.put(dsPrefix, ds);
            DynamicDataSourceContextHolder.dataSourceIds.add(dsPrefix);
            dataBinder(ds, env);
        }
        this.setTargetDataSources(targetDataSources);
    }
    
    /**
     * 创建DataSource
     * @param dsMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
		try {
			Class<? extends DataSource> dataSourceType;
			dataSourceType = (Class<? extends DataSource>) Class.forName((String) dsMap.get(paramsSource[0]));
			return DataSourceBuilder.create()
					.driverClassName((String) dsMap.get(paramsSource[1]))
					.url((String) dsMap.get(paramsSource[2]))
					.username((String) dsMap.get(paramsSource[3]))
					.password((String) dsMap.get(paramsSource[4]))
					.type(dataSourceType).build();
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
  
    /**
     * 为DataSource绑定更多数据
     * 共通绑定类
     * 
     * @param dataSource
     * @param env
     */
    private void dataBinder(DataSource dataSource, Environment env){
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        if(dataSourcePropertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, SPRING_SOURCE).getSubProperties(MARK);
            Map<String, Object> values = new HashMap<String, Object>(rpr);
            // 排除主数据源设置的属性,供以后数据源进行设置.
            for (int i = 0; i < paramsSource.length; i++) {
            	values.remove(paramsSource[i]);
			}
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

   
    /**
     * 动态创建新数据源,接口调用,类似更新接口重新绑定.
     * @param dsMap
     * 
     */
    public void addNewCustomDataSources(Map<String,Object> dsMap,String cloudId) {
		DataSource ds = buildDataSource(dsMap);
		// 创建新的数据源,加入到targetDataSources中去.
		targetDataSources.put(cloudId, ds);
		Properties properties=new Properties();
		properties.setProperty("custom.datasource.names",(String)dsMap.get("names"));
		for (int i = 0; i < paramsSource.length; i++) {
			properties.setProperty("custom.datasource."+cloudId+"."+paramsSource[i], (String)dsMap.get(paramsSource[i]));
		}
		PropertiesPropertySource propertySource =new PropertiesPropertySource(cloudId,properties); 
		// 优先级是先取出第一个key值，加在最后会自动忽略.
    	env.getPropertySources().addFirst(propertySource);
		dataBinder(ds, env);
        DynamicDataSourceContextHolder.dataSourceIds.add(cloudId);
        this.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
    /**
     * 动态删除新数据源,接口调用
     * @param dsMap
     * @param cloudId
     */
    
    public void deleteNewCustomDataSources(Map<String,Object> dsMap,String cloudId) {
    	targetDataSources.remove(cloudId);
    	env.getPropertySources().remove(cloudId);
    	DynamicDataSourceContextHolder.dataSourceIds.remove(cloudId);
    	this.setTargetDataSources(targetDataSources);
    	super.afterPropertiesSet();
    }
}
