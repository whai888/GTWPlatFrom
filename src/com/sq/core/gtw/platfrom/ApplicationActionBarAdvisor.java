package com.sq.core.gtw.platfrom;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.sq.core.gtw.actions.file.OpenFileAction;
import com.sq.core.gtw.actions.pub.BizDefinitionAction;
import com.sq.core.gtw.actions.pub.ConnMgnAction;
import com.sq.core.gtw.actions.pub.DictMgnAction;
import com.sq.core.gtw.actions.pub.PkgMgnAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	//退出
	private IWorkbenchAction exitAction;
	//关于
	private IWorkbenchAction aboutAction;
	//打开文件
	private IAction openFileAction;
	//复制
	private IWorkbenchAction copyAction;
	//保存
	private IWorkbenchAction saveAction;
	//粘贴
	private IWorkbenchAction pasteAction;
	//剪切
	private IWorkbenchAction cutAction;
	//撤销
	private IWorkbenchAction redoAction;
	//查找
	private IWorkbenchAction queryAction;
	//透视图
	private IContributionItem perspectivesMenu;
	//数据字典管理
	private IAction dictMgnAction;
	//通讯管理
	private IAction connMgnAction;
	//流程管理
//	private IAction flowMgnAction;
	//报文管理
	private IAction pkgMgnAction;
	//业务公用组件定义
	private IAction bizDefinitionAction;
	//视图
	private IContributionItem showviewAction ;

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		exitAction = ActionFactory.QUIT.create(window);
		exitAction.setText("退出");
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		aboutAction.setText("关于");
		register(aboutAction);
		openFileAction = new OpenFileAction(window);
		openFileAction.setText("打开文件");
		openFileAction.setId(OpenFileAction.ID);
		register(openFileAction);
		saveAction = ActionFactory.SAVE.create(window);
		saveAction.setText("保存");
		register(saveAction);
		copyAction = ActionFactory.COPY.create(window);
		copyAction.setText("复制");
		register(copyAction);
		pasteAction = ActionFactory.PASTE.create(window);
		pasteAction.setText("粘贴");
		register(pasteAction);
		cutAction = ActionFactory.CUT.create(window);
		cutAction.setText("剪切");
		register(cutAction);
		redoAction = ActionFactory.REDO.create(window);
		redoAction.setText("撤销");
		register(redoAction);
		queryAction = ActionFactory.FIND.create(window);
		queryAction.setText("查找");
		register(queryAction);
		perspectivesMenu = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);
		dictMgnAction = new DictMgnAction(window);
		dictMgnAction.setText("数据字典管理");
		dictMgnAction.setId(DictMgnAction.ID);
		register(dictMgnAction);
//		flowMgnAction = new FlowMgnAction(window);
//		flowMgnAction.setText("流程管理");
//		flowMgnAction.setId(FlowMgnAction.ID);
//		register(flowMgnAction);
		bizDefinitionAction = new BizDefinitionAction(window);
		bizDefinitionAction.setText("业务逻辑组件定义");
		bizDefinitionAction.setId(BizDefinitionAction.ID);
		register(bizDefinitionAction);
		connMgnAction = new ConnMgnAction(window);
		connMgnAction.setText("通讯管理");
		connMgnAction.setId(ConnMgnAction.ID);
		register(connMgnAction);
		pkgMgnAction = new PkgMgnAction(window);
		pkgMgnAction.setText("报文管理");
		pkgMgnAction.setId(PkgMgnAction.ID);
		register(pkgMgnAction);
		showviewAction = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
		
	}
	
	public void fillTrayItem(MenuManager trayMenu) {
		 trayMenu.add(aboutAction);
		 trayMenu.add(exitAction);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		MenuManager fileMenu = new MenuManager("文件", "fileMenu");
		fileMenu.add(openFileAction);
		fileMenu.add(saveAction);
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);
		MenuManager editMenu = new MenuManager("编辑", "editMenu");
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		editMenu.add(cutAction);
		editMenu.add(redoAction);
		menuBar.add(editMenu);
		MenuManager queryMenu = new MenuManager("查看", "queryMenu");
		queryMenu.add(queryAction);
		menuBar.add(queryMenu);
		MenuManager layoutMenu = new MenuManager("透视图", "layoutMenu");
		layoutMenu.add(perspectivesMenu);
		menuBar.add(layoutMenu);
		MenuManager pubMenu = new MenuManager("公用", "pubMenu");
		pubMenu.add(dictMgnAction);
//		pubMenu.add(flowMgnAction);
		pubMenu.add(connMgnAction);
		pubMenu.add(pkgMgnAction);
		pubMenu.add(bizDefinitionAction);
		menuBar.add(pubMenu);
		MenuManager windowMenu = new MenuManager("窗口", IWorkbenchActionConstants.M_WINDOW);
//        MenuManager showViewMenu = new MenuManager("视图显示",IWorkbenchActionConstants.SHOW_EXT);
        windowMenu.add(showviewAction);
        menuBar.add(windowMenu);
		MenuManager aboutMenu = new MenuManager("帮助", IWorkbenchActionConstants.ABOUT);
		aboutMenu.add(aboutAction);
		menuBar.add(aboutMenu);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		toolbar.add(openFileAction);
		toolbar.add(saveAction);
		toolbar.add(copyAction);
		toolbar.add(pasteAction);
		toolbar.add(cutAction);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));
	}


}
