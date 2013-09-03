package com.sq.core.gtw.actions.pub;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;

import com.sq.core.gtw.perspectives.PkgMgnPerspective;

/**
 * @���� whai
 * @�������� 2013-6-24
 * @�汾 V1.0
 * @�ļ��� PkgMgnAction.java
 */
public class PkgMgnAction extends Action {
	public static final String ID = "com.sq.core.gtw.actions.pub.ConnMgnAction"; //$NON-NLS-1$
	
	private IWorkbenchWindow window;

	public PkgMgnAction(IWorkbenchWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			window.getWorkbench().showPerspective(PkgMgnPerspective.ID, window);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
