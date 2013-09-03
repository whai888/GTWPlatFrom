package com.sq.core.gtw.edit.model;

import java.util.List;

/**
 * @���� whai
 * @�������� 2013-7-9
 * @�汾 V1.0
 * @�ļ��� IContentsModel.java
 */
public interface IContentsModel {

	/**
	 * �����ģ��
	 * @param child
	 */
	public void addChildren(Object child);
	/**
	 * ɾ����ģ��
	 * @param child
	 */
	public void removeChildren(Object child);

	//�õ���ģ��
	@SuppressWarnings("unchecked")
	public List getChildren();
	
}
