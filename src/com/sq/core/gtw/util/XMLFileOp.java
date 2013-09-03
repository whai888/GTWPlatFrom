package com.sq.core.gtw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import com.thoughtworks.xstream.XStream;

public class XMLFileOp {

	/**
	 * �������ݵ��ļ���
	 */
	public static void saveToFile(String path , Object obj , String headStr){
		XStream xstream = new XStream();
		OutputStreamWriter writer = null  ;
		FileOutputStream fos = null ;
		// ����XML�ļ�
		try {
			File file = new File(path) ;
			if(!file.exists()){
				//�����Ŀ¼�ļ����򴴽���Ŀ¼
				if(!file.getParentFile().exists()){
					if(!file.getParentFile().mkdirs()){
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ","����Ŀ���ļ����ڵ�Ŀ¼ʧ�ܣ�" + path);
						return ;
					}
				}
				if(!file.createNewFile()){
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ","����Ŀ���ļ�ʧ�ܣ�" + path);
					return ;
				}
			}
			fos = new FileOutputStream(file);
			writer = new OutputStreamWriter(fos , ConstantUtil.UNCODE_UTF8);
			writer.write(ConstantUtil.FILE_XML_HEAD);
			if(headStr != null)
				writer.write(headStr);
			xstream.autodetectAnnotations(true);
			xstream.toXML(obj, writer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(writer !=null)
					writer.close();
				if(fos !=null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ȡ�����ļ�������
	 * @param path
	 * @param obj
	 */
	public static Object readToFile(String path , XStream xstream){
		InputStream inStream = null ;
		BufferedReader br = null ;
		Object obj = null ;
		try {
			inStream = new FileInputStream(path) ;
			br = new BufferedReader(  
                    new InputStreamReader(inStream , ConstantUtil.UNCODE_UTF8));  
			obj = xstream.fromXML(br);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e instanceof FileNotFoundException)
				return  obj;
			e.printStackTrace();
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ","�ļ����ش������飬�ļ�·����" + path + " , ������Ϣ��" + e.getMessage());
		}finally{
			try {
				if(inStream !=null )
					inStream.close();
				if(br !=null )
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj; 
	}
}
