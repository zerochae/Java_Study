package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private static MemberDaoImpl instance;
	
	static Logger logger = Logger.getLogger(IMemberDao.class);
	
	private MemberDaoImpl() {
		
	}
	
	public static MemberDaoImpl getInstance() {
		if(instance == null) instance = new MemberDaoImpl();
		return instance;
	}

	@Override
	public int insertMember(MemberVO memvo) {

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성..");
			
			String insertSql = "INSERT INTO MYMEMBER(MEM_ID,MEM_PASS,MEM_NAME,MEM_TEL,MEM_ADDR)" 
							+ " VALUES(?,?,?,?,?)";

			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, memvo.getMem_id());
			pstmt.setString(2, memvo.getMem_pass());
			pstmt.setString(3, memvo.getMem_name());
			pstmt.setString(4, memvo.getMem_tel());
			pstmt.setString(5, memvo.getMem_addr());
			logger.info("PreparedStatement객체 생성..");
			logger.info("실행 SQL : " + insertSql);
			logger.info("사용 데이터 : [" + memvo.getMem_id() + ", " 
										  + memvo.getMem_pass() + ", "
										  + memvo.getMem_name() + ", "
										  + memvo.getMem_tel() + ", "
										  + memvo.getMem_addr() + "]" );
			
			cnt = pstmt.executeUpdate();
			logger.info("Insert 성공");

		} catch (SQLException e) {
			cnt = 0;
			logger.error("Insert 실패 " + e);
			//e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); logger.info("PreparedStatement객체 반납"); } catch (SQLException e) {};
			if (conn != null) try { conn.close(); logger.info("Connection객체 반납"); } catch (SQLException e) {};
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			 
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String deleteSql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(deleteSql);
			
			pstmt.setString(1, memId);
			logger.info("PreparedStatement 객체 생성");
			logger.info("실행 SQL : " + deleteSql);
			logger.info("삭제된 데이터 : " + memId);
			
			cnt = pstmt.executeUpdate();
			logger.info("Delete 성공");
			
		} catch (SQLException e) {
			logger.error("Delete 실패 " + e);
		} finally {
			if (pstmt != null) try { pstmt.close(); logger.info("PreparedStatement객체 반납"); } catch (SQLException e) {};
			if (conn != null) try { conn.close(); logger.info("Connection객체 반납"); } catch (SQLException e) {};
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memvo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			
		conn = DBUtil3.getConnection();
		logger.info("Connection 객체 생성");
			
		String updateSql = "UPDATE MYMEMBER SET MEM_PASS = ? , MEM_NAME = ? , MEM_TEL = ? , MEM_ADDR = ? WHERE MEM_ID = ?";
		
		pstmt = conn.prepareStatement(updateSql);
		
		
		pstmt.setString(1, memvo.getMem_pass());
		pstmt.setString(2, memvo.getMem_name());
		pstmt.setString(3, memvo.getMem_tel());
		pstmt.setString(4, memvo.getMem_addr());
		pstmt.setString(5, memvo.getMem_id());
		logger.info("PreparedStatement 객체 생성");
		logger.info("실행 SQL : " + updateSql);
		logger.info("사용 데이터 : [" + memvo.getMem_id() + ", " 
									  + memvo.getMem_pass() + ", "
									  + memvo.getMem_name() + ", "
									  + memvo.getMem_tel() + ", "
									  + memvo.getMem_addr() + "]" );
		
		
		cnt = pstmt.executeUpdate();
		logger.info("Update 성공");
		
	} catch (SQLException e) {
		logger.error("Update 실패 " + e);
	} finally {
		if (pstmt != null) try { pstmt.close(); logger.info("PreparedStatement객체 반납"); } catch (SQLException e) {};
		if (conn != null) try { conn.close(); logger.info("Connection객체 반납"); } catch (SQLException e) {};
	}
		return cnt;
	}

	@Override
	public List<MemberVO> getALLMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String selectSql = "SELECT * FROM MYMEMBER";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSql);
			logger.info("Statement 객체 생성");
			logger.info("실행 SQL : " + selectSql);
			
			while(rs.next()) {
				MemberVO memvo = new MemberVO();
				memvo.setMem_id(rs.getString("MEM_ID"));
				memvo.setMem_pass(rs.getString("MEM_PASS"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_tel(rs.getString("MEM_TEL"));
				memvo.setMem_addr(rs.getString("MEM_ADDR"));
				
				memList.add(memvo);
			}
			
			for(int i =0; i<memList.size(); i++) {
			logger.info("사용 데이터 : [ " + memList.get(i).getMem_id() + ", " 
										  + memList.get(i).getMem_pass() + ", "
										  + memList.get(i).getMem_name() + ", "
										  + memList.get(i).getMem_tel() + ", "
										  + memList.get(i).getMem_addr() + " ]" );
			}
			logger.info("Select 성공");
			
		}catch (SQLException e) {
			logger.error("Select 실패 : " + e);
		} finally {
			if (stmt != null) try { stmt.close(); logger.info("Statement객체 반납"); } catch (SQLException e) {};
			if (conn != null) try { conn.close(); logger.info("Connection객체 반납"); } catch (SQLException e) {};
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {

			conn = DBUtil3.getConnection();
			logger.info("Conncection 객체 생성");
			
			String idCheckSql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(idCheckSql);
			logger.info("PreparedStatement 객체 생성");

			pstmt.setString(1, memId);
			logger.info("실행 SQL :  " + idCheckSql);	
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("CNT");
			}
			logger.info("사용 데이터 : " + memId);
			logger.info("Count 성공");

		} catch (SQLException e) {
			logger.error("Count 실패 " + e);
		} finally {
			if (pstmt != null) try { pstmt.close(); logger.info("PreparedStatement객체 반납"); } catch (SQLException e) {};
			if (conn != null) try { conn.close(); logger.info("Connection객체 반납"); } catch (SQLException e) {};
		}
		return count;
	}
	

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String updateSql = "UPDATE MYMEMBER SET " + paramMap.get("field") + " = ? WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(updateSql);
			logger.info("PreparedStatement 객체 생성");
			pstmt.setString(1,paramMap.get("data"));
			pstmt.setString(2,paramMap.get("memId"));
			logger.info("실행 SQL : " + updateSql);
			logger.info("사용된 데이터 : " + paramMap.get("memId") +" , " 
										   + paramMap.get("data"));
			
			cnt = pstmt.executeUpdate();
			logger.info("Update 성공");
			
		} catch (SQLException e) {
			logger.error("Update 실패 " + e);
		} finally {
			if (pstmt != null) try { pstmt.close(); logger.info("PreparedStatement객체 반납"); } catch (SQLException e) {};
			if (conn != null) try { conn.close(); logger.info("Connection객체 반납"); } catch (SQLException e) {};
		}
		return cnt;
	}
}
