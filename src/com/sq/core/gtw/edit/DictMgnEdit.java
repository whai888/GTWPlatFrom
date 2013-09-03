package com.sq.core.gtw.edit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.dialog.DictMgnCreateDictDialog;
import com.sq.core.gtw.edit.labelcontent.DictMgnTableContentProvider;
import com.sq.core.gtw.edit.labelcontent.DictMgnTableLabelProvider;
import com.sq.core.gtw.edit.vo.DictMgnVo;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

/**
 * @作者 whai
 * @创建日期 2013-7-18
 * @版本 V1.0
 * @文件名 DictMgnEdit.java
 */
public class DictMgnEdit extends EditorPart {

	public static final String ID = "com.sq.core.gtw.edit.DictMgnEdit"; //$NON-NLS-1$
	private boolean dirty = false ;
	private String dictPath = ReadProperties.getSystemKey("PUB_DICT");
	private XStream xstream = new XStream();
	private Text text;
	private List<DictMgnVo> dictList = new ArrayList<DictMgnVo>();
	private TreeViewer tree ;
	//搜索数据
	private Button button ;
	//新增
	private Button button_1 ;
	//删除
	private Button button_2 ;
	//更新
	private Button button_3;
	
	public DictMgnEdit() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(5, false));
		Group group = new Group(container, SWT.FULL_SELECTION);
		group.setText("查找数据");
		group.setLayout(new GridLayout(4, false));
		group.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, true, false, 5, 1));
		
		Label lblId = new Label(group, SWT.NONE);
		lblId.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, true, false, 1, 1));
		lblId.setText("查找：");
		
		text = new Text(group, SWT.BORDER);
		GridData gd_text = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 176;
		text.setLayoutData(gd_text);
		text.setText("");
		
		button = new Button(group, SWT.NONE);
		button.setText("搜索数据");
		button.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, true, false, 2, 1));

		
		button_1 = new Button(container, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1));
		button_1.setText("  新  增  ");
		
		button_2 = new Button(container, SWT.NONE);
		button_2.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1));
		button_2.setText("  删  除  ");
		
		button_3 = new Button(container, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1));
		button_3.setText("  更  新  ");
		
		tree = new TreeViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		Tree tree_1 = tree.getTree();
		tree_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
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
		initializeDict();
		tree.setInput(dictList);
		
		buttonListener();
		
	}

	private void buttonListener() {
		// 新增
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DictMgnVo dictMgnVoCheck = getSelectElement() ;
				DictMgnCreateDictDialog createDict = new DictMgnCreateDictDialog(tree.getControl().getShell());
				if(createDict.open() == TitleAreaDialog.OK){
					DictMgnVo dictMgnVo = createDict.getDictMgnVo();
					//新增的代码不能与原代码相同
					for (DictMgnVo temp : dictList) {
						if(temp.getCode().equals(dictMgnVo.getCode())){
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息","新增的代码不能与现有代码相同！");
							return ;
						}
					}
					//如果当前选择的集合类型，则新增的元数据作为集合的子节点
					if(dictMgnVoCheck != null && dictMgnVoCheck.getType().equals("ListType")){
						tree.add(dictMgnVoCheck , dictMgnVo);
						dictMgnVoCheck.add(dictMgnVo);
					}else{
						dictList.add(dictMgnVo);
					}
					tree.setInput(dictList);
					tree.refresh();
					//刷新
					isflushDirty(true) ;
				}
			}
		});
		
		//删除
		button_2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				DictMgnVo dictMgnVoCheck = getSelectElement() ;
				dictList = deleteDictMgn(dictList , dictMgnVoCheck);
				tree.setInput(dictList);
				tree.refresh();
				//刷新
				isflushDirty(true) ;
			}

			private List<DictMgnVo> deleteDictMgn(List<DictMgnVo> dictTemp , DictMgnVo dictMgnVoCheck) {
				for (DictMgnVo dictMgnVo : dictTemp) {
					if(dictMgnVo.getCode().equals(dictMgnVoCheck.getCode())){
						dictTemp.remove(dictMgnVo);
						break ;
					}
					if(dictMgnVo.getChildren().size() > 0) 
						deleteDictMgn(dictMgnVo.getChildren(), dictMgnVoCheck);
				}
				return dictTemp ;
			}
			
		});
		//更新
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DictMgnVo dictMgnVoCheck = getSelectElement() ;
				DictMgnCreateDictDialog createDict = new DictMgnCreateDictDialog(tree.getControl().getShell());
				createDict.setDictMgnVo(dictMgnVoCheck);
				if(createDict.open() == TitleAreaDialog.OK){
					DictMgnVo dictMgnVo = createDict.getDictMgnVo();
					dictMgnVoCheck = dictMgnVo ;
					tree.refresh(dictMgnVoCheck,false);
					tree.setInput(dictList);
					//刷新
					isflushDirty(true) ;
				}
			}
		});
		//双击树节点
		tree.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				DictMgnVo dictMgnVoCheck = getSelectElement() ;
				DictMgnCreateDictDialog createDict = new DictMgnCreateDictDialog(tree.getControl().getShell());
				createDict.setDictMgnVo(dictMgnVoCheck);
				if(createDict.open() == TitleAreaDialog.OK){
					DictMgnVo dictMgnVo = createDict.getDictMgnVo();
					try {
						BeanUtils.copyProperties(dictMgnVoCheck, dictMgnVo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tree.setInput(dictList);
					tree.refresh();
					//刷新
					isflushDirty(true) ;
				}
			}
		});
		//查找
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
	}

	/**
	 * 初始化数据字典的值
	 */
	@SuppressWarnings("unchecked")
	private void initializeDict() {
		// TODO Auto-generated method stub
		xstream.alias("dict", DictMgnVo.class);
		dictList = (List<DictMgnVo>) XMLFileOp.readToFile(dictPath, xstream);
	}
	
	
	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private DictMgnVo getSelectElement(){
		StructuredSelection select = (StructuredSelection)tree.getSelection();
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
		XMLFileOp.saveToFile(dictPath, dictList, null);
		//更新状态
		isflushDirty(false);
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
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	/**
	 * 刷新保存的标签
	 * @param flag
	 */
	public void isflushDirty(boolean flag){
		dirty = flag ;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}
}
