package com.sq.core.gtw.edit.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.sq.core.gtw.edit.vo.DictMgnVo;

/**
 * @作者 whai
 * @创建日期 2013-7-18
 * @版本 V1.0
 * @文件名 DicMgnCreateDictDialog.java
 */
public class DictMgnCreateDictDialog extends TitleAreaDialog {
	private DictMgnVo dictMgnVo ;
	private Button button ;
	private Button button_1;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo;
	private Combo combo_1 ;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DictMgnCreateDictDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("创建数据字典");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(10, 10, 60, 13);
		label.setText("代        码：");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(10, 57, 60, 13);
		label_1.setText("数据类别：");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(10, 113, 60, 13);
		label_2.setText("初  始  值：");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBounds(281, 10, 60, 13);
		label_3.setText("中文名称：");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setBounds(281, 57, 60, 13);
		label_4.setText("数据类型：");
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setBounds(10, 172, 60, 13);
		label_5.setText("描        述：");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(76, 7, 139, 19);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(347, 10, 139, 19);
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setBounds(76, 107, 139, 19);
		
		text_3 = new Text(container, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text_3.setBounds(76, 142, 410, 61);
		
		combo = new Combo(container, SWT.READ_ONLY);
		combo.setItems(new String[] {"BasicType","ListType"});
		combo.setBounds(76, 54, 139, 21);
		
		combo_1 = new Combo(container, SWT.READ_ONLY);
		combo_1.setItems(new String[] {"String","Integer","Double","Float","Char","byte","boolean"});
		combo_1.setBounds(347, 54, 139, 21);

		initDate();
		
		return area;
	}

	private void initDate() {
		// TODO Auto-generated method stub
		if(dictMgnVo !=null ){
			text.setText(dictMgnVo.getCode());
			text.setEnabled(true);
			text_1.setText(dictMgnVo.getName());
			text_2.setText(dictMgnVo.getValue());
			text_3.setText(dictMgnVo.getDescrip());
			combo.setText(dictMgnVo.getType());
			combo_1.setText(dictMgnVo.getCodeType());
		}
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("  确  定  ");
		button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("  取  消  ");
		this.setMessage("字段的类型支持基本的数据类型，如果是集合，请选择对应的集合类型");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(587, 419);
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		dictMgnVo = new DictMgnVo();
		if(text.getText().isEmpty()){
			text.setFocus();
			setMessage("数据字典代码必须输入！" , IMessageProvider.ERROR);
			return ;
		}
		dictMgnVo.setCode(text.getText());
		if(text_1.getText().isEmpty()){
			text_1.setFocus();
			setMessage("中文名称必须输入！" , IMessageProvider.ERROR);
			return ;
		}
		dictMgnVo.setName(text_1.getText());
		dictMgnVo.setValue(text_2.getText());
		dictMgnVo.setDescrip(text_3.getText());
		if(combo.getText().isEmpty()){
			combo.setFocus();
			setMessage("请选择对应的数据类别！" , IMessageProvider.ERROR);
			return ;
		}
		dictMgnVo.setType(combo.getText());
		if(combo_1.getText().isEmpty()){
			combo_1.setFocus();
			setMessage("请选择对应的数据类型！" , IMessageProvider.ERROR);
			return ;
		}
		dictMgnVo.setCodeType(combo_1.getText());
		super.okPressed();
	}

	public DictMgnVo getDictMgnVo() {
		return dictMgnVo;
	}

	public void setDictMgnVo(DictMgnVo dictMgnVo) {
		this.dictMgnVo = dictMgnVo;
	}

}
