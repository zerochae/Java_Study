package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		
		System.out.println("계좌 번호 정보 추가하기");
		System.out.print("계좌번호 : ");
		String bankNo = scan.next();
		
		System.out.print("은 행 명 : ");
		String bankName = scan.next();
		
		System.out.print("예금주명 : ");
		String userName = scan.next();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kyc91","java");
			
			//String sql = "insert into bankinfo(bank_no,bank_name,bank_user_name,bank_date)"
			//		   + " values('"+bankNo+"','"+bankName+"','"+userName+"',sysdate)";
			
			stmt = conn.createStatement();
			
			// select문을 실행할 때는 executeQuery()메서드를 사용하고
			// insert, update, delete문과 같이 select문이 아닌 쿼리문을
			// 실행할 때는
			
		//	int cnt = stmt.executeUpdate(sql);
			
			// PreparedStatement객체를 이용한 처리
			
			// 쿼리문을 작성할 때 쿼리문에 데이터가 들어갈 자리를 물음표(?)로 표시
			String sql = "insert into bankinfo(bank_no,bank_name,bank_user_name,bank_date)"
					   + " values(?,?,?,sysdate)";
			//PreparedStatement객체 생성 => 이 때 처리할 쿼리문을 인수값을 넣어준다
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표 번호, 데이터값)
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3,userName);
			
			// 데이터의 셋팅이 완료되면 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 cnt = " + cnt);
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}
