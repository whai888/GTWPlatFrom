package com.sq.core.gtw.edit.extendtabproperties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.sq.core.gtw.db.vo.Delete;
import com.sq.core.gtw.db.vo.Insert;
import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.db.vo.Select;
import com.sq.core.gtw.db.vo.Update;
import com.sq.core.gtw.edit.FlowMgnBizMutiEdit;
import com.sq.core.gtw.edit.controller.BizEditorPart;
import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.model.impl.BizModel;
import com.sq.core.gtw.edit.model.impl.BizModelPropertiesSql;
import com.sq.core.gtw.edit.vo.BizServiceInfo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.pub.vo.DataMgnInfoVo;
import com.thoughtworks.xstream.XStream;

/**
 *@作者  whai 
 *@创建日期 2013-8-14
 *@版本 V1.0
 *@文件名 FlowMgnBizSqlPropertiesTabSection.java
 */
public class FlowMgnBizSqlPropertiesTabSection extends AbstractPropertySection {
	
	private XStream xstream = new XStream();
	private List<DataMgnInfoVo> dataMgnList = new ArrayList<DataMgnInfoVo>();
	private static String modelMain = ReadProperties.getSystemKey("MODEL_MAIN");
	private static String modelPath = ReadProperties.getSystemKey("MODEL_PATH");
	private CCombo combo_1 ;	//表名
	private CCombo combo ;		//SQL
	private CCombo combo_2 ;	//SQL服务
	private CCombo combo_3 ;	//数据源
	private CCombo combo_4 ;	//事物类型
	private BizModel bizModel ;
	private BizEditorPart bizEditorPart ;
	private FlowMgnBizMutiEdit flowMgnBizMutiEdit ;
	private BizModelPropertiesSql bizModelPropertiesSql ;
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label label_4 = getWidgetFactory().createLabel(composite, "事物类型：");
		label_4.setAlignment(SWT.RIGHT);
		GridData gd_label_4 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_4.heightHint = 20;
		gd_label_4.widthHint = 100;
		label_4.setLayoutData(gd_label_4);
		
		combo_4 = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_3 = getWidgetFactory().createLabel(composite, "数据源：");
		label_3.setAlignment(SWT.RIGHT);
		GridData gd_label_3 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_3.heightHint = 20;
		gd_label_3.widthHint = 100;
		label_3.setLayoutData(gd_label_3);
		
		combo_3 = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label = getWidgetFactory().createLabel(composite, "表名：");
		label.setAlignment(SWT.RIGHT);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 100;
		label.setLayoutData(gd_label);
		
		combo_1 = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = getWidgetFactory().createLabel(composite, "SQL：");
		label_1.setAlignment(SWT.RIGHT);
		GridData gd_label_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.heightHint = 20;
		gd_label_1.widthHint = 100;
		label_1.setLayoutData(gd_label_1);
		
		combo = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = getWidgetFactory().createLabel(composite, "执行SQL服务：");
		label_2.setAlignment(SWT.RIGHT);
		GridData gd_label_2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_2.heightHint = 20;
		gd_label_2.widthHint = 100;
		label_2.setLayoutData(gd_label_1);
		
