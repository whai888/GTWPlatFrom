package com.sq.core.gtw.edit.extendtabproperties;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.sq.core.gtw.edit.FlowMgnActionEdit;
import com.sq.core.gtw.edit.controller.BizContentsEditPart;
import com.sq.core.gtw.edit.controller.LineConnectionEditPart;
import com.sq.core.gtw.edit.model.impl.BizContentsModel;

/**
 *@作者  whai 
 *@创建日期 2013-8-17
 *@版本 V1.0
 *@文件名 FlowMgnBizContentsModelPropertiesTabSection.java
 */
public class FlowMgnBizContentsModelPropertiesTabSection extends
		AbstractPropertySection {
	private Text text ;	//id
	private Text text_1 ;	//名称
	private Text text_2 ;	//描述
	private BizContentsEditPart bizContentsEditPart ;	
	private BizContentsModel bizContentsModel ;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label lblId =  getWidgetFactory().createLabel(composite, "编号 :");
		lblId.setAlignment(SWT.RIGHT);
		GridData gd_lblId = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblId.heightHint = 20;
		gd_lblId.widthHint = 76;
		lblId.setLayoutData(gd_lblId);
		
		text = getWidgetFactory().createText(composite, "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblName = getWidgetFactory().createLabel(composite, "名称 :");
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setAlignment(SWT.RIGHT);
		
		text_1 = getWidgetFactory().createText(composite, "");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDesc = getWidgetFactory().createLabel(composite, "描述：");
		lblDesc.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDesc.setAlignment(SWT.RIGHT);
		
		text_2 = getWidgetFactory().createText(composite, "" , SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.heightHint = 118;
		text_2.setLayoutData(gd_text_2);
		
	}
	//组件信息
	private void createListener() {
		// TODO Auto-generated method stub
		//转移条件
		text.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(bizContentsModel != null){
					bizContentsModel.setId(text.getText());
					bizContentsModel.setName(text_1.getText());
					bizContentsModel.setDescription(text_2.getText());
					setDirty(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
//		super.refresh();
		
		if(bizContentsModel != null){
			text.setText(bizContentsModel.getId());
			text_1.setText(bizContentsModel.getName());
			text_2.setText(bizContentsModel.getDescription());
		}
		createListener();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.setInput(part, selection);
		if(selection instanceof IStructuredSelection){
			Object input = ((IStructuredSelection)selection).getFirstElement();
			if(input instanceof LineConnectionEditPart){
				bizContentsEditPart = (BizContentsEditPart)input ;
				bizContentsModel = (BizContentsModel)bizContentsEditPart.getModel() ;
			}
		}
	}

	private void setDirty(boolean flag){
		if(bizContentsEditPart != null)
			((FlowMgnActionEdit)bizContentsEditPart.getEditorPart()).setDirty(flag);
	}
}
