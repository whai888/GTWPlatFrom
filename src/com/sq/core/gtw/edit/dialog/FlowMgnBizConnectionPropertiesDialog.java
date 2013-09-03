package com.sq.core.gtw.edit.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;

/**
 *@作者  whai 
 *@创建日期 2013-8-22
 *@版本 V1.0
 *@文件名 FlowMgnBizConnectionPropertiesDialog.java
 */
public class FlowMgnBizConnectionPropertiesDialog extends TitleAreaDialog {
	private int courseInt = 0 ;
	private Text text;
	private String textStr = "" ;
	private Button button ;
	private Button button_0 ;
	private Button button_1 ;
	private Button button_2 ;
	private Button button_3 ;
	private Button button_4 ;
	private Button button_5 ;
	private Button button_6 ;
	private Button button_7 ;
	private Button button_8 ;
	private Button button_9 ;
	private Button button_10 ;
	private Button button_11 ;
	private Button button_12 ;
	private Button button_98 ;
	private Button button_99 ;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FlowMgnBizConnectionPropertiesDialog(Shell parentShell , String textStr ) {
		super(parentShell);
		this.textStr = textStr ;
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("业务逻辑条件编辑器");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group group_1 = new Group(container, SWT.NONE);
		group_1.setText("表达式");
		group_1.setLayout(new GridLayout(8, false));
		GridData gd_group_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_group_1.heightHint = 171;
		group_1.setLayoutData(gd_group_1);
		
		button = new Button(group_1, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		button.setText("插入变量");
		
		button_0 = new Button(group_1, SWT.NONE);
		button_0.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 6, 1));
		button_0.setText("插入常量");
		
		button_1 = new Button(group_1, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_1.setText("(");
		
		button_2 = new Button(group_1, SWT.NONE);
		button_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_2.setText(")");
		
		button_3 = new Button(group_1, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_3.setText("&&&&");
		
		button_4 = new Button(group_1, SWT.NONE);
		button_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_4.setText("||");
		
		button_5 = new Button(group_1, SWT.NONE);
		button_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_5.setText("+");
		
		button_6 = new Button(group_1, SWT.NONE);
		button_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_6.setText("-");
		
		button_7 = new Button(group_1, SWT.NONE);
		button_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_7.setText("*");
		
		button_8 = new Button(group_1, SWT.NONE);
		button_8.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_8.setText("/");
		
		button_9 = new Button(group_1, SWT.NONE);
		button_9.setText(">");
		
		button_10 = new Button(group_1, SWT.NONE);
		button_10.setText("<");
		
		button_11 = new Button(group_1, SWT.NONE);
		button_11.setText("=");
		
		button_12 = new Button(group_1, SWT.NONE);
		button_12.setText("!=");
		new Label(group_1, SWT.NONE);
		new Label(group_1, SWT.NONE);
		new Label(group_1, SWT.NONE);
		new Label(group_1, SWT.NONE);
		
		Group group = new Group(container, SWT.NONE);
		group.setText("描述");
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		text = new Text(group, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 102;
		text.setLayoutData(gd_text);

		initDate();
		createListener();
		return area;
	}

	private void initDate() {
		// TODO Auto-generated method stub
		text.setText(textStr);
	}

	private void createListener() {
		// TODO Auto-generated method stub
		button.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				InputDialog inputDialog = new InputDialog(getShell(), "输入元数据", "请输入元数据", "", null);
				if(inputDialog.open() == InputDialog.OK){
					String inputStr = inputDialog.getValue();
					String textStr = text.getText();
					String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
					text.setText(temp);
				}
			}
		});
		button_0.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				InputDialog inputDialog = new InputDialog(getShell(), "输入元数据对应的值", "请输入值", "", null);
				if(inputDialog.open() == InputDialog.OK){
					String inputStr = inputDialog.getValue();
					String textStr = text.getText();
					String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
					text.setText(temp);
				}
			}
		});
		button_1.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_1.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_2.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_2.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_3.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = "&&";
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_4.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_4.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_5.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_5.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_6.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_6.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_7.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_7.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_8.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_8.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_9.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_9.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_10.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_10.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_11.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_11.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
		button_12.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				courseInt = text.getSelection().x ;
				if(courseInt == 0 )
					courseInt = text.getText().length();
				String inputStr = button_12.getText();
				String textStr = text.getText();
				String temp =textStr.substring(0,courseInt) + inputStr + textStr.substring(courseInt) ;
				text.setText(temp);
			}
		});
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button_98 = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button_98.setText("  确  定  ");
		button_99 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_99.setText("  取  消  ");
		setMessage("业务逻辑条件编辑");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(628, 481);
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		textStr = text.getText();
		super.okPressed();
	}

	public String getText() {
		return textStr;
	}

}
