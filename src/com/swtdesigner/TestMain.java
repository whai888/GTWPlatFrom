package com.swtdesigner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;
import com.thoughtworks.xstream.XStream;


/**
 * @���� whai
 * @�������� 2013-7-14
 * @�汾 V1.0
 * @�ļ��� TestMain.java
 */
public class TestMain {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String bizMain = ReadProperties.getSystemKey("BIZ_MAIN");
		XStream xstream = new XStream();
		xstream.alias("bizs", List.class);
		xstream.autodetectAnnotations(true);
		FlowMgnBizInfo  bizInfo = new FlowMgnBizInfo ();
		FlowMgnBizInfo  bizInfo1 = new FlowMgnBizInfo ();
		bizInfo1.setCode("10000001");
		bizInfo1.setName("������Ϣ1");
		FlowMgnBizInfo  bizInfo2 = new FlowMgnBizInfo ();
		bizInfo2.setCode("10000002");
		bizInfo2.setName("������Ϣ2");
		FlowMgnBizInfo  bizInfo3 = new FlowMgnBizInfo ();
		bizInfo3.setCode("10000003");
		bizInfo3.setName("������Ϣ3");
		List list = new ArrayList();
		list.add(bizInfo1);
		list.add(bizInfo2);
		list.add(bizInfo3);
		bizInfo.setBiz(list);
		FileOutputStream outputStream = new FileOutputStream(bizMain);
		xstream.toXML(list, outputStream);
		
		 FileInputStream inputStream = new FileInputStream(bizMain);  
		 List bizInfoTemp = (List)xstream.fromXML(inputStream);  
		
		for (int i = 0; i < bizInfoTemp.size(); i++) {
			FlowMgnBizInfo  temp = (FlowMgnBizInfo)bizInfo.getBiz().get(i);
			System.out.println("code=" + temp.getCode() + "   name=" + temp.getName());
		}
	}

}
