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
 * @作者 whai
 * @创建日期 2013-7-6
 * @版本 V1.0
 * @文件名 FlowMgnActionBizView.java
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
	 * 上下文菜单
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
				//添加状态栏
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
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
				}
			}
		};
		//添加视图逻辑
		addAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowMgnCreateActionTransDialog createDict = new FlowMgnCreateActionTransDialog(viewer.getControl().getShell() , null );
				if(createDict.open() == TitleAreaDialog.OK){
					FlowMgnActionInfo flowMgnActionInfo = createDict.getFlowMgnActionInfo();
					//新增的代码不能与原代码相同
					for (FlowMgnActionInfo temp : flowMgnActionList) {
						if(temp.getCode().equals(flowMgnActionInfo.getCode())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息","新增的视图代码不能与原视图代码相同！");
							return ;
						}
					}
					//如果当前选择的集合类型，则新增的元数据作为集合的子节点
					flowMgnActionList.add(flowMgnActionInfo);
					viewer.setInput(flowMgnActionList);
					viewer.refresh();
					//保存数据到文件中
					saveToFile();
					
				}
			}
			
		};
		addAction.setText("新增视图逻辑");
		addAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		//删除业务逻辑
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
		deleteAction.setText("删除视图逻辑");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		//重命名
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
		xstream.alias("actions", List.class);
		xstream.processAnnotations(FlowMgnActionInfo.class);
		flowMgnActionList = (List)XMLFileOp.readToFile(actionMain, xstream);
		if(flowMgnActionList == null)
			flowMgnActionList = new ArrayList<FlowMgnActionInfo>() ;
	}
	
	/**
	 * 保存数据到文件中
	 */
	private void saveToFile(){
		XMLFileOp.saveToFile(actionMain, flowMgnActionList, null);
	}
	
	/**
	 * 获得树当前选中的节点
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
