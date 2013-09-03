package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.BizServiceInfo;

/**
 * @���� whai
 * @�������� 2013-7-20
 * @�汾 V1.0
 * @�ļ��� FlowMgnBizServiceDialogRightLabelProvider.java
 */
public class FlowMgnBizServiceDialogRightLabelProvider extends LabelProvider
		implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof BizServiceInfo){
			BizServiceInfo bizServiceInfo = (BizServiceInfo)element ;
			switch(columnIndex){
			case 0:
				return bizServiceInfo.getName();
			case 1:
				return bizServiceInfo.getDesc();
			}
		}
		return null;
	}

}
