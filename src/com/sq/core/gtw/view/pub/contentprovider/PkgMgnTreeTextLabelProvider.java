package com.sq.core.gtw.view.pub.contentprovider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.view.pub.vo.PkgMgnTreeElement;

/**
 * @作者 whai
 * @创建日期 2013-7-2
 * @版本 V1.0
 * @文件名 PkgMgnTreeTextLabelProvider.java
 */
public class PkgMgnTreeTextLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}

	/**
	 * 显示树节点的名字
	 */
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		return ((PkgMgnTreeElement)element).getName();
	}

}
