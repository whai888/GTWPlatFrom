package com.sq.core.gtw.edit.editinput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @作者 whai
 * @创建日期 2013-6-23
 * @版本 V1.0
 * @文件名 PkgMgnEditorInput.java
 */
public class PkgMgnTextEditorInput implements IEditorInput {
	private final String lastname;
	
	
	public PkgMgnTextEditorInput(String lastname) {
		this.lastname = lastname;
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
		return lastname;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "视图层";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (super.equals(obj)) {
			return true;
		}

		if (obj instanceof PkgMgnTextEditorInput) {
			return lastname.equals(((PkgMgnTextEditorInput) obj).getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return lastname.hashCode();
	}

}
