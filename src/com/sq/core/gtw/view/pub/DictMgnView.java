package com.sq.core.gtw.view.pub;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IStatusLineManager;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import com.sq.core.gtw.edit.DictMgnEdit;
import com.sq.core.gtw.edit.editinput.DictMgnEditorInput;
import com.sq.core.gtw.view.pub.contentprovider.DictTableContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.DictTableLabelProvider;

/**
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 DictMgnView.java
 */
public class DictMgnView extends ViewPart {

	public static final String ID = "com.sq.core.gtw.view.pub.DictMgnView"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private TableViewer table;
	private Action doubleClickAction;

	public DictMgnView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		table = new TableViewer(parent , SWT.MULTI|SWT.FULL_SELECTION);
		table.setContentProvider(new DictTableContentProvider());
		table.setLabelProvider(new DictTableLabelProvider());
		table.setInput(new String[]{"数据字典"});
		
		createActions();
		hookDoubleClickAction();
	}

	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	private void hookDoubleClickAction() {
		table.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
		table.addSelectionChangedListener(new ISelectionChangedListener() {
			
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
					ISelection selection = table.getSelection();
					Object obj = ((IStructuredSelection) selection).getFirstElement();
					IEditorInput input = new DictMgnEditorInput((String)obj);
					try {
						page.openEditor(input, DictMgnEdit.ID);
					} catch (PartInitException e) {
						e.printStackTrace();
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
					}
				}
			};
	}
	@Override
	public void setFocus() {
		// Set the focus
	}
}
