package com.sq.core.gtw.view.pub;

import gtwplatfrom.Activator;

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

import com.sq.core.gtw.edit.PkgMgnTextEdit;
import com.sq.core.gtw.edit.editinput.PkgMgnTextEditorInput;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.view.pub.contentprovider.PkgMgnContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.PkgMgnLabelProvider;

/**
 * @���� whai
 * @�������� 2013-6-24
 * @�汾 V1.0
 * @�ļ��� PkgMgnView.java
 */
public class PkgMgnView extends ViewPart {
	public PkgMgnView() {
	}
	public static final String ID = "com.sq.core.gtw.view.pub.PkgMgnView";

	private Action doubleClickAction;
	private TableViewer viewer;
	private Action deleteAction ;
	private Action addTextAction ;
	private Action addXmlAction ;
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new PkgMgnContentProvider());
		viewer.setLabelProvider(new PkgMgnLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(ConstantUtil.PKG_VIEW);
		
		hookDoubleClickAction();
		createActions();
		initializeToolBar();
		initializeMenu();
		hookContextMenu();
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
				//���״̬��
			 	IStatusLineManager statusline =	getViewSite().getActionBars().getStatusLineManager();
			 	statusline.setMessage(person);
			}
		});
	}

	private void createActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				String person = (String) obj;
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEditorInput input = new PkgMgnTextEditorInput(person);
				try {
					if(person.equals(ConstantUtil.PKG_VIEW[0])){
						page.openEditor(input , PkgMgnTextEdit.ID);
						page.setEditorAreaVisible(true);
					}else{
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "������Ϣ", "δ����ñ��ĵı༭��");
					}
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ", e.getMessage());
				}
			}
		};
		
		//ɾ��������Ϣ
		deleteAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
		};
		deleteAction.setText("ɾ��");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor("icons/delete_edit(1).gif"));
		//����ı�����
		addTextAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
			
		};
		addTextAction.setText("����SocketͨѶ");
		addTextAction.setImageDescriptor(Activator.getImageDescriptor("icons/add_att(1).gif"));
		//���XML����
		addXmlAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
			
		};
		addXmlAction.setText("����FTPͨѶ");
		addXmlAction.setImageDescriptor(Activator.getImageDescriptor("icons/add_att(1).gif"));
	}
	
	private void initializeMenu() {
		// TODO Auto-generated method stub
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
		manager.add(addTextAction);
		manager.add(addXmlAction);
		manager.add(deleteAction);
	}

	/**
	 * �����Ĳ˵�
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.add(addTextAction);
		menuMgr.add(addXmlAction);
		menuMgr.add(deleteAction);
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	private void initializeToolBar() {
		// TODO Auto-generated method stub
//		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
//		tbm.add(addTextAction);
//		tbm.add(addXmlAction);
//		tbm.add(deleteAction);
	}
}