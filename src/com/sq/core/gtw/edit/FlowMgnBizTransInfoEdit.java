package com.sq.core.gtw.edit;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.dialog.DictFindConfirmDialog;
import com.sq.core.gtw.edit.editinput.FlowMgnBizTransInfoEditorInput;
import com.sq.core.gtw.edit.labelcontent.DictMgnTableContentProvider;
import com.sq.core.gtw.edit.labelcontent.DictMgnTableLabelProvider;
import com.sq.core.gtw.edit.vo.BizDatatransInfo;
import com.sq.core.gtw.edit.vo.DictMgnVo;

/**
 * @作者 whai
 * @创建日期 2013-7-10
 * @版本 V1.0
 * @文件名 FlowMgnBizTransInfoEdit.java
 */
public class FlowMgnBizTransInfoEdit extends EditorPart {

	public static final String ID = "com.sq.core.gtw.edit.FlowMgnBizTransInfoEdit"; //$NON-NLS-1$

	private FlowMgnBizMutiEdit mutiEdit ;
	private BizDatatransInfo bizDatatransInfo; 
	private List<DictMgnVo> input ;
	private List<DictMgnVo> output ;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button button_1 ;
	private Button button ;
	private TreeViewer tree ;
	private TreeViewer tree_1 ;
	private TabFolder tabFolder_1;

