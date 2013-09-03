package com.sq.core.gtw.edit.model.impl;

import com.sq.core.gtw.edit.model.IConnectionModel;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizConnectionModel.java
 */
public class BizConnectionModel implements IConnectionModel{
	
	private BizModel source,target ;
	
	//���ӵ�����
	private String condition = "" ;
	
	//���ӵ�ͷ����ӵ�source
	public void attachSource(){
		if(!source.getModelSourceConnections().contains(this))
			source.addSourceConnection(this);
	}
	
	//���ӵ�β����ӵ�target
	public void attachTarget(){
		if(!target.getModelTargetConnections().contains(this))
			target.addTargetConnection(this);
	}
	
	//�Ƴ�Դ�ڵ�
	public void detachSource(){
		source.removeSourceConnection(this);
	}

	//�Ƴ����ӵ�β��
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
