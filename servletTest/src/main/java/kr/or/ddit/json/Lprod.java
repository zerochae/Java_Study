package kr.or.ddit.json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.BuildedSqlMapClient;
import kr.or.ddit.vo.lprod.LprodVO;

@WebServlet("/lprod.do")
public class Lprod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SqlMapClient smc = BuildedSqlMapClient.getSqlMapClient();
		
		// 요청 데이터 가져오기 - lgu
		String lgu = request.getParameter("lGu");
		
		// sql문 조회를 위해 service객체 얻어오기 - service -> dao -> sql조회
		 
		// service 메소드 호출하고 결과 값 리턴받기
		List<LprodVO> list = null;
		
		try {
			list = smc.queryForList("lprod.selectAll");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		// 결과적으로 json형태 응답데이터 생성 >> jsp에 위임 = jsp로 결과값을 보냄
		// jsp로 결과값을 보내기 위해 request에 결과 값을 저장
		request.setAttribute("listValue", list);
		// jsp로 포워딩 - servlet의 request 객체 변수를 활용하여 jsp에 공유하셈
		RequestDispatcher disp = request.getRequestDispatcher("/04/getLprod.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
