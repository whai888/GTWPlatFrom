package com.sq.core.gtw.edit;

import gtwplatfrom.Activator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.dialog.DictFindConfirmDialog;
import com.sq.core.gtw.edit.dialog.PkgFindConfirmDialog;
import com.sq.core.gtw.edit.editinput.FlowMgnBizDataPkgEditorInput;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageLeftContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageLeftLabelProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageRightContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageRightLabelProvider;
import com.sq.core.gtw.edit.vo.BizPackageInfo;
import com.sq.core.gtw.edit.vo.DictMgnVo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;

/**
 * @作者 whai
 * @创建日期 2013-7-10
 * @版本 V1.0
 * @文件名 FlowMgnBizDatePkgEdit.java
 */
public class FlowMgnBizDataPkgEdit extends EditorPart {

	public static final String ID = "com.sq.core.gtw.edit.FlowMgnBizDataPkgEdit"; //$NON-NLS-1$
	private FlowMgnBizMutiEdit mutiEdit ;
	private List<BizPackageInfo>  packInfoList ;
	private List<DictMgnVo> dictList = new ArrayList<DictMgnVo>();
	private Text text_1;
	private Table table;
	private Text text_2;
	private Text text;
	private Button button ;
	private Button button1 ;
	private Button button2 ;
	private Button button3 ;
	private Combo combo; 
	private TreeViewer treeViewer;
	private TableViewer tableViewer ;
	//删除节点
	private Action deleteAction ;
	//下级节点
	private Action nodeAction ;
	//新增节点
	private Action addAction ;

	public FlowMgnBizDataPkgEdit(FlowMgnBizMutiEdit mutiEdit) {
		this.mutiEdit = mutiEdit ;
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.FULL_SELECTION);
		GridLayout gl_container = new GridLayout(6, false);	//设置为4格
		container.setLayout(gl_container);
		
