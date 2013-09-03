package com.sq.core.gtw.edit.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 *@作者  whai 
 *@创建日期 2013-8-24
 *@版本 V1.0
 *@文件名 SQLSelectDialog.java
 */
public class SQLSelectDialog extends Dialog {
	private Table table;
	private Table table_1;
	private Table table_2;
	private Text text;
	private TableColumn tableColumn;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tableColumn_1;
	private TableViewerColumn tableViewerColumn_1;
	private TableColumn tableColumn_2;
	private TableViewerColumn tableViewerColumn_2;
	private TableColumn tableColumn_3;
	private TableViewerColumn tableViewerColumn_3;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SQLSelectDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		
		TableViewer tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd_table = new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1);
		gd_table.widthHint = 210;
		table.setLayoutData(gd_table);
		
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(220);
		tableColumn.setText("表名");
		
		TableViewer tableViewer_1 = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table_1.widthHint = 451;
		table_1.setLayoutData(gd_table_1);
		
		tableViewerColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1 = TableColumn_1.getColumn();
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("列名");
		
		TableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2 = TableColumn_2.getColumn();
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("类型");
		
		TableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3 = TableColumn_3.getColumn();
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("长度");
		
		TableViewer tableViewer_2 = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_2 = tableViewer_2.getTable();
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		text = new Text(container, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text.heightHint = 94;
		text.setLayoutData(gd_text);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(713, 659);
	}

}
