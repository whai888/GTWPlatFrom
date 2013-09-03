package com.sq.core.gtw.edit.form;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.swtdesigner.SWTResourceManager;

public class DataMgnStringDetailPage implements IDetailsPage {
	public DataMgnStringDetailPage() {
	}

	private IManagedForm managedForm;
	@SuppressWarnings("unused")
	private Object obj ;

	/**
	 * Initialize the details page.
	 * @param form
	 */
	public void initialize(IManagedForm form) {
		this.managedForm = form;
	}

	/**
	 * Create contents of the details page.
	 * @param parent
	 */
	public void createContents(Composite parent) {
		 //获得表单工具对象
		FormToolkit toolkit = managedForm.getToolkit();
		//设置父类面板的布局
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);
		Section section = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);;
		section.setText("详情");
		Composite composite = toolkit.createComposite(section);
		toolkit.paintBordersFor(composite);
		//创建内容区的所设置的面板
		section.setClient(composite);
	   //创建Detail的内容区
	   TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
	   td.grabHorizontal = true;
	   section.setLayoutData(td);
	   GridLayout gridLayout = new GridLayout();
	   gridLayout.marginWidth = 0;
	   gridLayout.marginHeight = 0;
	   gridLayout.horizontalSpacing=10;
	   composite.setLayout(gridLayout);
	   //创建Detail部分具体的各种控件
	   Label idlabel = toolkit.createLabel(composite, "请选择对应的子节点进行操作", SWT.NONE);
	   idlabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
	   idlabel.setAlignment(SWT.CENTER);
	   GridData gd_idlabel = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
	   gd_idlabel.widthHint = 260;
	   gd_idlabel.heightHint = 211;
	   idlabel.setLayoutData(gd_idlabel);
	}

	public void dispose() {
		// Dispose
	}

	public void setFocus() {
		// Set focus
	}

	private void update() {
		// Update
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	/**
	 * 当Master区域选中事件发生时，调用此方法
	 */
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if(structuredSelection.size() == 1 ){
			obj = structuredSelection.getFirstElement();
			update();
		}
	}

	public void commit(boolean onSave) {
		// Commit
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
		update();
	}

}
