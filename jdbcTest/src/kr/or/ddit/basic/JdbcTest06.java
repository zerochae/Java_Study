package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
 		회원을 관리하는 프로그램을 작성하시오.
 		(오라클의 MYMEMBER 테이블 이용)
 		
 		아래 메뉴의 기능을 모두 구현하시오. (CRUD기능 구현하기 )
 		
 		메뉴예시)
 		=========================
 			-- 작업 선택 --
 			1. 자료 추가
 			2. 자료 삭제
 			3. 자료 수정
 			4. 전체 자료 출력
 			0. 작업 끝.
 		=========================
 		
 		처리 조건)
 		
 		1) 자료 추가에서 '회원ID'는 중복되지 않는다(중복되면 다시 입력받는다.)
 		2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 		3) 자료 수정에서 '회원ID'는 변경되지 않는다.

 */

public class JdbcTest06 {

	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {

		new JdbcTest06().StartMenu();

	}

	public void StartMenu() {

			try {
				while (true) {
				if(conn == null) conn = DBUtil3.getConnection();
				
				System.out.println();
				System.out.println("================================");
				System.out.println("\t-- 작업 선택 --");
				System.out.println("\t 1. 자료 추가");
				System.out.println("\t 2. 자료 삭제");
				System.out.println("\t 3. 자료 수정");
				System.out.println("\t 4. 자료 하나 수정");
				System.out.println("\t 5. 전체 자료 출력");
				System.out.println("\t 0. 작업 끝");
				System.out.println("================================");
				System.out.print(" 메뉴를 입력하세요 ==>");
				int MenuNum = scan.nextInt();
				System.out.println();

				switch (MenuNum) {
				case 1:
					Insert();
					break;
				case 2:
					Delete();
					break;
				case 3:
					Update();
					break;
				case 4:
					Update2();
					break;
				case 5:
					Select();
					break;
				case 0:
					System.out.println("종료합니다.");
					System.exit(0);
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					StartMenu();
					break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try {rs.close();} catch (SQLException e) {}
				if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
			}
		}

	private void Select() {
		
		try {
			
			String selectSql = "SELECT * FROM MYMEMBER";
			pstmt = conn.prepareStatement(selectSql);
			
			rs = pstmt.executeQuery();
			System.out.println("================================");
			while(rs.next()) {
				System.out.println("아 이 디 : " + rs.getString("MEM_ID"));
				System.out.println("비밀번호 : " + rs.getString("MEM_PASS"));
				System.out.println("이    름 : " + rs.getString("MEM_NAME"));
				System.out.println("전화번호 : " + rs.getString("MEM_TEL"));
				System.out.println("주    소 : " + rs.getString("MEM_ADDR"));
				System.out.println("================================");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void Update2() {
		
		try {
			String memId;
			String idCheckSql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(idCheckSql);
			int count = 0;
			do {
				
				System.out.print("수정할 아이디를 입력하세요 >> ");
				memId = scan.next();
				
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt("CNT");
				}
				
				if (count == 0) {
					System.out.println("존재하지 않는 아이디 입니다.");
					System.out.println("다시 입력해주세요.");
				}
			}while(count == 0);
			
			System.out.println("무엇을 수정 하시겠습니까?");
			System.out.println("1.비밀번호 | 2.이름 | 3.전화번호 | 4. 주소");
			int input = scan.nextInt();
			String menu = "";
			switch (input) {
			case 1: menu = "MEM_PASS";
				break;
			case 2: menu = "MEM_NAME";
				break;
			case 3: menu = "MEM_TEL";
				break;
			case 4: menu = "MEM_ADDR";
				break;
			default: System.out.println("정확히 입력해주세요");
			System.out.println("1.비밀번호 | 2.이름 | 3.전화번호 | 4. 주소");
			input = scan.nextInt();
			break;
			}
			
			String updateSql = "UPDATE MYMEMBER SET "+ menu +" = ? WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(updateSql);
			System.out.println("수정할 내용을 입력해주세요.");
			String newInput = scan.next();
			
			pstmt.setString(1, newInput);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("수정 완료되었습니다.");
			} else {
				System.out.println("수정 실패!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void Update() {
		
		try {
			String memId;
			String idCheckSql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(idCheckSql);
			int count = 0;
			do {
				System.out.print("수정할 아이디를 입력하세요 >> ");
				memId = scan.next();

				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}
				if (count == 0) {
					System.out.println("존재하지 않는 아이디 입니다.");
					System.out.println("다시 입력해주세요.");
				}
			} while (count == 0);
			
			String updateSql = "UPDATE MYMEMBER SET MEM_PASS = ? , MEM_NAME = ? , MEM_TEL = ? , MEM_ADDR = ? WHERE MEM_ID = ?";
			
			pstmt = conn.prepareStatement(updateSql);
			
			System.out.print("비밀번호 >> ");
			String passWord = scan.next();
			System.out.print("이름 >> ");
			String nm = scan.next();
			System.out.print("전화번호 >> ");
			String tel = scan.next();
			System.out.print("주소 >> ");
			String addr = scan.next();
			
			pstmt.setString(1, passWord);
			pstmt.setString(2, nm);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("수정 완료되었습니다.");
			} else {
				System.out.println("수정 실패!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void Delete() {

		try {
			String memId;
			String idCheckSql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(idCheckSql);
			int count = 0;
			do {
				System.out.print("삭제할 아이디를 입력하세요 >> ");
				memId = scan.next();

				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}
				if (count == 0) {
					System.out.println("존재하지 않는 아이디 입니다.");
					System.out.println("다시 입력해주세요.");
				}
			} while (count == 0);

			String deletesql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(deletesql);

			pstmt.setString(1, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("정상 삭제 되었습니다.");
			} else {
				System.out.println("실패함");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	private void Insert() {

		try {
			String idCheckSql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			String memId;
			pstmt = conn.prepareStatement(idCheckSql);
			int count = 0;
			do {
				System.out.print("아이디 >> ");
				memId = scan.next();

				pstmt.setString(1, memId);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}

				if (count > 0) {
					System.out.println("입력한 아이디 " + memId + " 는 이미 존재합니다.");
					System.out.println("다시 입력해주세요.");
				}
			} while (count > 0);

			System.out.print("비밀번호 >> ");
			String passWord = scan.next();
			System.out.print("이름 >> ");
			String nm = scan.next();
			System.out.print("전화번호 >> ");
			String tel = scan.next();
			System.out.print("주소 >> ");
			String addr = scan.next();

			String insertSql = "INSERT INTO MYMEMBER(MEM_ID,MEM_PASS,MEM_NAME,MEM_TEL,MEM_ADDR)" + " VALUES(?,?,?,?,?)";

			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, memId);
			pstmt.setString(2, passWord);
			pstmt.setString(3, nm);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("정상 등록 되었습니다");
			} else {
				System.out.println("등록에 실패하였습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
