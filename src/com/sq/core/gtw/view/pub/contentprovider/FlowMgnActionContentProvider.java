package com.sq.core.gtw.view.pub.contentprovider;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @作者 whai
 * @创建日期 2013-7-22
 * @版本 V1.0
 * @文件名 FlowMgnActionContentProvider.java
 */
public class FlowMgnActionContentProvider implements IStructuredContentProvider{

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
		if (inputElement instanceof ArrayList) {
			return ((ArrayList)inputElement).toArray();
		}
        return new Object[0];
	}
}
