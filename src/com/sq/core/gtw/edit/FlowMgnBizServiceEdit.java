package com.sq.core.gtw.edit;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.dialog.ConnFindConfirmDialog;
import com.sq.core.gtw.edit.dialog.ServiceMgnCreateDialog;
import com.sq.core.gtw.edit.editinput.FlowMgnBizServiceEditorInput;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceLabelProvider;
import com.sq.core.gtw.edit.vo.BizServiceInfo;
import com.sq.core.gtw.util.ConstantUtil;

/**
 * @作者 whai
 * @创建日期 2013-7-10
 * @版本 V1.0
 * @文件名 FlowMgnBizServiceEdit.java
 */
public class FlowMgnBizServiceEdit extends EditorPart {

	public static final String ID = "com.sq.core.gtw.edit.FlowMgnBizServiceEdit"; //$NON-NLS-1$
	private FlowMgnBizMutiEdit mutiEdit ;
	private List<BizServiceInfo> serviceList; 
	private Text text;
	private Text text_1;
	private Button button ;
	private Button button_1 ;
	private Button button_2 ;
	private Button button_3 ;
	private TreeViewer  tree ;
	private Combo combo ;
	

	public FlowMgnBizServiceEdit(FlowMgnBizMutiEdit mutiEdit) {
		this.mutiEdit = mutiEdit;
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gl_container = new GridLayout(5, false);	//设置为4格
		gl_container.horizontalSpacing=10;
		container.setLayout(gl_container);
		
		Label lblid = new Label(container, SWT.NONE);
		lblid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblid.setText("服务名称：");
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("服务类型：");
		
		combo = new Combo(container,  SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false, 4, 1));
		combo.setItems(ConstantUtil.SERVICE_STR);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("描述：");
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text = new Text(container, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
		gd_text.heightHint = 42;
		text.setLayoutData(gd_text);
		
		button = new Button(container, SWT.NONE);
		button.setText("   创 建   ");
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		button_2 = new Button(container, SWT.NONE);
		button_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_2.setText("  删  除  ");
		
		button_3 = new Button(container, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_3.setText("  更  新  ");
		
		button_1 = new Button(container, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_1.setText("  引  入  ");
		
		
		tree = new TreeViewer(container, SWT.MULTI|SWT.FULL_SELECTION);
		tree.getTree().setLinesVisible(true);
		tree.getTree().setHeaderVisible(true);
		tree.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		
		TreeColumn TreeColumn = new TreeColumn(tree.getTree(), SWT.NONE);
		TreeColumn.setWidth(133);
		TreeColumn.setText("服务名字");
		
		TreeColumn TreeColumn_1 = new TreeColumn(tree.getTree(), SWT.NONE);
		TreeColumn_1.setWidth(100);
		TreeColumn_1.setText("服务类型");
		
		TreeColumn TreeColumn_2 = new TreeColumn(tree.getTree(), SWT.NONE);
		TreeColumn_2.setWidth(342);
		TreeColumn_2.setText("描述");
		
		tree.setLabelProvider(new FlowMgnBizServiceLabelProvider());
		tree.setContentProvider(new FlowMgnBizServiceContentProvider());
		
		initData();
		buttonListener();
		
		tree.setInput(serviceList);
	}

	/**
	 * 为按钮添加相应的事件
	 */
	private void buttonListener() {
		// TODO Auto-generated method stub
		//创建
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//检查名称及服务类型是否有值
				if(text_1.getText().isEmpty()){
					MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误提示", "请输入服务名称!");
					return ;
				}
				if(combo.getText().isEmpty()){
					MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误提示", "请选择服务类型!");
					return ;
				}
				//检查输入的服务名称是否已经存在
				for (BizServiceInfo serviceInfo : serviceList) {
					if(serviceInfo.getName().equals(text_1.getText())){
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误信息", "新增的服务名称不能相同");
						return ;
					}
				}
				BizServiceInfo serviceInfoVo = new BizServiceInfo();
				serviceInfoVo.setName(text_1.getText());
				serviceInfoVo.setServiceType(combo.getText());
				serviceInfoVo.setDesc(text.getText());
				ServiceMgnCreateDialog createDict = new ServiceMgnCreateDialog(tree.getControl().getShell() , serviceInfoVo);
				if(createDict.open() == Dialog.OK){
					serviceInfoVo.setDesc(createDict.getServiceInfo().getDesc());
					serviceInfoVo.setSubInfo(createDict.getServiceList());
					serviceList.add(serviceInfoVo);
					tree.refresh();
					//刷新
					mutiEdit.setDirty(true);
				}
			}
		});
		//引入
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConnFindConfirmDialog findServiceDialog = new ConnFindConfirmDialog(tree.getControl().getShell()) ;
				if(findServiceDialog.open() == Dialog.OK){
					BizServiceInfo serviceInfoVo = findServiceDialog.getBizServiceInfo() ;
					//检查引入的服务名是否有冲突
					for (BizServiceInfo serviceInfoTemp : serviceList) {
						if(serviceInfoTemp.getName().equals(serviceInfoVo.getName())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误信息", "引入的服务名称有冲突，请修改后重新引入");
							return ;
						}
					}
					serviceList.add(findServiceDialog.getBizServiceInfo());
					tree.refresh();
					//刷新
					mutiEdit.setDirty(true);
				}
			}
		});
		//删除
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BizServiceInfo serviceInfoVo = getSelectElement();
				serviceList.remove(serviceInfoVo);
				tree.refresh();
				//刷新
				mutiEdit.setDirty(true);
			}
		});
		//更新
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BizServiceInfo serviceInfoVo = getSelectElement();
				if(serviceInfoVo != null ){
					text_1.setText(serviceInfoVo.getName());
					combo.setText(serviceInfoVo.getServiceType());
					text.setText(serviceInfoVo.getDesc());
					ServiceMgnCreateDialog createDict = new ServiceMgnCreateDialog(tree.getControl().getShell() , serviceInfoVo);
					if(createDict.open() == Dialog.OK){
						serviceInfoVo.setDesc(createDict.getServiceInfo().getDesc());
						text.setText(serviceInfoVo.getDesc());
						serviceInfoVo.setSubInfo(createDict.getServiceList());
						tree.refresh();
						//刷新
						mutiEdit.setDirty(true);
					}
				}
			}
		});
		
		//点击改变事件
		tree.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				BizServiceInfo bizServiceInfo = getSelectElement() ;
				if(bizServiceInfo !=null && bizServiceInfo.getName() != null)
					text_1.setText(bizServiceInfo.getName());
				if(bizServiceInfo.getServiceType() != null)
					combo.setText(bizServiceInfo.getServiceType());
				if(bizServiceInfo.getDesc() != null)
					text.setText(bizServiceInfo.getDesc());
			}
		});
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// TODO Auto-generated method stub
		serviceList = ((FlowMgnBizServiceEditorInput)this.getEditorInput()).getServiceList() ;
	}

	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private BizServiceInfo getSelectElement(){
		StructuredSelection select = (StructuredSelection)tree.getSelection();
		BizServiceInfo serviceInfoVo = (BizServiceInfo)select.getFirstElement();
		return serviceInfoVo ;
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

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
