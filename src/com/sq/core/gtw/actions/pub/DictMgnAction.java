package com.sq.core.gtw.actions.pub;

import gtwplatfrom.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;

import com.sq.core.gtw.perspectives.DictMgnPerspective;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 DictMgnAction.java
 */
public class DictMgnAction extends Action {
	
	public static final String ID = "com.sq.core.gtw.actions.pub.DictMgnAction"; //$NON-NLS-1$
	
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public DictMgnAction() {
	}

	public DictMgnAction(IWorkbenchWindow window) {
		this.window = window;
	}
	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run() {
		try {
			window.getWorkbench().showPerspective(DictMgnPerspective.ID, window);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return Activator.getImageDescriptor("icons/newfolder_wiz.gif"); //设置action图标
	}

}