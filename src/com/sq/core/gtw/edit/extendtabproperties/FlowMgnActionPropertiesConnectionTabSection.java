package com.sq.core.gtw.edit.extendtabproperties;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.sq.core.gtw.edit.FlowMgnActionEdit;
import com.sq.core.gtw.edit.controller.LineConnectionEditPart;
import com.sq.core.gtw.edit.dialog.FlowMgnBizConnectionPropertiesDialog;
import com.sq.core.gtw.edit.model.impl.ActionConnectionModel;

/**
 *@作者  whai 
 *@创建日期 2013-8-17
 *@版本 V1.0
 *@文件名 FlowMgnActionPropertiesConnectionTabSection.java
 */
public class FlowMgnActionPropertiesConnectionTabSection extends
		AbstractPropertySection {
	private Text text ;	//转移条件
	private Button button ;
	private LineConnectionEditPart lineConnectionEditPart ;
	private ActionConnectionModel flowActionConnectionModel ;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		composite.setLayout(new GridLayout(3, false));
		
		Label label = getWidgetFactory().createLabel(composite, "转移条件：");
		label.setAlignment(SWT.CENTER);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 67;
		label.setLayoutData(gd_label);
		
		text = getWidgetFactory().createText(composite, "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		button = getWidgetFactory().createButton(composite, "条件编辑" , SWT.NONE);
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 67 ;
		button.setLayoutData(gd_button);
		
		createListener();
		
	}
	//组件信息
	private void createListener() {
		// TODO Auto-generated method stub
		//转移条件
		text.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(flowActionConnectionModel != null){
					flowActionConnectionModel.setCondition(text.getText());
					setDirty(true);
				}
			}
		});
		
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				FlowMgnBizConnectionPropertiesDialog flowMgnBizConnectionPropertiesDialog = new FlowMgnBizConnectionPropertiesDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() , text.getText());
				if(flowMgnBizConnectionPropertiesDialog.open() == TitleAreaDialog.OK){
					String inputText = flowMgnBizConnectionPropertiesDialog.getText();
					text.setText(inputText);
					flowActionConnectionModel.setCondition(inputText);
					setDirty(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
//		super.refresh();
		
		if(flowActionConnectionModel != null){
			text.setText(flowActionConnectionModel.getCondition());
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.setInput(part, selection);
		if(selection instanceof IStructuredSelection){
			Object input = ((IStructuredSelection)selection).getFirstElement();
			if(input instanceof LineConnectionEditPart){
				lineConnectionEditPart = (LineConnectionEditPart)input ;
				flowActionConnectionModel = (ActionConnectionModel)lineConnectionEditPart.getModel() ;
			}
		}
	}

	private void setDirty(boolean flag){
		if(lineConnectionEditPart != null)
			((FlowMgnActionEdit)lineConnectionEditPart.getEditorPart()).setDirty(flag);
	}
}
