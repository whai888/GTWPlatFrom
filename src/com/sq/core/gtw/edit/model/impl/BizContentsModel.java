package com.sq.core.gtw.edit.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.sq.core.gtw.edit.model.IContentsModel;


/**
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� ContentsModel.java
 */
public class BizContentsModel extends AbstractModel implements IContentsModel{
	
	private String id = "" ;
	
	private String name = "" ;
	
	private String description = "" ;
	
	private String lable = "";
	
	
	//��ģ���б�
	@SuppressWarnings("unchecked")
	private List children = new ArrayList();
	public static final String P_CHINLDREN = "_children" ;
	
	/**
	 * �����ģ��
	 * @param child
	 */
	@SuppressWarnings("unchecked")
	public void addChildren(Object child){
		children.add(child);
		//�����ģ�ͺ�֪ͨEditPart
		firePropertyChange(P_CHINLDREN, null, null);
	}
	
	/**
	 * ɾ����ģ��
	 * @param child
	 */
	public void removeChildren(Object child){
		children.remove(child);
		//ɾ����ģ�ͺ�֪ͨEditPart
		firePropertyChange(P_CHINLDREN, null, null);
	}

	//�õ���ģ��
	@SuppressWarnings("unchecked")
	public List getChildren() {
		return children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}
	
	
}
