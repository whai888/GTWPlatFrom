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
import com.sq.core.gtw.edit.model.IActionModelProperties;
import com.sq.core.gtw.edit.model.impl.ActionModel;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesAction;

/**
 *@作者  whai 
 *@创建日期 2013-8-9
 *@版本 V1.0
 *@文件名 FlowMgnActionPropertiesTabSection.java
 */
public class FlowMgnActionPropertiesActionTabSection extends AbstractPropertySection {
	
	private Text text ;
	private ActionEditorPart actionEditorPart ;
	private ActionModelPropertiesAction actionModelPropertiesAction ;
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label label = getWidgetFactory().createLabel(composite, "访问URL：");
		label.setAlignment(SWT.CENTER);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 67;
		label.setLayoutData(gd_label);
		
		text = getWidgetFactory().createText(composite, "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		createListener();
	}
	//组件URL
	private void createListener() {
		// TODO Auto-generated method stub
		text.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(actionModelPropertiesAction != null){
					actionModelPropertiesAction.setAction(text.getText());
					setDirty(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
		if(actionModelPropertiesAction != null){
			text.setText(actionModelPropertiesAction.getAction());
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
				ActionModel actionModel = (ActionModel)actionEditorPart.getModel() ;
	        	if(actionModel instanceof ActionModel ){
	        		IActionModelProperties iActionModelProperties = ((ActionModel)actionModel).getiActionModelProperties() ;
	        		if(iActionModelProperties instanceof ActionModelPropertiesAction)
	        			actionModelPropertiesAction = (ActionModelPropertiesAction) actionModel.getiActionModelProperties();
	        	}
			}
		}
	}
	
	private void setDirty(boolean flag){
		if(actionEditorPart != null)
			((FlowMgnActionEdit)actionEditorPart.getEditorPart()).setDirty(flag);
	}
}
