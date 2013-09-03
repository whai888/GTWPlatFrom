package com.sq.core.gtw.edit.extendtabproperties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

/**
 *@作者  whai 
 *@创建日期 2013-8-13
 *@版本 V1.0
 *@文件名 TestShell.java
 */
public class TestShell extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			TestShell shell = new TestShell(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public TestShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(2, false));
		
		Label lblId = new Label(this, SWT.CENTER);
		lblId.setAlignment(SWT.RIGHT);
		GridData gd_lblId = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblId.heightHint = 20;
		gd_lblId.widthHint = 76;
		lblId.setLayoutData(gd_lblId);
		lblId.setText("编号 :");
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblName = new Label(this, SWT.CENTER);
		GridData gd_lblName = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblName.widthHint = 123;
		lblName.setLayoutData(gd_lblName);
		lblName.setText("11111111111111111111111111111");
		lblName.setAlignment(SWT.RIGHT);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDesc = new Label(this, SWT.CENTER);
		lblDesc.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDesc.setText("描述：");
		lblDesc.setAlignment(SWT.RIGHT);
		
		text_2 = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.heightHint = 118;
		text_2.setLayoutData(gd_text_2);
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
