package com.sq.core.gtw.platfrom;

import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.sq.core.gtw.perspectives.TransPerspective;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = TransPerspective.ID;

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		//»√view±Í«©œ‘ æ≤®¿À
		 PlatformUI.getPreferenceStore().setValue(  
	                IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS,  
	                false);  
		return PERSPECTIVE_ID;
	}

}
