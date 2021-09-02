package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	문제) LPROD테이블에 새로운 데이터를 추가하기를 해보자
 	
 		LPROD_GU와 LPROD_NM은 직접 입력받아서 처리하고,
 		LPROD_ID는 현재의 LPROD_ID중 제일 큰 값 보다 1만큼 크게 해서 처리한다.
 		그리고 입력받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리함
 */


public class JdbcTest05 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		System.out.print("LPROD_GU 입력 >> ");
		String LPROD_GU = scan.next();
		System.out.print("LPROD_NM 입력 >> ");
		String LPROD_NM = scan.next();
		
		try {
			
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//	conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kyc91","java");
			
			conn = DBUtil.getConnection();
		
			stmt = conn.createStatement();
			
			while(true) {
				String sql2 = "SELECT COUNT(*) FROM LPROD WHERE LPROD_GU ='" + LPROD_GU+"'";
				rs = stmt.executeQuery(sql2);
				int num = 1;
				while(rs.next()) {
					num = rs.getInt("COUNT(*)");
					}
				if(num == 1) {
					System.out.print("중복임. LPROD_GU 다시 입력 >> ");
					LPROD_GU = scan.next();
				} else {
					break;
				}
			}
			String sql = "INSERT INTO LPROD(LPROD_ID,LPROD_GU,LPROD_NM)"
					   + " VALUES((SELECT MAX(LPROD_ID) FROM LPROD)+1,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, LPROD_GU);
			pstmt.setString(2, LPROD_NM);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null ) try {conn.close();}catch(SQLException e) {}
		}
	}
	
}
