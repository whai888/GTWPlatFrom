package com.sq.core.gtw.edit.extendtabproperties;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import com.sq.core.gtw.edit.dialog.FlowMgnActionPropertiesFlowDialog;
import com.sq.core.gtw.edit.model.IActionModelProperties;
import com.sq.core.gtw.edit.model.impl.ActionModel;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesFlow;

/**
 *@作者  whai 
 *@创建日期 2013-8-10
 *@版本 V1.0
 *@文件名 FlowMgnActionPropertiesFlowTabSection.java
 */
public class FlowMgnActionPropertiesFlowTabSection extends AbstractPropertySection {
	
	private Text text ;
	private Button button ;
	private ActionEditorPart actionEditorPart ;
	private ActionModelPropertiesFlow actionModelPropertiesFlow ;
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		composite.setLayout(new GridLayout(3, false));
		
		Label label = getWidgetFactory().createLabel(composite, "业务流程：");
		label.setAlignment(SWT.CENTER);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 67;
		label.setLayoutData(gd_label);
		
		text = getWidgetFactory().createText(composite, "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		button = getWidgetFactory().createButton(composite, "..." , SWT.NONE);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 30 ;
		button.setLayoutData(gd_button);
		
		createListener();
	}
	//组件URL
	private void createListener() {
		// TODO Auto-generated method stub
		text.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(actionModelPropertiesFlow != null){
					actionModelPropertiesFlow.setBizInfo(text.getText());
					setDirty(true);
				}
			}
		});
		
		//加载biz配置文件
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				FlowMgnActionPropertiesFlowDialog flowDialog = new FlowMgnActionPropertiesFlowDialog(text.getShell());
				if(flowDialog.open() == Dialog.OK){
					String bizInfo = flowDialog.getFileName() ;
					text.setText(bizInfo);
					actionModelPropertiesFlow.setBizInfo(bizInfo);
					setDirty(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
		if(actionModelPropertiesFlow != null){
			text.setText(actionModelPropertiesFlow.getBizInfo());
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
	        		if(iActionModelProperties instanceof ActionModelPropertiesFlow)
	        			actionModelPropertiesFlow = (ActionModelPropertiesFlow) actionModel.getiActionModelProperties();
	        	}
			}
		}
	}
	
	private void setDirty(boolean flag){
		if(actionEditorPart != null)
			((FlowMgnActionEdit)actionEditorPart.getEditorPart()).setDirty(flag);
	}
}
