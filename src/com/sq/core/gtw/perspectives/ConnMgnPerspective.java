package com.sq.core.gtw.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.view.pub.ConnMgnView;

/**
 * @���� whai
 * @�������� 2013-6-23
 * @�汾 V1.0
 * @�ļ��� ConnMgnPerspective.java
 */
public class ConnMgnPerspective implements IPerspectiveFactory {
	
	public static final String ID = "com.sq.core.gtw.perspectives.ConnMgnPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		//��ñ༭��
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		//ͨѶ��ͼ��
		//��Ӷ�������ͼ
		layout.addStandaloneView(ConnMgnView.ID, true , IPageLayout.LEFT,  .2f, editorArea);

	}

}
