package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import com.sq.core.gtw.db.vo.Delete;
import com.sq.core.gtw.db.vo.Insert;
import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.db.vo.Select;
import com.sq.core.gtw.db.vo.Update;

public class DataMgnMasterDetailLabelProvider implements ILabelProvider {

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof MyBatisMain){
			MyBatisMain myBatisMain = (MyBatisMain)element ;
			return myBatisMain.getNamespace();
		}
		if(element instanceof Select){
			Select select = (Select)element ;
			return select.getId();
		}
		if(element instanceof Delete){
			Delete delete = (Delete)element ;
			return delete.getId();
		}
		if(element instanceof Update){
			Update update = (Update)element ;
			return update.getId();
		}
		if(element instanceof Insert){
			Insert insert = (Insert)element ;
			return insert.getId();
		}
		if(element instanceof String){
			String string = (String)element ;
			return string;
		}
		return null;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

}
