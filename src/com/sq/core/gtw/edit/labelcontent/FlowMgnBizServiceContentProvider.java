package com.sq.core.gtw.edit.labelcontent;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sq.core.gtw.edit.vo.BizServiceInfo;

/**
 * @���� whai
 * @�������� 2013-7-16
 * @�汾 V1.0
 * @�ļ��� FlowMgnBizServiceContentProvider.java
 */
public class FlowMgnBizServiceContentProvider implements IStructuredContentProvider , ITreeContentProvider {

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
		if(inputElement instanceof List){
			return ((List)inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if(parentElement instanceof BizServiceInfo){
			return ((BizServiceInfo)parentElement).getSubInfo().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		//����true ˵����Ҫ�����ڵ���ʾ+�ţ����+�ţ�getChildren�ᶯ̬�������ӽڵ����ݣ�������ʵ����Lazy Load�ˡ�
		return ((BizServiceInfo)element).hasChildren();
	}

}
