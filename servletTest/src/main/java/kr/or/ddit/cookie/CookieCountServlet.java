package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Cookie cookieArr[] = request.getCookies();
		
		int count = 0;
		
		if(cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				
				String name = cookie.getName();
				
				if(name.equals("count")) {

					String value = cookie.getValue();
					count = Integer.parseInt(value);
				}
			}
		}
		count++;
		
		Cookie cntCookie = new Cookie("count",String.valueOf(count));
		response.addCookie(cntCookie);
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h2>어서오세요 당신은 " + count + "번째 방문입니다. </h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/CookieCountServlet.do'>카운트증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/02/cookieTest02.jsp'>시작문서로 이동</a>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
