package com.sq.core.gtw.edit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.sq.core.gtw.edit.editinput.FlowMgnBizDataPkgEditorInput;
import com.sq.core.gtw.edit.editinput.FlowMgnBizFlowEditorInput;
import com.sq.core.gtw.edit.editinput.FlowMgnBizMutiEditorInput;
import com.sq.core.gtw.edit.editinput.FlowMgnBizServiceEditorInput;
import com.sq.core.gtw.edit.editinput.FlowMgnBizTransInfoEditorInput;
import com.sq.core.gtw.edit.editinput.FlowMgnBizXmlSourceEditorInput;
import com.sq.core.gtw.edit.vo.BizDatatransInfo;
import com.sq.core.gtw.edit.vo.BizTransInfo;
import com.sq.core.gtw.edit.vo.FlowBizPaletteInfo;
import com.sq.core.gtw.util.ReadProperties;
import com.sq.core.gtw.util.XMLFileOp;
import com.sq.core.gtw.view.pub.vo.FlowMgnBizInfo;
import com.thoughtworks.xstream.XStream;

/**
 * @作者 whai
 * @创建日期 2013-7-9
 * @版本 V1.0
 * @文件名 FlowMgnBizMutiEditor.java
 */
public class FlowMgnBizMutiEdit extends MultiPageEditorPart implements ITabbedPropertySheetPageContributor{
	
	private String bizPath = "";
	private XStream xstream = new XStream();
	private String bizModelPath = ReadProperties.getSystemKey("PUB_BIZ_MODEL");
	//基本信息编辑器
	private FlowMgnBizTransInfoEdit flowMgnBizTransInfoEdit = new FlowMgnBizTransInfoEdit(this) ;
	private FlowMgnBizTransInfoEditorInput transInfo;
	//逻辑处理流程编辑器
	private FlowMgnBizFlowEdit flowMgnBizFlowEdit ;
	private FlowMgnBizFlowEditorInput flowMgnBizFlowEditorInput ;
	//服务定义 编辑器
	private FlowMgnBizServiceEdit flowMgnBizServiceEdit = new FlowMgnBizServiceEdit(this) ;
	private FlowMgnBizServiceEditorInput flowMgnBizServiceEditorInput;
	//数据报文编辑器
	private FlowMgnBizDataPkgEdit flowMgnBizDataPkgEdit = new FlowMgnBizDataPkgEdit(this) ;
	private FlowMgnBizDataPkgEditorInput flowMgnBizDataPkgEditorInput;
	//XML源码
	private FlowMgnBizXmlSourceEdit flowMgnBizXmlSourceEdit = new FlowMgnBizXmlSourceEdit(this) ;
	private FlowMgnBizXmlSourceEditorInput flowMgnBizXmlSourceEditorInput ;
	//业务逻辑Flow Palette定义
	public static Map<String , List<FlowBizPaletteInfo>> flowPaletteMap = new LinkedHashMap<String , List<FlowBizPaletteInfo>>();
	
	public static final String ID = "com.sq.core.gtw.edit.FlowMgnBizMutiEdit"; //$NON-NLS-1$
	
	private BizTransInfo bizTransInfo = null ;
//	private boolean dirty = flowMgnBizFlowEdit.isDirty() || flowMgnBizTransInfoEdit.isDirty() || flowMgnBizServiceEdit.isDirty() || flowMgnBizDataPkgEdit.isDirty()  ;
	
	private boolean dirty = false ;
	
