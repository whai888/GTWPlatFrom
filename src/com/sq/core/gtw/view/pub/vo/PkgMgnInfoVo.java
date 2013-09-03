package com.sq.core.gtw.view.pub.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 whai
 * @创建日期 2013-7-2
 * @版本 V1.0
 * @文件名 PkgMgnInfoVo.java
 */
public class PkgMgnInfoVo implements PkgMgnTreeElement {
	private String name ;
	@SuppressWarnings("unchecked")
	private List lists ;	//	所有子孙

	
	@SuppressWarnings("unchecked")
	public PkgMgnInfoVo(String name) {
		this.name = name;
		lists = new ArrayList();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		if(lists.size() > 0 )
			return true ;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getChildren() {
		// TODO Auto-generated method stub
		return lists;
	}

	/**
	 * 添加子节点
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void add(PkgMgnTreeElement element){
		lists.add(element);
	}
}
