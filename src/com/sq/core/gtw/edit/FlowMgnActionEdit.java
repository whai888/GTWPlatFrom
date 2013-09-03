package com.sq.core.gtw.edit;

import gtwplatfrom.Activator;

import java.util.EventObject;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.sq.core.gtw.edit.contextMenu.CustomContextMenuProvider;
import com.sq.core.gtw.edit.controller.PartFactory;
import com.sq.core.gtw.edit.editinput.FlowMgnActionEditorInput;
import com.sq.core.gtw.edit.model.impl.ActionConnectionModel;
import com.sq.core.gtw.edit.model.impl.ActionContentsModel;
import com.sq.core.gtw.edit.model.impl.ActionModel;
import com.sq.core.gtw.edit.vo.FlowActionPaletteInfo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.XMLFileOp;
import com.thoughtworks.xstream.XStream;

/**
 * @���� whai
 * @�������� 2013-7-6
 * @�汾 V1.0
 * @�ļ��� FlowMgnActionBizEdit.java
 */
public class FlowMgnActionEdit extends GraphicalEditorWithPalette implements ITabbedPropertySheetPageContributor{

	public static final String ID = "com.sq.core.gtw.edit.FlowMgnActionEdit"; //$NON-NLS-1$

	private String actionPath = "";
	private PartFactory partFactory = new PartFactory(this) ;
	private XStream xstream = new XStream();
	private GraphicalViewer viewer ;
	private ActionContentsModel actionContentsModel ;
	private boolean dirty = false ;
	
	public FlowMgnActionEdit() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
		XMLFileOp.saveToFile(actionPath, actionContentsModel, null);
		dirty = false ;
		getCommandStack().markSaveLocation();
		
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public boolean isDirty() {
		//����Trueʱ���ĵ�ǰ���һ��*��ʾdirty
		return dirty || getCommandStack().isDirty();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	
	@Override
	public void commandStackChanged(EventObject event) {
		// TODO Auto-generated method stub
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	public void setDirty(boolean value) {
		dirty = value;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}
	
	@Override
	protected void configureGraphicalViewer() {
		// TODO Auto-generated method stub
		super.configureGraphicalViewer();
		viewer = getGraphicalViewer();
		//�ṩ���ŵĹ���
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		//���ZoomManager
		ZoomManager manager = rootEditPart.getZoomManager();
		//ע��Ŵ�Action
		IAction action = new ZoomInAction(manager);
		getActionRegistry().registerAction(action);
		//ע����СAction
		action = new ZoomOutAction(manager);
		getActionRegistry().registerAction(action);
		
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
		
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer).setParent(keyHandler));

		ContextMenuProvider provider = new CustomContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		
		//��ӿ�����������
		viewer.setEditPartFactory(partFactory);
		
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		// ���༭�������ݽ��з���
		initXmlBizData();
//		if(actionFlowInfoList==null)
//			actionFlowInfoList = new ArrayList<ActionModel>();
//		
//		ActionContentsModel parent = new ActionContentsModel();
//		
//		//��ʼ��ģ��
//		for (int i = 0; i < actionFlowInfoList.size(); i++) {
//			ActionModel actionModel = (ActionModel)actionFlowInfoList.get(i);
//			parent.addChildren(actionModel);
//		}
		
		//����һ��ģ��
		viewer.setContents(actionContentsModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createActions() {
		// TODO Auto-generated method stub
		super.createActions();
		ActionRegistry registry = getActionRegistry();

		IAction action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		//(1) ����Ҫ����һ��palette��route
		PaletteRoot root = new PaletteRoot();
		//(2) ����һ�����������ڷ��ó���Tools
		PaletteGroup toolGroup = new PaletteGroup("����");
		//(3) ����һ��GEF�ṩ��"selection" ���߲�����ŵ�toolGroup��
		ToolEntry tool = new SelectionToolEntry();
		toolGroup.add(tool);
		root.setDefaultEntry(tool);
		//(4) ����һ��GEF�ṩ��"Marquee��ѡ" ���߲�����ŵ�toolGroup��
		tool = new MarqueeToolEntry();
		toolGroup.add(tool);
		//(5) ����һ��Drawer(����)���û�ͼ���ߣ��ó�������Ϊ"��ͼ"
		PaletteDrawer connectionDrawer = new PaletteDrawer("����");
		//ָ�� "����HelloModelģ��" ��������Ӧ��ͼ��
		ImageDescriptor newConnectionDescriptor = Activator.getImageDescriptor(IImageKeys.ARROWCONNECTION);
		//(6) ����"����HelloModelģ��"����
		ConnectionCreationToolEntry connxCreationEntry = new ConnectionCreationToolEntry("����", "����������", new SimpleFactory(ActionConnectionModel.class), newConnectionDescriptor, newConnectionDescriptor);
		//(7) ����ӵ������ĳ�����
		connectionDrawer.add(connxCreationEntry);
		
		//(8) ��󽫴��������鹤�߼ӵ�root��
		root.add(toolGroup);
		root.add(connectionDrawer);
		Map<String , List<FlowActionPaletteInfo>> flowMap = ConstantUtil.ACTION_PALETTE;
		for (String flowStr : flowMap.keySet()) {
			//(5) ����һ��Drawer(����)���û�ͼ���ߣ��ó�������Ϊ"��ͼ"
			PaletteDrawer flowDrawer = new PaletteDrawer(flowStr);
			final List<FlowActionPaletteInfo> flowList = flowMap.get(flowStr);
			for (final FlowActionPaletteInfo flowPaletteInfo : flowList) {
				//ָ�� "����HelloModelģ��" ��������Ӧ��ͼ��
				ImageDescriptor descriptor = Activator.getImageDescriptor(flowPaletteInfo.getImage());
				//(6) ����"����HelloModelģ��"����
				CreationToolEntry creationEntry = new CreationToolEntry(flowPaletteInfo.getName(), flowPaletteInfo.getName(), new CreationFactory() {
					
					@Override
					public Object getObjectType() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public Object getNewObject() {
						// TODO Auto-generated method stub
						ActionModel actionModel = new ActionModel(flowPaletteInfo.getContentName());
						actionModel.setiActionModelProperties(flowPaletteInfo.getiActionModelProperties());
						return actionModel;
					}
				}, descriptor, descriptor);
				//(7) ����ӵ������ĳ�����
				flowDrawer.add(creationEntry);
			}
			root.add(flowDrawer);
		}	
		return root;
	}
	
	/**
	 * ��ʼ����������
	 */
	private void initXmlBizData() {
		// TODO Auto-generated method stub
		FlowMgnActionEditorInput flowMgnActionEditorInput = null ;
		flowMgnActionEditorInput = (FlowMgnActionEditorInput)this.getEditorInput();
		actionPath = flowMgnActionEditorInput.getPath().toString();
		actionContentsModel = (ActionContentsModel)XMLFileOp.readToFile(actionPath, xstream);
		if(actionContentsModel == null)
			actionContentsModel = new ActionContentsModel();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class type) {
		// TODO Auto-generated method stub
		if(type == IPropertySheetPage.class ){
			return new TabbedPropertySheetPage(this);
		}
		return super.getAdapter(type);
	}

	@Override
	public String getContributorId() {
		// TODO Auto-generated method stub
		return getSite().getId();
	}

}
