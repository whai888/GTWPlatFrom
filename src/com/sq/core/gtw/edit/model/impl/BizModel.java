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
 * @���� whai
 * @�������� 2013-7-3
 * @�汾 V1.0
 * @�ļ��� HelloModel.java
 */
public class BizModel extends AbstractModel implements IFlowModel{
	private String text = "ȱʡ����" ;
	//������Ϣ
	private String desc = "" ;
	//���Լ��(��ʵ��ҵ���޹صĳ�Ա)
	private Rectangle constraint ;
	public static final String P_CONSTRAINT = "_constraint" ;
	//����ַ���ID �� �����ı�ͼ�ε��ı�ʱ��֪ͨ��Editpart
	public static final String P_TEXT = "_text" ;
	//���ӵ����
	public static final String P_SOURCE_CONNECTION="_source_connection";
	//���ӵ��յ�
	public static final String P_TARGET_CONNTION="_target_connecion";
	//ҵ���߼�����
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
		//ͼ���ı��ı���֪ͨ��EditPart
		firePropertyChange(P_TEXT, null, text) ;
	}

	@Override
	public Rectangle getConstraint() {
		return constraint;
	}

	@Override
	public void setConstraint(Rectangle rect) {
		constraint = rect;
		//�ı�֪ͨ
		firePropertyChange(P_CONSTRAINT, null, constraint);
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		IPropertyDescriptor[] descriptors = new IPropertyDescriptor[]{
				new TextPropertyDescriptor(P_TEXT, "����")
		};
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if(id.equals(P_TEXT)){
			//������Property view ���ı����Ե�ֵ
			return text ;
		}
		return null;
	}

	/**
	 * �ж�������ͼ�е�����ֵ�Ƿ�ı䣬���û��ָ���������򷵻�false
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
	 * ����ָ��ID������ֵ����������Բ��ܸı����û�������ѧ�Ǹ��������κ�����
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		if(id.equals(P_TEXT)){
			//�ı��ı�ʱ�����ı�������ֵ
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
