package com.sq.core.gtw.view.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.sq.core.gtw.view.pub.vo.DataMgnInfoVo;

/**
 * @作者 whai
 * @创建日期 2013-7-23
 * @版本 V1.0
 * @文件名 DataMgnCreateActionTransDialog.java
 */
public class DataMgnCreateActionTransDialog extends TitleAreaDialog {

	private DataMgnInfoVo dataMgnInfoVo ;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DataMgnCreateActionTransDialog(Shell parentShell , DataMgnInfoVo dataMgnInfoVo) {
		super(parentShell);
		this.dataMgnInfoVo = dataMgnInfoVo ;
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(10, 10, 60, 13);
		label.setText("  表      名 ：");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBounds(10, 49, 60, 13);
		label_3.setText("中文名称：");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(76, 7, 139, 19);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(76, 46, 290, 19);

		initDate();
		
		return area;
	}

	private void initDate() {
		// TODO Auto-generated method stub
		if(dataMgnInfoVo !=null ){
			text.setText(dataMgnInfoVo.getTableName());
			text.setEditable(false);	//不可编辑
			text_1.setText(dataMgnInfoVo.getCnName());
		}
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		this.getShell().setText("新增数据库视图");
		this.setTitle("新增数据库脚本");
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("  确  定  ");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("  取  消  ");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(532, 258);
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		dataMgnInfoVo = new DataMgnInfoVo();
		if(text.getText().isEmpty()){
			text.setFocus();
			setMessage("数据库表名！" , IMessageProvider.ERROR);
			return ;
		}
		dataMgnInfoVo.setTableName(text.getText());
		if(text_1.getText().isEmpty()){
			text_1.setFocus();
			setMessage("表名对应中文名称必须输入！" , IMessageProvider.ERROR);
			return ;
		}
		dataMgnInfoVo.setCnName(text_1.getText());
		super.okPressed();
	}

	public DataMgnInfoVo getDataMgnInfoVo() {
		return dataMgnInfoVo;
	}

	public void setDataMgnInfoVo(DataMgnInfoVo dataMgnInfoVo) {
		this.dataMgnInfoVo = dataMgnInfoVo;
	}


}
