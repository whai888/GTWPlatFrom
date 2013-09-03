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
 * @作者 whai
 * @创建日期 2013-7-6
 * @版本 V1.0
 * @文件名 FlowMgnActionBizEdit.java
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
		//返回True时在文档前面加一个*表示dirty
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
		//提供缩放的功能
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		//获得ZoomManager
		ZoomManager manager = rootEditPart.getZoomManager();
		//注册放大Action
		IAction action = new ZoomInAction(manager);
		getActionRegistry().registerAction(action);
		//注册缩小Action
		action = new ZoomOutAction(manager);
		getActionRegistry().registerAction(action);
		
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
		
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer).setParent(keyHandler));

		ContextMenuProvider provider = new CustomContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		
		//添加控制器工厂类
		viewer.setEditPartFactory(partFactory);
		
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		// 将编辑器的数据进行分离
		initXmlBizData();
//		if(actionFlowInfoList==null)
//			actionFlowInfoList = new ArrayList<ActionModel>();
//		
//		ActionContentsModel parent = new ActionContentsModel();
//		
//		//初始化模型
//		for (int i = 0; i < actionFlowInfoList.size(); i++) {
//			ActionModel actionModel = (ActionModel)actionFlowInfoList.get(i);
//			parent.addChildren(actionModel);
//		}
		
		//创建一个模型
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
		//(1) 首先要创建一个palette的route
		PaletteRoot root = new PaletteRoot();
		//(2) 创建一个工具组用于放置常规Tools
		PaletteGroup toolGroup = new PaletteGroup("工具");
		//(3) 创建一个GEF提供的"selection" 工具并将其放到toolGroup中
		ToolEntry tool = new SelectionToolEntry();
		toolGroup.add(tool);
		root.setDefaultEntry(tool);
		//(4) 创建一个GEF提供的"Marquee多选" 工具并将其放到toolGroup中
		tool = new MarqueeToolEntry();
		toolGroup.add(tool);
		//(5) 创建一个Drawer(抽屉)放置绘图工具，该抽屉名称为"画图"
		PaletteDrawer connectionDrawer = new PaletteDrawer("连接");
		//指定 "创建HelloModel模型" 工具所对应的图标
		ImageDescriptor newConnectionDescriptor = Activator.getImageDescriptor(IImageKeys.ARROWCONNECTION);
		//(6) 创建"创建HelloModel模型"工具
		ConnectionCreationToolEntry connxCreationEntry = new ConnectionCreationToolEntry("连接", "创建连接线", new SimpleFactory(ActionConnectionModel.class), newConnectionDescriptor, newConnectionDescriptor);
		//(7) 将其加到创建的抽屉中
		connectionDrawer.add(connxCreationEntry);
		
		//(8) 最后将创建的两组工具加到root上
		root.add(toolGroup);
		root.add(connectionDrawer);
		Map<String , List<FlowActionPaletteInfo>> flowMap = ConstantUtil.ACTION_PALETTE;
		for (String flowStr : flowMap.keySet()) {
			//(5) 创建一个Drawer(抽屉)放置绘图工具，该抽屉名称为"画图"
			PaletteDrawer flowDrawer = new PaletteDrawer(flowStr);
			final List<FlowActionPaletteInfo> flowList = flowMap.get(flowStr);
			for (final FlowActionPaletteInfo flowPaletteInfo : flowList) {
				//指定 "创建HelloModel模型" 工具所对应的图标
				ImageDescriptor descriptor = Activator.getImageDescriptor(flowPaletteInfo.getImage());
				//(6) 创建"创建HelloModel模型"工具
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
				//(7) 将其加到创建的抽屉中
				flowDrawer.add(creationEntry);
			}
			root.add(flowDrawer);
		}	
		return root;
	}
	
	/**
	 * 初始化交易数据
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
