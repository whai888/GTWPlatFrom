package com.sq.core.gtw.db.mysql;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sq.core.gtw.db.IDateCreateFile;
import com.sq.core.gtw.db.vo.Delete;
import com.sq.core.gtw.db.vo.Insert;
import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.db.vo.Select;
import com.sq.core.gtw.db.vo.Update;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.dialog.DataGenerateScriptTwoWizardPage;
import com.sq.core.gtw.view.pub.vo.DataMgnInfoVo;
import com.thoughtworks.xstream.XStream;

public class IDateCreateFileImp implements IDateCreateFile{

	private static String modelPath = ReadProperties.getSystemKey("MODEL_PATH");
	private static String modelMain = ReadProperties.getSystemKey("MODEL_MAIN");
	private IMySqlConnImp iMySqlConnImp = new IMySqlConnImp();
	
	@SuppressWarnings("unchecked")
	@Override
	public void initDateFile(DataGenerateScriptTwoWizardPage two) {
		//获取选择的表
		String[] result = two.getResult();
		// TODO Auto-generated method stub
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.alias("dao", List.class);
		xstream.processAnnotations(DataMgnInfoVo.class);
		List<DataMgnInfoVo> dataMgnList = (List<DataMgnInfoVo>)XMLFileOp.readToFile(modelMain, xstream);
		//遍历表名，生产数据库脚本文件
		for (String tableStr : result) {
			String path = modelPath + File.separator + tableStr + ConstantUtil.DATE_APPEN ;
			MyBatisMain myBatisMain = new MyBatisMain();
			myBatisMain.setNamespace(tableStr);
			if(two.getButton().getSelection()){
				//开始组装SELECT语句
				myBatisMain.setSelect(this.getSelectInfo(tableStr));
			}
			if(two.getButton_1().getSelection()){
				//开始组装UPDATE语句
				myBatisMain.setUpdate(this.getUpdateInfo(tableStr));
			}
			if(two.getButton_2().getSelection()){
				//开始组装DELETE语句
				myBatisMain.setDelete(this.getDeleteInfo(tableStr));
			}
			if(two.getButton_3().getSelection()){
				//开始组装INSERT语句
				myBatisMain.setInsert(this.getInsertInfo(tableStr));
			}
			// 保存XML文件
			XMLFileOp.saveToFile(path, myBatisMain,ConstantUtil.MYBATIS_XML_HEAD);
			//保存数据库主文件
			DataMgnInfoVo dataMgnInfoVo = new DataMgnInfoVo();
			dataMgnInfoVo.setTableName(tableStr);
			dataMgnInfoVo.setCnName(tableStr);
			dataMgnList.add(dataMgnInfoVo);
		}
		XMLFileOp.saveToFile(modelMain, dataMgnList, null);
	}

	/**
	 * 组装SELECT语句
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Select> getSelectInfo(String tableName){
		List<Select> list = new ArrayList<Select>();
		//全表查询
		Select select = new Select();
		select.setId("getTableAll");
		select.setParameterType("map");
		select.setResultType("hashmap");
		select.setSql("select * from " + tableName);
		list.add(select);
		
		//统计全表的数据
		Select selectCount = new Select();
		selectCount.setId("getTableAllCount");
		selectCount.setParameterType("map");
		selectCount.setResultType("int");
		selectCount.setSql("select count(*) from " + tableName);
		list.add(selectCount);
		
		//查询表的主键，根据表的主键查询数据
		Select selectPk = new Select();
		selectPk.setId("getTableByPk");
		selectPk.setParameterType("map");
		selectPk.setResultType("hashmap");
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("select * from " + tableName + " where 1=1 ");
		SqlSession session = iMySqlConnImp.getSession();
		Map map = new HashMap();
		map.put("tableName", tableName);
		List listTable = session.selectList("table.selectTablePK" , map);
		for (int i = 0; i < listTable.size(); i++) {
			HashMap obj = (HashMap)listTable.get(i);
			strBuff.append("and " + obj.get("COLUMN_NAME") + "= #{" + obj.get("COLUMN_NAME") + "} "  );
		}
		selectPk.setSql(strBuff.toString());
		list.add(selectPk);
		
		return list;
		
	}
	
	/**
	 * 组装update语句
	 * @param tableName
	 * @return
	 */
	private List<Update> getUpdateInfo(String tableName){
		List<Update> list = new ArrayList<Update>();
		Connection conn = null;
		Statement stamt = null;
		ResultSet rst = null;
		try {
			Update  update = new Update();
			update.setId("updateTableByPk");
			update.setParameterType("map");
			StringBuffer strBuffSet = new StringBuffer();
			strBuffSet.append("update " + tableName + " set ");
			
			StringBuffer strBuffPk = new StringBuffer();
			strBuffPk.append("where 1=1 ");
			
			SqlSession session = iMySqlConnImp.getSession();
			conn = session.getConnection();
			stamt = conn.createStatement();
			rst = stamt.executeQuery("show columns from " + tableName);
			while(rst.next()){
				strBuffSet.append(rst.getString("Field") + "= #{" + rst.getString("Field") + "} ,"  );
				
				//如果是主键，则添加到条件中
				if(rst.getString("Key")!=null && rst.getString("Key").equals("PRI"))
					strBuffPk.append("and " + rst.getString("Field") + "= #{" + rst.getString("Field") + "} ") ;
			}
			update.setSql(strBuffSet.toString().substring(0, strBuffSet.toString().length()-1) + strBuffPk.toString());
			list.add(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rst != null)
					rst.close();
				if(stamt != null)
					stamt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 组装delete语句
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Delete> getDeleteInfo(String tableName){
		List<Delete> list = new ArrayList<Delete>();
		Delete  delete = new Delete();
		delete.setId("deleteTableByPk");
		delete.setParameterType("map");
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("delete " + tableName + " where 1=1 ");
		
		SqlSession session = iMySqlConnImp.getSession();
		Map map = new HashMap();
		map.put("tableName", tableName);
		List listTable = session.selectList("table.selectTablePK" , map);
		for (int i = 0; i < listTable.size(); i++) {
			HashMap obj = (HashMap)listTable.get(i);
			strBuff.append("and " + obj.get("COLUMN_NAME") + "= #{" + obj.get("COLUMN_NAME") + "} "  );
		}
		delete.setSql(strBuff.toString());
		list.add(delete);
		return list;
	}
	
	/**
	 * 组装update语句
	 * @param tableName
	 * @return
	 */
	private List<Insert> getInsertInfo(String tableName){
		List<Insert> list = new ArrayList<Insert>();
		Connection conn = null;
		Statement stamt = null;
		ResultSet rst = null;
		try {
			Insert  insert = new Insert();
			insert.setId("insertTable");
			insert.setParameterType("map");
			StringBuffer strBuffSet = new StringBuffer();
			strBuffSet.append("insert into " + tableName + "( ");
			
			StringBuffer strBuffValue = new StringBuffer();
			strBuffValue.append("values (");
			
			SqlSession session = iMySqlConnImp.getSession();
			conn = session.getConnection();
			stamt = conn.createStatement();
			rst = stamt.executeQuery("show columns from " + tableName);
			while(rst.next()){
				strBuffSet.append(rst.getString("Field") + " ,"  );
				
				strBuffValue.append(" #{" + rst.getString("Field") + "} ,") ;
			}
			insert.setSql(strBuffSet.toString().substring(0, strBuffSet.toString().length()-1) + ") " + strBuffValue.toString().substring(0, strBuffValue.toString().length()-1)+")");
			list.add(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rst != null)
					rst.close();
				if(stamt != null)
					stamt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
