package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest04
 */
@WebServlet(name = "ServletContextTest", value="/ServletTest04.do")
public class ServletTest04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		/*
		 	Servlet 클래스나 JSP페이지의 환경 관련 정보는 javax.servlet.ServletContext 인터페이스 타입의 객체를 이용해서 얻을 수 있다.
		 	
		 	- getServerInfo()메서드 ==> Servlet이 속한 웹서버의 종류를 반환한다.
		 	- getMajorVersion()메서드와 getMinorVersion()메서드는 웹 컨테이너가 지원하는 Servlet의 버전을 반환한다.
		 	
		 	
		 */
		
		ServletContext context = getServletContext();

		String serverINfo = context.getServerInfo();
		int major = context.getMajorVersion();
		int minor = context.getMinorVersion();
		
		String servletName = getServletName();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='UTF-8'>");
		out.println("<title>웹 서버의 정보</title></head>");
		out.println("<body><h2>웹 서버의 종류 (ServerInfo) : " + serverINfo +"</h2> <br>");
		out.println("Servlet Name : " + servletName + "<br>");
		out.printf("지원하는 Servlet버전 : (%d.%d)<br><br>",major,minor);
		
		
		/*
		 
		 	초기화 파라미터 (web.xml에 설정된 값)
		 	1. 웹 애플리케이션 초기화 파라미터
		 	  ==> 특정 Servlet에 속하는 초기화파라미터가 아니라 웹 애플리케이션 전체에
		 	      속하는 초기화 파라미터를 말한다. 이 파라미터는 모든 Servlet에서
		 	      가져가 사용할 수 있다.
		 	  ==> 작성방법
		 	  	  <web-app>
		 	  	  	...
		 	  	  	<context-param>
		 	  	  		<param-name>파라미터이름</param-name>
		 	  	  		<param-value>값</param-value>
		 	  	  	</context-param>
		 	  	  	...
		 	  	  </web-app>	
		 	  
		 	  
		 	2. 서블릿 초기화 파라미터
		 	  ==> 특정 Servlet에서만 사용할 수 있는 초기화 파라미터
		 	  ==> 작성방법
		 	  	  <web-app>
		 	  	  		<servlet>
		 	  	  			<servlet-name>서블릿이름</servlet-name>
		 	  	  			<servlet-class>패키지명.클래스명</servlet-class>
		 	  	  			<init-param>
		 	  	  			<param-name>파라미터이름</param-name>
		 	  	  			<param-value>값</param-value>
		 	  	  			</init-param>
		 	  	  		</servlet>
		 	  	  </web-app>
		 
		 */
		
		// 웹 애플리케이션 초기화 파라미터는 ServletContext의 getInitParameter()메서드를 이용하여 사용할 수 있다.
		
		String email = context.getInitParameter("admin_email");
		out.println("<h2>web.xml에 설정된 email의 값"  + email + "</h2>");
		
		
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
