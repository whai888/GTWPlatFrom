package com.sq.core.gtw.view.pub.contentprovider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.sq.core.gtw.view.pub.vo.FlowMgnActionInfo;

/**
 * @作者 whai
 * @创建日期 2013-7-22
 * @版本 V1.0
 * @文件名 FlowMgnActionLaberProvider.java
 */
public class FlowMgnActionLaberProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof FlowMgnActionInfo){
			FlowMgnActionInfo flowMgnActionInfo = (FlowMgnActionInfo)element ;
			switch(columnIndex){
			case 0 :
				return flowMgnActionInfo.getCode() + "——" + flowMgnActionInfo.getName();
			}
		}
		return null;
	}

}
