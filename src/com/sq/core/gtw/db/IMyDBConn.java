package com.sq.core.gtw.db;

import java.util.List;

import com.sq.core.gtw.db.vo.DataTableVo;

/**
 * @���� whai
 * @�������� 2013-7-25
 * @�汾 V1.0
 * @�ļ��� IMyDBConn.java
 */
public interface IMyDBConn {

	public void setConnInfo(String url, String driver , String userName , String pwd);
	
	public List<DataTableVo> getTableInfo();
}
