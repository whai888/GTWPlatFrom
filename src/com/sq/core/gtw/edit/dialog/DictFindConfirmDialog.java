package com.sq.core.gtw.edit.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.sq.core.gtw.edit.labelcontent.DictMgnTableContentProvider;
import com.sq.core.gtw.edit.labelcontent.DictMgnTableLabelProvider;
import com.sq.core.gtw.edit.vo.DictMgnVo;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

/**
 * @���� whai
 * @�������� 2013-7-19
 * @�汾 V1.0
 * @�ļ��� DictFindConfirmDialog.java
 */
public class DictFindConfirmDialog extends Dialog {
	private DictMgnVo dictMgnVo ;
	private String dictPath = "";
	private XStream xstream = new XStream();
	private Text text;
	private List<DictMgnVo> dictList = new ArrayList<DictMgnVo>();
	private TreeViewer tree ;
	//��������
	private Button button ;
	//ȷ��
	private Button button_1 ;
	//ȡ��
	private Button button_2 ;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DictFindConfirmDialog(Shell parentShell) {
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
		Group group = new Group(container, SWT.FULL_SELECTION);
		group.setText("��������");
		group.setLayout(new GridLayout(4, false));
		group.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, true, false, 5, 1));
		
		Label lblId = new Label(group, SWT.NONE);
		lblId.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, true, false, 1, 1));
		lblId.setText("���ң�");
		
		text = new Text(group, SWT.BORDER);
		GridData gd_text = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 176;
		text.setLayoutData(gd_text);
		text.setText("");
		
		button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String find = text.getText();
				List<DictMgnVo> tempList = new ArrayList<DictMgnVo>();
				for (DictMgnVo dictMgnVo : dictList) {
					if(dictMgnVo.getCode().contains(find) || dictMgnVo.getName().contains(find)){
						tempList.add(dictMgnVo);
					}
				}
				tree.setInput(tempList);
				tree.refresh();
			}
		});
		button.setText("��������");
		button.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, true, false, 2, 1));
		
		tree = new TreeViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		Tree tree_1 = tree.getTree();
		tree_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		TreeColumn codeColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		codeColumn.setWidth(111);
		codeColumn.setText("����");
		
		TreeColumn nameColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		nameColumn.setWidth(184);
		nameColumn.setText("����");
		
		TreeColumn typeColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		typeColumn.setWidth(109);
		typeColumn.setText("���");
		
		TreeColumn lengthColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		lengthColumn.setWidth(87);
		lengthColumn.setText("����");
		
		TreeColumn valueColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		valueColumn.setWidth(87);
		valueColumn.setText("��ʼֵ");
		
		TreeColumn descripColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		descripColumn.setWidth(238);
		descripColumn.setText("����");
		
		tree.getTree().setHeaderVisible(true);
		tree.getTree().setLinesVisible(true);
		
		tree.setContentProvider(new DictMgnTableContentProvider());
		tree.setLabelProvider(new DictMgnTableLabelProvider());
		initializeDict();
		tree.setInput(dictList);

		return container;
	}

	/**
	 * ��ʼ�������ֵ��ֵ
	 */
	@SuppressWarnings("unchecked")
	private void initializeDict() {
		// TODO Auto-generated method stub
		dictPath = ReadProperties.getSystemKey("PUB_DICT");
		xstream.processAnnotations(DictMgnVo.class);
		xstream.alias("dictlist", List.class);
		dictList = (ArrayList<DictMgnVo>)XMLFileOp.readToFile(dictPath, xstream);
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
	private DictMgnVo getSelectElement(){
		StructuredSelection select = (StructuredSelection)tree.getSelection();
		DictMgnVo dictMgnVo = (DictMgnVo)select.getFirstElement();
		return dictMgnVo ;
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
		dictMgnVo = getSelectElement();
		if(dictMgnVo == null){
			MessageDialog.openWarning(this.getShell(), "������ʾ", "��ѡ��Ҫ��Ӧ�������ֵ�!");
			return ;
		}else{
			super.okPressed();
		}
	}

	public DictMgnVo getDictMgnVo() {
		return dictMgnVo;
	}

}
