package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		   /*
		    * 쿠키 정보 삭제하기
		    * 예) gender 쿠키 삭제하기
		    * 저장된 쿠키 중에서 삭제할 쿠키를 찾아서, 이 쿠키의 남은 수명을 0으로 설정하고, 다시 저장한다.
		    *    => setMaxAge() 메소드 사용.
		    * 
		    */
		
		Cookie[] cookieArr = request.getCookies();

		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Cookie 정보 읽어오기 </h2><br><br>");

		if (cookieArr == null || cookieArr.length == 0) {

			out.println("<h2>저장된 쿠키가 하나도 없습니다.</h2>");

		} else {

			// 쿠키배열에서 삭제할 쿠키를 찾는다.
			for (Cookie cookie : cookieArr) {

				String name = cookie.getName();

				if (name.equals("gender")) {
					// 찾은 쿠키의 수명을 0으로 설정한다.
					cookie.setMaxAge(0);
					cookie.setValue("0");
					
					// 이 쿠키를 다시 저장한다.
					response.addCookie(cookie);
				}
			}
			out.println("쿠키 삭제 완료!! <br><br>");
		}
		out.println("<a href='" + request.getContextPath() + "/02/cookieTest.jsp'>시작문서로 이동</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
