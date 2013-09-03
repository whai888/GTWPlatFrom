package com.sq.core.gtw.edit.dialog;

import gtwplatfrom.Activator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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

import com.sq.core.gtw.edit.vo.FileVo;
import com.sq.core.gtw.util.ConstantUtil;
import com.sq.core.gtw.util.IImageKeys;
import com.sq.core.gtw.util.ReadProperties;

/**
 *@作者  whai 
 *@创建日期 2013-8-23
 *@版本 V1.0
 *@文件名 FlowMgnActionPropertiesViewDialog.java
 */
public class FlowMgnActionPropertiesViewDialog extends Dialog {
	private static String jspPath = ReadProperties.getSystemKey("JSP_PATH");
	private List<FileVo> fileList = new ArrayList<FileVo>();
	private TableViewer tableViewer ;
	private Table table;
	private String fileName = "" ;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FlowMgnActionPropertiesViewDialog(Shell parentShell) {
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
		
		text_1 = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());

		initDate();
		tableViewer.setInput(fileList);
		
		createListener();
		return container;
	}

	private void createListener() {
		// TODO Auto-generated method stub
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				fileList = new ArrayList<FileVo>();
				getFileNameList(jspPath,text.getText());
				Collections.sort(fileList);
				tableViewer.setInput(fileList);
				tableViewer.refresh();
			}
		});
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				StructuredSelection select = (StructuredSelection)tableViewer.getSelection();
				FileVo fileVo = (FileVo)select.getFirstElement();
				if(fileVo != null )
					text_1.setText(fileVo.getPath());
			}
			
		});
	}

	private void initDate() {
		// TODO Auto-generated method stub
		this.getFileNameList(jspPath , text.getText());
	}

	private void getFileNameList(String filePath,String textStr){
		File file = new File(filePath);
		File[] fileTemp = file.listFiles(new FileFilter(textStr));
		for (File file2 : fileTemp) {
			if(!file2.isFile()){
				getFileNameList(file2.getPath() , textStr);
			}else{
				//只显示.jsp结尾的文件
				if(file2.getName().endsWith(ConstantUtil.VIEW_SUFFIX[0])){
					FileVo fileVo = new FileVo();
					fileVo.setFileName(file2.getName());
					fileVo.setPath(file2.getPath().substring(jspPath.length()+1));
					fileVo.setImage(IImageKeys.ACTION_VIEW);
					fileList.add(fileVo);
				}
			}
		}
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
		FileVo fileVo = (FileVo)select.getFirstElement();
		fileName = fileVo.getPath() + File.separator + fileVo.getFileName();
		super.okPressed();
	}


	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			if(element instanceof FileVo){
				FileVo fileVo = (FileVo)element ;
				switch(columnIndex){
				case 0:
					return Activator.getImageDescriptor(fileVo.getImage()).createImage() ;
				}
			}
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof FileVo){
				FileVo fileVo = (FileVo)element ;
				switch(columnIndex){
				case 0:
					return fileVo.getFileName() ;
				}
			}
			return element.toString();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof List){
				return ((List)inputElement).toArray();
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
			//如果文件不是.jsp结尾的。返回true
			if(!name.endsWith(ConstantUtil.VIEW_SUFFIX[0])){
				return true ; 
			}
			if(name.indexOf(str) != -1 )
				return true ;
			return false;
		}
		
		
	}
	public String getFileName() {
		return fileName;
	}
}