	public FlowMgnBizTransInfoEdit(FlowMgnBizMutiEdit mutiEdit) {
		this.mutiEdit = mutiEdit ;
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		Group group = new Group(container, SWT.FULL_SELECTION);
		GridData gd_group = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_group.widthHint = 619;
		group.setLayoutData(gd_group);
		group.setText("交易信息");
		group.setLayout(new GridLayout(4, false));
		
		Label lblId = new Label(group, SWT.NONE);
		lblId.setText("ID：");
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 200;
		text.setLayoutData(gd_text);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("描述：");
		label_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		text_2 = new Text(group, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 2);
		gd_text_1.heightHint = 50;
		gd_text_1.widthHint = 100;
		text_2.setLayoutData(gd_text_1);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("名称：");
		
		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_text_2= new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 200 ;
		text_1.setLayoutData(gd_text_2);
		
		initData();
		
		button_1 = new Button(container, SWT.NONE);
		button_1.setText("  新  增   ");
		
		button = new Button(container, SWT.NONE);
		button.setText("  删  除  ");
		
		tabFolder_1 = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_tabFolder_1.heightHint = 331;
		gd_tabFolder_1.widthHint = 721;
		tabFolder_1.setLayoutData(gd_tabFolder_1);
		
		TabItem tabItem_1 = new TabItem(tabFolder_1, SWT.NONE);
		tabItem_1.setText("  输  入  ");
		
		tree = new TreeViewer(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		TreeColumn codeColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		codeColumn.setWidth(111);
		codeColumn.setText("代码");
		
		TreeColumn nameColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		nameColumn.setWidth(184);
		nameColumn.setText("名称");
		
		TreeColumn typeColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		typeColumn.setWidth(109);
		typeColumn.setText("类别");
		
		TreeColumn lengthColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		lengthColumn.setWidth(87);
		lengthColumn.setText("类型");
		
		TreeColumn valueColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		valueColumn.setWidth(87);
		valueColumn.setText("初始值");
		
		TreeColumn descripColumn = new TreeColumn(tree.getTree(), SWT.CENTER);
		descripColumn.setWidth(238);
		descripColumn.setText("描述");
		
		tree.getTree().setHeaderVisible(true);
		tree.getTree().setLinesVisible(true);
		
		tree.setContentProvider(new DictMgnTableContentProvider());
		tree.setLabelProvider(new DictMgnTableLabelProvider());
		tabItem_1.setControl(tree.getTree());
		tree.setInput(input);
		
		
		TabItem tabItem = new TabItem(tabFolder_1, SWT.NONE);
		tabItem.setText("  输  出  ");
		tree_1 = new TreeViewer(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		TreeColumn codeColumn_1 = new TreeColumn(tree_1.getTree(), SWT.CENTER);
		codeColumn_1.setWidth(111);
		codeColumn_1.setText("代码");
		
		TreeColumn nameColumn_1 = new TreeColumn(tree_1.getTree(), SWT.CENTER);
		nameColumn_1.setWidth(184);
		nameColumn_1.setText("名称");
		
		TreeColumn typeColumn_1 = new TreeColumn(tree_1.getTree(), SWT.CENTER);
		typeColumn_1.setWidth(109);
		typeColumn_1.setText("类别");
		
		TreeColumn lengthColumn_1 = new TreeColumn(tree_1.getTree(), SWT.CENTER);
		lengthColumn_1.setWidth(87);
		lengthColumn_1.setText("类型");
		
		TreeColumn valueColumn_1 = new TreeColumn(tree_1.getTree(), SWT.CENTER);
		valueColumn_1.setWidth(87);
		valueColumn_1.setText("初始值");
		
		TreeColumn descripColumn_1 = new TreeColumn(tree_1.getTree(), SWT.CENTER);
		descripColumn_1.setWidth(238);
		descripColumn_1.setText("描述");
		
		tree_1.getTree().setHeaderVisible(true);
		tree_1.getTree().setLinesVisible(true);
		
		tree_1.setContentProvider(new DictMgnTableContentProvider());
		tree_1.setLabelProvider(new DictMgnTableLabelProvider());
		tabItem.setControl(tree_1.getTree());
		tree_1.setInput(output);
		
		buttonListener();
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		bizDatatransInfo = ((FlowMgnBizTransInfoEditorInput) this.getEditorInput()).getBizDatatransInfo() ;
		if(bizDatatransInfo != null){
			text.setText(bizDatatransInfo.getId());
			text_1.setText(bizDatatransInfo.getName());
			text_2.setText(bizDatatransInfo.getDesc());
		}
		input = bizDatatransInfo.getInput();
		output = bizDatatransInfo.getOutput();
	}

	private void buttonListener() {
		// 新增
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DictFindConfirmDialog createDict = new DictFindConfirmDialog(tree.getControl().getShell());
				if(createDict.open() == Dialog.OK){
					DictMgnVo dictMgnVo = createDict.getDictMgnVo();
					TabItem []  tabItem = tabFolder_1.getSelection();
					if(tabItem[0].getText().equals("  输  入  ")){
						input.add(dictMgnVo);
						tree.refresh();
					}else if(tabItem[0].getText().equals("  输  出  ")){
						output.add(dictMgnVo);
						tree_1.refresh();
					}
					//刷新
					mutiEdit.setDirty(true);
				}
			}
		});
		// 删除
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DictMgnVo dictMgnVoCheck = getSelectElement() ;
				if(dictMgnVoCheck == null){
					MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "错误提示", "请选择要对应的数据字典!");
					return ;
				}
				TabItem []  tabItem = tabFolder_1.getSelection();
				if(tabItem[0].getText().equals("  输  入  ")){
					input = deleteDictMgn(input, dictMgnVoCheck);
					tree.refresh();
				}else if(tabItem[0].getText().equals("  输  出  ")){
					output = deleteDictMgn(output, dictMgnVoCheck);
					tree_1.refresh();
				}
				//刷新
				mutiEdit.setDirty(true);
			}
			
			private List<DictMgnVo> deleteDictMgn(List<DictMgnVo> dictTemp , DictMgnVo dictMgnVoCheck) {
				for (DictMgnVo dictMgnVo : dictTemp) {
					if(dictMgnVo.getCode().equals(dictMgnVoCheck.getCode())){
						dictTemp.remove(dictMgnVo);
						break ;
					}
				}
				return dictTemp ;
			}
		});
	}
	
	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private DictMgnVo getSelectElement(){
		StructuredSelection select = null ;
		TabItem []  tabItem = tabFolder_1.getSelection();
		if(tabItem[0].getText().equals("  输  入  ")){
			select = (StructuredSelection)tree.getSelection();
		}else if(tabItem[0].getText().equals("  输  出  ")){
			select = (StructuredSelection)tree_1.getSelection();
		}
		DictMgnVo dictMgnVo = (DictMgnVo)select.getFirstElement();
		return dictMgnVo ;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public BizDatatransInfo getBizDatatransInfo() {
		return bizDatatransInfo;
	}
	
}

