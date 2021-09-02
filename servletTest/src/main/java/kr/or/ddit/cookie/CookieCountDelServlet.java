package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieCountDelServlet.do")
public class CookieCountDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 연습</title></head>");
		out.println("<body>");
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {			
			for (Cookie cookie : cookies) {
				//cookie.setMaxAge(0);
				cookie.setValue("0");
				response.addCookie(cookie);
			}
			out.println("<h2>쿠키 초기화 완료</h2> <br><br>");
		} else {
			out.println("<h2>쿠키가 없서용</h2> <br><br>");
		}
		
		out.println("<a href='" + request.getContextPath() + "/02/cookieTest02.jsp'>시작문서로 이동</a>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
