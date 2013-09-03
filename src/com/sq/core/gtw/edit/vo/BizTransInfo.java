package com.sq.core.gtw.edit.vo;

import java.util.ArrayList;
import java.util.List;

import com.sq.core.gtw.edit.model.impl.BizContentsModel;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @���� whai
 * @�������� 2013-7-15
 * @�汾 V1.0
 * @�ļ��� BizTransInfo.java
 */
@XStreamAlias("BizTransInfo")
public class BizTransInfo {
	
	private BizDatatransInfo transInfo = new BizDatatransInfo();
	
	private BizContentsModel bizContentsModel = new BizContentsModel();
	
	private List<BizServiceInfo> services = new ArrayList<BizServiceInfo>();
	
	private List<BizPackageInfo> packages = new ArrayList<BizPackageInfo>();
	
	//�Զ���ҵ���߼����
	private List<FlowBizPaletteInfo> definitionBiz = new ArrayList<FlowBizPaletteInfo>();

	public BizDatatransInfo getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(BizDatatransInfo transInfo) {
		this.transInfo = transInfo;
	}

	public List<BizServiceInfo> getServices() {
		if(services == null)
			services = new ArrayList<BizServiceInfo>();
		return services;
	}

	public void setServices(List<BizServiceInfo> services) {
		this.services = services;
	}

	public List<BizPackageInfo> getPackages() {
		if(packages == null )
			packages = new ArrayList<BizPackageInfo>();
		return packages;
	}

	public void setPackages(List<BizPackageInfo> packages) {
		this.packages = packages;
	}

	public BizContentsModel getBizContentsModel() {
		return bizContentsModel;
	}

	public void setBizContentsModel(BizContentsModel bizContentsModel) {
		this.bizContentsModel = bizContentsModel;
	}

	public List<FlowBizPaletteInfo> getDefinitionBiz() {
		return definitionBiz;
	}

	public void setDefinitionBiz(List<FlowBizPaletteInfo> definitionBiz) {
		this.definitionBiz = definitionBiz;
	}

}
