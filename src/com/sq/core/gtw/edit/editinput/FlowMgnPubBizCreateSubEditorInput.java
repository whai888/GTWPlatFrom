package com.sq.core.gtw.edit.editinput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 *@����  whai 
 *@�������� 2013-8-18
 *@�汾 V1.0
 *@�ļ��� FlowMgnPubBizCreateSubEditorInput.java
 */
public class FlowMgnPubBizCreateSubEditorInput implements IEditorInput {
		private String bizName ;
		
		public FlowMgnPubBizCreateSubEditorInput(String bizName) {
			super();
			this.bizName = bizName;
		}

		@Override
		public boolean exists() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public ImageDescriptor getImageDescriptor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return bizName;
		}

		@Override
		public IPersistableElement getPersistable() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getToolTipText() {
			// TODO Auto-generated method stub
			return bizName;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object getAdapter(Class adapter) {
			// TODO Auto-generated method stub
			return null;
		}

		

}
