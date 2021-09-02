package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest03 {
	
	/*
	 
	 	문제) Lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력해랑
	 
	 */
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kyc91","java");
			System.out.println("숫자 입력1");
			int num1 = scan.nextInt();
			System.out.println("숫자 입력2");
			int num2 = scan.nextInt();
			
			//String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + num1 + " AND " + num2;
			String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN ? AND ?";
			
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num1);
			pstmt.setInt(2, num2);
			
			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
			System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
			System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
			System.out.println("LPROD_NM : " + rs.getString("LPROD_nm"));
			System.out.println("=======================================");
			
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}
