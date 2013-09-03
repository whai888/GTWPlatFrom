//package com.sq.core.gtw.edit.editinput;
//
//import org.eclipse.core.runtime.IPath;
//import org.eclipse.jface.resource.ImageDescriptor;
//import org.eclipse.ui.IPathEditorInput;
//import org.eclipse.ui.IPersistableElement;
//
//import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;
//
///**
// * @作者 whai
// * @创建日期 2013-6-23
// * @版本 V1.0
// * @文件名 FlowMgnEditorInput.java
// */
//public class FlowMgnBizEditorInput implements IPathEditorInput {
//	private IPath path;
//	private FlowMgnBizInfo flowMgnBizInfo ;
//	
//	
//	public FlowMgnBizEditorInput(IPath path1 ,FlowMgnBizInfo flowMgnBizInfo ) {
//		this.path = path1;
//		this.flowMgnBizInfo = flowMgnBizInfo ;
//	}
//
//	@Override
//	public Object getAdapter(Class adapter) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean exists() {
//		// TODO Auto-generated method stub
//		return path.toFile().exists();
//	}
//
//	@Override
//	public ImageDescriptor getImageDescriptor() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public IPersistableElement getPersistable() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getToolTipText() {
//		// TODO Auto-generated method stub
//		return path.toString();
//	}
//
//	@Override
//	public IPath getPath() {
//		// TODO Auto-generated method stub
//		return path;
//	}
//
//	@Override
//	public String getName() {
//		// TODO Auto-generated method stub
//		return path.toString();
//	}
//
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return path.hashCode();
//	}
//
//}
