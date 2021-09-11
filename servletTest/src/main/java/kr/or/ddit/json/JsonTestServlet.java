package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
 
@WebServlet("/jsonTestServlet.do")
public class JsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// JSON 데이터를 만들어서 응답으로 보내주는 서블릿
		request.setCharacterEncoding("UTF-8");

		response.setCharacterEncoding("UTF-8");
		// JSON으로 응답할 때의 ContentType 설정 방법
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String choice = request.getParameter("choice");

		Gson gson = new Gson();
		String jsonData = null; // json데이터로 변경된 데이터가 저장될 변수

		switch (choice) {
		case "str": // 문자열
			String str = "안녕하세요.";
			jsonData = gson.toJson(str);
			break;
			
		case "array": // 배열
			int[] arr = {100,200,300,400,500};
			jsonData = gson.toJson(arr);
			break;
			
		case "obj": // 객체
			SampleVO vo = new SampleVO(100,"홍길동");
			jsonData = gson.toJson(vo);
			break;
			
		case "list": // 리스트
			List<SampleVO> list = new ArrayList<>();
			list.add(new SampleVO(1,"권영채"));
			list.add(new SampleVO(2,"박수정"));
			list.add(new SampleVO(3,"최가현"));
			list.add(new SampleVO(4,"서혜란"));
			list.add(new SampleVO(5,"구현수"));
			jsonData = gson.toJson(list);
			break;
			
		case "map": // 맵
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("PL", "권영채");
			map.put("TA", "박수정");
			map.put("AA", "최가현");
			map.put("DA", "서혜란");
			map.put("UA", "구현수");
			jsonData = gson.toJson(map);
			break;
			
			
			
		}
		
		System.out.println( choice + " : " + jsonData);
		
		out.println(jsonData);
		
		
		
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
