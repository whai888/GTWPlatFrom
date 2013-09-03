package com.sq.core.gtw.db.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("update")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"sql"})
public class Update {

	@XStreamAsAttribute
	private String id = "";
	@XStreamAsAttribute
	private String parameterType = "map" ;
	@XStreamAsAttribute
	private String flushCache = "false" ;
	@XStreamAsAttribute
	private String statementType = "PREPARED";
	@XStreamAsAttribute
	private String timeout = "" ;
	
	private String sql = "" ;

	public String getSql() {
		return sql;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	public boolean hasChildren(){
		return false ;
	}

	public String getFlushCache() {
		return flushCache;
	}

	public void setFlushCache(String flushCache) {
		this.flushCache = flushCache;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
}
