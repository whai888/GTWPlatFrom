package com.sq.core.gtw.edit.labelcontent;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 *@����  whai 
 *@�������� 2013-8-17
 *@�汾 V1.0
 *@�ļ��� FlowMgnBizCreateSubContentProvider.java
 */
public class FlowMgnBizCreateSubLeftContentProvider implements
		IStructuredContentProvider {

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof List){
			return ((List)inputElement).toArray();
		}
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
