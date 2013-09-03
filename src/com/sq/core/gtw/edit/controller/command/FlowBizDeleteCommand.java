package com.sq.core.gtw.edit.controller.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.sq.core.gtw.edit.model.IConnectionModel;
import com.sq.core.gtw.edit.model.IContentsModel;
import com.sq.core.gtw.edit.model.IFlowModel;

/**
 * @���� whai
 * @�������� 2013-7-5
 * @�汾 V1.0
 * @�ļ��� FlowBizDeleteCommand.java
 */
public class FlowBizDeleteCommand extends Command {
	
	private IContentsModel contentsModel;
	private IFlowModel flowModel;
	
	//ɾ��ģ��ʱ��ҲӦ��ɾ����ص�����
	@SuppressWarnings("unchecked")
	private List sourceConnections = new ArrayList();
	@SuppressWarnings("unchecked")
	private List targetConnections = new ArrayList();
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//��ɾ��һ��ģ�Ͷ���ǰ���������ģ�Ͷ�����Ϊ�����յ����������
		sourceConnections.addAll(flowModel.getModelSourceConnections());
		targetConnections.addAll(flowModel.getModelTargetConnections());
		//ɾ����ģ�Ͷ����Ӧ��source
		for (int i = 0; i < sourceConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel) sourceConnections.get(i);
			model.detachSource();
			model.detachTaret();
		}
		//ɾ����ģ�Ͷ����Ӧ��target
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
		//����ʱ��Ҫ��Ӹ�ģ�Ͷ����Ӧ��source
		for (int i = 0; i < sourceConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel)sourceConnections.get(i);
			model.attachSource();
			model.attachTarget();
		}
		//����ʱ��Ҫɾ����ģ�Ͷ����Ӧ��target
		for (int i = 0; i < targetConnections.size(); i++) {
			IConnectionModel model = (IConnectionModel)targetConnections.get(i);
			model.attachSource();
			model.attachTarget();
		}
		//�����¼
		sourceConnections.clear();
		targetConnections.clear();
		
		contentsModel.addChildren(flowModel);
	}
	
}
