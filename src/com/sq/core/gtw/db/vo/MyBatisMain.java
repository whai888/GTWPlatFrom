package com.sq.core.gtw.db.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("mapper")
public class MyBatisMain {
	
	//将其作为属性
	@XStreamAsAttribute
	private String namespace ;
	
	//重新指定一个名字
	@XStreamImplicit(itemFieldName="select")
	private List<Select> select = new ArrayList<Select>();
	
	//重新指定一个名字
	@XStreamImplicit(itemFieldName="update")
	private List<Update> update = new ArrayList<Update>() ;
	
	//重新指定一个名字
	@XStreamImplicit(itemFieldName="delete")
	private List<Delete> delete = new ArrayList<Delete>() ;
	
	//重新指定一个名字
	@XStreamImplicit(itemFieldName="insert")
	private List<Insert> insert = new ArrayList<Insert>() ;
	
	@XStreamOmitField
	private List<String> listStr = new ArrayList<String>();
	
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public List<Select> getSelect() {
		if(select == null)
			select = new ArrayList<Select>();
		return select;
	}

	public void setSelect(List<Select> select) {
		this.select = select;
	}

	public List<Update> getUpdate() {
		if(update == null)
			update = new ArrayList<Update>();
		return update;
	}

	public void setUpdate(List<Update> update) {
		this.update = update;
	}

	public List<Delete> getDelete() {
		if(delete == null)
			delete = new ArrayList<Delete>();
		return delete;
	}

	public void setDelete(List<Delete> delete) {
		this.delete = delete;
	}
	
	public List<Insert> getInsert() {
		if(insert == null)
			insert = new ArrayList<Insert>();
		return insert;
	}

	public void setInsert(List<Insert> insert) {
		this.insert = insert;
	}

	public List<String> getListStr() {
		listStr = new ArrayList<String>();
		if(getSelect().size() > 0 )
			listStr.add("Select");
		if(getUpdate().size() > 0 )
			listStr.add("Update");
		if(getDelete().size() > 0 )
			listStr.add("Delete");
		if(getInsert().size() > 0 )
			listStr.add("Insert");
		return listStr;
	}

	/**
	 * 判断是否有子节点
	 * @return
	 */
	public boolean hasChildren(){
		if(this.getSelect().size() > 0 || this.getDelete().size() > 0 || this.getUpdate().size() > 0 || this.getInsert().size() > 0 )
			return true ;
		return false ;
	}
	
	public int getListCount(){
		return this.getSelect().size() + this.getDelete().size() + this.getUpdate().size() + this.getInsert().size() ;
	}
}
