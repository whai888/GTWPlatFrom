package com.sq.core.gtw.db.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("insert")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = { "sql" })
public class Insert {

	@XStreamAsAttribute
	private String id = "";
	@XStreamAsAttribute
	private String parameterType = "map" ;
	@XStreamAsAttribute
	private String flushCache = "false" ;
	@XStreamAsAttribute
	private String statementType = "PREPARED";
	@XStreamAsAttribute
	private String keyProperty = "";
	@XStreamAsAttribute
	private String useGeneratedKeys  = "";
	@XStreamAsAttribute
	private String timeout  = "";

	private String sql = "";

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

	public String getKeyProperty() {
		return keyProperty;
	}

	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	public String getUseGeneratedKeys() {
		return useGeneratedKeys;
	}

	public void setUseGeneratedKeys(String useGeneratedKeys) {
		this.useGeneratedKeys = useGeneratedKeys;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getSql() {
		if(sql == null)
			sql = "";
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	public boolean hasChildren(){
		return false ;
	}
}
