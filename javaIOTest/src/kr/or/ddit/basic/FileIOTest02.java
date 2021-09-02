package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {
	
	public static void main(String[] args) {
		
		// 바이트 기반의 스트림을 이용하여 파일에 출력하기
		
		try {
			FileOutputStream fos = new FileOutputStream("D:/D_Other/out.txt");
			
			System.out.println("출력작업 시작...");
			
			for(char ch = 'A'; ch<='Z'; ch++) {
				fos.write(ch); // ch변수의 데이터를 파일로 출력한다.
				if((ch-64) % 6 == 0) {
					fos.write('\n');
				}
			}
			
			System.out.println("출력작업 완료..");
			fos.close(); // 쓰기 작업 완료 후 스트림 닫기
			
			// =========================================================
			// 위에서 저장한 파일을 얼겅와화면에 출력하시오.
			
			File file = new File("D:/D_Other/out.txt");
			FileInputStream fis = new FileInputStream(file);
			
			System.out.println("읽어온 파일 출력");
			int c;
			while((c = fis.read()) != -1) {
				System.out.print((char)c);
			}
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
