package kr.or.ddit.mvc.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.CryptoUtil;

public class MemberServiceImpl implements IMemberService {

	private static MemberServiceImpl instance;
	private String KEY = "abcdefg1234567891";

	private IMemberDao dao; // DAO객체가 저장될 변수 선언

	public MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance(); // DAO객체 생성
	}

	public static MemberServiceImpl getInstance() {
		if (instance == null)
			instance = new MemberServiceImpl();
		return instance;
	}

	@Override
	public int insertMember(MemberVO memvo) {
		try {
			
			String id = memvo.getMem_id();
			String pass = memvo.getMem_pass();
			memvo.setMem_id(CryptoUtil.encryptAES256(id, KEY));
			memvo.setMem_pass(CryptoUtil.sha512(pass));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) {
		try {

			memId = CryptoUtil.encryptAES256(memId, KEY);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) {

		try {

			String id = memvo.getMem_id();
			String pass = memvo.getMem_pass();
			memvo.setMem_id(CryptoUtil.encryptAES256(id, KEY));
			memvo.setMem_pass(CryptoUtil.sha512(pass));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return dao.updateMember(memvo);
	}

	@Override
	public List<MemberVO> getALLMemberList() {

		List<MemberVO> memList = dao.getALLMemberList();

		for (MemberVO memberVO : memList) {
			try {
				String id = memberVO.getMem_id();
				memberVO.setMem_id(CryptoUtil.decryptAES256(id, KEY));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
		}

		return memList;
	}

	@Override
	public int getMemberCount(String memId) {

		try {
			memId = CryptoUtil.encryptAES256(memId, KEY);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {

		try {
			
			String memId = paramMap.get("memId");
			memId = CryptoUtil.encryptAES256(memId, KEY);
			paramMap.put("memId", memId);

			String field = paramMap.get("field");
			if (field.equals("mem_pass")) {
				String pass = paramMap.get("data");
				pass = CryptoUtil.sha512(pass);
				paramMap.put("mem_pass", pass);
				
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.updateMember2(paramMap);
	}
}
