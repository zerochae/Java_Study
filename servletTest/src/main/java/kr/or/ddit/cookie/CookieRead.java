package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		
		// 저장된 쿠키 읽어오기
		// 1. 전체 쿠기 정보를 request객체를 이용해서 가져온다.
		//		==> 가져온 쿠키 정보들은 배열형태로 되어 있다.
		// 형식) Cookie[] 쿠키배열변수 = request.getCookies();
		
		Cookie cookieArr[] = request.getCookies();
		
		out.println("<html><head><meta charset='utf-8'>");
	    out.println("<title>Cookie 연습</title></head>");
	    out.println("<body>");
	    out.println("<h2>저장된 Cookie 정보 읽어오기 </h2><br><br>");
	    
	    if(cookieArr == null || cookieArr.length == 0) {
	    	
	    	out.println("<h2>저장된 쿠키가 하나도 없습니다.</h2>");
	    	
	    } else {
	    	
	    	for (Cookie c : cookieArr) {
	    		String name = c.getName(); // '쿠키변수'를 반환한다.
	    		//String value = c.getValue(); '쿠키값'을 반환한다.
	    		// 쿠키값이 한글일 경우에는 읽어온 값을 URLDecode.decoder()메서드로 디코딩한다.
	    		
	    		String value = URLDecoder.decode(c.getValue(), "UTF-8"); // 
				out.println("<h2>"+ name +" : " +value +"</h2><hr>");
			}
	    	out.println("쿠키 출력 끝!! <br><br>");
	    }
	    out.println("<a href='" + request.getContextPath() + "/02/cookieTest.jsp'>시작문서로 이동</a>");
	    out.println("</body></html>");
	    
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
