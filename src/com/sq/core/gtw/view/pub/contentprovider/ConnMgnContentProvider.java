package com.sq.core.gtw.view.pub.contentprovider;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @���� whai
 * @�������� 2013-6-28
 * @�汾 V1.0
 * @�ļ��� ConnMgnContentProvider.java
 */
public class ConnMgnContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}
        return new Object[0];
	}

}
