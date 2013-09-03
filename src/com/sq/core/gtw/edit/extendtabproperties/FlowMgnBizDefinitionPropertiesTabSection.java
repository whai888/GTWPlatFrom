package com.sq.core.gtw.edit.extendtabproperties;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.sq.core.gtw.edit.FlowMgnBizMutiEdit;
import com.sq.core.gtw.edit.controller.BizEditorPart;
import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.model.impl.BizModel;
import com.sq.core.gtw.edit.model.impl.BizModelPropertiesSystem;
import com.sq.core.gtw.edit.vo.imp.system.BizDefinitionVo;
import com.sq.core.gtw.util.ConstantUtil;

/**
 *@作者  whai 
 *@创建日期 2013-8-19
 *@版本 V1.0
 *@文件名 FlowMgnBizDefinitionPropertiesTabSection.java
 */
public class FlowMgnBizDefinitionPropertiesTabSection extends AbstractPropertySection {
	private BizModel bizModel ;
	private BizEditorPart bizEditorPart ;
	private BizModelPropertiesSystem bizModelPropertiesSystem ;
	private FlowMgnBizMutiEdit flowMgnBizMutiEdit ;
	private Composite composite ;
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		composite = getWidgetFactory().createFlatFormComposite(parent);
		composite.setLayout(new GridLayout(2, false));
		
	}

	private void createControls() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<BizDefinitionVo> bizDefinitionList = bizModel.getBizDefinitionVoList();
		for (final BizDefinitionVo bizDefinitionVo : bizDefinitionList) {
			Label label_3 = getWidgetFactory().createLabel(composite, bizDefinitionVo.getLabel() + "：");
			label_3.setAlignment(SWT.RIGHT);
			GridData gd_label_3 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
			gd_label_3.heightHint = 20;
			gd_label_3.widthHint = Integer.parseInt(bizDefinitionVo.getLabelLenth());
			label_3.setLayoutData(gd_label_3);
			
			//Text控件
			if(bizDefinitionVo.getType().equals(ConstantUtil.BIZ_DEFINITION_COMBO_TYPE[0])){
				final Text text = getWidgetFactory().createText(composite, BeanUtils.getProperty(bizModelPropertiesSystem, bizDefinitionVo.getClassField()));
				text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text.addListener(SWT.KeyUp, new Listener() {
					
					@Override
					public void handleEvent(Event event) {
						// TODO Auto-generated method stub
						try {
							BeanUtils.setProperty(bizModelPropertiesSystem, bizDefinitionVo.getClassField(), text.getText());
							setDirty(true);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
						}
					}
				});
			}
		}
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
		try {
			createControls();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"错误信息", e.getMessage());
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.setInput(part, selection);
		if(selection instanceof IStructuredSelection){
			Object input = ((IStructuredSelection)selection).getFirstElement();
			if(input instanceof BizEditorPart){
				bizEditorPart = (BizEditorPart)input ;
				bizModel = (BizModel)bizEditorPart.getModel() ;
				flowMgnBizMutiEdit = (FlowMgnBizMutiEdit)bizEditorPart.getEditorPart() ;
	        	if(bizModel instanceof BizModel ){
	        		IFlowModelProperties ibizModelProperties = ((BizModel)bizModel).getiFlowModelProperties() ;
	        		if(ibizModelProperties instanceof BizModelPropertiesSystem)
	        			bizModelPropertiesSystem = (BizModelPropertiesSystem) bizModel.getiFlowModelProperties();
	        	}
			}
		}
	}
	
	private void setDirty(boolean flag){
		if(bizEditorPart != null){
			bizModel.setiFlowModelProperties(bizModelPropertiesSystem);
			flowMgnBizMutiEdit.setDirty(flag);
		}
	}
}
