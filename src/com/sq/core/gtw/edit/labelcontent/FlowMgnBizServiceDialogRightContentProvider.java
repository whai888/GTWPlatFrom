package com.sq.core.gtw.edit.labelcontent;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sq.core.gtw.edit.vo.BizServiceInfo;

/**
 * @���� whai
 * @�������� 2013-7-20
 * @�汾 V1.0
 * @�ļ��� FlowMgnBizServiceDialogRightContentProvider.java
 */
public class FlowMgnBizServiceDialogRightContentProvider implements
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
		if(inputElement instanceof List){
			return ((List<BizServiceInfo>)inputElement).toArray();
		}
		return null ;
	}

}
