package com.sq.core.gtw.edit.form;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.sq.core.gtw.db.vo.Insert;
import com.sq.core.gtw.util.ConstantUtil;

public class DataMgnInsertDetailPage implements IDetailsPage {
	

	private DataMgnMasterDetailPage page ;
	private IManagedForm managedForm;
	private Insert insert ;
	private Text idtext;
	private Text parameterTypetext;
	private Text sqltext;
	private Combo flushCachecombo ;
	private Combo statementTypecombo ;
	private Text keyPropertytext ;
	private Text useGeneratedKeystext ;
	private Text timeouttext ;

	public DataMgnInsertDetailPage(FormPage page) {
		this.page = (DataMgnMasterDetailPage) page ;
	}
	
	/**
	 * Initialize the details page.
	 * @param form
	 */
	public void initialize(IManagedForm form) {
		this.managedForm = form;
	}

	/**
	 * Create contents of the details page.
	 * @param parent
	 */
	public void createContents(Composite parent) {
		 //��ñ������߶���
		FormToolkit toolkit = managedForm.getToolkit();
		//���ø������Ĳ���
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);
		Section section = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);;
		section.setText("Update����");
		Composite composite = toolkit.createComposite(section);
		toolkit.paintBordersFor(composite);
		//�����������������õ����
		section.setClient(composite);
	   //����Detail��������
	   TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
	   td.grabHorizontal = true;
	   section.setLayoutData(td);
	   GridLayout gridLayout = new GridLayout();
	   gridLayout.marginWidth = 0;
	   gridLayout.marginHeight = 0;
	   gridLayout.numColumns = 2;
	   gridLayout.horizontalSpacing=10;
	   composite.setLayout(gridLayout);
	   //����Detail���־���ĸ��ֿؼ�
	   Label idlabel = toolkit.createLabel(composite, "ID ��", SWT.NONE);
	   idlabel.setToolTipText("�������ռ���Ψһ�ı�ʶ�������Ա���������������䡣");
	   idlabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   idtext = toolkit.createText(composite, "", SWT.NONE);
	   idtext.setText("");
	   idtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label parameterTypelable = toolkit.createLabel(composite, "parameterType��", SWT.NONE);
	   parameterTypelable.setToolTipText("���ᴫ���������Ĳ��������ȫ�޶����������");
	   parameterTypelable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   parameterTypetext = toolkit.createText(composite, "", SWT.NONE);
	   parameterTypetext.setText("");
	   parameterTypetext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label flushCachelable = toolkit.createLabel(composite, "flushCache��", SWT.NONE);
	   flushCachelable.setToolTipText("��������Ϊtrue���������ʲôʱ�򱻵��ã����ᵼ�»��汻��ա�Ĭ��ֵ��false");
	   flushCachelable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   flushCachecombo = new Combo(composite, SWT.NONE);
	   flushCachecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   flushCachecombo.setItems(ConstantUtil.CONSTANT_BOOLEAN);
	   flushCachecombo.setText(ConstantUtil.CONSTANT_BOOLEAN[1]);
	   toolkit.adapt(flushCachecombo);
	   toolkit.paintBordersFor(flushCachecombo);
	   
	   Label statementTypelable = toolkit.createLabel(composite, "statementType��", SWT.NONE);
	   statementTypelable.setToolTipText("STATEMENT,PREPARED��CALLABLE��һ�֣������MyBatisʹ��ѡ��Statement,PreparedStatement��CallableStatement��Ĭ��ֵ��PREPARED��");
	   statementTypelable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   statementTypecombo = new Combo(composite, SWT.NONE);
	   statementTypecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   statementTypecombo.setItems(new String[]{"STATEMENT","PREPARED","CALLABLE"});
	   statementTypecombo.setText("PREPARED");
	   toolkit.adapt(statementTypecombo);
	   toolkit.paintBordersFor(statementTypecombo);
	   
	   Label keyPropertylable = toolkit.createLabel(composite, "keyProperty��", SWT.NONE);
	   keyPropertylable.setToolTipText("���һ�����ԣ�MyBatis��ͨ��getGeneratedKeys����ͨ��insert����selectKey��Ԫ����������ֵ��Ĭ�ϣ�������");
	   keyPropertylable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   keyPropertytext = toolkit.createText(composite, "", SWT.NONE);
	   keyPropertytext.setText("");
	   keyPropertytext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label useGeneratedKeyslable = toolkit.createLabel(composite, "useGeneratedKeys��", SWT.NONE);
	   useGeneratedKeyslable.setToolTipText("������MyBatisʹ��JDBC��getGeneratedKeys������ȡ��������(���磺��MySQL��SQL Server���������ݿ����ϵͳ���Զ������ֶ�)�ڲ����ɵ�������");
	   useGeneratedKeyslable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   useGeneratedKeystext = toolkit.createText(composite, "", SWT.NONE);
	   useGeneratedKeystext.setText("");
	   useGeneratedKeystext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label timeoutlable = toolkit.createLabel(composite, "timeout��", SWT.NONE);
	   timeoutlable.setToolTipText("���������������ȴ����ݿⷵ�������������׳��쳣ʱ������ȴ�ֵ��Ĭ�ϲ�����(�������д���)");
	   timeoutlable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   timeouttext = toolkit.createText(composite, "", SWT.NONE);
	   timeouttext.setText("");
	   timeouttext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label sqllable = toolkit.createLabel(composite, "SQL��", SWT.NONE);
	   sqllable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   sqltext = toolkit.createText(composite, "", SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
	   sqltext.setText("");
	   GridData gd_sqltext = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
	   gd_sqltext.heightHint = 100;
	   sqltext.setLayoutData(gd_sqltext);

	   textListener();
	}

	private void textListener() {
		// TODO Auto-generated method stub
		//�ı��޸��¼�
	   idtext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setId(idtext.getText());
			}
	   });
	   parameterTypetext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setParameterType(parameterTypetext.getText());
			}
	   });
	   flushCachecombo.addSelectionListener(new SelectionAdapter() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			page.setDirty(true);
			insert.setFlushCache(flushCachecombo.getText());
		}
		   
	   });
	   statementTypecombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setStatementType(statementTypecombo.getText());
			}
	   });
	   keyPropertytext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setKeyProperty(keyPropertytext.getText());
			}
	   });
	   useGeneratedKeystext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setUseGeneratedKeys(useGeneratedKeystext.getText());
			}
	   });
	   timeouttext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setTimeout(timeouttext.getText());
			}
	   });
	   sqltext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				insert.setSql(sqltext.getText());
			}
	   });
	}

	public void dispose() {
		// Dispose
	}

	public void setFocus() {
		// Set focus
	}

	private void update() {
		// Update
		idtext.setText(insert.getId());
		parameterTypetext.setText(insert.getParameterType());
		flushCachecombo.setText(insert.getFlushCache());
		statementTypecombo.setText(insert.getStatementType());
		keyPropertytext.setText(insert.getKeyProperty());
		useGeneratedKeystext.setText(insert.getUseGeneratedKeys());
		timeouttext.setText(insert.getTimeout());
		sqltext.setText(insert.getSql());
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	/**
	 * ��Master����ѡ���¼�����ʱ�����ô˷���
	 */
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if(structuredSelection.size() == 1 ){
			insert = (Insert)structuredSelection.getFirstElement();
			update();
		}
	}

	public void commit(boolean onSave) {
		// Commit
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
		update();
	}

}