package com.sq.core.gtw.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.view.pub.DictMgnView;

/**
 * @作者 whai
 * @创建日期 2013-6-23
 * @版本 V1.0
 * @文件名 DictMgnPerspective.java
 */
public class DictMgnPerspective implements IPerspectiveFactory {
	
	public static final String ID = "com.sq.core.gtw.perspectives.DictMgnPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		//获得编辑器
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		//添加交易信息视图
		layout.addView(DictMgnView.ID, IPageLayout.LEFT, 0.2f, editorArea);

	}

}
