package com.sq.core.gtw.db;

import java.util.List;

import com.sq.core.gtw.db.vo.DataTableVo;

/**
 * @作者 whai
 * @创建日期 2013-7-25
 * @版本 V1.0
 * @文件名 IMyDBConn.java
 */
public interface IMyDBConn {

	public void setConnInfo(String url, String driver , String userName , String pwd);
	
	public List<DataTableVo> getTableInfo();
}
