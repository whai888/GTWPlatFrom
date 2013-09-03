package com.sq.core.gtw.db.mysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import com.sq.core.gtw.db.IMyDBConn;
import com.sq.core.gtw.db.vo.DataTableVo;
import com.sq.core.gtw.util.ReadPlugInfo;

/**
 * @���� whai
 * @�������� 2013-7-25
 * @�汾 V1.0
 * @�ļ��� MySqlConn.java
 */
public class IMySqlConnImp implements IMyDBConn {

	@SuppressWarnings("unused")
	private String url;
	@SuppressWarnings("unused")
	private String drive;
	@SuppressWarnings("unused")
	private String userName;
	@SuppressWarnings("unused")
	private String password;
	private Connection conn = null ;
	private Statement stmt = null ;
	private ResultSet rset = null ;
	private SqlSession session ;

	/**
	 * �������ݿ�������Ϣ
	 */
	@Override
	public void setConnInfo(String url, String driver, String userName,
			String pwd) {
		// TODO Auto-generated method stub
		//����������ݿ���Ϣ���浽�����ļ���
		InputStream is = null ;
		OutputStream out = null ;
		try {
			String fileName = ReadPlugInfo.getFilePath(IMySqlConnImp.class, "jdbc.properties");
			Properties prop = new Properties();
			is = new FileInputStream(fileName);
			prop.load(is);
			is.close();
			prop.setProperty("url", url);
			prop.setProperty("driver", driver);
			prop.setProperty("username", userName);
			prop.setProperty("password", pwd);
			out = new FileOutputStream(fileName);
			prop.store(out, "save jdbc");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(is != null)
					is.close();
				if(out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * ���ݿ�����
	 */
	public void connection(){
		//���ؼ�ע��JDBC��������
		String fileName = IMySqlConnImp.class.getResource("configuration.xml").getPath();
		try {
			Reader reader = Resources.getResourceAsReader(fileName);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),"������Ϣ","���ݿ�����ʧ��" + e.getMessage());
			return ;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DataTableVo> getTableInfo() {
		// TODO Auto-generated method stub
		if(conn == null)
			this.connection() ;
		List listTable = session.selectList("table.selectTable");
		return listTable;
	}

	/**
	 * �ر�JDBC������
	 */
	public void close(){
		try {
			if(rset != null)
				rset.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SqlSession getSession() {
		this.connection();
		return session;
	}

	public static void main(String[] args) {
			System.out.println(new File("./jdbc.properties").getPath());
	}
}
