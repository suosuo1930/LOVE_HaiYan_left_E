package aloneExampleDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn = null;
	static String user = "root";
	static String password = "666666";
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/test";

	public DBUtil() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("���� ���ݿ�����ʧ�ܣ�");
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("��ȡ����ʧ�ܣ�");
		}

	}

	public static Connection getconn() {
		if (conn == null) {
			new DBUtil();
			return conn;
		}
		return conn;
	}

	public void close() {
		if (conn == null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}