package com.sq.core.gtw.view.pub.contentprovider;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;

/**
 * @作者 whai
 * @创建日期 2013-6-28
 * @版本 V1.0
 * @文件名 FlowMgnLabelProvider.java
 */
public class FlowMgnBizLabelProvider extends LabelProvider implements ITableLabelProvider {

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
		return getImage(element);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof FlowMgnBizInfo){
			FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo)element ;
			switch(columnIndex){
			case 0 :
				return flowMgnBizInfo.getCode() + "——" + flowMgnBizInfo.getName();
			}
		}
		return null;
//		FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo)element ;
//		return getText(flowMgnBizInfo.getCode() + "——" + flowMgnBizInfo.getName());
	}
	
	public Image getImage(Object obj) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}
}
