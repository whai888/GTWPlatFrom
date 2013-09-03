package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.DictMgnVo;

/**
 * @���� whai
 * @�������� 2013-7-16
 * @�汾 V1.0
 * @�ļ��� FlowMgnBizPackageRightLabelProvider.java
 */
public class FlowMgnBizPackageRightLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof DictMgnVo){
			DictMgnVo dictMgnVo = (DictMgnVo)element ;
			switch(columnIndex){
			case 0 :
				return dictMgnVo.getCode();
			case 1 :
				return dictMgnVo.getName();
			case 2 :
				return dictMgnVo.getDescrip();
			}
		}
		return null;
	}
}
