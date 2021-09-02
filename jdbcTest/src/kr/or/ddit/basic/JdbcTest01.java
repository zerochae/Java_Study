package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// JDBC(java DataBase Connectivity)라이브러리를 이용해서 DB자료를 처리하기
public class JdbcTest01 {
	
	/*
	 
	 	JDBC를 이용한 DB자료 처리 순서
	 	
	 	1. 드라이버 로딩 ==>  라이브러리를 사용할 수 있게 메모리에 읽어 들이는 작업
	 	Class.forName("orcle.jdbc.driver.OracleDriver");
	 	DBC버전 4이상에서는 getConnection()메서드에서 자동으로 로딩해기 때문에 생략할 수 있음
	 	(그렇지만 생략하지 않고 사용할 예정)
	 	
	 	2. DB에 접속하기 ==> 접속이 완료되면 Connection객체가 반환된다.
	 	DriverManager.getConnection()메서드를 이용한다.
	 	
	 	3. 질의 ==> SQL문장을 DB서버로 보내서 결과를 얻어온다.
	 	(Statement객체나 PreparedStatement객체를 이용하여 작업한다.)
	 	
	 	4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
	 		1) 실행한 SQL문이 select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환된다.
	 		2) 실행한 SQL문 select문이 아닐 경우에는(insert,update,delete)에는 정수값이 반환된다.
	 		   (이 정수 값은 보통 실행에 성공한 레코드 수를 의미한다.)
	 		   
	 	5. 사용한 자원을 반납한다. ==> close() 메서드를 이용한다.	   	
	 
	 */
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
			//DB작업에 필요한 객체 변수 선언
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
		
			try {
				
				// 1. 드라이버 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// 2. DB연결 ==> Connection 객체 생성
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kyc91","java");
				
				// 3. 질의
				// 3-1. SQL문 작성
				
				System.out.println("숫자 입력");
				String input = scan.next();
				
				String sql = "select lprod_id, lprod_gu, lprod_nm as nm from lprod where lprod_id >" + input;
				
				// 3-2 Statement객체 생성 ==> Connection객체를 이용해서 생성한다.
				stmt = conn.createStatement();
				
				// 3-3 작성한 SQL문을 DB서버로 보내서 결과를 얻어온다.
				//     (실행할 SQL문이 select문이기 때문에 결과가 
				//		ResultSet객체에 저장되어 반환된다.)
				rs = stmt.executeQuery(sql);
				
				
				System.out.println("=== 쿼리문 처리 결과 ===");
				// 4. 결과 처리 => 한 레코드씩 화면에 출력하기
				// ResultSet객체에서 데이터를 꺼내오려면 반복문과 next()메서드를 이용한다,
				
				//rs.next() ==> ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드 위치로 이동시키고, 
				//				그 곳에 데이터가 있으면 true, 없으면 false를 반환한다.
				
				
				
				while(rs.next()) {
					// 현재 포인터가 가리키는 곳의 자료를 가져오는 방법
					// 형식1) rs.get자료형이름("컬럼명")
					// 형식2) rs.get자료형이름(컬럼번호) ==> 컬럼번호는 1부터 시작
					// 형식3) rs.get자료형이름("컬럼의 alias명")
					
					System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_gu : " + rs.getString(2));
					System.out.println("Lprod_nm : " + rs.getString("nm"));
					System.out.println("=========================");
				}
				

				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				//	5. 자원 반납
				if(rs!=null)try {rs.close();} catch(SQLException e) {}
				if(stmt!=null)try {stmt.close();} catch(SQLException e) {}
				if(conn!=null)try {conn.close();} catch(SQLException e) {}
			}
	}

}
