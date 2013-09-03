package com.sq.core.gtw.edit.labelcontent;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sq.core.gtw.db.vo.Delete;
import com.sq.core.gtw.db.vo.Insert;
import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.db.vo.Select;
import com.sq.core.gtw.db.vo.Update;

public class DataMgnMasterDetailContentProvider implements ITreeContentProvider {
	
	private MyBatisMain myBatisMain ;

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if(parentElement instanceof String){
			String dataStr = (String)parentElement;
			if(dataStr.equals("Select"))
				return myBatisMain.getSelect().toArray() ;
			if(dataStr.equals("Delete"))
				return myBatisMain.getDelete().toArray() ;
			if(dataStr.equals("Update"))
				return myBatisMain.getUpdate().toArray() ;
			if(dataStr.equals("Insert"))
				return myBatisMain.getInsert().toArray() ;
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof Select)
			return ((Select)element).hasChildren();
		if(element instanceof Update)
			return ((Update)element).hasChildren();
		if(element instanceof Delete)
			return ((Delete)element).hasChildren();
		if(element instanceof Insert)
			return ((Insert)element).hasChildren();
		
		return true ;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof MyBatisMain){
			myBatisMain = (MyBatisMain)inputElement ;
			return myBatisMain.getListStr().toArray();
		}
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
