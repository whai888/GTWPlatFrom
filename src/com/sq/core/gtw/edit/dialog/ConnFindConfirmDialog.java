package com.sq.core.gtw.edit.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceLabelProvider;
import com.sq.core.gtw.edit.vo.BizServiceInfo;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

/**
 *@����  whai 
 *@�������� 2013-8-12
 *@�汾 V1.0
 *@�ļ��� ConnFindConfirmDialog.java
 */
public class ConnFindConfirmDialog extends Dialog {

	private BizServiceInfo bizServiceInfo ;
	private String dictPath = ReadProperties.getSystemKey("PUB_CONN");;
	private XStream xstream = new XStream();
	private List<BizServiceInfo> bizServiceList = new ArrayList<BizServiceInfo>();
	private TreeViewer tree ;
	//ȷ��
	private Button button_1 ;
	//ȡ��
	private Button button_2 ;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ConnFindConfirmDialog(Shell parentShell) {
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
		container.setLayout(new GridLayout(5, false));
		
		tree = new TreeViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		Tree tree_1 = tree.getTree();
		tree_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		TreeColumn TreeColumn = new TreeColumn(tree.getTree(), SWT.NONE);
		TreeColumn.setWidth(133);
		TreeColumn.setText("��������");
		
		TreeColumn TreeColumn_1 = new TreeColumn(tree.getTree(), SWT.NONE);
		TreeColumn_1.setWidth(100);
		TreeColumn_1.setText("��������");
		
		TreeColumn TreeColumn_2 = new TreeColumn(tree.getTree(), SWT.NONE);
		TreeColumn_2.setWidth(342);
		TreeColumn_2.setText("����");
		
		tree.getTree().setHeaderVisible(true);
		tree.getTree().setLinesVisible(true);
		
		tree.setContentProvider(new FlowMgnBizServiceContentProvider());
		tree.setLabelProvider(new FlowMgnBizServiceLabelProvider());
		
		initializeDict();
		tree.setInput(bizServiceList);

		return container;
	}

	/**
	 * ��ʼ�������ֵ��ֵ
	 */
	@SuppressWarnings("unchecked")
	private void initializeDict() {
		// TODO Auto-generated method stub
		xstream.processAnnotations(BizServiceInfo.class);
		bizServiceList = (ArrayList<BizServiceInfo>)XMLFileOp.readToFile(dictPath, xstream);
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button_1 = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button_1.setText("  ȷ  ��  ");
		button_2 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_2.setText("  ȡ  ��  ");
	}

	/**
	 * �������ǰѡ�еĽڵ�
	 * @return
	 */
	private BizServiceInfo getSelectElement(){
		StructuredSelection select = (StructuredSelection)tree.getSelection();
		BizServiceInfo bizServiceInfo = (BizServiceInfo)select.getFirstElement();
		return bizServiceInfo ;
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
		bizServiceInfo = getSelectElement();
		if(bizServiceInfo == null){
			MessageDialog.openWarning(this.getShell(), "������ʾ", "��ѡ��Ҫ��Ӧ�ķ���!");
			return ;
		}else{
			super.okPressed();
		}
	}

	public BizServiceInfo getBizServiceInfo() {
		return bizServiceInfo;
	}
}
