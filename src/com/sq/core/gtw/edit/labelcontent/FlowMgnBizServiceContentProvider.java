package com.sq.core.gtw.edit.labelcontent;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sq.core.gtw.edit.vo.BizServiceInfo;

/**
 * @作者 whai
 * @创建日期 2013-7-16
 * @版本 V1.0
 * @文件名 FlowMgnBizServiceContentProvider.java
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
		//返回true 说明需要给树节点显示+号，点击+号，getChildren会动态的载入子节点数据，这样就实现了Lazy Load了。
		return ((BizServiceInfo)element).hasChildren();
	}

}
