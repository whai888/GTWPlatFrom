package com.sq.core.gtw.view.pub;

import org.eclipse.jface.action.Action;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.sq.core.gtw.edit.editinput.TransInfoEditorInput;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.view.dialog.DataGenerateScriptWizard;
import com.sq.core.gtw.view.dialog.DataGenerateScriptWizardDialog;
import com.sq.core.gtw.view.pub.contentprovider.TransInfoContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.TransInfoLabelProvider;
import com.sq.core.gtw.view.transinfo.DataMgnView;
import com.sq.core.gtw.view.transinfo.FlowMgnActionView;
import com.sq.core.gtw.view.transinfo.FlowMgnBizView;

public class TransInfoView extends ViewPart {
	public static final String ID = "com.sq.core.gtw.view.pub.TransInfoView";

	private Action doubleClickAction;
	private TableViewer viewer;
	private Action dataScriptAction ;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new TransInfoContentProvider());
		viewer.setLabelProvider(new TransInfoLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(ConstantUtil.TRANS_VIEW);
		
		hookDoubleClickAction();
		contributeActions();
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
				if(person!=null && person.equals(ConstantUtil.TRANS_VIEW[2])){
					dataScriptAction.setEnabled(true);
				}else{
					dataScriptAction.setEnabled(false);
				}
				//添加状态栏
			 	IStatusLineManager statusline =	getViewSite().getActionBars().getStatusLineManager();
			 	statusline.setMessage(person);
			}
		});
	}

	@SuppressWarnings("unused")
	private void contributeActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				String person = (String) obj;
				TransInfoEditorInput input = new TransInfoEditorInput(person);
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			 	
				try {
					if(person.equals(ConstantUtil.TRANS_VIEW[1])){
						//逻辑层
						page.showView(FlowMgnBizView.ID);
						page.setEditorAreaVisible(true);
					}else if(person.equals(ConstantUtil.TRANS_VIEW[0])){
						//视图层
						page.showView(FlowMgnActionView.ID);
						page.setEditorAreaVisible(true);
					}else if(person.equals(ConstantUtil.TRANS_VIEW[2])){
						//数据库层
						page.showView(DataMgnView.ID);
						page.setEditorAreaVisible(true);
					}else{
						
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误信息", "未定义该报文的编辑器");
					}
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
				}
			}
		};
		//数据库脚本生成向导
		dataScriptAction = new Action() {
			public void run() {
				DataGenerateScriptWizard dataGenerateScriptWizard = new DataGenerateScriptWizard();
				DataGenerateScriptWizardDialog dialog = new DataGenerateScriptWizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), dataGenerateScriptWizard);
				dialog.open();
			}
		};
		dataScriptAction.setText("生成数据库脚本");
	}

	/**
	 * 上下文菜单
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.add(dataScriptAction);
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
}