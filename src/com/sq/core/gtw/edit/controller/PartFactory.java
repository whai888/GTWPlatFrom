package com.sq.core.gtw.edit.controller;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.sq.core.gtw.edit.model.IConnectionModel;
import com.sq.core.gtw.edit.model.impl.ActionContentsModel;
import com.sq.core.gtw.edit.model.impl.ActionModel;
import com.sq.core.gtw.edit.model.impl.BizContentsModel;
import com.sq.core.gtw.edit.model.impl.BizModel;

/**
 * @作者 whai
 * @创建日期 2013-7-3
 * @版本 V1.0
 * @文件名 PartFactory.java
 */
public class PartFactory implements EditPartFactory {

	private BizContentsEditPart contentsEditPart ;
	private ActionContentsEditPart actionEditPart ;
	private LineConnectionEditPart lineConnectionEditPart ;
	private EditorPart editorPart  ;
	
	
	
	public PartFactory(EditorPart editorPart ) {
		super();
		this.editorPart = editorPart ;
	}

	/**
	 * 1、首先根据模型类型创建其控制器
	 * 2、然后连接模型和其控制器
	 */
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		EditPart part = getPartForElement(model);
		part.setModel(model);
		return part;
	}
 
	private EditPart getPartForElement(Object model) {
		// TODO Auto-generated method stub
		if(model == null)
			return null ;
//		System.out.println(model.getClass());
		//将控制器和模型链接起来
		if(model instanceof BizContentsModel){
			contentsEditPart = new BizContentsEditPart(editorPart);
			return contentsEditPart;
		}
		if(model instanceof ActionContentsModel){
			actionEditPart = new ActionContentsEditPart(editorPart);
			return actionEditPart;
		}
		if(model instanceof BizModel){
			return new BizEditorPart(editorPart);
		}
		if(model instanceof IConnectionModel){
			lineConnectionEditPart = new LineConnectionEditPart(editorPart);
			return lineConnectionEditPart;
		}
		if(model instanceof ActionModel)
			return new ActionEditorPart(editorPart);
		
		ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "创建模型失败", "模型创建失败，请检查是否正确"+model.getClass().getName() ,
				new Status(IStatus.ERROR, PartFactory.class.toString(), "未找到模型对应的控制器"), IStatus.ERROR);
		return null;
	}

	public BizContentsEditPart getContentsEditPart() {
		return contentsEditPart;
	}

	public LineConnectionEditPart getLineConnectionEditPart() {
		return lineConnectionEditPart;
	}

	public ActionContentsEditPart getActionEditPart() {
		return actionEditPart;
	}

}
