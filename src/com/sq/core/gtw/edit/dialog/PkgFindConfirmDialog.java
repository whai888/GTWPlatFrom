package com.sq.core.gtw.edit.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageLeftContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageLeftLabelProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageRightContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizPackageRightLabelProvider;
import com.sq.core.gtw.edit.vo.BizPackageInfo;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

/**
 *@作者  whai 
 *@创建日期 2013-8-12
 *@版本 V1.0
 *@文件名 PkgFindConfirmDialog.java
 */
public class PkgFindConfirmDialog extends Dialog {

	private List<BizPackageInfo>  packInfoList ;
	private String pkgPath = ReadProperties.getSystemKey("PUB_PKG");;
	private XStream xstream = new XStream();
	private BizPackageInfo bizPackageInfo ;
	private TreeViewer treeViewer ;
	private Tree tree ;
	//确定
	private Button button_1 ;
	//取消
	private Button button_2 ;

	private TableViewer tableViewer ;
	private Table table;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PkgFindConfirmDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		
		GridData gd_tableViewer = new GridData(SWT.FILL,SWT.FILL, true, true, 1, 1);
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
		
		tableViewer.setInput(packInfoList);
		
		treeViewer = new TreeViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		TreeColumn TreeColumn = new TreeColumn(tree, SWT.NONE);
		TreeColumn.setWidth(133);
		TreeColumn.setText("服务名字");
		
		TreeColumn TreeColumn_1 = new TreeColumn(tree, SWT.NONE);
		TreeColumn_1.setWidth(100);
		TreeColumn_1.setText("服务类型");
		
		TreeColumn TreeColumn_2 = new TreeColumn(tree, SWT.NONE);
		TreeColumn_2.setWidth(342);
		TreeColumn_2.setText("描述");
		
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		treeViewer.setLabelProvider(new FlowMgnBizPackageRightLabelProvider());
		treeViewer.setContentProvider(new FlowMgnBizPackageRightContentProvider());

		return container;
	}

	private void buttonListener() {
		// TODO Auto-generated method stub
		//表格选择事件
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				BizPackageInfo bizPackageInfo = getSelectElement() ;
				if(bizPackageInfo != null ){
					treeViewer.setInput(bizPackageInfo.getDictList());
					treeViewer.refresh();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void initData() {
		// TODO Auto-generated method stub
		xstream.processAnnotations(BizPackageInfo.class);
		packInfoList = (ArrayList<BizPackageInfo>)XMLFileOp.readToFile(pkgPath, xstream);
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
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button_1 = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button_1.setText("  确  定  ");
		button_2 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_2.setText("  取  消  ");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(840, 468);
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		bizPackageInfo = getSelectElement();
		if(bizPackageInfo == null){
			MessageDialog.openWarning(this.getShell(), "错误提示", "请选择要对应的报文!");
			return ;
		}else{
			super.okPressed();
		}
	}

	public BizPackageInfo getBizPackageInfo() {
		return bizPackageInfo;
	}

}
