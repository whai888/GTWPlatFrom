package com.sq.core.gtw.edit.dialog;

import gtwplatfrom.Activator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.ReadProperties;

/**
 *@作者  whai 
 *@创建日期 2012-8-23
 *@版本 V1.0
 *@文件名 FlowMgnActionPropertiesActionDialog.java
 */
public class FlowMgnActionPropertiesFlowDialog extends Dialog {
	private static String bizPath = ReadProperties.getSystemKey("BIZ_PATH");
	private String[] fileArray;
	private TableViewer tableViewer ;
	private Table table;
	private String fileName = "" ;
	private Text text;
	private File file ;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FlowMgnActionPropertiesFlowDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(370);
		tableColumn.setText("biz文件名");
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());

		initDate();
		tableViewer.setInput(fileArray);
		
		createListener();
		return container;
	}

	private void createListener() {
		// TODO Auto-generated method stub
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				FileFilter filter = new FileFilter(text.getText());
				fileArray = file.list(filter);
				Arrays.sort(fileArray);
				tableViewer.setInput(fileArray);
				tableViewer.refresh();
			}
		});
	}

	private void initDate() {
		// TODO Auto-generated method stub
		file = new File(bizPath);
		FileFilter filter = new FileFilter(text.getText());
		fileArray = file.list(filter);
		Arrays.sort(fileArray);
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(392, 510);
	}

	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		StructuredSelection select = (StructuredSelection)tableViewer.getSelection();
		fileName = (String)select.getFirstElement();
		super.okPressed();
	}


	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			if(element instanceof String)
				switch(columnIndex){
				case 0:
					return Activator.getImageDescriptor(IImageKeys.ACTION_FLOW).createImage();
				}
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof String){
				String fileName = (String)element ;
				switch(columnIndex){
				case 0:
					return fileName ;
				}
			}
			return element.toString();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof String[]){
				return ((String[])inputElement);
			}
			return null;
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private static class FileFilter implements FilenameFilter {
		private String str ;
		
		public FileFilter(String str) {
			super();
			this.str = str;
		}

		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			if(name != null)
			if(name.indexOf(str) != -1 )
				return true ;
			return false;
		}
		
		
	}
	public String getFileName() {
		return fileName;
	}
	
}
