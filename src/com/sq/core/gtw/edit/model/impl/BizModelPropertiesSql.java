package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IFlowModelProperties;

/**
 *@����  whai 
 *@�������� 2013-8-14
 *@�汾 V1.0
 *@�ļ��� BizModelPropertiesSql.java
 */
public class BizModelPropertiesSql implements IFlowModelProperties {
	
	//��ѯ����
	private String tableName = "" ;
	
	//��ѯSQL���
	private String sql = "" ;
	
	//SQL����
	private String sqlService = "" ;
	
	//����Դ
	private String dataSource = "";
	
	//��������
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
