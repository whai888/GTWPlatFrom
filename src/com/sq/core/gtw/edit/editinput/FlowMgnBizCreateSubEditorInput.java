package com.sq.core.gtw.edit.editinput;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;

/**
 *@作者  whai 
 *@创建日期 2013-8-17
 *@版本 V1.0
 *@文件名 FlowMgnBizCreateSubEditorInput.java
 */
public class FlowMgnBizCreateSubEditorInput implements IPathEditorInput {
	private IPath path;
	private FlowMgnBizInfo flowMgnBizInfo ;
	
	public FlowMgnBizCreateSubEditorInput(IPath path , FlowMgnBizInfo flowMgnBizInfo ) {
		super();
		this.path = path;
		this.flowMgnBizInfo = flowMgnBizInfo ;
	}

	@Override
	public IPath getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return path.toFile().isFile();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return flowMgnBizInfo.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return flowMgnBizInfo.getName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public FlowMgnBizInfo getFlowMgnBizInfo() {
		return flowMgnBizInfo;
	}

}
