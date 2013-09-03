package com.sq.core.gtw.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @���� whai
 * @�������� 2013-7-13
 * @�汾 V1.0
 * @�ļ��� ReadProperties.java
 */
public class ReadProperties {
	
	/**
	 * ��BIZ�ļ�·��
	 */
	private static String systemFileName = "system" ;
	private static ResourceBundle systemResource ;
	
	/**
	 * ������Դ�ļ�
	 */
	static{
		Locale locale = Locale.getDefault();
		systemResource = ResourceBundle.getBundle("config/pub/" + systemFileName , locale);
	}
	
	/**
	 * ȡϵͳ�ļ��е�key
	 * @param key
	 * @return
	 */
	public static String getSystemKey(String key){
		return systemResource.getString(key);
	}

}
