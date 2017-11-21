package com.key.api.ws.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 加载，写入properties配置文件信息
 *
 */

public class PropertyUtil {
	// spring的配置文件名称
	private static  String fileName="application-jdbc.properties";
	private static  File f = new File(PropertyUtil.class.getResource("/").getPath()+"/"+fileName);
	public static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

	public static String getProperty(String key) {  
        String value = "";  
        // 第一步是取得一个Properties对象  
        Properties props = new PropertiesImpl();  
        // 第二步是取得配置文件的输入流  
        InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);//在非WEB环境下用这种方式比较方便  
        try {  
        	// 第三步讲配置文件的输入流load到Properties对象中，这样在后面就可以直接取来用了  
            props.load(is);  
            value = props.getProperty(key);  
            is.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return value;  
    } 
	public static void setProperty(String key,String value) {  
		
		// 第一步也是取得一个Properties对象  
		Properties props = new PropertiesImpl();  
		// 第二步也是取得该配置文件的输入流  
		        try {  
		        	InputStream is = new FileInputStream(f); 
		// 第三步是把配置文件的输入流load到Properties对象中，  
		            props.load(is);  
		            is.close();  
		// 接下来就可以随便往配置文件里面添加内容了  
		            props.setProperty(key, value); 
		// 在保存配置文件之前还需要取得该配置文件的输出流，切记，如果该项目是需要导出的且是一个非WEB项目，则该配置文件应当放在根目录下，否则会提示找不到配置文件  
		            OutputStream out = new FileOutputStream(f);  
		// 最后就是利用Properties对象保存配置文件的输出流到文件中;  
		            props.store(out,null);  
		            out.close();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
		    }  
	 public static  void deleteKey(String key){
		try {
			Properties props = new PropertiesImpl();
			InputStream is = new FileInputStream(f);
			// 第三步是把配置文件的输入流load到Properties对象中，
			props.load(is);
			is.close();
			if (!props.containsKey(key)) {
				return;
			}
			props.remove(key);
			OutputStream out = new FileOutputStream(f);
			// 最后就是利用Properties对象保存配置文件的输出流到文件中;
			props.store(out, null);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("write fail");
		}
		    }
	private PropertyUtil() {}
}