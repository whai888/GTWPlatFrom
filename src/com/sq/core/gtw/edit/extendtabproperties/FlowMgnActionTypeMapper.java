package com.sq.core.gtw.edit.extendtabproperties;

import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;

import com.sq.core.gtw.edit.controller.ActionContentsEditPart;
import com.sq.core.gtw.edit.controller.ActionEditorPart;
import com.sq.core.gtw.edit.controller.LineConnectionEditPart;
import com.sq.core.gtw.edit.model.IActionModelProperties;
import com.sq.core.gtw.edit.model.impl.ActionModel;

/**
 *@作者  whai 
 *@创建日期 2013-8-9
 *@版本 V1.0
 *@文件名 TypeMapper.java
 */
public class FlowMgnActionTypeMapper extends AbstractTypeMapper {
	@SuppressWarnings("unchecked")
	public Class mapType(Object object) {
        if (object instanceof ActionContentsEditPart) {
            return ((ActionContentsEditPart) object).getModel().getClass();
        }
        if (object instanceof LineConnectionEditPart) {
            return ((LineConnectionEditPart) object).getModel().getClass();
        }
        if (object instanceof ActionEditorPart) {
        	Object obj = ((ActionEditorPart) object).getModel() ;
        	if(obj instanceof ActionModel ){
        		IActionModelProperties iActionModelProperties = ((ActionModel)obj).getiActionModelProperties() ;
        		return iActionModelProperties.getClass();
//        		if(iActionModelProperties instanceof ActionModelPropertiesView)
//        			return ActionModelPropertiesView.class ;
//        		if(iActionModelProperties instanceof ActionModelPropertiesFlow)
//        			return ActionModelPropertiesFlow.class ;
//        		if(iActionModelProperties instanceof ActionModelPropertiesAction)
//        			return ActionModelPropertiesAction.class ;
        	}
            return ((ActionEditorPart) object).getModel().getClass();
        }
        return super.mapType(object);
    }
}
