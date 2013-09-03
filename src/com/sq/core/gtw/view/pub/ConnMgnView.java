package com.sq.core.gtw.view.pub;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.sq.core.gtw.edit.ConnMgnSerivceEdit;
import com.sq.core.gtw.edit.editinput.ConnFtpMgnEditorInput;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.view.pub.contentprovider.ConnMgnContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.ConnMgnLabelProvider;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 ConnMgnView.java
 */
public class ConnMgnView extends ViewPart {
	public static final String ID = "com.sq.core.gtw.view.pub.ConnMgnView";

	private Action doubleClickAction;
	private TableViewer viewer;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new ConnMgnContentProvider());
		viewer.setLabelProvider(new ConnMgnLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(ConstantUtil.CONN_VIEW);
		
		hookDoubleClickAction();
		createActions();
		initializeToolBar();
		initializeMenu();
		hookContextMenu();
	}

	@SuppressWarnings("unused")
	private void initializeMenu() {
		// TODO Auto-generated method stub
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	/**
	 * 上下文菜单
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void initializeToolBar() {
		// TODO Auto-generated method stub
//		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
//		tbm.add(addSocketAction);
//		tbm.add(addFtpAction);
//		tbm.add(deleteAction);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				String person = (String) obj;
				//添加状态栏
			 	IStatusLineManager statusline =	getViewSite().getActionBars().getStatusLineManager();
			 	statusline.setMessage(person);
			}
		});
	}

	private void createActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				String connStr = (String) obj;
				String editId = "" ;
				IEditorInput input = null ;
				if(connStr.contentEquals(ConstantUtil.CONN_VIEW[0])){
					input = new ConnFtpMgnEditorInput(connStr);
					editId = ConnMgnSerivceEdit.ID ;
				}
				try {
					page.openEditor(input, editId);
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
				}
			}
		};
	}
}