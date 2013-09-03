package com.sq.core.gtw.view.dialog;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.sq.core.gtw.db.IDateCreateFile;
import com.sq.core.gtw.db.mysql.IDateCreateFileImp;

/**
 * @���� whai
 * @�������� 2013-7-24
 * @�汾 V1.0
 * @�ļ��� DataGenerateScriptWizard.java
 */
public class DataGenerateScriptWizard extends Wizard implements INewWizard{

	private DataGenerateScriptOneWizardPage one = null;
	private DataGenerateScriptTwoWizardPage two = null;

	public DataGenerateScriptWizard() {
		setWindowTitle("�ű�������");
	}

	@Override
	public void addPages() {
		one = new DataGenerateScriptOneWizardPage();
		two = new DataGenerateScriptTwoWizardPage(one);
		this.addPage(one);
		this.addPage(two);
		this.setWindowTitle("���ݿ�ű�������");
	}

	/**
	 * ִ����ɲ���
	 */
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
//		ProgressMonitorDialog progress = new ProgressMonitorDialog(this.getShell());
//        try {
//        	IRunnableWithProgress runnable = new IRunnableWithProgress() {
//                public void run(IProgressMonitor monitor) throws InvocationTargetException,    InterruptedException {
//                    monitor.beginTask("�����������ݿ�ű�", IProgressMonitor.UNKNOWN);
//                    monitor.setTaskName("�����������ݿ�ű�");
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
		//���ݻ�õı������ɽű�
		IDateCreateFile iDateCreateFile = new IDateCreateFileImp();
		iDateCreateFile.initDateFile(two);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
