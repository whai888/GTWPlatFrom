package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.BizPackageInfo;

/**
 * @作者 whai
 * @创建日期 2013-7-16
 * @版本 V1.0
 * @文件名 FlowMgnBizPackageLeftLabelProvider.java
 */
public class FlowMgnBizPackageLeftLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof BizPackageInfo){
			BizPackageInfo bizPackageInfo = (BizPackageInfo)element ;
			switch(columnIndex){
			case 0 :
				return bizPackageInfo.getName();
			case 1 :
				return bizPackageInfo.getPackageType();
			case 2 :
				return bizPackageInfo.getChName();
			}
		}
		return null;
	}
}
