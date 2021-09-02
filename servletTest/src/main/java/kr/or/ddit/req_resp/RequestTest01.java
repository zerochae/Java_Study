package kr.or.ddit.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Post방식으로 전달되는 데이터의 문자 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		
		// getParameter("파라미터변수명") ==> 해당 '파라미터변수'에 설정된 '값'을 가져온다
		// 		==> 가져오는 '값'의 자료형은 'String'이다.
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// getParameterValues("파라미터변수명")
		// 		==> '파라미터변수명'이 같은 것이 여러개 일 경우에 사용한다.
		//		==> 가져오는 '값' 자료형은 'String[]'이 된다.
		
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='UTF-8'>");
		out.println("<title> Request 객체 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2> Request 결과 </h2><hr>");
		
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" +userName+ "</td></tr>");
		out.println("<tr><td>직업</td>");
		out.println("<td>" +job+ "</td></tr>");
		out.println("<tr><td>취미</td>");
		out.println("<td>");
		
		if(hobbies == null) {
			out.println("없숨");
		} else {
			
			for (String s : hobbies) {
				out.println(s + "<br>");
			}
		}
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<hr>");
		out.println("<h2> Request 결과 2 </h2><hr>");
		out.println("<ol>");
		out.println("<li> 클라이언트 주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li> 요청 메서드 : " + request.getMethod() + "</li>");
		out.println("<li> Context Path : " + request.getContextPath() + "</li>");
		out.println("<li> 프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li> URL 정보 : " + request.getRequestURL() + "</li>");
		out.println("<li> URI 정보 : " + request.getRequestURI() + "</li>");
		out.println("</ol>");
		
		out.println("<table border='1>");
		
		out.println("</table>");
		
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
