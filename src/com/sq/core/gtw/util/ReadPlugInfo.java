package com.sq.core.gtw.util;

import gtwplatfrom.Activator;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

/**
 * @作者 whai
 * @创建日期 2013-7-25
 * @版本 V1.0
 * @文件名 ReadPlugInfo.java
 */
public class ReadPlugInfo {
	
	/**
	 * 读取插件文件的路径
	 * @param obj
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getFilePath(Class cls , String fileName){
		URL urlPath = Activator.getDefault().getBundle().getResource(cls.getResource(fileName).getPath());
		try {
			return FileLocator.toFileURL(urlPath).getFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
