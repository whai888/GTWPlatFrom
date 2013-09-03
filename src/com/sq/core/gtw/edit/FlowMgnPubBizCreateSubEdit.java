package com.sq.core.gtw.edit;

import gtwplatfrom.Activator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.dialog.BizDefinitionDialog;
import com.sq.core.gtw.edit.editinput.FlowMgnPubBizCreateSubEditorInput;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizCreateSubBottomContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizCreateSubBottomLableProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizCreateSubLeftContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizCreateSubLeftLableProvider;
import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.model.impl.BizModelPropertiesSystem;
import com.sq.core.gtw.edit.service.model.IBizModelService;
import com.sq.core.gtw.edit.vo.FlowBizPaletteInfo;
import com.sq.core.gtw.edit.vo.imp.system.BizDefinitionVo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

/**
 *@����  whai 
 *@�������� 2013-8-18
 *@�汾 V1.0
 *@�ļ��� FlowMgnPubBizCreateSubEdit.java
 */
public class FlowMgnPubBizCreateSubEdit extends EditorPart {


	public static final String ID = "com.sq.core.gtw.edit.FlowMgnPubBizCreateSubEdit"; //$NON-NLS-1$
	private XStream xstream = new XStream();
	private String bizModelPath = ReadProperties.getSystemKey("PUB_BIZ_MODEL");
	private boolean dirty = false ;
	private Text text;			//Ԫ������
	private ModifyListener textModifyListener ;
	private Text text_1;		//ʵ������
	private ModifyListener text1ModifyListener ;
	private Text text_2;		//ͼ������
	private ModifyListener text2ModifyListener ;
	private Text text_3;		//����
	private ModifyListener text3ModifyListener ;
	private Text text_4;		//��ʾԪ������
	private ModifyListener text4ModifyListener ;
	private Text text_9;
	private ModifyListener text9ModifyListener ;
	private Combo combo ;		//�ؼ�����
	private Table table;
	private TableViewer tableViewer ;
	private TableViewer tableViewer_1 ;
	private List<FlowBizPaletteInfo> bizPaletteInfoList ;
	private Map<String , List<FlowBizPaletteInfo>> flowPaletteMap = new LinkedHashMap<String, List<FlowBizPaletteInfo>>();
	private List<BizDefinitionVo> bizDefinitionVoList ;
	private Action addBiz ;
	private Action deleteBiz ;
	private Table table_1;
	private Text text_5;
	private Text text_6;
	private Button button ;		//����
	private Button button_1 ;	//�޸�
	private Button button_2 ;	//ɾ��
	private String[] comboType = ConstantUtil.BIZ_DEFINITION_COMBO_TYPE;
	private String bizInputStr ;
	private Text text_7;

	public FlowMgnPubBizCreateSubEdit() {
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.widthHint = 328;
		table.setLayoutData(gd_table);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tableColumn.setWidth(170);
		tableColumn.setText("Ԫ������");

		tableViewer.setLabelProvider(new FlowMgnBizCreateSubLeftLableProvider());
		tableViewer.setContentProvider(new FlowMgnBizCreateSubLeftContentProvider());
		tableViewer.setInput(bizPaletteInfoList);
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1,1);
		gd_tabFolder.heightHint = 468;
		gd_tabFolder.widthHint = 576;
		tabFolder.setLayoutData(gd_tabFolder);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("������Ϣ");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		composite.setLayout(new GridLayout(1, false));

