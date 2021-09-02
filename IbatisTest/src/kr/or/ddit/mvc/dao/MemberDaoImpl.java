package kr.or.ddit.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {

	private static MemberDaoImpl instance;

	private SqlMapClient smc; // ibatis용 SqlMapClinet객체 변수 선언

	// 생성자 -> ibatis 환경설정 및 SqlMapClient객체 생성
	private MemberDaoImpl() {
		
		smc = SqlMapClientFactory.getSqlMapClient();

//		try {
//
//			Charset charset = Charset.forName("UTF-8");
//			Resources.setCharset(charset);
//
//			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
//
//			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//
//			rd.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public static MemberDaoImpl getInstance() {

		if (instance == null)
			instance = new MemberDaoImpl();
		return instance;
	}

	@Override
	public int insertMember(MemberVO memvo) {

		int cnt = 0;

		try {

			Object obj = smc.insert("member.insertMember", memvo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;

		try {

			cnt = smc.delete("member.deleteMember", memId);

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memvo) {

		int cnt = 0;

		try {

			cnt = smc.update("member.updateMember", memvo);

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getALLMemberList() {

		List<MemberVO> memList = null;

		try {

			memList = smc.queryForList("member.getAllMember");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {

		int cnt = 0;

		try {

			cnt = (int) smc.queryForObject("member.getMemberCount", memId);

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		int cnt = 0;
		try {

			cnt = smc.update("member.updateMember2", paramMap);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
