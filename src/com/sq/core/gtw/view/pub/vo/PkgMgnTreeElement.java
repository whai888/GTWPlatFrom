package com.sq.core.gtw.view.pub.vo;

import java.util.List;

/**
 * @���� whai
 * @�������� 2013-7-2
 * @�汾 V1.0
 * @�ļ��� PkgMgnTreeElement.java
 */
public interface PkgMgnTreeElement {

	//�ڵ�����
	public String getName();
	//�Ƿ�������
	public boolean hasChildren();
	//�����������
	@SuppressWarnings("unchecked")
	public List getChildren();
}