	@Override
	protected void createPages() {
		// TODO Auto-generated method stub
		//添加第一页
		try {
			// 将编辑器的数据进行分离
			initXmlBizData();
			transInfo = new FlowMgnBizTransInfoEditorInput(bizTransInfo.getTransInfo()) ;
			addPage( 0 , flowMgnBizTransInfoEdit, transInfo );
			setPageText(0, "基本属性");
			flowMgnBizFlowEdit = new FlowMgnBizFlowEdit(this , flowPaletteMap );
			flowMgnBizFlowEditorInput = new FlowMgnBizFlowEditorInput(bizTransInfo.getBizContentsModel());
			addPage(1 , flowMgnBizFlowEdit, flowMgnBizFlowEditorInput);
			setPageText(1, "逻辑处理流程");
			flowMgnBizServiceEditorInput = new FlowMgnBizServiceEditorInput(bizTransInfo.getServices());
			addPage(flowMgnBizServiceEdit, flowMgnBizServiceEditorInput);
			setPageText(2, "服务定义");
			flowMgnBizDataPkgEditorInput = new FlowMgnBizDataPkgEditorInput(bizTransInfo.getPackages()) ;
			addPage(flowMgnBizDataPkgEdit, flowMgnBizDataPkgEditorInput);
			setPageText(3, "数据报文");
			flowMgnBizXmlSourceEditorInput = new FlowMgnBizXmlSourceEditorInput(bizTransInfo);
			addPage(flowMgnBizXmlSourceEdit, flowMgnBizXmlSourceEditorInput );
			setPageText(4, "XML源码");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初始化交易数据
	 */
	private void initXmlBizData() {
		// TODO Auto-generated method stub
		FlowMgnBizMutiEditorInput flowMgnBizMutiEditorInput = null ;
		flowMgnBizMutiEditorInput = (FlowMgnBizMutiEditorInput)this.getEditorInput();
		bizPath = flowMgnBizMutiEditorInput.getPath().toString();
//		flowMgnBizMutiEditorInput.getFlowMgnBizInfo();
		xstream.processAnnotations(BizTransInfo.class);
		bizTransInfo = (BizTransInfo)XMLFileOp.readToFile(bizPath, xstream);
		
		if(bizTransInfo == null )
			bizTransInfo = new BizTransInfo();
		BizDatatransInfo bizDatatransInfo = bizTransInfo.getTransInfo();
		FlowMgnBizInfo flowMgnBizInfo = flowMgnBizMutiEditorInput.getFlowMgnBizInfo();
		bizDatatransInfo.setId(flowMgnBizInfo.getCode());
		bizDatatransInfo.setName(flowMgnBizInfo.getName());
		//初始化业务流程组件
		initBizPaletteInfo();
	}

	@SuppressWarnings("unchecked")
	private void initBizPaletteInfo() {
		// TODO Auto-generated method stub
		flowPaletteMap = (Map<String, List<FlowBizPaletteInfo>>) XMLFileOp.readToFile(bizModelPath, xstream);
		flowPaletteMap.put("自定义组件", bizTransInfo.getDefinitionBiz());
	}

	/**
	 * 页面切换时触发的事件，可以用来给不同页面之间赋值
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		// TODO Auto-generated method stub
		super.pageChange(newPageIndex);
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		flowMgnBizFlowEdit.doSave(monitor);
		
		BizTransInfo bizTransContent = new BizTransInfo() ;
		bizTransContent.setTransInfo(transInfo.getBizDatatransInfo());
		bizTransContent.setPackages(bizTransInfo.getPackages());
		bizTransContent.setServices(bizTransInfo.getServices());
		bizTransContent.setDefinitionBiz(bizTransInfo.getDefinitionBiz());
		bizTransContent.setBizContentsModel(flowMgnBizFlowEdit.getBizContentsModel());
		
		XMLFileOp.saveToFile(bizPath, bizTransContent, null);
		setDirty(false);
			
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		flowMgnBizTransInfoEdit.setFocus();
//		super.setFocus();
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}
	
	public void setDirty(boolean value) {
		dirty = value;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	public BizTransInfo getBizTransInfo() {
		return bizTransInfo;
	}

	@Override
	public String getContributorId() {
		// TODO Auto-generated method stub
		return getSite().getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		if(adapter == IPropertySheetPage.class) {
		    return new TabbedPropertySheetPage(this);
		  }
		return super.getAdapter(adapter);
	}

}
