package com.sq.core.gtw.edit.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.sq.core.gtw.edit.vo.imp.system.BizDefinitionVo;

/**
 *@作者  whai 
 *@创建日期 2013-8-18
 *@版本 V1.0
 *@文件名 BizDefinitionDialog.java
 */
public class BizDefinitionDialog extends TitleAreaDialog {
	
	private BizDefinitionVo bizDefinitionVo ;
	private Button button ;
	private Button button_1 ;
	private Text text;
	private Text text_1;
	private Text text_4;
	private Combo combo ;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public BizDefinitionDialog(Shell parentShell ,BizDefinitionVo bizDefinitionVo ) {
		super(parentShell);
		this.bizDefinitionVo = bizDefinitionVo ;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setText("标签名称：");
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("标签长度：");
		
		text_4 = new Text(container, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("控件类型：");
		
		combo = new Combo(container, SWT.READ_ONLY);
		combo.setItems(new String[] {"Text"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("属性字段名：");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		initDate();
		
		return area;
	}

	private void initDate() {
		// TODO Auto-generated method stub
		if(bizDefinitionVo != null){
			text_1.setText(bizDefinitionVo.getLabel());
			combo.setText(bizDefinitionVo.getType());
			text.setText(bizDefinitionVo.getClassField());
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
		button.setText("确定");
		button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		setTitle("自定义业务逻辑属性设置");
		setMessage("请选择对应的属性");
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		bizDefinitionVo = new BizDefinitionVo();
		if(text_1.getText().isEmpty()){
			text_1.setFocus();
			setMessage("标签名称必须输入！" , IMessageProvider.ERROR);
			return ;
		}
		bizDefinitionVo.setLabel(text_1.getText());
		if(combo.getText().isEmpty()){
			combo.setFocus();
			setMessage("请选择对应的控件类型！" , IMessageProvider.ERROR);
			return ;
		}
		bizDefinitionVo.setType(combo.getText());
		if(text.getText().isEmpty()){
			text.setFocus();
			setMessage("属性字段名必须输入！" , IMessageProvider.ERROR);
			return ;
		}
		bizDefinitionVo.setClassField(text.getText());
		bizDefinitionVo.setLabelLenth(text_4.getText());
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(601, 488);
	}

	public BizDefinitionVo getBizDefinitionVo() {
		return bizDefinitionVo;
	}

	public void setBizDefinitionVo(BizDefinitionVo bizDefinitionVo) {
		this.bizDefinitionVo = bizDefinitionVo;
	}

}
