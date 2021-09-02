package kr.or.ddit.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 그 결과를 작성해서 Service에 전달하는 DAO의 interface
 * 
 * @author PC-23
 *
 */

public interface IMemberDao {

	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memvo DB에 insert할 데이터가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1 , 실패 : 0
	 */

	public int insertMember(MemberVO memvo);

	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return delete작업 성공 : 1 , 실패 : 0
	 */

	public int deleteMember(String memId);

	/**
	 * MemberVO자료를 이용하여 DB의 회원 정보를 Update하는 메서드
	 * 
	 * @param memvo update할 데이터가 저장된 MemberVO객체
	 * @return update작업 성공 : 1 , 실패 : 0
	 */

	public int updateMember(MemberVO memvo);

	/**
	 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체를 담고 있는 List객체
	 */

	public List<MemberVO> getALLMemberList();
	
	public int getMemberCount(String memId);
	
	/**
	 * 매개변수로 넘어온 Map정보를 이용하여 회원 정보 중 원하는 항목을 수정하는 메서드
	 * 		key값 정보 --> 회원ID(memId), 수정할 컬럼명(field), 수정할 데이터(data)
	 * @param paramMap 회원Id, 수정할 컬럼, 수정할 데이터가 저장된 Map객체
	 * @return 성공 : 1 / 실패 : 0
	 */
	public int updateMember2(Map<String,String> paramMap);

}
