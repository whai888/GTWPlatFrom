package com.sq.core.gtw.edit.editinput;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.sq.core.gtw.edit.vo.BizServiceInfo;

/**
 * @���� whai
 * @�������� 2013-6-29
 * @�汾 V1.0
 * @�ļ��� FlowMgnBizServiceEditorInput.java
 */
public class FlowMgnBizServiceEditorInput implements IEditorInput {
	
	private final List<BizServiceInfo> serviceList;
	
	
	@SuppressWarnings("unchecked")
	public FlowMgnBizServiceEditorInput(List serviceList) {
		this.serviceList = serviceList;
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
		return "������";
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
		return serviceList.hashCode();
	}

	public List<BizServiceInfo> getServiceList() {
		return serviceList;
	}

}
