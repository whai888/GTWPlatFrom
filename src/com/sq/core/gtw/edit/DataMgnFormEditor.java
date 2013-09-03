package com.sq.core.gtw.edit;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.edit.editinput.DataMgnFormEditorInput;
import com.sq.core.gtw.edit.form.DataMgnMasterDetailPage;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

public class DataMgnFormEditor extends FormEditor {
	
	public static final String ID="com.sq.core.gtw.edit.DataMgnFormEditor";
	private XStream xstream = new XStream();
	private IPath path ;
	private DataMgnMasterDetailPage dataMgnMasterDetailPage = null ;

	@Override
	protected void addPages() {
		// TODO Auto-generated method stub
		path = ((DataMgnFormEditorInput)this.getEditorInput()).getPath();
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(MyBatisMain.class);
		xstream.alias("mapper", MyBatisMain.class);
		MyBatisMain myBatisMain = (MyBatisMain) XMLFileOp.readToFile(path.toString(), xstream);
		//ÃÌº”“≥√Ê
		try {
			dataMgnMasterDetailPage = new DataMgnMasterDetailPage(this , myBatisMain , path) ;
			addPage(dataMgnMasterDetailPage);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		dataMgnMasterDetailPage.doSave(monitor);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

}