		Label lblid = new Label(container, SWT.NONE);
		lblid.setText("报文名称：");
		GridData gd_text = new GridData(SWT.FILL,SWT.CENTER, true, false, 3, 1);
//		gridData.verticalSpan = 2; //跨两行 
//		gridData.horizontalSpan=2; //跨两列 
//		gridData.verticalAlignment = GridData.FILL; //垂直方向充满 
//		gridData.grabExcessVerticalSpace = true; //抢占垂直方向额外空间 
//		gridData.horizontalAlignment = GridData.FILL;//水平方向充满 
//		gridData.grabExcessHorizontalSpace = true;//抢占水平方向额外空间 
		text = new Text(container, SWT.BORDER);
		gd_text.widthHint = 289;
		text.setLayoutData(gd_text);
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setText("报 文 类 型 ：");
		combo = new Combo(container,  SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.FILL,SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 200;
		combo.setLayoutData(gd_combo);
		combo.setItems(ConstantUtil.DATA_PKG);
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("报文中文名：");
		text_2 = new Text(container, SWT.BORDER);
		GridData gd_text_2 = new GridData(SWT.FILL,SWT.CENTER, true, false, 5, 1);
		gd_text_2.widthHint = 200;
		text_2.setLayoutData(gd_text_2);
		Label label = new Label(container, SWT.NONE);
		label.setText("  描  述：");
		text_1 = new Text(container, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_text_1 = new GridData(SWT.FILL,SWT.CENTER, true, false, 3, 1);
		gd_text_1.heightHint = 69;
		gd_text_1.widthHint = 200;
		text_1.setLayoutData(gd_text_1);
		
		GridData gd_label_4 = new GridData(SWT.FILL,SWT.BOTTOM, false, false, 1, 1);
		gd_label_4.heightHint = 20;
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("数据列表：");
		label_4.setLayoutData(gd_label_4);
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("");
		label_5.setLayoutData(new GridData(SWT.RIGHT,SWT.CENTER, true, false, 1, 1));
		
		button = new Button(container, SWT.NONE);
		button.setText("   创 建   ");
		button.setLayoutData(new GridData(SWT.RIGHT,SWT.CENTER, false, false, 1, 1));
		button1 = new Button(container, SWT.NONE);
		button1.setText("   删 除   ");
		button2 = new Button(container, SWT.NONE);
		button2.setText("   更 新   ");
		button3 = new Button(container, SWT.NONE);
		button3.setText("   引 入   ");
		
		GridData gd_treeViewer = new GridData(SWT.FILL,SWT.FILL, true, true, 2, 3);
		gd_treeViewer.heightHint=400;
		treeViewer = new TreeViewer(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		Tree tree = treeViewer.getTree();
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(gd_treeViewer);
		TreeColumn treeColumn = new TreeColumn(tree, SWT.CENTER);
		treeColumn.setWidth(100);
		treeColumn.setText("数据代码");
		TreeColumn treeColumn_1 = new TreeColumn(tree, SWT.CENTER);
		treeColumn_1.setWidth(115);
		treeColumn_1.setText("数据名称");
		TreeColumn treeColumn_2 = new TreeColumn(tree, SWT.CENTER);
		treeColumn_2.setWidth(110);
		treeColumn_2.setText("描述");
		treeViewer.setLabelProvider(new FlowMgnBizPackageRightLabelProvider());
		treeViewer.setContentProvider(new FlowMgnBizPackageRightContentProvider());
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setText("报文定义列表：");
		label_6.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER, false, false, 2, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		GridData gd_tableViewer = new GridData(SWT.FILL,SWT.FILL, true, true, 4, 1);
		gd_tableViewer.heightHint=300;
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(gd_tableViewer);
		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tblclmnid.setWidth(112);
		tblclmnid.setText("报文名称");
		TableColumn typeColumn = new TableColumn(table, SWT.NONE);
		typeColumn.setWidth(112);
		typeColumn.setText("报文类型");
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(164);
		tableColumn.setText("报文中文名");
		tableViewer.setLabelProvider(new FlowMgnBizPackageLeftLabelProvider());
		tableViewer.setContentProvider(new FlowMgnBizPackageLeftContentProvider());
		
		initData();
		buttonListener();
		createActions();
		
		tableViewer.setInput(packInfoList);
	}

	private void buttonListener() {
		// TODO Auto-generated method stub
		//创建按钮
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//检查名称及服务类型是否有值
				if(text.getText().isEmpty()){
					MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误提示", "报文名称必须输入!");
					return ;
				}
				if(combo.getText().isEmpty()){
					MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误提示", "请选择报文类型!");
					return ;
				}
				if(text_2.getText().isEmpty()){
					MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误提示", "报文中文名称必须输入!");
					return ;
				}
				//检查输入的服务名称是否已经存在
				for (BizPackageInfo bizPackageInfo : packInfoList) {
					if(bizPackageInfo.getName().equals(text.getText())){
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误信息", "新增的报文名称不能相同");
						return ;
					}
				}
				
				BizPackageInfo bizPackageInfo = new BizPackageInfo();
				bizPackageInfo.setName(text.getText());
				bizPackageInfo.setChName(text_2.getText());
				bizPackageInfo.setPackageType(combo.getText());
				bizPackageInfo.setDesc(text_1.getText());
				packInfoList.add(bizPackageInfo);
				tableViewer.setInput(packInfoList);
				tableViewer.refresh();
				
				mutiEdit.setDirty(true) ;
			}
		});
		//删除按钮
		button1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				BizPackageInfo bizPackageInfo = getSelectElement();
				packInfoList.remove(bizPackageInfo);
				tableViewer.refresh();
				treeViewer.setInput(null);
				treeViewer.refresh();
				//刷新
				mutiEdit.setDirty(true) ;
			}

		});
		//更新按钮
		button2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				BizPackageInfo bizPackageInfo = getSelectElement() ;
				if(!bizPackageInfo.getName().equals(text.getText())){
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误信息", "更新的报文节点名字与选中的不匹配");
					return ;
				}
				bizPackageInfo.setChName(text_2.getText());
				bizPackageInfo.setDesc(text_1.getText());
				tableViewer.refresh();
			}
			
		});
		//表格选择事件
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				BizPackageInfo bizPackageInfo = getSelectElement() ;
				if(bizPackageInfo != null ){
					text.setText(bizPackageInfo.getName());
					combo.setText(bizPackageInfo.getPackageType());
					text_2.setText(bizPackageInfo.getChName());
					text_1.setText(bizPackageInfo.getDesc());
					treeViewer.setInput(bizPackageInfo.getDictList());
					dictList = bizPackageInfo.getDictList();
					treeViewer.refresh();
					hookContextMenu();
				}
			}
		});
		//引入
		button3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PkgFindConfirmDialog pkgFindConfirmDialog = new PkgFindConfirmDialog(tableViewer.getControl().getShell()) ;
				if(pkgFindConfirmDialog.open() == Dialog.OK){
					BizPackageInfo bizPackageInfo = pkgFindConfirmDialog.getBizPackageInfo() ;
					//检查引入的服务名是否有冲突
					for (BizPackageInfo bizPackageInfoTemp : packInfoList) {
						if(bizPackageInfoTemp.getName().equals(bizPackageInfo.getName())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误信息", "引入的服务名称有冲突，请修改后重新引入");
							return ;
						}
					}
					packInfoList.add(bizPackageInfo);
					tableViewer.refresh();
					//刷新
					mutiEdit.setDirty(true);
				}
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		packInfoList = ((FlowMgnBizDataPkgEditorInput)getEditorInput()).getBizPackageInfo() ;
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}

	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private BizPackageInfo getSelectElement(){
		StructuredSelection select = (StructuredSelection)tableViewer.getSelection();
		BizPackageInfo bizPackageInfo = (BizPackageInfo)select.getFirstElement();
		return bizPackageInfo ;
	}
	
	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private DictMgnVo getTreeSelectElement(){
		StructuredSelection select = (StructuredSelection)treeViewer.getSelection();
		DictMgnVo dictMgnVo = (DictMgnVo)select.getFirstElement();
		return dictMgnVo ;
	}
	
	private void createActions() {
		//新增报文节点
		addAction = new Action() {
			public void run() {
				// TODO Auto-generated method stub
				DictFindConfirmDialog createDict = new DictFindConfirmDialog(treeViewer.getControl().getShell());
				if(createDict.open() == Dialog.OK){
					DictMgnVo dictMgnVo = createDict.getDictMgnVo();
					dictList.add(dictMgnVo);
					treeViewer.setInput(dictList);
					treeViewer.refresh();
					//刷新
					mutiEdit.setDirty(true) ;
				}
			}
		};
		addAction.setText("新增报文节点");
		addAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		//删除
		deleteAction = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DictMgnVo dictMgnVo = getTreeSelectElement();
				dictList.remove(dictMgnVo);
				treeViewer.setInput(dictList);
				treeViewer.refresh();
				//刷新
				mutiEdit.setDirty(true) ;
			}
			
		};
		deleteAction.setText("删除");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		
		//新增下级节点
		nodeAction = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DictFindConfirmDialog createDict = new DictFindConfirmDialog(treeViewer.getControl().getShell());
				if(createDict.open() == Dialog.OK){
					DictMgnVo dictMgnVo = createDict.getDictMgnVo();
					DictMgnVo dictMgnTemp = getTreeSelectElement();
					dictMgnTemp.add(dictMgnVo);
					treeViewer.setInput(dictList);
					treeViewer.refresh();
					//刷新
					mutiEdit.setDirty(true) ;
				}
			}
			
		};
		nodeAction.setText("下级节点");
		nodeAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
	}
	
	/**
	 * 上下文菜单
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		BizPackageInfo bizPackageInfo = getSelectElement();
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		//字符串报文
		if(bizPackageInfo.getPackageType().equals(ConstantUtil.DATA_PKG[0])){
			menuMgr.add(addAction);
			menuMgr.add(deleteAction);
		}
		//XML报文
		if(bizPackageInfo.getPackageType().equals(ConstantUtil.DATA_PKG[1])){
			menuMgr.add(addAction);
			menuMgr.add(nodeAction);
			menuMgr.add(deleteAction);
		}
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
}