		combo_2 = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
	}
	//组件URL
	private void createListener() {
		// TODO Auto-generated method stub
		//表名
		combo_1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				//加载SQL语句
				if(bizModelPropertiesSql != null){
					bizModelPropertiesSql.setTableName(combo_1.getText());
					setDirty(true);
					//给SQL赋值
					String[] sqlStr = getTableNameToSql(combo_1.getText());
					combo.setItems(sqlStr);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//SQL语句
		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesSql != null){
					bizModelPropertiesSql.setSql(combo.getText());
					setDirty(true);
				}
			}
		});
		//执行SQL服务
		combo_2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesSql != null){
					bizModelPropertiesSql.setSqlService(combo_2.getText());
					setDirty(true);
				}
			}
		});
		//数据源
		combo_3.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesSql != null){
					bizModelPropertiesSql.setDataSource(combo_3.getText());
					setDirty(true);
				}
			}
		});
		//事物类型
		combo_4.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesSql != null){
					bizModelPropertiesSql.setTransType(combo_4.getText());
					setDirty(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
		initData();
		if(bizModelPropertiesSql != null){
			//加载表名
			combo_1.setText(bizModelPropertiesSql.getTableName());
			//加载SQL语句
			if(!combo_1.getText().isEmpty()){
				String[] sqlStr = getTableNameToSql(combo_1.getText());
				combo.setItems(sqlStr);
				combo.setText(bizModelPropertiesSql.getSql());
			}
			//加载sql
			combo_2.setText(bizModelPropertiesSql.getSqlService());
			//加载数据源
			combo_3.setText(bizModelPropertiesSql.getDataSource());
			//加载事物类型
			combo_4.setText(bizModelPropertiesSql.getTransType());
		}
		createListener();
	}

	private void initData() {
		// TODO Auto-generated method stub
		//表名
		initTableNameValue();
		String [] tabNameStr = new String[dataMgnList.size()];
		for (int i = 0; i < dataMgnList.size(); i++) {
			DataMgnInfoVo dataMgnInfoVo = dataMgnList.get(i);
			tabNameStr[i] = dataMgnInfoVo.getTableName() ;
		}
		combo_1.setItems(tabNameStr);
		//SQL服务
		List<BizServiceInfo> serviceInfoList = flowMgnBizMutiEdit.getBizTransInfo().getServices();
		String [] serviceStr = new String[serviceInfoList.size()];
		for (int i = 0; i < serviceInfoList.size(); i++) {
			BizServiceInfo bizServiceInfo = serviceInfoList.get(i);
			serviceStr[i]=bizServiceInfo.getName();
		}
		combo_2.setItems(serviceStr);
		
		//数据源
		combo_3.setItems(serviceStr);
		
		//事物类型
		combo_4.setItems(ConstantUtil.TRANSACTION_TYPE);

	}
	
	/**
	 * 初始化业务流程列表
	 */
	@SuppressWarnings("unchecked")
	private void initTableNameValue(){
		xstream.alias("dao", List.class);
		xstream.processAnnotations(DataMgnInfoVo.class);
		dataMgnList = (List)XMLFileOp.readToFile(modelMain, xstream);
	}
	
	/**
	 * 初始化业务流程列表
	 */
	private String [] getTableNameToSql(String tableName){
		String path = modelPath + File.separator + tableName + ".xml";
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(MyBatisMain.class);
		xstream.alias("mapper", MyBatisMain.class);
		MyBatisMain myBatisMain = (MyBatisMain) XMLFileOp.readToFile(path.toString(), xstream);
		String [] sqlStr = new String[myBatisMain.getListCount()];
		//select
		int count = 0 ;
		for (int i = 0; i < myBatisMain.getSelect().size(); i++) {
			Select select = myBatisMain.getSelect().get(i);
			sqlStr[count] = select.getId();
			count += 1 ;
		}
		//update
		for (int i = 0; i < myBatisMain.getUpdate().size(); i++) {
			Update update = myBatisMain.getUpdate().get(i);
			sqlStr[count] = update.getId();
			count += 1 ;
		}
		//delete
		for (int i = 0; i < myBatisMain.getDelete().size(); i++) {
			Delete delete = myBatisMain.getDelete().get(i);
			sqlStr[count] = delete.getId();
			count += 1 ;
		}
		//insert
		for (int i = 0; i < myBatisMain.getInsert().size(); i++) {
			Insert insert = myBatisMain.getInsert().get(i);
			sqlStr[count] = insert.getId();
			count += 1 ;
		}
		return sqlStr ;
	}
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.setInput(part, selection);
		if(selection instanceof IStructuredSelection){
			Object input = ((IStructuredSelection)selection).getFirstElement();
			if(input instanceof BizEditorPart){
				bizEditorPart = (BizEditorPart)input ;
				bizModel = (BizModel)bizEditorPart.getModel() ;
				flowMgnBizMutiEdit = (FlowMgnBizMutiEdit)bizEditorPart.getEditorPart();
	        	if(bizModel instanceof BizModel ){
	        		IFlowModelProperties ibizModelProperties = ((BizModel)bizModel).getiFlowModelProperties() ;
	        		if(ibizModelProperties instanceof BizModelPropertiesSql)
	        			bizModelPropertiesSql = (BizModelPropertiesSql) bizModel.getiFlowModelProperties();
	        	}
			}
		}
	}
	
	private void setDirty(boolean flag){
		if(bizEditorPart != null){
			bizModel.setiFlowModelProperties(bizModelPropertiesSql);
			flowMgnBizMutiEdit.setDirty(flag);
		}
	}
}
