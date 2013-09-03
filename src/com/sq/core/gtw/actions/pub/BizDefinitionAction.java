package com.sq.core.gtw.actions.pub;

import gtwplatfrom.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;

import com.sq.core.gtw.view.pub.BizDefinitionView;

/**
 *@����  whai 
 *@�������� 2013-8-18
 *@�汾 V1.0
 *@�ļ��� BizDefinitionAction.java
 */
public class BizDefinitionAction extends Action {

	public static final String ID = "com.sq.core.gtw.actions.pub.BizDefinitionAction"; //$NON-NLS-1$
	
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public BizDefinitionAction() {
	}

	public BizDefinitionAction(IWorkbenchWindow window) {
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
			window.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(BizDefinitionView.ID);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return Activator.getImageDescriptor("icons/newfolder_wiz.gif"); //����actionͼ��
	}

}
