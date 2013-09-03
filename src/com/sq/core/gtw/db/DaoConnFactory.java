package com.sq.core.gtw.db;

import com.sq.core.gtw.db.mysql.IMySqlConnImp;
import com.sq.core.gtw.util.ConstantUtil;


/**
 * @作者 whai
 * @创建日期 2013-7-25
 * @版本 V1.0
 * @文件名 DaoConnFactory.java
 */
public class DaoConnFactory {
	
	public static IMyDBConn getInstance(String dbStr){
		if(dbStr.equals(ConstantUtil.DB_TYPE[0])){
			return new IMySqlConnImp();
		}
		return null ;
	}

}
