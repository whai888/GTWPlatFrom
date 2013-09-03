package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.imp.system.BizDefinitionVo;

/**
 *@����  whai 
 *@�������� 2013-8-18
 *@�汾 V1.0
 *@�ļ��� FlowMgnBizCreateSubBottomLableProvider.java
 */
public class FlowMgnBizCreateSubBottomLableProvider extends LabelProvider
		implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if( element instanceof BizDefinitionVo){
			BizDefinitionVo bizDefinitionVo = (BizDefinitionVo)element;
			switch(columnIndex){
			case 0:
				return bizDefinitionVo.getLabel();
			case 1:
				return bizDefinitionVo.getType();
			case 2:
				return bizDefinitionVo.getClassField();
			}
		}
		return null;
	}

}
