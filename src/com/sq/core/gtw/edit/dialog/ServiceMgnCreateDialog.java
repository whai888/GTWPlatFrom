package com.sq.core.gtw.edit.dialog;

import gtwplatfrom.Activator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceDialogLeftContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceDialogLeftLabelProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceDialogRightContentProvider;
import com.sq.core.gtw.edit.labelcontent.FlowMgnBizServiceDialogRightLabelProvider;
import com.sq.core.gtw.edit.vo.BizServiceInfo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;

/**
 * @作者 whai
 * @创建日期 2013-7-20
 * @版本 V1.0
 * @文件名 ServicemMgnCreateDialog.java
 */
public class ServiceMgnCreateDialog extends Dialog {
	//表格个的列名称
	public static final String[] COLUMN_NAME = { "名称" ,"值"};
	private BizServiceInfo serviceInfo ;
	private Text text;
	private Text text_1;
	private Text text_2;
	private List<BizServiceInfo> serviceList = new ArrayList<BizServiceInfo>();
	private List<BizServiceInfo> subServiceList = new ArrayList<BizServiceInfo>();
	private TableViewer tableViewer_1 ;
	private TableViewer tableViewer ;
	private Table table_1 ;
	private Table table ;
	//确定
	private Button button_1 ;
	//取消
	private Button button_2 ;
	private Action addServiceType ;
	private Action deleteAction ;
	private Group group ;
	
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ServiceMgnCreateDialog(Shell parentShell , BizServiceInfo serviceInfo ) {
		super(parentShell);
		this.serviceInfo = serviceInfo ;
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gl_container = new GridLayout(5, false);
		gl_container.horizontalSpacing = 15;
		gl_container.marginHeight = 10;
		gl_container.verticalSpacing = 10;
		container.setLayout(gl_container);
		group = new Group(container, SWT.FULL_SELECTION);
		GridData gd_group = new GridData(SWT.LEFT, SWT.CENTER, true, false, 5, 2);
		gd_group.widthHint = 754;
		group.setLayoutData(gd_group);
		group.setText("");
		group.setLayout(new GridLayout(4, false));
		
		Label lblId = new Label(group, SWT.NONE);
		lblId.setText("名称：");
		
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
		gd_text_1.widthHint = 282;
		text_2.setLayoutData(gd_text_1);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("服务类型：");
		
		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_text_2= new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 200 ;
		text_1.setLayoutData(gd_text_2);
		
		initData();
		
		tableViewer_1 = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		TableColumn codeColumn = new TableColumn(table_1, SWT.CENTER);
		codeColumn.setWidth(244);
		codeColumn.setText("通讯类型");
		
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		tableViewer_1.setLabelProvider(new FlowMgnBizServiceDialogLeftLabelProvider());
		tableViewer_1.setContentProvider(new FlowMgnBizServiceDialogLeftContentProvider());
		tableViewer_1.setInput(serviceList);

		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tableViewer.setColumnProperties(COLUMN_NAME) ;
		
		TableColumn tblclmnKey = new TableColumn(table, SWT.CENTER);
		tblclmnKey.setWidth(200);
		tblclmnKey.setText("名称");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(257);
		tableColumn_1.setText("值");
		
		tableViewer.setContentProvider(new FlowMgnBizServiceDialogRightContentProvider());
		tableViewer.setLabelProvider(new FlowMgnBizServiceDialogRightLabelProvider());
		
		//第二列可编辑的单元格
		final CellEditor[] editors = new CellEditor[COLUMN_NAME.length]; 
		editors[0] = null;
		editors[1] = new TextCellEditor(tableViewer.getTable()); 
		tableViewer.setCellEditors(editors);
		tableViewer.setCellModifier(new ICellModifier() {
			
			/**
			 * 当修改后，更新数据
			 */
			@Override
			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				if (element instanceof Item) 
				      element = ((Item) element).getData();
				BizServiceInfo serviceInfoVo = (BizServiceInfo)element ;
				if(property.equals(COLUMN_NAME[1]))
					serviceInfoVo.setDesc((String)value) ;
				tableViewer.refresh();
			}
			
			/**
			 * 当处于编辑状态是，所显示的值
			 */
			@Override
			public Object getValue(Object element, String property) {
				// TODO Auto-generated method stub
				if (element instanceof Item) 
				      element = ((Item) element).getData();
				BizServiceInfo serviceInfoVo = (BizServiceInfo)element ;
				if(property.equals(COLUMN_NAME[1]))
					return serviceInfoVo.getDesc() ;
				return null ;
			}
			
			@Override
			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				if(property.equals(COLUMN_NAME[0]))
					return false;
				else
					return true ;
			}
		});
		
		hookClickAction();
		createActions();
		hookContextMenu();
		
		return container;
	}

	private void initData() {
		// TODO Auto-generated method stub
		if(serviceInfo !=null ){
			group.setText(serviceInfo.getServiceType());
			text.setText(serviceInfo.getName());
			text_1.setText(serviceInfo.getServiceType());
			text_2.setText(serviceInfo.getDesc());
			serviceList = serviceInfo.getSubInfo();
		}
		
	}

	private void hookClickAction() {
		// TODO Auto-generated method stub
		tableViewer_1.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				StructuredSelection select = (StructuredSelection)tableViewer_1.getSelection();
				BizServiceInfo serviceInfoVo = (BizServiceInfo)select.getFirstElement();
				if(serviceInfoVo !=null && serviceInfoVo.getSubInfo().size() > 0 ){
					subServiceList = serviceInfoVo.getSubInfo();
				}else{
					subServiceList = getHostInfo();
				}
				tableViewer.setInput(subServiceList);
				if(serviceInfoVo!=null)
					serviceInfoVo.setSubInfo((List)tableViewer.getInput());
				tableViewer.refresh();
			}
			
		});
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		this.getShell().setText("服务定义");
		button_1 = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button_1.setText("  确  定  ");
		button_2 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_2.setText("  取  消  ");
	}

	/**
	 * 上下文菜单
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		if(serviceInfo.getServiceType().equals(ConstantUtil.SERVICE_STR[0])){
			menuMgr.add(addServiceType);
			menuMgr.add(deleteAction);
		}
		Menu menu = menuMgr.createContextMenu(tableViewer_1.getControl());
		tableViewer_1.getControl().setMenu(menu);
	}
	
	/**
	 * 获得树当前选中的节点
	 * @return
	 */
	private BizServiceInfo getSelectElement(){
		StructuredSelection select = (StructuredSelection)tableViewer_1.getSelection();
		BizServiceInfo serviceInfoVo = (BizServiceInfo)select.getFirstElement();
		return serviceInfoVo ;
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(786, 468);
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		serviceInfo.setDesc(text_2.getText());
		super.okPressed();
	}

	public List<BizServiceInfo> getServiceList() {
		return serviceList;
	}

	
	public BizServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	private void createActions() {
		//新增服务类型
		addServiceType = new Action() {
			public void run() {
				// TODO Auto-generated method stub
				BizServiceInfo serviceInfoVo = new BizServiceInfo();
				serviceInfoVo.setName("主机信息");
				serviceList.add(serviceInfoVo);
				tableViewer_1.setInput(serviceList);
				tableViewer_1.refresh();
			}
		};
		addServiceType.setText("新增主机信息");
		addServiceType.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		//删除
		deleteAction = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				BizServiceInfo serviceInfoVo = getSelectElement();
				serviceList.remove(serviceInfoVo);
				tableViewer_1.setInput(serviceList);
				tableViewer_1.refresh();
			}
			
		};
		deleteAction.setText("删除");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
	}
	
	public List<BizServiceInfo> getHostInfo(){
		List<BizServiceInfo> infoList = new ArrayList<BizServiceInfo>();
		infoList.add(new BizServiceInfo("主机IP" , " " , ""));
		infoList.add(new BizServiceInfo("主机端口" , " " , ""));
		infoList.add(new BizServiceInfo("用户名" , " " , ""));
		infoList.add(new BizServiceInfo("密码" , " " , ""));
		return infoList;
	}
}
