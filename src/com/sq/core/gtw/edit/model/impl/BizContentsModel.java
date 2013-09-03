package com.sq.core.gtw.edit.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.sq.core.gtw.edit.model.IContentsModel;


/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 ContentsModel.java
 */
public class BizContentsModel extends AbstractModel implements IContentsModel{
	
	private String id = "" ;
	
	private String name = "" ;
	
	private String description = "" ;
	
	private String lable = "";
	
	
	//子模型列表
	@SuppressWarnings("unchecked")
	private List children = new ArrayList();
	public static final String P_CHINLDREN = "_children" ;
	
	/**
	 * 添加子模型
	 * @param child
	 */
	@SuppressWarnings("unchecked")
	public void addChildren(Object child){
		children.add(child);
		//添加子模型后通知EditPart
		firePropertyChange(P_CHINLDREN, null, null);
	}
	
	/**
	 * 删除子模型
	 * @param child
	 */
	public void removeChildren(Object child){
		children.remove(child);
		//删除子模型后通知EditPart
		firePropertyChange(P_CHINLDREN, null, null);
	}

	//得到子模型
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
