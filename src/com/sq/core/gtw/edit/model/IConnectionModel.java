package com.sq.core.gtw.edit.model;
/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� AbstractConnectionModel.java
 */
public interface IConnectionModel {
	
	//���ӵ�ͷ����ӵ�source
	public void attachSource();
	
	//���ӵ�β����ӵ�target
	public void attachTarget();
	
	//�Ƴ�Դ�ڵ�
	public void detachSource();

	//�Ƴ����ӵ�β��
	public void detachTaret();

	public Object getSource();

	public void setSource(Object source) ;

	public Object getTarget();

	public void setTarget(Object target) ;
}
