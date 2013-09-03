package com.sq.core.gtw.edit.editinput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.sq.core.gtw.edit.model.impl.BizContentsModel;

/**
 * @作者 whai
 * @创建日期 2013-6-29
 * @版本 V1.0
 * @文件名 FlowMgnBizFlowEditorInput.java
 */
public class FlowMgnBizFlowEditorInput implements IEditorInput {
	
	private final BizContentsModel bizContentsModel ;
	
	public FlowMgnBizFlowEditorInput(BizContentsModel bizContentsModel) {
		this.bizContentsModel = bizContentsModel;
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
		return "流程定义";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "流程定义";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (super.equals(obj)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return bizContentsModel.hashCode();
	}

	public BizContentsModel getBizContentsModel() {
		return bizContentsModel;
	}
}
