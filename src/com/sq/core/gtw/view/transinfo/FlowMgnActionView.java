package com.sq.core.gtw.view.transinfo;

import gtwplatfrom.Activator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.sq.core.gtw.edit.FlowMgnActionEdit;
import com.sq.core.gtw.edit.editinput.FlowMgnActionEditorInput;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.dialog.FlowMgnCreateActionTransDialog;
import com.sq.core.gtw.view.pub.contentprovider.FlowMgnActionContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.FlowMgnActionLaberProvider;
import com.sq.core.gtw.view.pub.vo.FlowMgnActionInfo;
import com.thoughtworks.xstream.XStream;

/**
 * @���� whai
 * @�������� 2013-7-6
 * @�汾 V1.0
 * @�ļ��� FlowMgnActionBizView.java
 */
public class FlowMgnActionView extends ViewPart{

	public static final String ID = "com.sq.core.gtw.view.transinfo.FlowMgnActionView"; //$NON-NLS-1$

	private XStream xstream = new XStream();
	private List<FlowMgnActionInfo> flowMgnActionList = new ArrayList<FlowMgnActionInfo>();
	private static String actionMain = ReadProperties.getSystemKey("ACTION_MAIN");
	private static String actionPath = ReadProperties.getSystemKey("ACTION_PATH");
	private Action doubleClickAction;
	private TableViewer viewer;
	private Action addAction ;
	private Action deleteAction ;
	private Action reNameAction ;
	
	public FlowMgnActionView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new FlowMgnActionContentProvider());
		viewer.setLabelProvider(new FlowMgnActionLaberProvider());
		// Provide the input to the ContentProvider
		initInputValue();
		
		viewer.setInput(flowMgnActionList);
		
		hookDoubleClickAction();
		contributeActions();
		initializeMenu();
		hookContextMenu();
	}

	private void initializeMenu() {
		// TODO Auto-generated method stub
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
		manager.add(addAction);
		manager.add(deleteAction);
		manager.add(reNameAction);
	}

	/**
	 * �����Ĳ˵�
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.add(addAction);
		menuMgr.add(deleteAction);
		menuMgr.add(reNameAction);
		
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
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
				FlowMgnActionInfo flowMgnActionInfo = (FlowMgnActionInfo) obj;
				//���״̬��
			 	IStatusLineManager statusline =	getViewSite().getActionBars().getStatusLineManager();
			 	if(flowMgnActionInfo==null)
			 		statusline.setMessage("");
			 	else
			 	statusline.setMessage(flowMgnActionInfo.getName());
			}
		});
	}

	private void contributeActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				FlowMgnActionInfo flowMgnActionInfo = (FlowMgnActionInfo) obj;
				String path = actionPath + File.separator + flowMgnActionInfo.getCode() + ".action";
				IEditorInput input = new FlowMgnActionEditorInput(new Path(path));
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			 	
				try {
					page.openEditor(input, FlowMgnActionEdit.ID , true);
					page.showView(ConstantUtil.PROPERTY_VIEW);
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ", e.getMessage());
				}
			}
		};
		//�����ͼ�߼�
		addAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnCreateActionTransDialog createDict = new FlowMgnCreateActionTransDialog(viewer.getControl().getShell() , null );
				if(createDict.open() == TitleAreaDialog.OK){
					FlowMgnActionInfo flowMgnActionInfo = createDict.getFlowMgnActionInfo();
					//�����Ĵ��벻����ԭ������ͬ
					for (FlowMgnActionInfo temp : flowMgnActionList) {
						if(temp.getCode().equals(flowMgnActionInfo.getCode())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ","��������ͼ���벻����ԭ��ͼ������ͬ��");
							return ;
						}
					}
					//�����ǰѡ��ļ������ͣ���������Ԫ������Ϊ���ϵ��ӽڵ�
					flowMgnActionList.add(flowMgnActionInfo);
					viewer.setInput(flowMgnActionList);
					viewer.refresh();
					//�������ݵ��ļ���
					saveToFile();
					
				}
			}
			
		};
		addAction.setText("������ͼ�߼�");
		addAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		//ɾ��ҵ���߼�
		deleteAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnActionInfo flowMgnActionInfo = getSelectElement() ;
				flowMgnActionList.remove(flowMgnActionInfo);
				viewer.refresh();
				saveToFile();
			}
			
		};
		deleteAction.setText("ɾ����ͼ�߼�");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		//������
		reNameAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnActionInfo flowMgnActionInfoTemp = getSelectElement() ;
				FlowMgnCreateActionTransDialog createDict = new FlowMgnCreateActionTransDialog(viewer.getControl().getShell() , flowMgnActionInfoTemp );
				if(createDict.open() == TitleAreaDialog.OK){
					FlowMgnActionInfo flowMgnActionInfo = createDict.getFlowMgnActionInfo();
					flowMgnActionInfoTemp.setName(flowMgnActionInfo.getName());
					viewer.refresh();
					//�������ݵ��ļ���
					saveToFile();
					
				}
			}
			
		};
		reNameAction.setText("������");
//		reNameAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
	}

	/**
	 * ��ʼ��ҵ�������б�
	 */
	@SuppressWarnings("unchecked")
	private void initInputValue(){
		xstream.alias("actions", List.class);
		xstream.processAnnotations(FlowMgnActionInfo.class);
		flowMgnActionList = (List)XMLFileOp.readToFile(actionMain, xstream);
		if(flowMgnActionList == null)
			flowMgnActionList = new ArrayList<FlowMgnActionInfo>() ;
	}
	
	/**
	 * �������ݵ��ļ���
	 */
	private void saveToFile(){
		XMLFileOp.saveToFile(actionMain, flowMgnActionList, null);
	}
	
	/**
	 * �������ǰѡ�еĽڵ�
	 * @return
	 */
	private FlowMgnActionInfo getSelectElement(){
		StructuredSelection select = (StructuredSelection)viewer.getSelection();
		FlowMgnActionInfo flowMgnActionInfo = (FlowMgnActionInfo)select.getFirstElement();
		return flowMgnActionInfo ;
	}
	
	/**
	 * Initialize the toolbar.
	 */
	@SuppressWarnings("unused")
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(addAction);
		toolbarManager.add(deleteAction);
		toolbarManager.add(reNameAction);
	}

}
