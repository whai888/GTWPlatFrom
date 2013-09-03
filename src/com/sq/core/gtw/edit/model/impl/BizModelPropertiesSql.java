package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IFlowModelProperties;

/**
 *@作者  whai 
 *@创建日期 2013-8-14
 *@版本 V1.0
 *@文件名 BizModelPropertiesSql.java
 */
public class BizModelPropertiesSql implements IFlowModelProperties {
	
	//查询表名
	private String tableName = "" ;
	
	//查询SQL语句
	private String sql = "" ;
	
	//SQL服务
	private String sqlService = "" ;
	
	//数据源
	private String dataSource = "";
	
	//事物类型
	private String transType = "" ;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSqlService() {
		return sqlService;
	}

	public void setSqlService(String sqlService) {
		this.sqlService = sqlService;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	
}
