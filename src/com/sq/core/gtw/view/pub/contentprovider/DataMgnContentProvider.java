package com.sq.core.gtw.view.pub.contentprovider;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @���� whai
 * @�������� 2013-7-23
 * @�汾 V1.0
 * @�ļ��� DataMgnContentProvider.java
 */
public class DataMgnContentProvider implements IStructuredContentProvider {

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
		if (inputElement instanceof ArrayList) {
			return ((ArrayList)inputElement).toArray();
		}
        return new Object[0];
	}

}
