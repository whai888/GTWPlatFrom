package com.sq.core.gtw.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.view.pub.PkgMgnView;

/**
 * @作者 whai
 * @创建日期 2013-6-23
 * @版本 V1.0
 * @文件名 PkgMgnPerspective.java
 */
public class PkgMgnPerspective implements IPerspectiveFactory {
	
	public static final String ID = "com.sq.core.gtw.perspectives.PkgMgnPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		//获得编辑器
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		//如果fixed设置为true 设置该perspective中的所有view，其大小不可变动，无最大最小按钮
		layout.setFixed(false);
		//添加交易信息视图
		layout.addView(PkgMgnView.ID, IPageLayout.LEFT, 0.2f, editorArea);

	}

}
