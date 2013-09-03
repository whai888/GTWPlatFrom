package com.sq.core.gtw.view.transinfo;

import gtwplatfrom.Activator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
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

import com.sq.core.gtw.edit.FlowMgnBizCreateSubEdit;
import com.sq.core.gtw.edit.FlowMgnBizMutiEdit;
import com.sq.core.gtw.edit.editinput.FlowMgnBizCreateSubEditorInput;
import com.sq.core.gtw.edit.editinput.FlowMgnBizMutiEditorInput;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.dialog.FlowMgnCreateBizTransDialog;
import com.sq.core.gtw.view.pub.contentprovider.FlowMgnBizContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.FlowMgnBizLabelProvider;
import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;
import com.thoughtworks.xstream.XStream;

/**
 * @���� whai
 * @�������� 2013-6-24
 * @�汾 V1.0
 * @�ļ��� FlowMgnView.java
 */
public class FlowMgnBizView extends ViewPart {
	public FlowMgnBizView() {
	}
	public static final String ID = "com.sq.core.gtw.view.transinfo.FlowMgnBizView";

	private XStream xstream = new XStream();
	private Action doubleClickAction;
	private TableViewer viewer;
	@SuppressWarnings("unchecked")
	private Map inputValue = new TreeMap();
	private List<FlowMgnBizInfo> flowMgnBizList = new ArrayList<FlowMgnBizInfo>();
	private static String bizMain = ReadProperties.getSystemKey("BIZ_MAIN");
	private static String bizPath = ReadProperties.getSystemKey("BIZ_PATH");
	private Action addAction ;
	private Action deleteAction ;
	private Action reNameAction ;
	private Action createBizAction ;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new FlowMgnBizContentProvider());
		viewer.setLabelProvider(new FlowMgnBizLabelProvider());
		// Provide the input to the ContentProvider
		initInputValue();
		viewer.setInput(flowMgnBizList);
		
		hookDoubleClickAction();
		contributeActions();
		initializeMenu();
		hookContextMenu();
		
	}

	private void initializeMenu() {
		// TODO Auto-generated method stub
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
		manager.add(addAction);
		manager.add(createBizAction);
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
		menuMgr.add(createBizAction);
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
				FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo) obj;
				//���״̬��
			 	IStatusLineManager statusline =	getViewSite().getActionBars().getStatusLineManager();
			 	if(flowMgnBizInfo==null)
			 		statusline.setMessage("");
			 	else
			 		statusline.setMessage(flowMgnBizInfo.getName());
			 	
			}
		});
	}

	private void contributeActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo) obj;
				//ָ�������ļ���·��
				//ָ���򿪵��ļ����˴����ݽ������������ļ�
				String path = bizPath + File.separator + flowMgnBizInfo.getCode() +".biz";
				IEditorInput input = new FlowMgnBizMutiEditorInput(new Path(path) , flowMgnBizInfo);
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					page.openEditor(input, FlowMgnBizMutiEdit.ID , true );
					page.showView(ConstantUtil.PROPERTY_VIEW);
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ", e.getMessage());
				}
			}
		};
		
		//���ҵ���߼�
		addAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnCreateBizTransDialog createDict = new FlowMgnCreateBizTransDialog(viewer.getControl().getShell() , null );
				if(createDict.open() == TitleAreaDialog.OK){
					FlowMgnBizInfo flowMgnBizInfo = createDict.getFlowMgnBizInfo();
					//�����Ĵ��벻����ԭ������ͬ
					for (FlowMgnBizInfo temp : flowMgnBizList) {
						if(temp.getCode().equals(flowMgnBizInfo.getCode())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ","������ҵ����벻����ԭҵ�������ͬ��");
							return ;
						}
					}
					//�����ǰѡ��ļ������ͣ���������Ԫ������Ϊ���ϵ��ӽڵ�
					flowMgnBizList.add(flowMgnBizInfo);
					viewer.setInput(flowMgnBizList);
					viewer.refresh();
					//�������ݵ��ļ���
					saveToFile();
					
				}
			}
			
		};
		addAction.setText("����ҵ���߼�");
		addAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		createBizAction  = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo) obj;
				//ָ�������ļ���·��
				//ָ���򿪵��ļ����˴����ݽ������������ļ�
				String path = bizPath + File.separator + flowMgnBizInfo.getCode() +".biz";
				IEditorInput input = new FlowMgnBizCreateSubEditorInput(new Path(path) , flowMgnBizInfo);
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					page.openEditor(input, FlowMgnBizCreateSubEdit.ID , true );
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ", e.getMessage());
				}
			}
			
		};
		createBizAction.setText("ҵ���������");
		createBizAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		//ɾ��ҵ���߼�
		deleteAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnBizInfo flowMgnBizInfo = getSelectElement() ;
				flowMgnBizList.remove(flowMgnBizInfo);
				viewer.refresh();
				saveToFile();
			}
			
		};
		deleteAction.setText("ɾ��ҵ���߼�");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		//������
		reNameAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnBizInfo flowMgnBizInfoTemp = getSelectElement() ;
				FlowMgnCreateBizTransDialog createDict = new FlowMgnCreateBizTransDialog(viewer.getControl().getShell() , flowMgnBizInfoTemp );
				if(createDict.open() == TitleAreaDialog.OK){
					FlowMgnBizInfo flowMgnBizInfo = createDict.getFlowMgnBizInfo();
					flowMgnBizInfoTemp.setName(flowMgnBizInfo.getName());
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
		xstream.alias("bizs", List.class);
		xstream.processAnnotations(FlowMgnBizInfo.class);
		flowMgnBizList = (List)XMLFileOp.readToFile(bizMain, xstream);;
	}
	
	/**
	 * �������ݵ��ļ���
	 */
	private void saveToFile(){
		XMLFileOp.saveToFile(bizMain, flowMgnBizList, null);
	}
	
	/**
	 * �������ǰѡ�еĽڵ�
	 * @return
	 */
	private FlowMgnBizInfo getSelectElement(){
		StructuredSelection select = (StructuredSelection)viewer.getSelection();
		FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo)select.getFirstElement();
		return flowMgnBizInfo ;
	}
	
	@SuppressWarnings("unchecked")
	public Map getInputValue() {
		return inputValue;
	}

	@SuppressWarnings("unchecked")
	public void setInputValue(Map inputValue) {
		this.inputValue = inputValue;
	}
	
}