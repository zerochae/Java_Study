package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private static MemberServiceImpl instance;
	
	
	private IMemberDao dao; //DAO객체가 저장될 변수 선언
	
	public MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance(); //DAO객체 생성
	}
	
	public static MemberServiceImpl getInstance() {
		if(instance == null) instance = new MemberServiceImpl();
		return instance;
		
	}
 
	@Override
	public int insertMember(MemberVO memvo) {
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) {
		return dao.updateMember(memvo);
	}

	@Override
	public List<MemberVO> getALLMemberList() {
		return dao.getALLMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}

	@Override
	public MemberVO getMember(String memId) {
		
		return dao.getMember(memId);
	}

}
