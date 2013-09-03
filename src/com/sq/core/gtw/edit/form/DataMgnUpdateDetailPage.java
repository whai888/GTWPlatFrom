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

import com.sq.core.gtw.db.vo.Update;
import com.sq.core.gtw.util.ConstantUtil;

public class DataMgnUpdateDetailPage implements IDetailsPage {
	

	private DataMgnMasterDetailPage page ;
	private IManagedForm managedForm;
	private Update update ;
	private Text idtext;
	private Text parameterTypetext;
	private Combo flushCachecombo ;
	private Combo statementTypecombo ;
	private Text timeouttext ;
	private Text sqltext;

	public DataMgnUpdateDetailPage(FormPage page) {
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
		 //获得表单工具对象
		FormToolkit toolkit = managedForm.getToolkit();
		//设置父类面板的布局
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);
		Section section = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);;
		section.setText("Update详情");
		Composite composite = toolkit.createComposite(section);
		toolkit.paintBordersFor(composite);
		//创建内容区的所设置的面板
		section.setClient(composite);
	   //创建Detail的内容区
	   TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
	   td.grabHorizontal = true;
	   section.setLayoutData(td);
	   GridLayout gridLayout = new GridLayout();
	   gridLayout.marginWidth = 0;
	   gridLayout.marginHeight = 0;
	   gridLayout.numColumns = 2;
	   gridLayout.horizontalSpacing=10;
	   composite.setLayout(gridLayout);
	   //创建Detail部分具体的各种控件
	   Label idlabel = toolkit.createLabel(composite, "ID ：", SWT.NONE);
	   idlabel.setToolTipText("在命名空间中唯一的标识符，可以被用来引用这条语句。");
	   idlabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   idtext = toolkit.createText(composite, "", SWT.NONE);
	   idtext.setText("");
	   idtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label parameterTypelable = toolkit.createLabel(composite, "parameterType：", SWT.NONE);
	   parameterTypelable.setToolTipText("将会传入这条语句的参数类的完全限定名或别名。");
	   parameterTypelable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   parameterTypetext = toolkit.createText(composite, "", SWT.NONE);
	   parameterTypetext.setText("");
	   parameterTypetext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label flushCachelable = toolkit.createLabel(composite, "flushCache：", SWT.NONE);
	   flushCachelable.setToolTipText("将其设置为true，无论语句什么时候被调用，都会导致缓存被清空。默认值：false");
	   flushCachelable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   flushCachecombo = new Combo(composite, SWT.NONE);
	   flushCachecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   flushCachecombo.setItems(ConstantUtil.CONSTANT_BOOLEAN);
	   flushCachecombo.setText(ConstantUtil.CONSTANT_BOOLEAN[1]);
	   toolkit.adapt(flushCachecombo);
	   toolkit.paintBordersFor(flushCachecombo);
	   
	   Label statementTypelable = toolkit.createLabel(composite, "statementType：", SWT.NONE);
	   statementTypelable.setToolTipText("STATEMENT,PREPARED或CALLABLE的一种，这回让MyBatis使用选择Statement,PreparedStatement或CallableStatement。默认值：PREPARED。");
	   statementTypelable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   statementTypecombo = new Combo(composite, SWT.NONE);
	   statementTypecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   statementTypecombo.setItems(new String[]{"STATEMENT","PREPARED","CALLABLE"});
	   statementTypecombo.setText("PREPARED");
	   toolkit.adapt(statementTypecombo);
	   toolkit.paintBordersFor(statementTypecombo);
	   
	   Label timeoutlable = toolkit.createLabel(composite, "timeout：", SWT.NONE);
	   timeoutlable.setToolTipText("这个设置驱动程序等待数据库返回请求结果，并抛出异常时间的最大等待值。默认不设置(驱动自行处理)");
	   timeoutlable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   
	   timeouttext = toolkit.createText(composite, "", SWT.NONE);
	   timeouttext.setText("");
	   timeouttext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	   Label sqllable = toolkit.createLabel(composite, "SQL：", SWT.NONE);
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
		//文本修改事件
	   idtext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				update.setId(idtext.getText());
			}
	   });
	   parameterTypetext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				update.setParameterType(parameterTypetext.getText());
			}
	   });
	   flushCachecombo.addSelectionListener(new SelectionAdapter() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			page.setDirty(true);
			update.setFlushCache(flushCachecombo.getText());
		}
		   
	   });
	   statementTypecombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				update.setStatementType(statementTypecombo.getText());
			}
	   });
	   timeouttext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				update.setTimeout(timeouttext.getText());
			}
	   });
	   sqltext.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				page.setDirty(true);
				update.setSql(sqltext.getText());
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
		idtext.setText(update.getId());
		parameterTypetext.setText(update.getParameterType());
		flushCachecombo.setText(update.getFlushCache());
		statementTypecombo.setText(update.getStatementType());
		timeouttext.setText(update.getTimeout());
		sqltext.setText(update.getSql());
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	/**
	 * 当Master区域选中事件发生时，调用此方法
	 */
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if(structuredSelection.size() == 1 ){
			update = (Update)structuredSelection.getFirstElement();
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
