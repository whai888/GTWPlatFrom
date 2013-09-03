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
 * @作者 whai
 * @创建日期 2013-6-24
 * @版本 V1.0
 * @文件名 FlowMgnView.java
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
	 * 上下文菜单
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
				//添加状态栏
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
				//指定加载文件的路径
				//指定打开的文件，此处根据交易码来定义文件
				String path = bizPath + File.separator + flowMgnBizInfo.getCode() +".biz";
				IEditorInput input = new FlowMgnBizMutiEditorInput(new Path(path) , flowMgnBizInfo);
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					page.openEditor(input, FlowMgnBizMutiEdit.ID , true );
					page.showView(ConstantUtil.PROPERTY_VIEW);
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
				}
			}
		};
		
		//添加业务逻辑
		addAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnCreateBizTransDialog createDict = new FlowMgnCreateBizTransDialog(viewer.getControl().getShell() , null );
				if(createDict.open() == TitleAreaDialog.OK){
					FlowMgnBizInfo flowMgnBizInfo = createDict.getFlowMgnBizInfo();
					//新增的代码不能与原代码相同
					for (FlowMgnBizInfo temp : flowMgnBizList) {
						if(temp.getCode().equals(flowMgnBizInfo.getCode())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息","新增的业务代码不能与原业务代码相同！");
							return ;
						}
					}
					//如果当前选择的集合类型，则新增的元数据作为集合的子节点
					flowMgnBizList.add(flowMgnBizInfo);
					viewer.setInput(flowMgnBizList);
					viewer.refresh();
					//保存数据到文件中
					saveToFile();
					
				}
			}
			
		};
		addAction.setText("新增业务逻辑");
		addAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		createBizAction  = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				FlowMgnBizInfo flowMgnBizInfo = (FlowMgnBizInfo) obj;
				//指定加载文件的路径
				//指定打开的文件，此处根据交易码来定义文件
				String path = bizPath + File.separator + flowMgnBizInfo.getCode() +".biz";
				IEditorInput input = new FlowMgnBizCreateSubEditorInput(new Path(path) , flowMgnBizInfo);
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					page.openEditor(input, FlowMgnBizCreateSubEdit.ID , true );
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
				}
			}
			
		};
		createBizAction.setText("业务组件定义");
		createBizAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		//删除业务逻辑
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
		deleteAction.setText("删除业务逻辑");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		//重命名
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
					//保存数据到文件中
					saveToFile();
					
				}
			}
			
		};
		reNameAction.setText("重命名");
//		reNameAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
	}

	/**
	 * 初始化业务流程列表
	 */
	@SuppressWarnings("unchecked")
	private void initInputValue(){
		xstream.alias("bizs", List.class);
		xstream.processAnnotations(FlowMgnBizInfo.class);
		flowMgnBizList = (List)XMLFileOp.readToFile(bizMain, xstream);;
	}
	
	/**
	 * 保存数据到文件中
	 */
	private void saveToFile(){
		XMLFileOp.saveToFile(bizMain, flowMgnBizList, null);
	}
	
	/**
	 * 获得树当前选中的节点
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