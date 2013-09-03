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
 * @作者 whai
 * @创建日期 2013-7-15
 * @版本 V1.0
 * @文件名 ContansUtil.java
 */
public class ConstantUtil {
	
	public static String PROPERTY_VIEW =  "org.eclipse.ui.views.PropertySheet";
//	public static String PROPERTY_VIEW =  FlowMgnPageBookView.ID;
	
	public static String UNCODE_GBK = "GBK" ;
	
	public static String UNCODE_UTF8 = "UTF-8" ;
	
	//数据库文件的后缀
	public static String DATE_APPEN = ".xml" ;
	
	//展示层结尾的文件名后缀
	public static String[] VIEW_SUFFIX = new String[]{".jsp"};
	
	public static String FILE_XML_HEAD = "<?xml version=\"1.0\" encoding=\""+UNCODE_UTF8+"\" ?>\n";
	//mybatis文件头
	public static String MYBATIS_XML_HEAD = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n";
	
	//服务定义
	public static String[] SERVICE_STR = new String[]{"FTPFileService"} ;
	
	//字符串报文
	public static String[] DATA_PKG = new String[] {"字符串报文" , "XML报文"} ;
	
	//交易视图
	public static String[] TRANS_VIEW = new String[]{"视图层" , "业务逻辑层" , "数据库层"};
	
	//通讯视图
	public static String[] CONN_VIEW = new String[]{"通讯服务"};
	
	//公用业务组件定义
	public static String[] BIZ_DEFINITION_VIEW = new String[]{"业务组件定义","数据操作组件","通讯组件","数据库组件"};
	
	public static String[] PKG_VIEW = new String[] {"报文管理"} ;
	
	//数据库类型
	public static String[] DB_TYPE = new String[]{"MYSQL"};
	
	//文件传输类型
	public static String[] FILE_SEND_TYPE = new String[]{"上传" , "下载"};
	
	//文件传输方式
	public static String[] FILE_SEND_STYLE = new String[]{"二进制" , "文本"};
	
	//事物类型
	public static String[] TRANSACTION_TYPE = new String[]{"全局事务","独立事务"};
	
	//业务逻辑自定义组件
	public static String[] BIZ_DEFINITION_COMBO_TYPE = new String[]{"Text"};
	
	//boolean类型
	public static String[] CONSTANT_BOOLEAN = new String[]{"true","false"};
	
	//业务逻辑Action Palette定义
	public static Map<String , List<FlowActionPaletteInfo>> ACTION_PALETTE = new LinkedHashMap<String , List<FlowActionPaletteInfo>>();
	
	static{
		List<FlowActionPaletteInfo> actionList = new ArrayList<FlowActionPaletteInfo>();
		FlowActionPaletteInfo actionPalette = new FlowActionPaletteInfo();
			actionPalette.setImage(IImageKeys.ACTION_ACTION);
			actionPalette.setContentName("ACTION");
			actionPalette.setName("创建Action");
			actionPalette.setiActionModelProperties(new ActionModelPropertiesAction());
			actionList.add(actionPalette);
		actionPalette = new FlowActionPaletteInfo();
			actionPalette.setImage(IImageKeys.ACTION_FLOW);
			actionPalette.setContentName("FLOW");
			actionPalette.setName("创建Flow");
			actionPalette.setiActionModelProperties(new ActionModelPropertiesFlow());
			actionList.add(actionPalette);
		actionPalette = new FlowActionPaletteInfo();
			actionPalette.setImage(IImageKeys.ACTION_VIEW);
			actionPalette.setContentName("VIEW");
			actionPalette.setName("创建View");
			actionPalette.setiActionModelProperties(new ActionModelPropertiesView());
			actionList.add(actionPalette);
		ACTION_PALETTE.put("视图组件", actionList);
		
	}
}
