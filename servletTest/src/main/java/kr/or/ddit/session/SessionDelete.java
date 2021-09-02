package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// Session 삭제하기
		
		// 1. 세션 생성 또는 현재 세션 가져오기
		HttpSession session = request.getSession();
		
		// 2. 세션에 저장된 개별적인 데이터 삭제하기
		//	  ==> 세션 자체는 삭제되지 않고 개별적인 '세션값'만 삭제된다.	
		// 형식) session객체.removeAttribute("삭제할 key값")
		session.removeAttribute("testSession");
		
		out.println("<html><head><meta charset='utf-8'>");
	    out.println("<title>Cookie 연습</title></head>");
	    out.println("<body>");
	    out.println("<h2>Cookie 데이터가 삭제되었습니다. </h2><br><br>");
	    out.println("<a href='" + request.getContextPath() + "/03/sessionTest.jsp'>시작문서로 이동</a>");
	    out.println("</body></html>");
	    
	    //3. 세션 자체를 삭제하기
	    // 형식) session객체.invaildate();
	    session.invalidate(); // 전체 삭제
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
