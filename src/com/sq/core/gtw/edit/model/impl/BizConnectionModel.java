package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizConnectionModel.java
 */
public class BizConnectionModel implements IConnectionModel{
	
	private BizModel source,target ;
	
	//连接的条件
	private String condition = "" ;
	
	//链接的头端添加到source
	public void attachSource(){
		if(!source.getModelSourceConnections().contains(this))
			source.addSourceConnection(this);
	}
	
	//链接的尾端添加到target
	public void attachTarget(){
		if(!target.getModelTargetConnections().contains(this))
			target.addTargetConnection(this);
	}
	
	//移除源节点
	public void detachSource(){
		source.removeSourceConnection(this);
	}

	//移除链接的尾端
	public void detachTaret(){
		target.removeTargetConnection(this);
	}

	public BizModel getSource() {
		return source;
	}

	@Override
	public void setSource(Object source) {
		this.source = (BizModel) source;
	}

	public BizModel getTarget() {
		return target;
	}

	@Override
	public void setTarget(Object target) {
		this.target = (BizModel) target;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
