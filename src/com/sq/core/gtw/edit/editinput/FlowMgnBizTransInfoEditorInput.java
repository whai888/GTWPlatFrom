package com.sq.core.gtw.edit.editinput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.sq.core.gtw.edit.vo.BizDatatransInfo;

/**
 * @���� whai
 * @�������� 2013-6-29
 * @�汾 V1.0
 * @�ļ��� FlowMgnBizTransInfoEditorInput.java
 */
public class FlowMgnBizTransInfoEditorInput implements IEditorInput {
	
	private final BizDatatransInfo bizDatatransInfo;
	
	
	public FlowMgnBizTransInfoEditorInput(BizDatatransInfo bizDatatransInfo) {
		this.bizDatatransInfo = bizDatatransInfo;
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
		return bizDatatransInfo.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "������Ϣ";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (super.equals(obj)) {
			return true;
		}

		if (obj instanceof BizDatatransInfo) {
			return bizDatatransInfo.equals(((BizDatatransInfo) obj));
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return bizDatatransInfo.hashCode();
	}

	public BizDatatransInfo getBizDatatransInfo() {
		return bizDatatransInfo;
	}

	
}
