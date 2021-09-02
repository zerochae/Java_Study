package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	
	public static void main(String[] args) {
		
		// 바이트 기반의 스트림을 이용하여 파일 내용을 읽어오기
		// ==> FileInputStream을 이용한다.
		
		
		
		try {
		// 파일 입력용 스트림 객체 생성
		
		// 방법1 ==> 읽어올 파일명과 경로를 문자열로 지정한다.
//		FileInputStream fin = new FileInputStream("D:/D_Other/test.txt");
		
			
		// 방법2 ==> 읽어올 파일 정보가 저장될 File객체를 이용하는 방법	
		File file = new File("D:/D_Other/test.txt");
		FileInputStream fin = new FileInputStream(file);
		
		int c; //읽어온 데이터가 저장될 변수
		while((c = fin.read()) != -1) {
			// 읽어온 데이터를 화면에 출력하기
			System.out.print((char)c);
		}
		fin.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
