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

import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;

/**
 * @���� whai
 * @�������� 2013-7-18
 * @�汾 V1.0
 * @�ļ��� FlowMgnCreateTransDialog.java
 */
public class FlowMgnCreateBizTransDialog extends TitleAreaDialog {
	private FlowMgnBizInfo flowMgnBizInfo ;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FlowMgnCreateBizTransDialog(Shell parentShell , FlowMgnBizInfo flowMgnBizInfo) {
		super(parentShell);
		this.flowMgnBizInfo = flowMgnBizInfo ;
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
		label.setText("ҵ����룺");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBounds(10, 49, 60, 13);
		label_3.setText("�������ƣ�");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(76, 7, 139, 19);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(76, 46, 290, 19);

		initDate();
		
		return area;
	}

	private void initDate() {
		// TODO Auto-generated method stub
		if(flowMgnBizInfo !=null ){
			text.setText(flowMgnBizInfo.getCode());
			text.setEditable(false);	//���ɱ༭
			text_1.setText(flowMgnBizInfo.getName());
		}
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		this.getShell().setText("����ҵ�����");
		this.setTitle("����ҵ�����");
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("  ȷ  ��  ");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("  ȡ  ��  ");
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
		flowMgnBizInfo = new FlowMgnBizInfo();
		if(text.getText().isEmpty()){
			text.setFocus();
			setMessage("ҵ�����������룡" , IMessageProvider.ERROR);
			return ;
		}
		flowMgnBizInfo.setCode(text.getText());
		if(text_1.getText().isEmpty()){
			text_1.setFocus();
			setMessage("�������Ʊ������룡" , IMessageProvider.ERROR);
			return ;
		}
		flowMgnBizInfo.setName(text_1.getText());
		super.okPressed();
	}

	public FlowMgnBizInfo getFlowMgnBizInfo() {
		return flowMgnBizInfo;
	}

	public void setFlowMgnBizInfo(FlowMgnBizInfo flowMgnBizInfo) {
		this.flowMgnBizInfo = flowMgnBizInfo;
	}

}
