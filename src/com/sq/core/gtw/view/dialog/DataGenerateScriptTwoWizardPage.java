package com.sq.core.gtw.view.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.sq.core.gtw.db.DaoConnFactory;
import com.sq.core.gtw.db.IMyDBConn;
import com.sq.core.gtw.db.vo.DataTableVo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.view.dialog.contentprovider.DataGenerateScriptTwoContentProvider;
import com.sq.core.gtw.view.dialog.contentprovider.DataGenerateScriptTwoLabelProvider;

/**
 * @作者 whai
 * @创建日期 2013-7-24
 * @版本 V1.0
 * @文件名 DataGenerateScriptTwoWizardPage.java
 */
public class DataGenerateScriptTwoWizardPage extends WizardPage {
	private CheckboxTableViewer checkboxTableViewer;
	private Table checkTable ;
	@SuppressWarnings("unused")
	private DataGenerateScriptOneWizardPage text;
	private List<DataTableVo> dataTableList ;
	private String[] result ;
	private Button button ;
	private Button button_1 ;
	private Button button_2 ;
	private Button button_3 ;

	/** 
	 * Create the wizard.
	 */
	public DataGenerateScriptTwoWizardPage(DataGenerateScriptOneWizardPage text) {
		super("wizardPage");
		setTitle("表结构");
		this.text = text ;
		setDescription("请选择对应的数据库表结构");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		container.setLayout(null);
		
		button = new Button(container, SWT.CHECK);
		button.setBounds(266, 48, 254, 16);
		button.setSelection(true);
		button.setText(" 查 询 ");
		
		button_1 = new Button(container, SWT.CHECK);
		button_1.setBounds(266, 93, 254, 16);
		button_1.setText(" 更 新 ");
		button_1.setSelection(true);
		
		button_2 = new Button(container, SWT.CHECK);
		button_2.setBounds(267, 133, 54, 16);
		button_2.setText(" 删 除 ");
		button_2.setSelection(true);
		
		button_3 = new Button(container, SWT.CHECK);
		button_3.setText(" 插 入 ");
		button_3.setSelection(true);
		button_3.setBounds(266, 175, 54, 16);
		
		checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
		checkTable = checkboxTableViewer.getTable();
		checkTable.setLinesVisible(true);
		checkTable.setHeaderVisible(true);
		checkTable.setBounds(21, 0, 226, 348);
		
		TableColumn tableColumn = new TableColumn(checkTable, SWT.NONE);
		tableColumn.setWidth(220);
		tableColumn.setText("表名");
		
		checkboxTableViewer.setLabelProvider(new DataGenerateScriptTwoLabelProvider());
		checkboxTableViewer.setContentProvider(new DataGenerateScriptTwoContentProvider());
	  }

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		if(visible){
			DataGenerateScriptOneWizardPage dataGenerateScriptOneWizardPage = 
			((DataGenerateScriptOneWizardPage)getPreviousPage());
				getDBTableInfo(dataGenerateScriptOneWizardPage);
		}
		super.setVisible(visible);
	}

	/**
	 * 取数据库中表结构的信息
	 * @param dataGenerateScriptOneWizardPage
	 */
	public void getDBTableInfo(DataGenerateScriptOneWizardPage dataGenerateScriptOneWizardPage){
		IMyDBConn myDBconn = null ;
		//数据库类型
		String dbType = dataGenerateScriptOneWizardPage.getCombo().getText();
		//地址
		String url = dataGenerateScriptOneWizardPage.getText().getText();
		//驱动
		String driver = dataGenerateScriptOneWizardPage.getText_1().getText();
		//用户名
		String userName = dataGenerateScriptOneWizardPage.getText_2().getText();
		//密码
		String pwd = dataGenerateScriptOneWizardPage.getText_3().getText();
		//mysql数据库
		if(dbType.equals(ConstantUtil.DB_TYPE[0])){
			myDBconn = DaoConnFactory.getInstance(dbType);
			myDBconn.setConnInfo(url, driver, userName, pwd);
			dataTableList = myDBconn.getTableInfo();
			checkboxTableViewer.setInput(dataTableList);
			checkboxTableViewer.refresh();
		}
	}

	@SuppressWarnings("unchecked")
	public String[] getResult() {
		TableItem[] children = checkTable.getItems();  
        ArrayList v = new ArrayList(children.length);  
        for (int i = 0; i < children.length; i++) {  
            TableItem item = children[i];  
            if (item.getChecked()) {
                v.add(item.getData());  
            }
        }  
        int size = v.size();  
        result = new String[size];  
        for (int i = 0; i < v.size(); i++)  
            result[i] = v.get(i).toString();
		return result;
	}

	public Button getButton() {
		return button;
	}

	public Button getButton_1() {
		return button_1;
	}

	public Button getButton_2() {
		return button_2;
	}

	public Button getButton_3() {
		return button_3;
	}
}
