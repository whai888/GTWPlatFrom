package com.sq.core.gtw.view.dialog;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.sq.core.gtw.util.ConstantUtil;

/**
 * @作者 whai
 * @创建日期 2013-7-24
 * @版本 V1.0
 * @文件名 DataGenerateScriptOneWizardPage.java
 */
public class DataGenerateScriptOneWizardPage extends WizardPage {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo ;

	/**
	 * Create the wizard.
	 */
	public DataGenerateScriptOneWizardPage() {
		super("数据库选择");
		setTitle("数据库选择");
		setDescription("请选择对应的数据库");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(114, 33, 72, 13);
		label.setText("数据库类型：");
		
		combo = new Combo(container, SWT.READ_ONLY);
		combo.setItems(ConstantUtil.DB_TYPE);
		combo.setBounds(215, 30, 206, 21);
		combo.setText(ConstantUtil.DB_TYPE[0]);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("数据库地址：");
		label_1.setBounds(114, 76, 72, 13);
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(215, 73, 206, 19);
		text.setText("jdbc:mysql://localhost:3306/sindocw?useUnicode=true&characterEncoding=utf-8");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("数据库驱动：");
		label_2.setBounds(114, 118, 72, 13);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(215, 115, 206, 19);
		text_1.setText("com.mysql.jdbc.Driver");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setText(" 用 户 名 ：");
		label_3.setBounds(114, 160, 72, 13);
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setBounds(215, 157, 206, 19);
		text_2.setText("root");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("  密   码  ：");
		label_4.setBounds(114, 203, 72, 13);
		
		text_3 = new Text(container, SWT.BORDER);
		text_3.setBounds(215, 200, 206, 19);
		text_3.setText("root");
	}

	@Override
	public IWizardPage getNextPage() {
		// TODO Auto-generated method stub
		return super.getNextPage();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Text getText_1() {
		return text_1;
	}

	public void setText_1(Text text_1) {
		this.text_1 = text_1;
	}

	public Text getText_2() {
		return text_2;
	}

	public void setText_2(Text text_2) {
		this.text_2 = text_2;
	}

	public Text getText_3() {
		return text_3;
	}

	public void setText_3(Text text_3) {
		this.text_3 = text_3;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}
}
