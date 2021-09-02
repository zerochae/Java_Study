package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	/*
	
	Servlet 
	웹 컨테이너에 의해 관리되는 자바 기반 웹 컴포넌트로서
	동적인 웹 컨텐츠 생성을 가능하게 한다.
	
	http://localhost:80/servletTest/servletTest01.do
	http://localhost:80servletTest/servletTest01.do
	- http ==> 프로토콜
	- localhost ==> 컴퓨터이름(도메인명) 또는 IP주소
	- 8081 ==> Port번호 (80번일 경우에는 생략가능)
	- /servletTest 	    ==> Context Path(컨텍스트 패스) 
					    ==> 보통 프로젝트 이름으로 지정한다.
	- /servletTest01.do ==> 서블릿 요청 URL패턴
	- 이 예제는 배포서술자(DD, Deployment Descriptor => web.xml문서)를
	  이용해서 실행할 servlet을 설정하여 처리하는 예제이다.
			
	*/

//servlet클래스는 HttpServlet클래스를 상속해서 작성한다. 
public class ServletTest01 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// 이 곳 영역에서는 보통 service()메서드 또는 doGet()메서드나 doPost()메서드를 재정의해서 작성한다.
	
	// doGet()메서드나 doPost()메서드는 service()메서드를 통해서 호출된다.
	// 이 때 다음과 같은 객체가 자동으로 생성되어서 위 메서드의 파라미터로 넘겨진다.
	// - HttpServletRequest객체  ==> 요청에 관련된 정보 및 메서드를 관리하는 객체
	// - HttpServletResponse객체 ==> 응답에 관련된 정보 및 메서드를 관리하는 객체
	
		// doGet()메서드 --> GET방식의 요청을 처리하는 메서드
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setCharacterEncoding("UTF-8"); // 응답문서의 인코딩 방식 지정
			response.setContentType("text/html; charset=UTF-8"); //응답문서의 ContentType 설정
			
			// 처리한 내용을 응답을 보내기 위해 PrintWriter객체를 생성한다.
			// (response객체를 이용한다.)
			PrintWriter out = response.getWriter();
			
			// 처리한 내용을 출력한다.
			
			// 방법1 : append()메서드 이용하기
			out.append("<html>");
			out.append("<head>");
			out.append("<meta charset='UTF-8'>");
			out.append("<title>첫번째 Servlet 연습</title>");
			out.append("</head>");
			out.append("<body>");
			out.append("<h1 style='text-align:center;'>");
			out.append("Hiㅋㅋ 이것은 First Servlet Program");
			out.append("<br>");
			out.append("Served at " + request.getContextPath());
			out.append("</h1>");
			out.append("</body>");
			out.append("</html>");
			
		}
		// doPost()메서드 --> POST방식의 요청을 처리하는 메서드
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			super.doPost(request, response);
		}
}
