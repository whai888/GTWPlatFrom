package com.sq.core.gtw.platfrom;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.view.pub.TransInfoView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		layout.setEditorAreaVisible(true);
		//��Ӷ�������ͼ
//		layout.addStandaloneView(TransInfoView.ID, true, IPageLayout.LEFT, .15f, layout.getEditorArea());
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.3f, layout.getEditorArea());
		left.addView(TransInfoView.ID);
		left.addView(ConstantUtil.PROPERTY_VIEW);
		//ȥ���رյĹ���
//		layout.getViewLayout(TransInfoView.ID).setCloseable(true);
//		layout.setFixed(false);
		
	}

}
