package com.sq.core.gtw.util;

import gtwplatfrom.Activator;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

/**
 * @���� whai
 * @�������� 2013-7-25
 * @�汾 V1.0
 * @�ļ��� ReadPlugInfo.java
 */
public class ReadPlugInfo {
	
	/**
	 * ��ȡ����ļ���·��
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
