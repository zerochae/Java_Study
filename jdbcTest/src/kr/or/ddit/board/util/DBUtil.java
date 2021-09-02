package kr.or.ddit.board.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DBUtil {

	private DBUtil() {}
	
	static ResourceBundle bundle;
	
	static {
		
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		try {
			
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 없서용");
			e.printStackTrace();
		}
	}
	
	public static Connection getConncetion() {
		try {
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass")
					);
					
		} catch (Exception e) {
			System.out.println("오라클 연결 실패함 ㅋ");
			return null;
		}
	}
}
