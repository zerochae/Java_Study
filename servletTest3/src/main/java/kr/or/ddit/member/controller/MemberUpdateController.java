package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 ID를 파라미터로 받아서 해당 회원 정보를 구해서 UpdateForm으로 보낸다.
		
		request.setCharacterEncoding("UTF-8");
		
		String mem_id = request.getParameter("meme_id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		MemberVO memvo = service.getMember(mem_id);
		
		request.setAttribute("memberVO", memvo);
		
		request.getRequestDispatcher("/WEB-INF/view/memberUpdateForm.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
