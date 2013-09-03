package com.sq.core.gtw.edit.vo;

import java.util.ArrayList;
import java.util.List;

import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.service.model.IBizModelService;
import com.sq.core.gtw.edit.vo.imp.system.BizDefinitionVo;

/**
 *@���� whai
 *@�������� 2013-8-14
 *@�汾 V1.0
 *@�ļ��� FlowBizPaletteInfo.java
 */
public class FlowBizPaletteInfo {

	private String image = "";

	private String contentName = "";

	private String toolTipTextname = "";

	private String descript = "";

	private List<BizDefinitionVo> bizDefinitionVoList = new ArrayList<BizDefinitionVo>();

	// ��Ӧģ�͵�����
	private IFlowModelProperties iFlowModelProperties;

	// ��Ӧģ�͵�ʵ����
	private IBizModelService iBizModelService;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getToolTipTextname() {
		return toolTipTextname;
	}

	public void setToolTipTextname(String toolTipTextname) {
		this.toolTipTextname = toolTipTextname;
	}

	public IFlowModelProperties getiFlowModelProperties() {
		return iFlowModelProperties;
	}

	public void setiFlowModelProperties(
			IFlowModelProperties iFlowModelProperties) {
		this.iFlowModelProperties = iFlowModelProperties;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<BizDefinitionVo> getBizDefinitionVoList() {
		if (bizDefinitionVoList == null)
			bizDefinitionVoList = new ArrayList<BizDefinitionVo>();
		return bizDefinitionVoList;
	}

	public void setBizDefinitionVoList(List<BizDefinitionVo> bizDefinitionVoList) {
		this.bizDefinitionVoList = bizDefinitionVoList;
	}

	public IBizModelService getiBizModelService() {
		return iBizModelService;
	}

	public void setiBizModelService(IBizModelService iBizModelService) {
		this.iBizModelService = iBizModelService;
	}

}
