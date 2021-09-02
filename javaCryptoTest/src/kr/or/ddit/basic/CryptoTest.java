package kr.or.ddit.basic;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws 
	InvalidKeyException, NoSuchPaddingException,
	InvalidAlgorithmParameterException, 
	IllegalBlockSizeException, BadPaddingException {
		
		String plainText = "my name is Zerochae";
		String key = "abcdefg1234567891"; // 암호화 키 값 설정(16자리)
		
		try {
			System.out.println("단방향 : " + CryptoUtil.sha512(plainText));
			String enStr = CryptoUtil.encryptAES256(plainText, key);
			System.out.println("암호화 : " + enStr);
			System.out.println("복호화 : " + CryptoUtil.decryptAES256(enStr, key));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
