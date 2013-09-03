package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.edit.vo.DictMgnVo;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 DictTableLabelProvider.java
 */
public class DictMgnTableLabelProvider extends LabelProvider implements ITableLabelProvider {

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
		if(element instanceof DictMgnVo){
			DictMgnVo dictMgnVo = (DictMgnVo)element ;
			switch(columnIndex){
			case 0 :
				return dictMgnVo.getCode();
			case 1 :
				return dictMgnVo.getName();
			case 2 :
				return dictMgnVo.getType();
			case 3 :
				return dictMgnVo.getCodeType();
			case 4 :
				return dictMgnVo.getValue();
			case 5 :
				return dictMgnVo.getDescrip();
			}
		}
		return null ;
	}
	
	public Image getImage(Object obj) {
		return null;
	}

}