		Group group = new Group(composite, SWT.NONE);
		group.setText("Ԫ������");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(group, SWT.NONE);
		label.setText("Ԫ�����ƣ�");
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,1, 1));

		text = new Text(group, SWT.BORDER | SWT.NONE);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1, 1));
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("��ʾԪ�����ƣ�");
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,1, 1));
		label_1.setText("ʵ��������");
		label_1.setToolTipText("ʵ�ֵ���������ʵ��IFlowModelProperties�ӿ�");

		text_1 = new Text(group, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("ͼ�����ƣ�");

		text_2 = new Text(group, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("������");

		text_3 = new Text(group, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_text_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_3.heightHint = 156;
		text_3.setLayoutData(gd_text_3);

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("��������");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("��ǩ���ƣ�");
		
		text_5 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_8.setText("��ǩ���ȣ�");
		
		text_7 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("�ؼ����ͣ�");
		
		combo = new Combo(composite_1, SWT.NONE | SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.setItems(comboType);
		
		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("�����ֶ�����");
		
		text_6 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		button = new Button(composite_1, SWT.NONE);
		button.setText("  ��    ��    ");
		
		button_1 = new Button(composite_1, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		button_1.setText("  ��    ��  ");
		
		button_2 = new Button(composite_1, SWT.NONE);
		button_2.setText("  ɾ    ��  ");
		new Label(composite_1, SWT.NONE);
		
		tableViewer_1 = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		
		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("��ǩ����");
		
		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("�ؼ�����");
		
		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("�����ֶ���");

		tableViewer_1.setLabelProvider(new FlowMgnBizCreateSubBottomLableProvider());
		tableViewer_1.setContentProvider(new FlowMgnBizCreateSubBottomContentProvider());
		
		listener();
		hookClickAction();
		createActions();
		hookContextMenu();
		
	}

	private void hookClickAction() {
		// TODO Auto-generated method stub
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				StructuredSelection select = (StructuredSelection)tableViewer.getSelection();
				FlowBizPaletteInfo flowBizPaletteInfo = (FlowBizPaletteInfo)select.getFirstElement();
				if(flowBizPaletteInfo !=null ){
					removeModifyListener();
					//Ԫ������
					text.setText(flowBizPaletteInfo.getContentName());
					//ʵ������
					if(flowBizPaletteInfo.getiFlowModelProperties() != null )
						text_1.setText(flowBizPaletteInfo.getiFlowModelProperties().getClass().getName());
					if(flowBizPaletteInfo.getiBizModelService() != null )
						text_9.setText(flowBizPaletteInfo.getiBizModelService().getClass().getName());
					//ͼ������
					text_2.setText(flowBizPaletteInfo.getImage());
					//������
					text_3.setText(flowBizPaletteInfo.getDescript());
					//��ʾԪ������
					text_4.setText(flowBizPaletteInfo.getToolTipTextname());
					
					bizDefinitionVoList = flowBizPaletteInfo.getBizDefinitionVoList();
					tableViewer_1.setInput(bizDefinitionVoList);
					tableViewer_1.refresh();
					addModifyListener();
				}
			}
			
		});
		
		tableViewer_1.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				BizDefinitionVo bizDefinitionVo = getBottomTableSelectElement();
				if(bizDefinitionVo !=null ){
					//��ǩ����
					text_5.setText(bizDefinitionVo.getLabel());
					//��ǩ����
					text_7.setText(bizDefinitionVo.getLabelLenth());
					//�ؼ�����
					combo.setText(bizDefinitionVo.getType());
					//�����ֶ���
					text_6.setText(bizDefinitionVo.getClassField());
				}
			}
			
		});
		
		tableViewer_1.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				BizDefinitionVo bizDefinitionVo = getBottomTableSelectElement();
				BizDefinitionDialog bizDefinitionDialog = new BizDefinitionDialog(tableViewer_1.getControl().getShell() , bizDefinitionVo );
				if(bizDefinitionDialog.open() == TitleAreaDialog.OK){
					BizDefinitionVo bizDefinitionVoTemp = bizDefinitionDialog.getBizDefinitionVo();
					try {
						BeanUtils.copyProperties(bizDefinitionVo, bizDefinitionVoTemp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableViewer_1.setInput(bizDefinitionVoList);
					tableViewer_1.refresh();
					isflushDirty(true);
				}
			}
		});
	}
	
	/**
	 * �����Ĳ˵�
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.add(addBiz);
		menuMgr.add(deleteBiz);
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
	}
	
	private void createActions() {
		//�����Զ���ҵ���߼�
		addBiz = new Action() {
			public void run() {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = new FlowBizPaletteInfo();
				flowBizPaletteInfo.setContentName("ҵ���������");
				flowBizPaletteInfo.setiFlowModelProperties(new BizModelPropertiesSystem());
				bizPaletteInfoList.add(flowBizPaletteInfo);
				tableViewer.setInput(bizPaletteInfoList);
				tableViewer.refresh();
			}
		};
		addBiz.setText("����");
		addBiz.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		//ɾ���Զ���ҵ���߼�
		deleteBiz = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				bizPaletteInfoList.remove(flowBizPaletteInfo);
				tableViewer.setInput(bizPaletteInfoList);
				tableViewer.refresh();
				isflushDirty(true);
			}
			
		};
		deleteBiz.setText("ɾ��");
		deleteBiz.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
		
		//����
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowBizPaletteInfo flowBizPaletteInfo = getLeftTableSelectElement();
				BizDefinitionDialog bizDefinitionDialog = new BizDefinitionDialog(tableViewer_1.getControl().getShell() , null );
				if(bizDefinitionDialog.open() == TitleAreaDialog.OK){
					BizDefinitionVo bizDefinitionVo = bizDefinitionDialog.getBizDefinitionVo();
					bizDefinitionVoList.add(bizDefinitionVo);
					tableViewer_1.setInput(bizDefinitionVoList);
					flowBizPaletteInfo.setBizDefinitionVoList(bizDefinitionVoList);
					tableViewer_1.refresh();
					isflushDirty(true);
				}
			}
		});
		//�޸�
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BizDefinitionVo bizDefinitionVo = getBottomTableSelectElement();
				BizDefinitionDialog bizDefinitionDialog = new BizDefinitionDialog(tableViewer_1.getControl().getShell() , bizDefinitionVo );
				if(bizDefinitionDialog.open() == TitleAreaDialog.OK){
					BizDefinitionVo bizDefinitionVoTemp = bizDefinitionDialog.getBizDefinitionVo();
					try {
						BeanUtils.copyProperties(bizDefinitionVo, bizDefinitionVoTemp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableViewer_1.setInput(bizDefinitionVoList);
					tableViewer_1.refresh();
					isflushDirty(true);
				}
			}
		});
		//ɾ��
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BizDefinitionVo bizDefinitionVo = getBottomTableSelectElement();
				bizDefinitionVoList.remove(bizDefinitionVo);
				tableViewer_1.setInput(bizDefinitionVoList);
				tableViewer_1.refresh();
				isflushDirty(true);
			}
		});
	}
	
	public void removeModifyListener(){
		//Ԫ������
		text.removeModifyListener(textModifyListener);
		//ʵ������
		text_1.removeModifyListener(text1ModifyListener);
		//ͼ������
		text_2.removeModifyListener(text2ModifyListener);
		//����
		text_3.removeModifyListener(text3ModifyListener);
		//��ʾԪ������
		text_4.removeModifyListener(text4ModifyListener);
		//ģ�͵�ʵ����
		text_9.removeModifyListener(text9ModifyListener);
	}
	
	public void addModifyListener(){
		//Ԫ������
		text.addModifyListener(textModifyListener);
		//ʵ������
		text_1.addModifyListener(text1ModifyListener);
		//ͼ������
		text_2.addModifyListener(text2ModifyListener);
		//����
		text_3.addModifyListener(text3ModifyListener);
		//��ʾԪ������
		text_4.addModifyListener(text4ModifyListener);
		//ģ�͵�ʵ����
		text_9.addModifyListener(text9ModifyListener);
	}
	
	public void listener(){
		textModifyListener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				flowBizPaletteInfo.setContentName(text.getText());
				isflushDirty(true);
			}
		};
		text1ModifyListener = new ModifyListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				try {
					Class clss = Class.forName(text_1.getText());
					flowBizPaletteInfo.setiFlowModelProperties((IFlowModelProperties) clss.newInstance());
					isflushDirty(true);
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ", e1.getMessage());
				}
			}
		};
		text2ModifyListener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				flowBizPaletteInfo.setImage(text_2.getText());
				isflushDirty(true);
			}
		};
		text3ModifyListener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				flowBizPaletteInfo.setDescript(text_3.getText());
				isflushDirty(true);
			}
		};
		text4ModifyListener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				flowBizPaletteInfo.setToolTipTextname(text_4.getText());
				isflushDirty(true);
			}
		};
		text9ModifyListener = new ModifyListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FlowBizPaletteInfo flowBizPaletteInfo  = getLeftTableSelectElement();
				try{
					Class clss = Class.forName(text_9.getText());
					flowBizPaletteInfo.setiBizModelService((IBizModelService) clss.newInstance());
					isflushDirty(true);
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ", e1.getMessage());
				}
			}
		};
	}
	/**
	 * �������ǰѡ�еĽڵ�
	 * @return
	 */
	private FlowBizPaletteInfo getLeftTableSelectElement(){
		StructuredSelection select = (StructuredSelection)tableViewer.getSelection();
		FlowBizPaletteInfo flowBizPaletteInfo = (FlowBizPaletteInfo)select.getFirstElement();
		return flowBizPaletteInfo ;
	}
	
	/**
	 * �������ǰѡ�еĽڵ�
	 * @return
	 */
	private BizDefinitionVo getBottomTableSelectElement(){
		StructuredSelection select = (StructuredSelection)tableViewer_1.getSelection();
		BizDefinitionVo bizDefinitionVo = (BizDefinitionVo)select.getFirstElement();
		return bizDefinitionVo ;
	}
	
	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
		flowPaletteMap.put(bizInputStr, bizPaletteInfoList);
		XMLFileOp.saveToFile(bizModelPath, flowPaletteMap, null);
		tableViewer.refresh();
		isflushDirty(false);
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
		
	}

	/**
	 * ��ʼ������
	 */
	@SuppressWarnings("unchecked")
	public void initXmlBizData(){
		bizInputStr = ((FlowMgnPubBizCreateSubEditorInput)this.getEditorInput()).getName();
		flowPaletteMap = (Map<String, List<FlowBizPaletteInfo>>)XMLFileOp.readToFile(bizModelPath, xstream);
		if(flowPaletteMap == null)
			flowPaletteMap =  new LinkedHashMap<String, List<FlowBizPaletteInfo>>();
		
		bizPaletteInfoList = flowPaletteMap.get(bizInputStr);
		if(bizPaletteInfoList == null )
			bizPaletteInfoList = new ArrayList<FlowBizPaletteInfo>();
		
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		setSite(site);
		setInput(input);
		setPartName(input.getName());
		initXmlBizData();
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
	 * ˢ�±���ı�ǩ
	 * @param flag
	 */
	public void isflushDirty(boolean flag){
		dirty = flag ;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}
}
