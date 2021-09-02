package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

//JDBC 드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
//(dbinfo.properties 파일을 이용하여 설정하기)

//방법2) ResourceBundle객체 이용하기
public class DBUtil3 {
	
	static Logger logger = Logger.getLogger(DBUtil3.class);
	static ResourceBundle bundle; // ResourceBundle객체 변수 선언
	
   // 정적(static) 초기화 블럭
   static {
	   
      bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo"); // 객체 생성
      logger.info("ResourceBundle객체 생성 - dbinfo.properties파일 읽기");
      
      try {
         //Class.forName("oracle.jdbc.driver.OracleDriver");
    	 logger.info("DB드라이버 로딩 성공");
         Class.forName(bundle.getString("driver"));
          
      } catch (ClassNotFoundException e) {
         //System.out.println("드라이버 로딩 실패~~~");
    	 logger.error("드라이버 로딩 실패");
         e.printStackTrace();
      }
   }

   public static Connection getConnection() {
	   Connection conn = null;
      try {
//         return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "leejunseok", "java");
         conn = DriverManager.getConnection(
                bundle.getString("url"),
                bundle.getString("user"),
                bundle.getString("pass"));
         
         logger.info("DB서버에 연결 성공");
         
         return conn;
      } catch (SQLException e) {
    	 logger.error("DB서버 연결 실패");
        // System.out.println("오라클 연결 실패~~~");
         return null;
      }
   }
}