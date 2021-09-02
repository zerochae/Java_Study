package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.DaoInterface;
import kr.or.ddit.board.vo.BoardVo;

public class BoardService implements ServiceInterFace {

	private static BoardService instance;
	
	private DaoInterface dao;
	
	public BoardService() {
		dao = BoardDao.getInstance();
	}
	
	public static BoardService getInstance() {
		if(instance == null) instance = new BoardService();
		return instance;
	}
	
	@Override
	public int insertBoard(BoardVo boardvo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardvo);
	}
 
	@Override
	public int deleteBoard(BoardVo boardvo) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardvo);
	}

	@Override
	public int updateBoard(BoardVo boardvo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(boardvo);
	}

	@Override
	public List<BoardVo> getAllBoardList() {
		// TODO Auto-generated method stub
		return dao.getAllBoardList();
	}

	@Override
	public List<BoardVo> searchBoard(String param) {
		// TODO Auto-generated method stub
		return dao.searchBoard(param);
	}

	@Override
	public List<BoardVo> selectOne(int boardNo) {
		// TODO Auto-generated method stub
		return dao.selectOne(boardNo);
	}

	@Override
	public int updateCnt(int boardNo) {
		// TODO Auto-generated method stub
		return dao.updateCnt(boardNo);
	}
}
