package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//JDBC 드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
//(dbinfo.properties 파일을 이용하여 설정하기)

//방법2) ResourceBundle객체 이용하기
public class DBUtil3 {
	
	private DBUtil3() {
		
	}
	
   // ResourceBundle객체 변수 선언
   static ResourceBundle bundle;

   // 정적(static) 초기화 블럭
   static {
	   
	   
      bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo"); // 객체 생성

      try {
         //Class.forName("oracle.jdbc.driver.OracleDriver");
         Class.forName(bundle.getString("driver"));
          
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버 로딩 실패~~~");
         e.printStackTrace();
      }
   }

   public static Connection getConnection() {
      try {
//         return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "leejunseok", "java");
         return DriverManager.getConnection(
               bundle.getString("url"),
               bundle.getString("user"),
               bundle.getString("pass"));
      } catch (SQLException e) {
         System.out.println("오라클 연결 실패~~~");
         return null;
      }
   }
}