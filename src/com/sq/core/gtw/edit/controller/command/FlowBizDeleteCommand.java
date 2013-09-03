package com.sq.core.gtw.edit.controller.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;
import com.sq.core.gtw.edit.model.IContentsModel;
import com.sq.core.gtw.edit.model.IFlowModel;

/**
 * @作者 whai
 * @创建日期 2013-7-5
 * @版本 V1.0
 * @文件名 FlowBizDeleteCommand.java
 */
public class FlowBizDeleteCommand extends Command {
	
	private IContentsModel contentsModel;
	private IFlowModel flowModel;
	
	//删除模型时，也应该删除相关的连接
	@SuppressWarnings("unchecked")
	private List sourceConnections = new ArrayList();
	@SuppressWarnings("unchecked")
	private List targetConnections = new ArrayList();
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//在删除一个模型对象前，搜索这个模型对象作为起点和终点的所有连接
		sourceConnections.addAll(flowModel.getModelSourceConnections());
		targetConnections.addAll(flowModel.getModelTargetConnections());
		//删除该模型对象对应的source
		for (int i = 0; i < sourceConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel) sourceConnections.get(i);
			model.detachSource();
			model.detachTaret();
		}
		//删除该模型对象对应的target
		for (int i = 0; i < targetConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel)targetConnections.get(i);
			model.detachSource();
			model.detachTaret();
		}
		contentsModel.removeChildren(flowModel);
	}
	public void setContentsModel(Object contentsModel) {
		this.contentsModel = (IContentsModel) contentsModel;
	}
	public void setflowModel(Object flowModel) {
		this.flowModel = (IFlowModel) flowModel;
	}
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		//撤销时需要添加该模型对象对应的source
		for (int i = 0; i < sourceConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel)sourceConnections.get(i);
			model.attachSource();
			model.attachTarget();
		}
		//撤销时需要删除该模型对象对应的target
		for (int i = 0; i < targetConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel)targetConnections.get(i);
			model.attachSource();
			model.attachTarget();
		}
		//清除记录
		sourceConnections.clear();
		targetConnections.clear();
		
		contentsModel.addChildren(flowModel);
	}
	
}
