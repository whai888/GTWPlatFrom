package com.sq.core.gtw.view.pub.contentprovider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.view.pub.vo.PkgMgnTreeElement;

/**
 * @���� whai
 * @�������� 2013-7-2
 * @�汾 V1.0
 * @�ļ��� PkgMgnTreeTextLabelProvider.java
 */
public class PkgMgnTreeTextLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}

	/**
	 * ��ʾ���ڵ������
	 */
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		return ((PkgMgnTreeElement)element).getName();
	}

}
