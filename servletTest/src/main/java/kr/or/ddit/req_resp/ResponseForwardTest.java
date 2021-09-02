package kr.or.ddit.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseForwardTest
 */
@WebServlet("/ResponseForwardTest.do")
public class ResponseForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터로 넘어온 데이터 받기
		String userName = request.getParameter("username");
		
		// setAttrivute()메서드로 넘어온 데이터 받기
		String tel = (String) request.getAttribute("tel");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='UTF-8'>");
		out.println("<title> forward 연습 연습</title></head>");
		out.println("<body>");
		out.println("<h2> Forward 결과 </h2><hr>");
		out.println("<ul>");
		out.println("<li>username :" +userName+ "</li>");
		out.println("<li>tel :" +tel+ "</li>");
		out.println("</ul>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
