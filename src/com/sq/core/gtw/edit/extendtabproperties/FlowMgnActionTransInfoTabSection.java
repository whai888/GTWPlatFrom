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
import com.sq.core.gtw.edit.controller.ActionEditorPart;
import com.sq.core.gtw.edit.model.impl.ActionModel;

/**
 *@作者  whai 
 *@创建日期 2013-8-9
 *@版本 V1.0
 *@文件名 FlowMgnActionTabSection.java
 */
public class FlowMgnActionTransInfoTabSection extends AbstractPropertySection{
	
	private Text text ;
	private Text text_1 ;
	private ActionEditorPart actionEditorPart ;
	private ActionModel actionModel ;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		composite.setLayout(new GridLayout(2, false));
		
		Label label = getWidgetFactory().createLabel(composite, "组件名称：");
		label.setAlignment(SWT.CENTER);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 67;
		label.setLayoutData(gd_label);
		
		text = getWidgetFactory().createText(composite, "" , SWT.READ_ONLY);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = getWidgetFactory().createLabel(composite, "描     述：");
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_1 = getWidgetFactory().createText(composite, "" , SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_1.heightHint = 159;
		text_1.setLayoutData(gd_text_1);
		
		createListener();
	}
	//组件信息
	private void createListener() {
		// TODO Auto-generated method stub
		text.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(actionModel != null){
					actionModel.setText(text.getText());
					setDirty(true);
				}
			}
		});
		//描述信息
		text_1.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(actionModel != null){
					actionModel.setDesc(text_1.getText());
					setDirty(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
//		super.refresh();
		
		if(actionModel != null){
			text.setText(actionModel.getText());
			text_1.setText(actionModel.getDesc());
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.setInput(part, selection);
		if(selection instanceof IStructuredSelection){
			Object input = ((IStructuredSelection)selection).getFirstElement();
			if(input instanceof ActionEditorPart){
				actionEditorPart = (ActionEditorPart)input ;
				actionModel = (ActionModel)actionEditorPart.getModel() ;
			}
		}
	}

	private void setDirty(boolean flag){
		if(actionEditorPart != null)
			((FlowMgnActionEdit)actionEditorPart.getEditorPart()).setDirty(flag);
	}
	
}
