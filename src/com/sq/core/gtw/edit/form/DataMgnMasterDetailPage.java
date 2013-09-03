package com.sq.core.gtw.edit.form;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.XMLFileOp;

public class DataMgnMasterDetailPage extends FormPage {
	
	public static final String ID="com.sq.core.gtw.edit.form.DataMgnMasterDetailPage";
	
	private DataMgnMasterDetailsBlock block ;
	@SuppressWarnings("unused")
	private MyBatisMain myBatisMain ;
	private boolean isDirtyFlag = false ;
	private IPath path ;

	private IManagedForm managedForm;

	
	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public DataMgnMasterDetailPage(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public DataMgnMasterDetailPage(FormEditor editor , MyBatisMain myBatisMain , IPath path) {
		super(editor, ID, "���ݿ�ű����");
		this.myBatisMain = myBatisMain ;
		this.path = path ;
		block = new DataMgnMasterDetailsBlock(this , myBatisMain);
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
//		FormToolkit toolkit = managedForm.getToolkit();
		this.managedForm = managedForm ;
		ScrolledForm form = managedForm.getForm();
		//���ñ��ı���
		form.setText("���ݿ�ű����");
//		Composite body = form.getBody();
//		toolkit.decorateFormHeading(form.getForm());
//		toolkit.paintBordersFor(body);
		//�÷����ǳ���Ҫ�����𴴽�Master��Detail���򣬾�����������
		block.createContent(managedForm);
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return isDirtyFlag;
	}
	
	public void setDirty(boolean value) {
        isDirtyFlag = value;
        dirtyStateChanged();
	}

	public void dirtyStateChanged() {
		managedForm.dirtyStateChanged();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		//��������
		XMLFileOp.saveToFile(path.toString(), block.getMyBatisMain(), ConstantUtil.MYBATIS_XML_HEAD);
		setDirty(false);
		managedForm.staleStateChanged();
		block.reflush();
	}
	
	
}
