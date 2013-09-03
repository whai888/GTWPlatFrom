package com.sq.core.gtw.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.view.pub.PkgMgnView;

/**
 * @���� whai
 * @�������� 2013-6-23
 * @�汾 V1.0
 * @�ļ��� PkgMgnPerspective.java
 */
public class PkgMgnPerspective implements IPerspectiveFactory {
	
	public static final String ID = "com.sq.core.gtw.perspectives.PkgMgnPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		//��ñ༭��
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		//���fixed����Ϊtrue ���ø�perspective�е�����view�����С���ɱ䶯���������С��ť
		layout.setFixed(false);
		//��ӽ�����Ϣ��ͼ
		layout.addView(PkgMgnView.ID, IPageLayout.LEFT, 0.2f, editorArea);

	}

}
