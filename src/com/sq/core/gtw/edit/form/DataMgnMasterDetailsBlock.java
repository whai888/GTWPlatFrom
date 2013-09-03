package com.sq.core.gtw.edit.form;

import gtwplatfrom.Activator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.sq.core.gtw.db.vo.Delete;
import com.sq.core.gtw.db.vo.Insert;
import com.sq.core.gtw.db.vo.MyBatisMain;
import com.sq.core.gtw.db.vo.Select;
import com.sq.core.gtw.db.vo.Update;
import com.sq.core.gtw.edit.labelcontent.DataMgnMasterDetailContentProvider;
import com.sq.core.gtw.edit.labelcontent.DataMgnMasterDetailLabelProvider;
import com.sq.core.gtw.util.IImageKeys;

public class DataMgnMasterDetailsBlock extends MasterDetailsBlock {

	@SuppressWarnings("unused")
	private IManagedForm managedForm ;
	private SectionPart spart ;
	private FormToolkit toolkit;
	private DataMgnMasterDetailPage page ;
	private MyBatisMain myBatisMain ;
	private Tree tree ;
	private TreeViewer viewer ;
	private Action createAction ;
	private Action deleteAction ;

	/**
	 * Create the master details block.
	 */
	public DataMgnMasterDetailsBlock(FormPage page , MyBatisMain myBatisMain) {
		// Create the master details block
		this.page = (DataMgnMasterDetailPage) page ;
		this.myBatisMain = myBatisMain ;
	}

	/**
	 * Create contents of the master details block.
	 * @param managedForm
	 * @param parent
	 */
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
		this.managedForm = managedForm ;
		toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText("���ݿ�ű�");
		section.marginHeight=5;
		section.marginWidth = 10;
		//���������������
		Composite composite = toolkit.createComposite(section, SWT.NONE);
		//���Ƹ����ı߿�����ķ��һ��
		toolkit.paintBordersFor(composite);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 100 ;
		composite.setLayout(gridLayout);
		
		tree = toolkit.createTree(section, SWT.BORDER | SWT.FULL_SELECTION);
		section.setClient(tree);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolkit.paintBordersFor(tree);
		
		spart = new SectionPart(section);
		//ע��ö���IManagedForm����������
		managedForm.addPart(spart);
		//����ͨ������װ��MVC����
		viewer = new TreeViewer(tree);
		
		//ע������ѡ���¼�������
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			//����������ĳһ���ڵ�ʱ
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				//ͨ��IManagedForm������IFormPart����Ӧ���¼�
				hookContextMenu();
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});
		//������������
		viewer.setContentProvider(new DataMgnMasterDetailContentProvider());
		//�������ı�ǩ
		viewer.setLabelProvider(new DataMgnMasterDetailLabelProvider());
		//���ó�ʼ���������
		viewer.setInput(myBatisMain);
		
		createActions();
	}

	/**
	 * ע����ϸҳ�沿��
	 * @param part
	 */
	@Override
	protected void registerPages(DetailsPart part) {
		// Register the pages
		part.registerPage(String.class, new DataMgnStringDetailPage());
		part.registerPage(Select.class, new DataMgnSelectDetailPage(page));
		part.registerPage(Delete.class, new DataMgnDeleteDetailPage(page));
		part.registerPage(Update.class, new DataMgnUpdateDetailPage(page));
		part.registerPage(Insert.class, new DataMgnInsertDetailPage(page));
		
	}

	/**
	 * ����������Action
	 * @param managedForm
	 */
	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		// Create the toolbar actions
		final ScrolledForm form = managedForm.getForm();
		//ˮƽ���ֲ���
		Action hAction = new Action("ˮƽ" , Action.AS_RADIO_BUTTON){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
			
		};
		hAction.setChecked(true);
		hAction.setToolTipText("ˮƽ����");
//		hAction.setImageDescriptor(newImage);
		//��ֱ���ֲ���
		Action vAction = new Action("��ֱ" , Action.AS_RADIO_BUTTON){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
			
		};
		vAction.setChecked(false);
		vAction.setToolTipText("��ֱ����");
