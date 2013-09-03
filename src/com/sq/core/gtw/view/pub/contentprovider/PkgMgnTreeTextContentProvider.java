package com.sq.core.gtw.view.pub.contentprovider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sq.core.gtw.view.pub.vo.PkgMgnTreeElement;


/**
 * @作者 whai
 * @创建日期 2013-7-2
 * @版本 V1.0
 * @文件名 PkgMgnTreeTextContentProvider.java
 */
public class PkgMgnTreeTextContentProvider implements
		IStructuredContentProvider, ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return ((PkgMgnTreeElement) parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return ((PkgMgnTreeElement) element).getChildren().size() >  0 ;  
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return ((List)inputElement).toArray();
	}

}
