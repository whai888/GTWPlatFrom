package org.g4studio.system.admin.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;
import org.g4studio.core.web.BizAction;

import com.sq.core.gtw.edit.model.IActionModelProperties;
import com.sq.core.gtw.edit.model.impl.ActionConnectionModel;
import com.sq.core.gtw.edit.model.impl.ActionContentsModel;
import com.sq.core.gtw.edit.model.impl.ActionModel;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesAction;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesFlow;
import com.sq.core.gtw.edit.model.impl.ActionModelPropertiesView;
import com.sq.core.gtw.edit.vo.BizTransInfo;

/**
 *@作者  whai 
 *@创建日期 2013-8-28
 *@版本 V1.0
 *@文件名 GTWPlatFromAction.java
 */
public class GTWPlatFromAction extends BizAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		ServletContext servletContext = getServlet().getServletContext();
		Map<String , ActionContentsModel> actionConfigMap = (Map<String, ActionContentsModel>) servletContext.getAttribute("ACTIONCONFIGMAP");
		Map<String , BizTransInfo> bizConfigMap = (Map<String, BizTransInfo>) servletContext.getAttribute("BIZCONFIGMAP");
		String transCode = request.getParameter("TRANS_CODE");
		String actionUrl = request.getParameter("ACTION_URL");
		for (String mapKey : actionConfigMap.keySet()) {
			if(transCode.equals(mapKey)){
				ActionContentsModel actionContentsModel = actionConfigMap.get(mapKey);
				List<ActionModel> actionModelList = actionContentsModel.getChildren();
				for (ActionModel actionModel : actionModelList) {
					if(actionModel.getText().equals("ACTION")){
						ActionModel bizAction = getActionModel(actionModel, new ActionModelPropertiesFlow());
					}
					if(actionModel.getText().equals("BIZ")){
						
					}
					if(actionModel.getText().equals("VIEW")){
						mapping.setForward(actionModel.getiActionModelProperties().getJsp());
					}
				}
			}
		}
		return mapping.findForward(forwardName);
	}

	/**
	 * 根据给定的actionModel获得下一步需要执行的actionModel
	 * @param actionModel
	 * @return
	 */
	public ActionModel getActionModel(ActionModel actionModel , IActionModelProperties iActionModelProperties){
		ActionModel model = null ;
		if(iActionModelProperties instanceof ActionModelPropertiesAction){
			List targetConnList = actionModel.getModelTargetConnections();
			for (Object object : targetConnList) {
				ActionConnectionModel actionConnectionModel = (ActionConnectionModel)object;
				model = actionConnectionModel.getTarget();
			}
		}
		if(iActionModelProperties instanceof ActionModelPropertiesFlow){
			List targetConnList = actionModel.getModelTargetConnections();
			for (Object object : targetConnList) {
				ActionConnectionModel actionConnectionModel = (ActionConnectionModel)object;
				model = actionConnectionModel.getTarget();
			}
		}
		if(iActionModelProperties instanceof ActionModelPropertiesView){
			List targetConnList = actionModel.getModelTargetConnections();
			for (Object object : targetConnList) {
				ActionConnectionModel actionConnectionModel = (ActionConnectionModel)object;
				model = actionConnectionModel.getTarget();
			}
		}
		return model ;
	}
}
