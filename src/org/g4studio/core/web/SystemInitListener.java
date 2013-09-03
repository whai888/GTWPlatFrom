package org.g4studio.core.web;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.model.SpringBeanLoader;
import org.g4studio.core.model.dao.Dao;
import org.g4studio.core.model.dao.Reader;
import org.g4studio.core.properties.PropertiesFactory;
import org.g4studio.core.properties.PropertiesFile;
import org.g4studio.core.properties.PropertiesHelper;
import org.g4studio.core.util.G4Constants;
import org.g4studio.core.util.G4Utils;
import org.g4studio.system.admin.service.MonitorService;
import org.g4studio.system.common.util.SystemConstants;
import org.springframework.context.ApplicationContext;

import com.sq.core.gtw.edit.model.impl.ActionContentsModel;
import com.sq.core.gtw.edit.model.impl.BizContentsModel;
import com.sq.core.gtw.edit.vo.BizTransInfo;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.pub.vo.FlowMgnActionInfo;
import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;
import com.thoughtworks.xstream.XStream;

/**
 * 系统启动监听器
 * 
 * @author XiongChun
 * @since 2010-04-13
 */
public class SystemInitListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(SystemInitListener.class);
	private boolean success = true;
	private XStream xstream = new XStream();
	@SuppressWarnings("unused")
	private List<FlowMgnActionInfo> flowMgnActionList = new ArrayList<FlowMgnActionInfo>();
	private static String bizMain = ReadProperties.getSystemKey("BIZ_MAIN");
	private static String bizPath = ReadProperties.getSystemKey("BIZ_PATH");
	private static String actionMain = ReadProperties.getSystemKey("ACTION_MAIN");
	private static String actionPath = ReadProperties.getSystemKey("ACTION_PATH");
	@SuppressWarnings("unused")
	private ApplicationContext wac = null;

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		systemStartup(sce.getServletContext());
	}

	/**
	 * 应用平台启动
	 */
	@SuppressWarnings("unchecked")
	private void systemStartup(ServletContext servletContext) {
		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
		String forceLoad = pHelper.getValue("forceLoad", SystemConstants.FORCELOAD_N);
		long start = System.currentTimeMillis();
		if (forceLoad.equalsIgnoreCase(SystemConstants.FORCELOAD_N)) {
			log.info("*******************************************************");
			log.info("G4系统集成与应用开发平台[G4Studio]开始启动...");
			log.info("*******************************************************");
		}
		try {
			wac = SpringBeanLoader.getApplicationContext();
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		if (success) {
			MonitorService monitorService = (MonitorService) SpringBeanLoader.getSpringBean("monitorService");
			monitorService.deleteHttpSession(new BaseDto());
			try {
				initDbType();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (success) {
			log.info("系统开始启动字典装载程序...");
			log.info("开始加载字典...");
			Reader g4Reader = (Reader) SpringBeanLoader.getSpringBean("g4Reader");
			List codeList = null;
			try {
				codeList = g4Reader.queryForList("Resource.getCodeViewList");
				log.info("字典加载成功!");
			} catch (Exception e) {
				success = false;
				log.error("字典加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EACODELIST", codeList);
		}
		if (success) {
			log.info("系统开始启动全局参数表装载程序...");
			log.info("开始加载全局参数表...");
			List paramList = null;
			try {
				Reader g4Reader = (Reader) SpringBeanLoader.getSpringBean("g4Reader");
				paramList = g4Reader.queryForList("Resource.getParamList");
				log.info("全局参数表加载成功!");
			} catch (Exception e) {
				success = false;
				log.error("全局参数表加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EAPARAMLIST", paramList);
		}
		if (success) {
			log.info("系统开始启动交易配置文件...");
			Map<String , ActionContentsModel> actionConfigMap = null;
			Map<String , BizTransInfo> bizConfigMap = null;
			try {
				log.info("开始加载ACTION交易配置文件...");
				actionConfigMap = initActionConfig();
				log.info("ACTION交易配置文件加载成功!");
				log.info("*******************************************************");
				log.info("开始加载BIZ交易配置文件...");
				bizConfigMap = initBizConfig();
				log.info("BIZ交易配置文件加载成功!");
			} catch (Exception e) {
				success = false;
				log.error("交易配置文件加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("ACTIONCONFIGMAP", actionConfigMap);
			servletContext.setAttribute("BIZCONFIGMAP", bizConfigMap);
		}
		long timeSec = (System.currentTimeMillis() - start) / 1000;
		log.info("********************************************");
		if (success) {
			log.info("G4系统集成与应用开发平台[G4Studio]启动成功[" + G4Utils.getCurrentTime() + "]");
			log.info("启动总耗时: " + timeSec / 60 + "分 " + timeSec % 60 + "秒 ");
		} else {
			log.error("G4系统集成与应用开发平台[G4Studio]启动失败[" + G4Utils.getCurrentTime() + "]");
			log.error("启动总耗时: " + timeSec / 60 + "分" + timeSec % 60 + "秒");
		}
		log.info("********************************************");
	}

	@SuppressWarnings("unchecked")
	private Map<String, BizTransInfo> initBizConfig() {
		// TODO Auto-generated method stub
		Map<String ,BizTransInfo> bizConfigMap = new HashMap<String, BizTransInfo>();
		xstream.alias("bizs", List.class);
		xstream.processAnnotations(FlowMgnBizInfo.class);
		List<FlowMgnBizInfo> flowMgnBizInfoList = (List<FlowMgnBizInfo>)XMLFileOp.readToFile(bizMain, xstream);
		if(flowMgnBizInfoList != null){
			for (FlowMgnBizInfo flowMgnBizInfo : flowMgnBizInfoList) {
				String path = bizPath + File.separator + flowMgnBizInfo.getCode() + ".biz";
				xstream.processAnnotations(BizTransInfo.class);
				BizTransInfo bizTransInfo = (BizTransInfo)XMLFileOp.readToFile(path, xstream);
				bizConfigMap.put(flowMgnBizInfo.getCode(), bizTransInfo);
			}
		}
		return bizConfigMap;
	}

	@SuppressWarnings("unchecked")
	private Map<String ,ActionContentsModel> initActionConfig() {
		// TODO Auto-generated method stub
		Map<String ,ActionContentsModel> actionConfigMap = new HashMap<String, ActionContentsModel>();
		xstream.alias("actions", List.class);
		xstream.processAnnotations(FlowMgnActionInfo.class);
		List<FlowMgnActionInfo> flowMgnActionList = (List<FlowMgnActionInfo>)XMLFileOp.readToFile(actionMain, xstream);
		if(flowMgnActionList != null){
			for (FlowMgnActionInfo flowMgnActionInfo : flowMgnActionList) {
				String path = actionPath + File.separator + flowMgnActionInfo.getCode() + ".action";
				ActionContentsModel actionContentsModel = (ActionContentsModel)XMLFileOp.readToFile(path, xstream);
				actionConfigMap.put(flowMgnActionInfo.getCode(), actionContentsModel);
			}
		}
		return actionConfigMap;
	}

	/**
	 * 识别缺省的JDBC驱动类型(g4Dao)
	 * 
	 * @throws SQLException
	 */
	private void initDbType() throws SQLException {
		Dao g4Dao = (Dao) SpringBeanLoader.getSpringBean("g4Dao");
		Connection connection = g4Dao.getConnection();
		String dbString = connection.getMetaData().getDatabaseProductName().toLowerCase();
		try {
			connection.close();
		} catch (Exception e) {
			log.error(G4Constants.Exception_Head + "未正常关闭数据库连接");
			e.printStackTrace();
		}
		if (dbString.indexOf("ora") > -1) {
			System.setProperty("g4Dao.db", "oracle");
		} else if (dbString.indexOf("mysql") > -1) {
			System.setProperty("g4Dao.db", "mysql");
		}else if (dbString.indexOf("microsoft") > -1) {
			System.setProperty("g4Dao.db", "sqlserver");
		} else {
			if (log.isErrorEnabled()) {
				log.error(G4Constants.Exception_Head + "G4Studio平台目前还不支持你使用的数据库产品.如需获得支持,请和我们联系!");
			}
			System.exit(0);
		}
	}
}
