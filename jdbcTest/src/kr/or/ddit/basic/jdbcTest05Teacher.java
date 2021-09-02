package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class jdbcTest05Teacher {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {

			//Class.forName("oracle.jdbc.driver.OracleDriver");

			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kyc91", "java");
			conn = DBUtil.getConnection();
			
			// LPROD_ID는 현재의 LPROD_ID중 제일 큰 값보다 1만큼 크게 해서 처리한다.
			String sql = "SELECT NVL(MAX(LPROD_ID),0)+1 FROM LPROD";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int maxNum = 1;
			if (rs.next()) { // SELECT한 결과가 1개의 레코드일 경우에는 IF문으로 처리가능
				maxNum = rs.getInt("NVL(MAX(LPROD_ID),0)+1");
			}

			// 입력받은 LPROD_GU가 이미 등록됭 ㅓ있으면 다시 입력 받아서 처리한다.
			String gu; // 상품 분류 코드(LPROD_GU)가 저장될 변수 선언
			int count = 0; // 입력한 상품 분류 코드의 개수가 저장될 변수

			String sql2 = "SELECT COUNT(*) CNT FROM LPROD WHERE LPROD_GU = ?";
			pstmt1 = conn.prepareStatement(sql2);

			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = scan.next();

				pstmt1.setString(1, gu);

				rs = pstmt1.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}

				if (count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 존재합니다.");
					System.out.println("다시 입력해주세요.");
				}
			} while (count > 0);
			
			System.out.print("상품 분류명(LPROD_NM) 을 입력 : ");
			String nm = scan.next();
			
			String sql3 = "INSERT INTO LPROD(LPROD_ID,LPROD_GU,LPROD_NM)"
						+ " VALUES(?,?,?)";
			pstmt2 = conn.prepareStatement(sql3);
			pstmt2.setInt(1, maxNum);
			pstmt2.setString(2, gu);
			pstmt2.setString(3, nm);
			
			int cnt = pstmt2.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("정상 등록 되었습니다.");
			} else {
				System.out.println("등록에 실패 하였습니다.");
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt1 != null) try {pstmt1.close();} catch(SQLException e) {}
			if(pstmt2 != null) try {pstmt2.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}

	}

}
