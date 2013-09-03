package com.sq.core.gtw.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesAction;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesFlow;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesView;
import com.sq.core.gtw.edit.vo.FlowActionPaletteInfo;

/**
 * @���� whai
 * @�������� 2013-7-15
 * @�汾 V1.0
 * @�ļ��� ContansUtil.java
 */
public class ConstantUtil {
	
	public static String PROPERTY_VIEW =  "org.eclipse.ui.views.PropertySheet";
//	public static String PROPERTY_VIEW =  FlowMgnPageBookView.ID;
	
	public static String UNCODE_GBK = "GBK" ;
	
	public static String UNCODE_UTF8 = "UTF-8" ;
	
	//���ݿ��ļ��ĺ�׺
	public static String DATE_APPEN = ".xml" ;
	
	//չʾ���β���ļ�����׺
	public static String[] VIEW_SUFFIX = new String[]{".jsp"};
	
	public static String FILE_XML_HEAD = "<?xml version=\"1.0\" encoding=\""+UNCODE_UTF8+"\" ?>\n";
	//mybatis�ļ�ͷ
	public static String MYBATIS_XML_HEAD = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n";
	
	//������
	public static String[] SERVICE_STR = new String[]{"FTPFileService"} ;
	
	//�ַ�������
	public static String[] DATA_PKG = new String[] {"�ַ�������" , "XML����"} ;
	
	//������ͼ
	public static String[] TRANS_VIEW = new String[]{"��ͼ��" , "ҵ���߼���" , "���ݿ��"};
	
	//ͨѶ��ͼ
	public static String[] CONN_VIEW = new String[]{"ͨѶ����"};
	
	//����ҵ���������
	public static String[] BIZ_DEFINITION_VIEW = new String[]{"ҵ���������","���ݲ������","ͨѶ���","���ݿ����"};
	
	public static String[] PKG_VIEW = new String[] {"���Ĺ���"} ;
	
	//���ݿ�����
	public static String[] DB_TYPE = new String[]{"MYSQL"};
	
	//�ļ���������
	public static String[] FILE_SEND_TYPE = new String[]{"�ϴ�" , "����"};
	
	//�ļ����䷽ʽ
	public static String[] FILE_SEND_STYLE = new String[]{"������" , "�ı�"};
	
	//��������
	public static String[] TRANSACTION_TYPE = new String[]{"ȫ������","��������"};
	
	//ҵ���߼��Զ������
	public static String[] BIZ_DEFINITION_COMBO_TYPE = new String[]{"Text"};
	
	//boolean����
	public static String[] CONSTANT_BOOLEAN = new String[]{"true","false"};
	
	//ҵ���߼�Action Palette����
	public static Map<String , List<FlowActionPaletteInfo>> ACTION_PALETTE = new LinkedHashMap<String , List<FlowActionPaletteInfo>>();
	
	static{
		List<FlowActionPaletteInfo> actionList = new ArrayList<FlowActionPaletteInfo>();
		FlowActionPaletteInfo actionPalette = new FlowActionPaletteInfo();
			actionPalette.setImage(IImageKeys.ACTION_ACTION);
			actionPalette.setContentName("ACTION");
			actionPalette.setName("����Action");
			actionPalette.setiActionModelProperties(new ActionModelPropertiesAction());
			actionList.add(actionPalette);
		actionPalette = new FlowActionPaletteInfo();
			actionPalette.setImage(IImageKeys.ACTION_FLOW);
			actionPalette.setContentName("FLOW");
			actionPalette.setName("����Flow");
			actionPalette.setiActionModelProperties(new ActionModelPropertiesFlow());
			actionList.add(actionPalette);
		actionPalette = new FlowActionPaletteInfo();
			actionPalette.setImage(IImageKeys.ACTION_VIEW);
			actionPalette.setContentName("VIEW");
			actionPalette.setName("����View");
			actionPalette.setiActionModelProperties(new ActionModelPropertiesView());
			actionList.add(actionPalette);
		ACTION_PALETTE.put("��ͼ���", actionList);
		
	}
}
