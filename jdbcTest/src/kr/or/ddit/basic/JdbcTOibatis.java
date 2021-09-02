package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


// jdbc공부할 때 사용했던 jdbcTest05.java 프로그램을
// ibatis용으로 변환하시오.
// (SQL문이 저장될 xml문서의 파일명은 jdbc.xml 로 한다.)


public class JdbcTOibatis {

		public static void main(String[] args) {
			
			Scanner scan = new Scanner(System.in);
			
			try {
				
				Charset charset = Charset.forName("UTF-8");
				Resources.setCharset(charset);
				
				Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
				
				SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close();
				
				int lprodId = (int) smc.queryForObject("lprod.getLprodId");
				
				String lprodGu;
				int cnt = 0;
				
				do {
					
					System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
					lprodGu = scan.next();
					
					int idCount = (int) smc.queryForObject("lprod.countLprodGu",lprodGu);
					
					if(idCount > 0) {
						System.out.println("입력한 상품 분류 코드 " + lprodGu + "는 이미 존재합니다.");
						System.out.println("다시 입력해주세요.");
					}
					
				}while(cnt > 0);
				
				System.out.print("상품 분류명(LPROD_NM)을 입력 : ");
				String lprodNm = scan.next();
				
				LprodVO lprodVO = new LprodVO();
				lprodVO.setLprod_id(lprodId);
				lprodVO.setLprod_gu(lprodGu);
				lprodVO.setLprod_nm(lprodNm);
				
				Object obj = smc.insert("lprod.insertLprod", lprodVO);
				
				if(obj == null) {
					System.out.println("작업 성공");
				} else {
					System.out.println("작업 실패");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}

