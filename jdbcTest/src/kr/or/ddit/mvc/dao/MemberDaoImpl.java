package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private static MemberDaoImpl instance;
	
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

			String insertSql = "INSERT INTO MYMEMBER(MEM_ID,MEM_PASS,MEM_NAME,MEM_TEL,MEM_ADDR)" + " VALUES(?,?,?,?,?)";

			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, memvo.getMem_id());
			pstmt.setString(2, memvo.getMem_pass());
			pstmt.setString(3, memvo.getMem_name());
			pstmt.setString(4, memvo.getMem_tel());
			pstmt.setString(5, memvo.getMem_addr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			;
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
			;
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
			
			String deleteSql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(deleteSql);
			
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			
		String updateSql = "UPDATE MYMEMBER SET MEM_PASS = ? , MEM_NAME = ? , MEM_TEL = ? , MEM_ADDR = ? WHERE MEM_ID = ?";
		
		pstmt = conn.prepareStatement(updateSql);
		
		
		pstmt.setString(1, memvo.getMem_pass());
		pstmt.setString(2, memvo.getMem_name());
		pstmt.setString(3, memvo.getMem_tel());
		pstmt.setString(4, memvo.getMem_addr());
		pstmt.setString(5, memvo.getMem_id());
		
		cnt = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
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
			
			String selectSql = "SELECT * FROM MYMEMBER";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSql);
			
			while(rs.next()) {
				MemberVO memvo = new MemberVO();
				memvo.setMem_id(rs.getString("MEM_ID"));
				memvo.setMem_pass(rs.getString("MEM_PASS"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_tel(rs.getString("MEM_TEL"));
				memvo.setMem_addr(rs.getString("MEM_ADDR"));
				
				memList.add(memvo);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
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

			String idCheckSql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			conn = DBUtil3.getConnection();
			pstmt = conn.prepareStatement(idCheckSql);

				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			;
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			;
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
			;
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
			
			String updateSql = "UPDATE MYMEMBER SET " + paramMap.get("field") + " = ? WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setString(1,paramMap.get("data"));
			pstmt.setString(2,paramMap.get("memId"));
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {};
			if(conn!=null) try {conn.close();}catch(SQLException e) {};
		}
		return cnt;
	}
}