//		vAction.setImageDescriptor(newImage);
		form.getToolBarManager().add(hAction);
		form.getToolBarManager().add(vAction);
	}
	
	private void createActions() {
		//����Select
		createAction = new Action() {
			public void run() {
				// TODO Auto-generated method stub
				Object obj = getSelectElement() ;
				if(obj instanceof String ){
					String strName = (String)obj;
					//����Select
					if(strName.equals("Select")){
						Select select = new Select();
						select.setId("newSelect_1");
						List<Select> selectList = myBatisMain.getSelect();
						selectList.add(select);
					}
					//����Update
					if(strName.equals("Update")){
						Update update = new Update();
						update.setId("newUpdate_1");
						List<Update> updateList = myBatisMain.getUpdate();
						updateList.add(update);
					}
					//����Delete
					if(strName.equals("Delete")){
						Delete delete = new Delete();
						delete.setId("newDelete_1");
						List<Delete> deleteList = myBatisMain.getDelete();
						deleteList.add(delete);
					}
					//����Insert
					if(strName.equals("Insert")){
						Insert insert = new Insert();
						insert.setId("newInsert_1");
						List<Insert> insertList = myBatisMain.getInsert();
						insertList.add(insert);
					}
				}
				reflush();
				page.setDirty(true);
			}
		};
		createAction.setText("����");
		createAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.ADD_ATT_1));
		//ɾ��
		deleteAction = new Action(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Object obj = getSelectElement() ;
				//ɾ��Select
				if(obj instanceof Select ){
					Select select = (Select)obj;
					myBatisMain.getSelect().remove(select);
				}
				//ɾ��Update
				if(obj instanceof Update ){
					Update update = (Update)obj;
					myBatisMain.getUpdate().remove(update);
				}
				//ɾ��Delete
				if(obj instanceof Delete ){
					Delete delete = (Delete)obj;
					myBatisMain.getDelete().remove(delete);
				}
				//ɾ��Insert
				if(obj instanceof Insert ){
					Insert insert = (Insert)obj;
					myBatisMain.getInsert().remove(insert);
				}
				if(obj instanceof String ){
					String strName = (String)obj;
					//ɾ��Select
					if(strName.equals("Select")){
						myBatisMain.setSelect(new ArrayList<Select>());
					}
					//ɾ��Update
					if(strName.equals("Update")){
						myBatisMain.setUpdate(new ArrayList<Update>());
					}
					//ɾ��Delete
					if(strName.equals("Delete")){
						myBatisMain.setDelete(new ArrayList<Delete>());
					}
					//ɾ��Insert
					if(strName.equals("Insert")){
						myBatisMain.setInsert(new ArrayList<Insert>());
					}
				}
				reflush();
				page.setDirty(true);
			}
			
		};
		deleteAction.setText("ɾ��");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IImageKeys.DELETE_1));
	}
	
	/**
	 * �����Ĳ˵�
	 */
	private void hookContextMenu() {
		// TODO Auto-generated method stub
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		Object obj = getSelectElement() ;
		if(obj instanceof String ){
			menuMgr.add(createAction);
			menuMgr.add(deleteAction);
		}else if(obj instanceof Select ){
			menuMgr.add(createAction);
			menuMgr.add(deleteAction);
		}else if(obj instanceof Update ){
			menuMgr.add(createAction);
			menuMgr.add(deleteAction);
		}else if(obj instanceof Delete ){
			menuMgr.add(createAction);
			menuMgr.add(deleteAction);
		}else if(obj instanceof Insert ){
			menuMgr.add(createAction);
			menuMgr.add(deleteAction);
		}
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
	}
	
	/**
	 * �������ǰѡ�еĽڵ�
	 * @return
	 */
	private Object getSelectElement(){
		StructuredSelection select = (StructuredSelection)viewer.getSelection();
		Object obj = select.getFirstElement();
		return obj ;
	}

	public MyBatisMain getMyBatisMain() {
		return myBatisMain;
	}

	public void reflush(){
		viewer.setInput(myBatisMain);
		viewer.refresh();
	}
}
