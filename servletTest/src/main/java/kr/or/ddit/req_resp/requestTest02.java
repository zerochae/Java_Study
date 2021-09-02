package kr.or.ddit.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class requestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int num1 = Integer.parseInt(request.getParameter("num1"));
	String operator = request.getParameter("operator");
	int num2 = Integer.parseInt(request.getParameter("num2"));
	
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	out.println("<!DOCTYPE html>");
	out.println("<html><head><meta charset='UTF-8'>");
	out.println("<title> Request 객체 연습</title></head>");
	out.println("<body>");
	
	out.println("<h2> 계산 결과 </h2><hr>");
	out.println(num1 + " ");
	out.println(operator + " ");
	out.println(num2 + " = ");
	
	int result = 0;
	
	switch (operator) {
	case "+": result = num1 + num2;
		break;
	case "-": result = num1 - num2;
		break;
	case "*": result = num1 * num2;
		break;
	case "/": result = num1 / num2;
		break;
	case "%": result = num1 % num2;
	break;
	}
	out.println(result);
	out.println("</body></html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
