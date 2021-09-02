package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest02 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("입력");
		int num = scan.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kyc91","java");
			
			//String sql = "select * from lprod"
			//		   + " where lprod_id > " + num;	
			
			String sql = "SELECT * FROM LPROD WHERE LPROD_ID > ?";
			
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("=======================================");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(stmt!=null)try {stmt.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
	}

}
