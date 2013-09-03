package com.sq.core.gtw.db.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("select")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"sql"})
public class Select {
	
	@XStreamAsAttribute
	private String id = "";
	@XStreamAsAttribute
	private String parameterType = "map";
	@XStreamAsAttribute
	private String resultType = "hashmap";
	@XStreamAsAttribute
	private String resultMap = "";
	@XStreamAsAttribute
	private String flushCache = "false" ;
	@XStreamAsAttribute
	private String useCache = "true" ;
	@XStreamAsAttribute
	private String timeout = "" ;
	@XStreamAsAttribute
	private String fetchSize = "" ;
	@XStreamAsAttribute
	private String statementType = "PREPARED" ;
	@XStreamAsAttribute
	private String resultSetType = "" ;
	
	private String sql ;

	
	
	public Select() {
		super();
	}

	public Select(String id, String parameterType, String resultType , String sql) {
		super();
		this.id = id;
		this.parameterType = parameterType;
		this.resultType = resultType;
		this.sql = sql ;
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

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
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

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

	public String getFlushCache() {
		return flushCache;
	}

	public void setFlushCache(String flushCache) {
		this.flushCache = flushCache;
	}

	public String getUseCache() {
		return useCache;
	}

	public void setUseCache(String useCache) {
		this.useCache = useCache;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(String fetchSize) {
		this.fetchSize = fetchSize;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public String getResultSetType() {
		return resultSetType;
	}

	public void setResultSetType(String resultSetType) {
		this.resultSetType = resultSetType;
	}
	
}
