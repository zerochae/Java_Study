package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVo;

public interface ServiceInterFace {
	
	/**
	 * 새 글 작성하는 메소드
	 * @return 성공 1 , 실패 0
	 */
	public int insertBoard(BoardVo boardvo);
	
	
	/**
	 * 작성자를 검색해서 해당 글을 삭제함
	 * @param board_no
	 * @return 성공 1, 실패 0
	 */
	public int deleteBoard(BoardVo boardvo);
	
	/**
	 *  글 수정 메소드
	 * @param paramMap
	 * @return 성공 1, 실패 0
	 */
	public int updateBoard(BoardVo boardvo);

	/**
	 * 글 전체 출력
	 * @return 글 전체 목록 리스트(글번호, 제목, 작성자, 조회수)
	 */
	public List<BoardVo> getAllBoardList();
	
	/**
	 * 단어로 게시물 검색
	 * @param param(검색 단어)
	 * @return 단어가 포함된 게시글 리스트
	 */
	public List<BoardVo> searchBoard(String param);
	
	/**
	 * 게시물 하나를 보여주는 메소드 
	 * @param boardNo
	 * @return 제목,작성자,내용,작성일,조회수
	 */
	public List<BoardVo> selectOne(int boardNo);
	
	public int updateCnt(int boardNo);

}
