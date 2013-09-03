package com.sq.core.gtw.edit.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.sq.core.gtw.edit.model.IFlowModel;
import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.vo.imp.system.BizDefinitionVo;


/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 HelloModel.java
 */
public class BizModel extends AbstractModel implements IFlowModel{
	private String text = "缺省名称" ;
	//描述信息
	private String desc = "" ;
	//添加约束(与实际业务无关的成员)
	private Rectangle constraint ;
	public static final String P_CONSTRAINT = "_constraint" ;
	//添加字符串ID ， 这样改变图形的文本时可通知其Editpart
	public static final String P_TEXT = "_text" ;
	//链接的起点
	public static final String P_SOURCE_CONNECTION="_source_connection";
	//链接的终点
	public static final String P_TARGET_CONNTION="_target_connecion";
	//业务逻辑属性
	private IFlowModelProperties iFlowModelProperties ;
	@SuppressWarnings("unchecked")
	private List sourceConnection = new ArrayList();
	@SuppressWarnings("unchecked")
	private List targetConnection = new ArrayList();
	
	private List<BizDefinitionVo> bizDefinitionVoList = new ArrayList<BizDefinitionVo>();

	public BizModel(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
		//图形文本改变是通知其EditPart
		firePropertyChange(P_TEXT, null, text) ;
	}

	@Override
	public Rectangle getConstraint() {
		return constraint;
	}

	@Override
	public void setConstraint(Rectangle rect) {
		constraint = rect;
		//改变通知
		firePropertyChange(P_CONSTRAINT, null, constraint);
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		IPropertyDescriptor[] descriptors = new IPropertyDescriptor[]{
				new TextPropertyDescriptor(P_TEXT, "名称")
		};
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if(id.equals(P_TEXT)){
			//这里获得Property view 中文本属性的值
			return text ;
		}
		return null;
	}

	/**
	 * 判断属性视图中的属性值是否改变，如果没有指定的属性则返回false
	 */
	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		if(id.equals(P_TEXT))
			return true ;
		else
			return false ;
	}

	/**
	 * 设置指定ID的属性值，如果该属性不能改变或者没有这个数学那个，则不做任何事情
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		if(id.equals(P_TEXT)){
			//改变文本时设置文本的属性值
			setText((String)value);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addSourceConnection(Object connx){
		sourceConnection.add(connx);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addTargetConnection(Object connx){
		targetConnection.add(connx);
		firePropertyChange(P_TARGET_CONNTION, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getModelSourceConnections(){
		return sourceConnection;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getModelTargetConnections(){
		return targetConnection;
	}
	
	@Override
	public void removeSourceConnection(Object connx){
		sourceConnection.remove(connx);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}
	
	@Override
	public void removeTargetConnection(Object connx){
		targetConnection.remove(connx);
		firePropertyChange(P_TARGET_CONNTION, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setModelSourceConnections(List sourceConnections ) {
		// TODO Auto-generated method stub
		this.sourceConnection = sourceConnections ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setModelTargetConnections(List targetConnection ) {
		// TODO Auto-generated method stub
		this.targetConnection = targetConnection ;
	}

	public String getDesc() {
		if(desc == null)
			desc = "" ;
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public IFlowModelProperties getiFlowModelProperties() {
		return iFlowModelProperties;
	}

	public void setiFlowModelProperties(Object iFlowModelProperties) {
		this.iFlowModelProperties = (IFlowModelProperties)iFlowModelProperties;
	}

	public List<BizDefinitionVo> getBizDefinitionVoList() {
		return bizDefinitionVoList;
	}

	public void setBizDefinitionVoList(List<BizDefinitionVo> bizDefinitionVoList) {
		this.bizDefinitionVoList = bizDefinitionVoList;
	}
	
}
