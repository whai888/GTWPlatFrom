package com.sq.core.gtw.db;

import com.sq.core.gtw.db.mysql.IMySqlConnImp;
import com.sq.core.gtw.util.ConstantUtil;


/**
 * @���� whai
 * @�������� 2013-7-25
 * @�汾 V1.0
 * @�ļ��� DaoConnFactory.java
 */
public class DaoConnFactory {
	
	public static IMyDBConn getInstance(String dbStr){
		if(dbStr.equals(ConstantUtil.DB_TYPE[0])){
			return new IMySqlConnImp();
		}
		return null ;
	}

}
