package com.sq.core.gtw.view.dialog.contentprovider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @���� whai
 * @�������� 2013-7-24
 * @�汾 V1.0
 * @�ļ��� DataGenerateScriptTwoContentProvider.java
 */
public class DataGenerateScriptTwoContentProvider implements
		IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return ((List)inputElement).toArray();
	}

}
