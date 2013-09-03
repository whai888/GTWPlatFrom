package com.sq.core.gtw.edit.extendtabproperties;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

import com.sq.core.gtw.edit.FlowMgnBizMutiEdit;
import com.sq.core.gtw.edit.controller.BizEditorPart;
import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.model.impl.BizModel;
import com.sq.core.gtw.edit.model.impl.BizModelPropertiesFtp;
import com.sq.core.gtw.edit.vo.BizServiceInfo;
import com.sq.core.gtw.util.ConstantUtil;

/**
 *@����  whai 
 *@�������� 2013-8-14
 *@�汾 V1.0
 *@�ļ��� FlowMgnBizFtpPropertiesTabSection.java
 */
public class FlowMgnBizFtpPropertiesTabSection extends AbstractPropertySection {
	
	private Text text ;			//�����ļ�·��
	private Text text_1 ;		//�������ļ�·��
	private Text text_6 ;		//�����ļ���
	private Text text_7 ;		//�������ļ���
	private CCombo combo_1 ;	//�ļ����䷽ʽ
	private CCombo combo ;		//�ļ���������
	private CCombo combo_2 ;	//�ļ�����
	private BizModel bizModel ;
	private BizEditorPart bizEditorPart ;
	private BizModelPropertiesFtp bizModelPropertiesFtp ;
	private FlowMgnBizMutiEdit flowMgnBizMutiEdit ;
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label label_3 = getWidgetFactory().createLabel(composite, "�ļ����䷽ʽ��");
		label_3.setAlignment(SWT.RIGHT);
		GridData gd_label_3 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_3.heightHint = 20;
		gd_label_3.widthHint = 100;
		label_3.setLayoutData(gd_label_3);
		
		combo_1 = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = getWidgetFactory().createLabel(composite, "�ļ��������ͣ�");
		label_4.setAlignment(SWT.RIGHT);
		GridData gd_label_4 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_4.heightHint = 20;
		gd_label_4.widthHint = 100;
		label_4.setLayoutData(gd_label_4);
		
		combo = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label = getWidgetFactory().createLabel(composite, "�����ļ�·����");
		label.setAlignment(SWT.RIGHT);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 100;
		label.setLayoutData(gd_label);
		
		text = getWidgetFactory().createText(composite, "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = getWidgetFactory().createLabel(composite, "�����ļ�����");
		label_6.setAlignment(SWT.RIGHT);
		GridData gd_label_6 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_6.heightHint = 20;
		gd_label_6.widthHint = 100;
		label_6.setLayoutData(gd_label_6);
		
		text_6 = getWidgetFactory().createText(composite, "");
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = getWidgetFactory().createLabel(composite, "�������ļ�·����");
		label_1.setAlignment(SWT.RIGHT);
		GridData gd_label_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.heightHint = 20;
		gd_label_1.widthHint = 100;
		label_1.setLayoutData(gd_label_1);
		
		text_1 = getWidgetFactory().createText(composite, "");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_7 = getWidgetFactory().createLabel(composite, "�������ļ�����");
		label_7.setAlignment(SWT.RIGHT);
		GridData gd_label_7 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_7.heightHint = 20;
		gd_label_7.widthHint = 100;
		label_7.setLayoutData(gd_label_7);
		
		text_7 = getWidgetFactory().createText(composite, "");
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = getWidgetFactory().createLabel(composite, "�ļ�����");
		label_2.setAlignment(SWT.RIGHT);
		GridData gd_label_2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_2.heightHint = 20;
		gd_label_2.widthHint = 100;
		label_2.setLayoutData(gd_label_1);
		
		combo_2 = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
	}
	
	/**
	 * ��ʼ������
	 */
	private void initData() {
		// TODO Auto-generated method stub
		//�ļ����䷽ʽ
		combo_1.setItems(ConstantUtil.FILE_SEND_STYLE);
		//�ľߴ�������
		combo.setItems(ConstantUtil.FILE_SEND_TYPE);
		//�ļ�����
		List<BizServiceInfo> serviceInfoList = flowMgnBizMutiEdit.getBizTransInfo().getServices();
		String [] serviceStr = new String[serviceInfoList.size()];
		for (int i = 0; i < serviceInfoList.size(); i++) {
			BizServiceInfo bizServiceInfo = serviceInfoList.get(i);
			serviceStr[i]=bizServiceInfo.getName();
		}
		combo_2.setItems(serviceStr);
	}
	
	//���URL
	private void createListener() {
		// TODO Auto-generated method stub
		//�����ļ�·��
		text.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setLocalFilePath(text.getText());
					setDirty(true);
				}
			}
		});
		//�����ļ���
		text_6.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setLocalFileName(text_6.getText());
					setDirty(true);
				}
			}
		});
		//Զ���ļ�·��
		text_1.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setRemoteFilePath(text_1.getText());
					setDirty(true);
				}
			}
		});
		//�������ļ���
		text_7.addListener(SWT.KeyUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setRemoteFileName(text_7.getText());
					setDirty(true);
				}
			}
		});
		//�ļ����䷽ʽ
		combo_1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setSendStype(combo_1.getText());
					setDirty(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//�ļ���������
		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setSendType(combo.getText());
					setDirty(true);
				}
			}
			
			
		});
		//Service����
		combo_2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(bizModelPropertiesFtp != null){
					bizModelPropertiesFtp.setService(combo_2.getText());
					setDirty(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
		initData() ;
		if(bizModelPropertiesFtp != null){
			text.setText(bizModelPropertiesFtp.getLocalFilePath());
			text_1.setText(bizModelPropertiesFtp.getRemoteFilePath());
			text_6.setText(bizModelPropertiesFtp.getLocalFileName());
			text_7.setText(bizModelPropertiesFtp.getRemoteFileName());
			combo_1.setText(bizModelPropertiesFtp.getSendStype());
			combo.setText(bizModelPropertiesFtp.getSendType());
			combo_2.setText(bizModelPropertiesFtp.getService());
		}
		createListener();
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
	        		if(ibizModelProperties instanceof BizModelPropertiesFtp)
	        			bizModelPropertiesFtp = (BizModelPropertiesFtp) bizModel.getiFlowModelProperties();
	        	}
			}
		}
	}
	
	private void setDirty(boolean flag){
		if(bizEditorPart != null){
			bizModel.setiFlowModelProperties(bizModelPropertiesFtp);
			flowMgnBizMutiEdit.setDirty(flag);
		}
	}
}
