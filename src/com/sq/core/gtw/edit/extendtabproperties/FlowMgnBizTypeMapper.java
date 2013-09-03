package com.sq.core.gtw.edit.extendtabproperties;

import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;

import com.sq.core.gtw.edit.controller.BizContentsEditPart;
import com.sq.core.gtw.edit.controller.BizEditorPart;
import com.sq.core.gtw.edit.controller.LineConnectionEditPart;
import com.sq.core.gtw.edit.model.IFlowModelProperties;
import com.sq.core.gtw.edit.model.impl.BizModel;

/**
 *@作者  whai 
 *@创建日期 2013-8-14
 *@版本 V1.0
 *@文件名 FlowMgnFlowTypeMapper.java
 */
public class FlowMgnBizTypeMapper extends AbstractTypeMapper {
	@SuppressWarnings("unchecked")
	public Class mapType(Object object) {
        if (object instanceof BizContentsEditPart) {
            return ((BizContentsEditPart) object).getModel().getClass();
        }
        if(object instanceof LineConnectionEditPart){
        	return ((LineConnectionEditPart) object).getModel().getClass();
        }
        if (object instanceof BizEditorPart) {
        	Object obj = ((BizEditorPart) object).getModel() ;
        	if(obj instanceof BizModel ){
        		IFlowModelProperties iFlowModelProperties = ((BizModel)obj).getiFlowModelProperties() ;
        		return iFlowModelProperties.getClass();
//        		if(iFlowModelProperties instanceof BizModelPropertiesSql)
//        			return BizModelPropertiesSql.class ;
//        		if(iFlowModelProperties instanceof BizModelPropertiesFtp)
//        			return BizModelPropertiesFtp.class;
//        		if(iFlowModelProperties instanceof BizModelPropertiesSystem)
//        			return BizModelPropertiesSystem.class;
        	}
            return object.getClass();
        }
        return super.mapType(object);
    }
}
