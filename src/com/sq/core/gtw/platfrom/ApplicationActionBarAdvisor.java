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
	//�˳�
	private IWorkbenchAction exitAction;
	//����
	private IWorkbenchAction aboutAction;
	//���ļ�
	private IAction openFileAction;
	//����
	private IWorkbenchAction copyAction;
	//����
	private IWorkbenchAction saveAction;
	//ճ��
	private IWorkbenchAction pasteAction;
	//����
	private IWorkbenchAction cutAction;
	//����
	private IWorkbenchAction redoAction;
	//����
	private IWorkbenchAction queryAction;
	//͸��ͼ
	private IContributionItem perspectivesMenu;
	//�����ֵ����
	private IAction dictMgnAction;
	//ͨѶ����
	private IAction connMgnAction;
	//���̹���
//	private IAction flowMgnAction;
	//���Ĺ���
	private IAction pkgMgnAction;
	//ҵ�����������
	private IAction bizDefinitionAction;
	//��ͼ
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
		exitAction.setText("�˳�");
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		aboutAction.setText("����");
		register(aboutAction);
		openFileAction = new OpenFileAction(window);
		openFileAction.setText("���ļ�");
		openFileAction.setId(OpenFileAction.ID);
		register(openFileAction);
		saveAction = ActionFactory.SAVE.create(window);
		saveAction.setText("����");
		register(saveAction);
		copyAction = ActionFactory.COPY.create(window);
		copyAction.setText("����");
		register(copyAction);
		pasteAction = ActionFactory.PASTE.create(window);
		pasteAction.setText("ճ��");
		register(pasteAction);
		cutAction = ActionFactory.CUT.create(window);
		cutAction.setText("����");
		register(cutAction);
		redoAction = ActionFactory.REDO.create(window);
		redoAction.setText("����");
		register(redoAction);
		queryAction = ActionFactory.FIND.create(window);
		queryAction.setText("����");
		register(queryAction);
		perspectivesMenu = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);
		dictMgnAction = new DictMgnAction(window);
		dictMgnAction.setText("�����ֵ����");
		dictMgnAction.setId(DictMgnAction.ID);
		register(dictMgnAction);
//		flowMgnAction = new FlowMgnAction(window);
//		flowMgnAction.setText("���̹���");
//		flowMgnAction.setId(FlowMgnAction.ID);
//		register(flowMgnAction);
		bizDefinitionAction = new BizDefinitionAction(window);
		bizDefinitionAction.setText("ҵ���߼��������");
		bizDefinitionAction.setId(BizDefinitionAction.ID);
		register(bizDefinitionAction);
		connMgnAction = new ConnMgnAction(window);
		connMgnAction.setText("ͨѶ����");
		connMgnAction.setId(ConnMgnAction.ID);
		register(connMgnAction);
		pkgMgnAction = new PkgMgnAction(window);
		pkgMgnAction.setText("���Ĺ���");
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
		MenuManager fileMenu = new MenuManager("�ļ�", "fileMenu");
		fileMenu.add(openFileAction);
		fileMenu.add(saveAction);
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);
		MenuManager editMenu = new MenuManager("�༭", "editMenu");
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		editMenu.add(cutAction);
		editMenu.add(redoAction);
		menuBar.add(editMenu);
		MenuManager queryMenu = new MenuManager("�鿴", "queryMenu");
		queryMenu.add(queryAction);
		menuBar.add(queryMenu);
		MenuManager layoutMenu = new MenuManager("͸��ͼ", "layoutMenu");
		layoutMenu.add(perspectivesMenu);
		menuBar.add(layoutMenu);
		MenuManager pubMenu = new MenuManager("����", "pubMenu");
		pubMenu.add(dictMgnAction);
//		pubMenu.add(flowMgnAction);
		pubMenu.add(connMgnAction);
		pubMenu.add(pkgMgnAction);
		pubMenu.add(bizDefinitionAction);
		menuBar.add(pubMenu);
		MenuManager windowMenu = new MenuManager("����", IWorkbenchActionConstants.M_WINDOW);
//        MenuManager showViewMenu = new MenuManager("��ͼ��ʾ",IWorkbenchActionConstants.SHOW_EXT);
        windowMenu.add(showviewAction);
        menuBar.add(windowMenu);
		MenuManager aboutMenu = new MenuManager("����", IWorkbenchActionConstants.ABOUT);
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
