package kr.or.ddit.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.ServiceInterFace;
import kr.or.ddit.board.vo.BoardVo;

public class BoardController {
	
	private Scanner scan;
	private BufferedReader br;
	private ServiceInterFace service; 
	private List<BoardVo> boardList;

	public BoardController() {
		service = BoardService.getInstance();
		scan = new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) throws IOException {
		
		new BoardController().start();
		
	}

	public void start() throws IOException {
		
		mainBoard();
		
		loop:
		while(true){
			
			int choice = displayMainBoard();
			switch(choice){
			case 1 : // 새글 작성
			insert(); 
			break;
			case 2 : // 게시글 보기
			selectOne(); 
			break;
			case 3 : // 게시글 검색
			search(); 
			break;
			case 0 : // 종료
			System.out.println("작업 종료");
			break loop;
			default:
			System.out.println("번호를 잘못 입력. 다시 입력하셈");
			
			}
		}
	}

	private void search() {
		
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("검색할 제목 입력 >> ");
		String searchStr = scan.next();
		
		boardList = service.searchBoard(searchStr);
		
		if(boardList.size() == 0) {
			System.out.println("검색 결과가 없습니다.");
			mainBoard();
		} else {
		
		System.out.println("-------------------------------------------------------------");
		System.out.println("No	        제 목            작성자 	조회수 ");
		
		for (BoardVo boardVo : boardList) {
			System.out.println(	boardVo.getBoard_no()+"	        "
				  + boardVo.getBoard_title()+"            "
				  + boardVo.getBoard_writer()+" 	"
				  + boardVo.getBoard_cnt());
		}
		System.out.println("-------------------------------------------------------------");
		}
	}

	private void selectOne() throws IOException {
		
		System.out.print("보기를 원하는 게시물 번호 입력 >>");
		int boardNum = scan.nextInt();
		service.updateCnt(boardNum);
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_no(boardNum);
		boardList = service.selectOne(boardNum);
		
		
		System.out.println(boardVo.getBoard_no() + "번 글 내용");
		System.out.println("------------------------------------------------------------");
		for (BoardVo vo : boardList) {
			
			System.out.println("제  목 : " + vo.getBoard_title());
			System.out.println("작성자 : " + vo.getBoard_writer());
			System.out.println("내  용 : " + vo.getBoard_content());
			System.out.println("작성일 : " + vo.getBoard_date().split(" ")[0]);
			System.out.println("조회수 : " + vo.getBoard_cnt());
			
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("1. 수정 | 2.삭제 | 3.리스트로 돌아가기");
		
		int menuNum = scan.nextInt();
		
		switch (menuNum) {
		case 1: update(boardNum); //수정
			break;
		case 2: delete(boardNum); //삭제
			break;
		case 3: goBack(); 
			break;
		default:
			break;
		}
	}

	private void goBack() {
		mainBoard();
	}

	private void delete(int boardNo) {
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoard_no(boardNo);
		
		int cnt = service.deleteBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번 글이 삭제되었습니다. ");
		} else {
			System.out.println("게시글 삭제 실패");
		}
		System.out.println();
		mainBoard();
	}

	private void update(int boardNo) throws IOException {
		
		System.out.println("게시글 수정");
		System.out.println("-----------------------------------");
		System.out.print("제  목 >> ");
		String title = br.readLine();
		System.out.print("내  용 >> ");
		String content = br.readLine();
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		boardVo.setBoard_no(boardNo);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("게시글 수정 완료");
		} else {
			System.out.println("게시글 수정 실패");
		}
		System.out.println();
		mainBoard();
	}

	private void insert() throws IOException {
		
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("제  목 >> ");
		String title = br.readLine();
		System.out.print("작성자 >> ");
		String writer = br.readLine();
		System.out.print("내  용 >> ");
		String content = br.readLine(); 
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("게시물 등록 성공");
		} else {
			System.out.println("게시물 등록 실패");
		}
		mainBoard();
	}

	private int displayMainBoard() {
		
		System.out.println("1. 새글작성 | 2. 게시글 보기 | 3. 검색 | 0. 작업 끝");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}
	
	private void mainBoard() {
		
		boardList = service.getAllBoardList();
		
		System.out.println("-------------------------------------------------------------");
		System.out.println("No	        제 목            작성자 	조회수 ");
		System.out.println("-------------------------------------------------------------");
		
		for (BoardVo boardVo : boardList) {
			System.out.println(
					boardVo.getBoard_no()+"	        "
				  + boardVo.getBoard_title()+"            "
				  + boardVo.getBoard_writer()+" 	"
				  + boardVo.getBoard_cnt());
		}
		System.out.println();
	}
}
