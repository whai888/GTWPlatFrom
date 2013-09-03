package com.sq.core.gtw.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.sq.core.gtw.view.transinfo.FlowMgnBizView;

/**
 * @���� whai
 * @�������� 2013-6-23
 * @�汾 V1.0
 * @�ļ��� FlowMgnPerspective.java
 */
public class FlowMgnPerspective implements IPerspectiveFactory {
	
	public static final String ID = "com.sq.core.gtw.perspectives.FlowMgnPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		//��ñ༭��
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		//��ӽ�����Ϣ��ͼ
		layout.addView(FlowMgnBizView.ID, IPageLayout.LEFT, 0.2f, editorArea);

	}

}
