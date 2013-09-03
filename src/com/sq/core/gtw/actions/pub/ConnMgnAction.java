package com.sq.core.gtw.actions.pub;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;

import com.sq.core.gtw.perspectives.ConnMgnPerspective;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 ConnMgnAction.java
 */
public class ConnMgnAction extends Action {
	public static final String ID = "com.sq.core.gtw.actions.pub.ConnMgnAction"; //$NON-NLS-1$
	
	private IWorkbenchWindow window;

	public ConnMgnAction(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
//			window.getActivePage().closeAllPerspectives(false, false) ;
			window.getWorkbench().showPerspective(ConnMgnPerspective.ID, window);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
