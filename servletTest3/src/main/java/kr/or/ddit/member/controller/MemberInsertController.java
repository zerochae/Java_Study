package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식으로 요청이 오면 회원 가입 창이 나오게 하고
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/memberForm.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 요청이 오면 form에서 입력한 데이터를 DB에 저장한다.
		
		request.setCharacterEncoding("UTF-8");
		
		// 요청할 때 파라미터로 온 데이터 구하기
		String mem_id = request.getParameter("mem_id");
		String mem_name = request.getParameter("mem_name");
		String mem_pass = request.getParameter("mem_pass");
		String mem_tel = request.getParameter("mem_tel");
		String mem_addr = request.getParameter("mem_addr");
		
		// 데이터를 VO객체에 저장하기
		MemberVO memvo = new MemberVO();
		memvo.setMem_id(mem_id);
		memvo.setMem_name(mem_name);
		memvo.setMem_pass(mem_pass);
		memvo.setMem_tel(mem_tel);
		memvo.setMem_addr(mem_addr);
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		int cnt = service.insertMember(memvo);
		
		// Insert작업 완료 후에는 회원 목록으로 이동한다.
		response.sendRedirect(request.getContextPath()+"/member/memberList.do");
		
	}

}
