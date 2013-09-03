package com.sq.core.gtw.edit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.editinput.FlowMgnBizXmlSourceEditorInput;
import com.sq.core.gtw.edit.vo.BizTransInfo;
import com.thoughtworks.xstream.XStream;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

/**
 * @作者 whai
 * @创建日期 2013-7-10
 * @版本 V1.0
 * @文件名 FlowMgnBizXmlSourceEdit.java
 */
public class FlowMgnBizXmlSourceEdit extends EditorPart {

	public static final String ID = "com.sq.core.gtw.edit.FlowMgnBizXmlSourceEdit"; //$NON-NLS-1$
	@SuppressWarnings("unused")
	private FlowMgnBizMutiEdit mutiEdit ;
	private XStream xstream = new XStream();
	private Text text;

	public FlowMgnBizXmlSourceEdit(FlowMgnBizMutiEdit mutiEdit) {
		this.mutiEdit = mutiEdit ;
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		text =new Text(container,SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true, 1, 1));
		xstream.processAnnotations(BizTransInfo.class);
		BizTransInfo bizTransInfo = ((FlowMgnBizXmlSourceEditorInput)this.getEditorInput()).getBizTransInfo();
		String xml = xstream.toXML(bizTransInfo);
		text.setText(xml);
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
