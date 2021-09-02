package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.controller.BoardController;
import kr.or.ddit.board.util.DBUtil;
import kr.or.ddit.board.vo.BoardVo;

public class BoardDao implements DaoInterface{
	
	private static BoardDao instance;
	
	private SqlMapClient smc;
	
	private BoardDao() {
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static BoardDao getInstance() {
		if(instance == null) instance = new BoardDao();
		return instance;
	}

	@Override
	public int insertBoard(BoardVo boardvo) {
		
		int cnt = 0;
		try {
			Object obj = smc.insert("board.insertBoard",boardvo);
			
			if(obj == null) {
				cnt = 1;
			}
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(BoardVo boardvo) {
		int cnt = 0;
		
		try {
			
			cnt = smc.delete("board.deleteBoard",boardvo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVo boardvo) {
		int cnt = 0;
		
		try {
			
			cnt = smc.update("board.updateBoard",boardvo);
			
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVo> getAllBoardList() {
		
		List<BoardVo> boardList = null;

		try {
			
			boardList = smc.queryForList("board.getAllBoardList");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return	boardList;
	}

	@Override
	public List<BoardVo> searchBoard(String param) {
		
		List<BoardVo> boardList = null;
		
		try {
			
			boardList = smc.queryForList("board.searchBoard",param);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public List<BoardVo> selectOne(int boardNo) {
		
		List<BoardVo> board = null;
		
		try {
			
			board = smc.queryForList("board.getBoard",boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	@Override
	public int updateCnt(int boardNo) {
		
		int cnt = 0;
		
		try {
			
			cnt = smc.update("board.updateCnt",boardNo);
			
		} catch (SQLException e) {
			
		}
		return cnt;
	}

}
