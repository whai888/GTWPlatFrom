package com.sq.core.gtw.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @作者 whai
 * @创建日期 2013-7-13
 * @版本 V1.0
 * @文件名 ReadProperties.java
 */
public class ReadProperties {
	
	/**
	 * 读BIZ文件路径
	 */
	private static String systemFileName = "system" ;
	private static ResourceBundle systemResource ;
	
	/**
	 * 加载资源文件
	 */
	static{
		Locale locale = Locale.getDefault();
		systemResource = ResourceBundle.getBundle("config/pub/" + systemFileName , locale);
	}
	
	/**
	 * 取系统文件中的key
	 * @param key
	 * @return
	 */
	public static String getSystemKey(String key){
		return systemResource.getString(key);
	}

}
