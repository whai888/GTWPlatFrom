package com.sq.core.gtw.edit.editinput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @���� whai
 * @�������� 2013-6-23
 * @�汾 V1.0
 * @�ļ��� TransInfoEditorInput.java
 */
public class TransInfoEditorInput implements IEditorInput {
	private final String lastname;
	
	
	public TransInfoEditorInput(String lastname) {
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
		return lastname;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (super.equals(obj)) {
			return true;
		}

		if (obj instanceof TransInfoEditorInput) {
			return lastname.equals(((TransInfoEditorInput) obj).getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return lastname.hashCode();
	}

}
