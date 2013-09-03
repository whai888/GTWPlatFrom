package com.sq.core.gtw.view.dialog.contentprovider;

import gtwplatfrom.Activator;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.util.IImageKeys;

/**
 * @���� whai
 * @�������� 2013-7-24
 * @�汾 V1.0
 * @�ļ��� DataGenerateScriptTwoLabelProvider.java
 */
public class DataGenerateScriptTwoLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof String){
			switch(columnIndex){
			case 0:
				return Activator.getImageDescriptor(IImageKeys.OBJ_TABLE).createImage();
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof String){
			String dataTableVo = (String)element ;
			switch(columnIndex){
			case 0 :
				return dataTableVo;
			}
		}
		return null;
	}

}
