package com.sq.core.gtw.view.dialog;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.sq.core.gtw.db.IDateCreateFile;
import com.sq.core.gtw.db.mysql.IDateCreateFileImp;

/**
 * @作者 whai
 * @创建日期 2013-7-24
 * @版本 V1.0
 * @文件名 DataGenerateScriptWizard.java
 */
public class DataGenerateScriptWizard extends Wizard implements INewWizard{

	private DataGenerateScriptOneWizardPage one = null;
	private DataGenerateScriptTwoWizardPage two = null;

	public DataGenerateScriptWizard() {
		setWindowTitle("脚本生成向导");
	}

	@Override
	public void addPages() {
		one = new DataGenerateScriptOneWizardPage();
		two = new DataGenerateScriptTwoWizardPage(one);
		this.addPage(one);
		this.addPage(two);
		this.setWindowTitle("数据库脚本生成向导");
	}

	/**
	 * 执行完成操作
	 */
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
//		ProgressMonitorDialog progress = new ProgressMonitorDialog(this.getShell());
//        try {
//        	IRunnableWithProgress runnable = new IRunnableWithProgress() {
//                public void run(IProgressMonitor monitor) throws InvocationTargetException,    InterruptedException {
//                    monitor.beginTask("正在生成数据库脚本", IProgressMonitor.UNKNOWN);
//                    monitor.setTaskName("正在生成数据库脚本");
                    doFinish();
//                    monitor.done();
//                } 
//            };
//            progress.run(true, false, runnable );
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		return true;
	}

	private void doFinish() {
		// TODO Auto-generated method stub
		//根据获得的表名生成脚本
		IDateCreateFile iDateCreateFile = new IDateCreateFileImp();
		iDateCreateFile.initDateFile(two);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
