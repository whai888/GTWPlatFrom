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
		 //��ñ����߶���
		FormToolkit toolkit = managedForm.getToolkit();
		//���ø������Ĳ���
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);
		Section section = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);;
		section.setText("����");
		Composite composite = toolkit.createComposite(section);
		toolkit.paintBordersFor(composite);
		//�����������������õ����
		section.setClient(composite);
	   //����Detail��������
	   TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
	   td.grabHorizontal = true;
	   section.setLayoutData(td);
	   GridLayout gridLayout = new GridLayout();
	   gridLayout.marginWidth = 0;
	   gridLayout.marginHeight = 0;
	   gridLayout.horizontalSpacing=10;
	   composite.setLayout(gridLayout);
	   //����Detail���־���ĸ��ֿؼ�
	   Label idlabel = toolkit.createLabel(composite, "��ѡ���Ӧ���ӽڵ���в���", SWT.NONE);
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
	 * ��Master����ѡ���¼�����ʱ�����ô˷���
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
