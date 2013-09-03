package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.BizServiceInfo;

/**
 * @作者 whai
 * @创建日期 2013-7-16
 * @版本 V1.0
 * @文件名 FlowMgnBizServiceLabelProvider.java
 */
public class FlowMgnBizServiceLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof BizServiceInfo){
			BizServiceInfo bizServiceInfo= (BizServiceInfo)element ;
			switch(columnIndex){
			case 0 :
				return bizServiceInfo.getName();
			case 1 :
				return bizServiceInfo.getServiceType();
			case 2 :
				return bizServiceInfo.getDesc();
			default:
				break ;
			}
		}
		return null;
	}

	
}
