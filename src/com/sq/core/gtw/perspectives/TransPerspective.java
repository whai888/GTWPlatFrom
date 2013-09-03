package com.sq.core.gtw.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.view.pub.TransInfoView;

/**
 * @���� whai
 * @�������� 2013-6-23
 * @�汾 V1.0
 * @�ļ��� TransPerspective.java
 */
public class TransPerspective implements IPerspectiveFactory {
	
	public static final String ID = "com.sq.core.gtw.perspectives.TransPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		//��ñ༭��
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		//��ӽ�����Ϣ��ͼ
		layout.addView(TransInfoView.ID, IPageLayout.LEFT, 0.6f, editorArea);

	}

}
