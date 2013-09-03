package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.FlowBizPaletteInfo;

/**
 *@����  whai 
 *@�������� 2013-8-17
 *@�汾 V1.0
 *@�ļ��� FlowMgnBizCreateSubLableProvider.java
 */
public class FlowMgnBizCreateSubLeftLableProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof FlowBizPaletteInfo){
			FlowBizPaletteInfo flowBizPaletteInfo = (FlowBizPaletteInfo)element ;
			switch(columnIndex){
			case 0 :
				return flowBizPaletteInfo.getContentName();
			}
		}
		return null;
	}

}
