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

import com.sq.core.gtw.edit.DataMgnFormEditor;
import com.sq.core.gtw.edit.editinput.DataMgnFormEditorInput;
import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.dialog.DataMgnCreateActionTransDialog;
import com.sq.core.gtw.view.pub.contentprovider.DataMgnContentProvider;
import com.sq.core.gtw.view.pub.contentprovider.DataMgnLaberProvider;
import com.sq.core.gtw.view.pub.vo.DataMgnInfoVo;
import com.thoughtworks.xstream.XStream;

/**
 * @作者 whai
 * @创建日期 2013-7-23
 * @版本 V1.0
 * @文件名 DataMgnView.java
 */
public class DataMgnView extends ViewPart {

	public static final String ID = "com.sq.core.gtw.view.transinfo.DataMgnView"; //$NON-NLS-1$
	
	private XStream xstream = new XStream();
	private List<DataMgnInfoVo> dataMgnList = new ArrayList<DataMgnInfoVo>();
	private static String modelMain = ReadProperties.getSystemKey("MODEL_MAIN");
	private static String modelPath = ReadProperties.getSystemKey("MODEL_PATH");
	private Action doubleClickAction;
	private TableViewer viewer;
	private Action addAction ;
	private Action deleteAction ;
	private Action reNameAction ;

	public DataMgnView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new DataMgnContentProvider());
		viewer.setLabelProvider(new DataMgnLaberProvider());
		// Provide the input to the ContentProvider
		initInputValue();
		
		viewer.setInput(dataMgnList);
		
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
				DataMgnInfoVo dataMgnInfoVo = (DataMgnInfoVo) obj;
				//添加状态栏
			 	IStatusLineManager statusline =	getViewSite().getActionBars().getStatusLineManager();
			 	if(dataMgnInfoVo==null)
			 		statusline.setMessage("");
			 	else
			 	statusline.setMessage(dataMgnInfoVo.getTableName());
			}
		});
	}

	private void contributeActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				DataMgnInfoVo dataMgnInfoVo = (DataMgnInfoVo) obj;
				String path = modelPath + File.separator + dataMgnInfoVo.getTableName() + ".xml";
				IEditorInput input = new DataMgnFormEditorInput(new Path(path));
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			 	
				try {
					page.openEditor(input, DataMgnFormEditor.ID , true);
				} catch (PartInitException e) {
					e.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
				}
			}
		};
		//添加数据库逻辑
		addAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DataMgnCreateActionTransDialog createDict = new DataMgnCreateActionTransDialog(viewer.getControl().getShell() , null );
				if(createDict.open() == TitleAreaDialog.OK){
					DataMgnInfoVo dataMgnInfoVo = createDict.getDataMgnInfoVo();
					//新增的代码不能与原代码相同
					for (DataMgnInfoVo temp : dataMgnList) {
						if(temp.getTableName().equals(dataMgnInfoVo.getTableName())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息","新增的视图代码不能与原视图代码相同！");
							return ;
						}
					}
					//如果当前选择的集合类型，则新增的元数据作为集合的子节点
					dataMgnList.add(dataMgnInfoVo);
					viewer.setInput(dataMgnList);
					viewer.refresh();
					//保存数据到文件中
					XMLFileOp.saveToFile(modelPath , dataMgnList , null);
					
				}
			}
			
		};
		addAction.setText("新增数据库文件");
		addAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		
		//删除业务逻辑
		deleteAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DataMgnInfoVo dataMgnInfoVo = getSelectElement() ;
				dataMgnList.remove(dataMgnInfoVo);
				viewer.refresh();
				XMLFileOp.saveToFile(modelPath , dataMgnList , null);
			}
			
		};
		deleteAction.setText("删除数据库文件");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		//重命名
		reNameAction = new Action(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DataMgnInfoVo dataMgnInfoVoTemp = getSelectElement() ;
				DataMgnCreateActionTransDialog createDict = new DataMgnCreateActionTransDialog(viewer.getControl().getShell() , dataMgnInfoVoTemp );
				if(createDict.open() == TitleAreaDialog.OK){
					DataMgnInfoVo dataMgnInfoVo = createDict.getDataMgnInfoVo();
					dataMgnInfoVoTemp.setCnName(dataMgnInfoVo.getCnName());
					viewer.refresh();
					//保存数据到文件中
					XMLFileOp.saveToFile(modelPath , dataMgnList , null);
					
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
		xstream.alias("dao", List.class);
		xstream.processAnnotations(DataMgnInfoVo.class);
		dataMgnList = (List)XMLFileOp.readToFile(modelMain, xstream);
	}
	
	
	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private DataMgnInfoVo getSelectElement(){
		StructuredSelection select = (StructuredSelection)viewer.getSelection();
		DataMgnInfoVo dataMgnInfoVo = (DataMgnInfoVo)select.getFirstElement();
		return dataMgnInfoVo ;
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
