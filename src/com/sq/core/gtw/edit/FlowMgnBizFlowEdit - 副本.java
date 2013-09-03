package com.sq.core.gtw.edit;

import gtwplatfrom.Activator;

import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
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
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.sq.core.gtw.edit.controller.PartFactory;
import com.sq.core.gtw.edit.editinput.FlowMgnBizFlowEditorInput;
import com.sq.core.gtw.edit.model.impl.BizConnectionModel;
import com.sq.core.gtw.edit.model.impl.BizContentsModel;
import com.sq.core.gtw.edit.model.impl.BizModel;
import com.sq.core.gtw.edit.vo.FlowBizPaletteInfo;
import com.sq.core.gtw.util.IImageKeys;

/**
 * @作者 whai
 * @创建日期 2013-7-2
 * @版本 V1.0
 * @文件名 FlowMgnEdit.java
 */
public class FlowMgnBizFlowEdit extends GraphicalEditorWithPalette{

	public static final String ID = "com.sq.core.gtw.edit.FlowMgnBizFlowEdit"; //$NON-NLS-1$
	private FlowMgnBizMutiEdit mutiEdit ;
	private PartFactory partFactory  ;
	private BizContentsModel bizContentsModel ;
	private Map<String , List<FlowBizPaletteInfo>> flowPaletteMap = new LinkedHashMap<String , List<FlowBizPaletteInfo>>();

	GraphicalViewer viewer ;
	
	public FlowMgnBizFlowEdit(FlowMgnBizMutiEdit mutiEdit , Map<String , List<FlowBizPaletteInfo>> flowPaletteMap) {
		this.flowPaletteMap = flowPaletteMap ;
		this.mutiEdit = mutiEdit ;
		setEditDomain(new DefaultEditDomain(this));
		partFactory = new PartFactory(mutiEdit);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
		getCommandStack().markSaveLocation();
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public boolean isDirty() {
		//返回True时在文档前面加一个*表示dirty
		return getCommandStack().isDirty();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		// TODO Auto-generated method stub
		mutiEdit.setDirty(true);
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
		
		//添加控制器工厂类
		viewer.setEditPartFactory(partFactory);
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		bizContentsModel = ((FlowMgnBizFlowEditorInput)this.getEditorInput()).getBizContentsModel() ;
		
		//将helloModel添加到模型中
		viewer.setContents(bizContentsModel);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site, input);
	}

	@Override
	protected void createActions() {
		// TODO Auto-generated method stub
		super.createActions();
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
		ConnectionCreationToolEntry connxCreationEntry = new ConnectionCreationToolEntry("简单连接", "创建最简单的连接", new SimpleFactory(BizConnectionModel.class), newConnectionDescriptor, newConnectionDescriptor);
		//(7) 将其加到创建的抽屉中
		connectionDrawer.add(connxCreationEntry);
		
		//(8) 最后将创建的两组工具加到root上
		root.add(toolGroup);
		root.add(connectionDrawer);
		Map<String , List<FlowBizPaletteInfo>> flowMap = flowPaletteMap;
		for (String flowStr : flowMap.keySet()) {
			//(5) 创建一个Drawer(抽屉)放置绘图工具，该抽屉名称为"画图"
			PaletteDrawer flowDrawer = new PaletteDrawer(flowStr);
			final List<FlowBizPaletteInfo> flowList = flowMap.get(flowStr);
			for (final FlowBizPaletteInfo flowPaletteInfo : flowList) {
				//指定 "创建HelloModel模型" 工具所对应的图标
				ImageDescriptor descriptor = Activator.getImageDescriptor(flowPaletteInfo.getImage());
				//(6) 创建"创建HelloModel模型"工具
				CreationToolEntry creationEntry = new CreationToolEntry(flowPaletteInfo.getToolTipTextname(), flowPaletteInfo.getToolTipTextname(), new CreationFactory() {
					
					@Override
					public Object getObjectType() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public Object getNewObject() {
						// TODO Auto-generated method stub
						BizModel bizModel = new BizModel(flowPaletteInfo.getContentName()) ;
						bizModel.setiFlowModelProperties(flowPaletteInfo.getiFlowModelProperties());
						bizModel.setBizDefinitionVoList(flowPaletteInfo.getBizDefinitionVoList());
						bizModel.setDesc(flowPaletteInfo.getDescript());
						return bizModel ;
					}
				}, descriptor, descriptor);
				//(7) 将其加到创建的抽屉中
				flowDrawer.add(creationEntry);
			}
			root.add(flowDrawer);
		}
		return root;
	}

	public BizContentsModel getBizContentsModel() {
		return bizContentsModel;
	}

}