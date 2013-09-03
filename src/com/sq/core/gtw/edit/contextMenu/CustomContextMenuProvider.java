package com.sq.core.gtw.edit.contextMenu;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionFactory;

/**
 *@作者  whai 
 *@创建日期 2013-8-19
 *@版本 V1.0
 *@文件名 CustomContextMenuProvider.java
 */
public class CustomContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry registry;

    public void setActionRegistry(ActionRegistry registry) {
            this.registry = registry;
    }

    public ActionRegistry getActionRegistry() {
            return registry;
    }

    public CustomContextMenuProvider(EditPartViewer viewer,
                    ActionRegistry registry) {
            super(viewer);
            setActionRegistry(registry);
    }

    @Override
    public void buildContextMenu(IMenuManager menu) {
            GEFActionConstants.addStandardActionGroups(menu);
//            menu.add(registry.getAction(ActionFactory.REDO.getId()));
//            menu.add(registry.getAction(ActionFactory.UNDO.getId()));
            menu.add(registry.getAction(ActionFactory.DELETE.getId()));
            menu.add(registry.getAction(GEFActionConstants.ZOOM_IN));
            menu.add(registry.getAction(GEFActionConstants.ZOOM_OUT));
    }

	@Override
	public Menu createMenuBar(Decorations parent) {
		// TODO Auto-generated method stub
		return super.createMenuBar(parent);
	}

	
}

