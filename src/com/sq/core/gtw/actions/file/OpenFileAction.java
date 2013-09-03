package com.sq.core.gtw.actions.file;

import gtwplatfrom.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 OpenFileAction.java
 */
public class OpenFileAction extends Action {
	
	public static final String ID = "com.sq.core.gtw.actions.file.OpenFileAction"; //$NON-NLS-1$
	
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public OpenFileAction() {
	}

	public OpenFileAction(IWorkbenchWindow window) {
		this.window = window;
	}
	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run() {
		MessageDialog.openInformation(
			window.getShell(),
			"GTWPlatFrom",
			"打开文件");
//			try {
//				window.getWorkbench().showPerspective(TransPerspective.ID, window);
//			} catch (WorkbenchException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return Activator.getImageDescriptor("icons/newfolder_wiz.gif"); //设置action图标
	}

//	@Override
//	public void run(IAction action) {
//		// TODO Auto-generated method stub
//		try {
//			window.getActivePage().showView(TransInfoView.ID);
//		} catch (PartInitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public void init(IWorkbenchWindow window) {
//		// TODO Auto-generated method stub
//		this.window = window;
//	}
//
//	@Override
//	public void selectionChanged(IAction action, ISelection selection) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void dispose() {
//		// TODO Auto-generated method stub
//		
//	}

	

	
}