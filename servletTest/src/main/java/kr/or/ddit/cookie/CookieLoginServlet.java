package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		boolean check = request.getParameter("check") != null;

		if(check) {
			
			Cookie cookie = new Cookie("id",id);
			response.addCookie(cookie);
		}
		
		if(id.equals("test") && pass.equals("1234")) {
			
			response.sendRedirect(request.getContextPath()+"/02/cookieMain.jsp");
			
		} else {
			response.sendRedirect(request.getContextPath()+"/02/cookieLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
