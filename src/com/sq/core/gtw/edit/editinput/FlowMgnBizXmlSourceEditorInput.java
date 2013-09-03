package com.sq.core.gtw.edit.editinput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.sq.core.gtw.edit.vo.BizTransInfo;

/**
 * @作者 whai
 * @创建日期 2013-7-21
 * @版本 V1.0
 * @文件名 FlowMgnBizXmlSourceEditorInput.java
 */
public class FlowMgnBizXmlSourceEditorInput implements IEditorInput {

	private BizTransInfo bizTransInfo ;
	
	
	public FlowMgnBizXmlSourceEditorInput(BizTransInfo bizTransInfo) {
		super();
		this.bizTransInfo = bizTransInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "";
	}

	public BizTransInfo getBizTransInfo() {
		return bizTransInfo;
	}

}
