package com.sq.core.gtw.edit.editinput;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.sq.core.gtw.edit.vo.BizPackageInfo;

/**
 * @作者 whai
 * @创建日期 2013-6-29
 * @版本 V1.0
 * @文件名 FlowMgnBizDataPkgEditorInput.java
 */
public class FlowMgnBizDataPkgEditorInput implements IEditorInput {
	
	private final List<BizPackageInfo> bizPackageInfo;
	
	
	public FlowMgnBizDataPkgEditorInput(List<BizPackageInfo> bizPackageInfo) {
		this.bizPackageInfo = bizPackageInfo;
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
		return "报文信息";
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
		return bizPackageInfo.hashCode();
	}

	public List<BizPackageInfo> getBizPackageInfo() {
		return bizPackageInfo;
	}

}
