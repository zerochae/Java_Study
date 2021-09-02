package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	
	public static void main(String[] args) {
		
		try {
			
			FileOutputStream fout = new FileOutputStream("D:/D_Other/bufferTest.txt");
			
			//버퍼의 크기가 5인 버퍼스트림 객체 생성
			// ==> 버퍼의 크기를 지정하지 않으면 기본 크기로 8Kb(8196byte)로 지정된다.
			BufferedOutputStream bout = new BufferedOutputStream(fout,5);
			
			System.out.println("작업 시작...");
			
			for(int i='1'; i<='9'; i++) {
				bout.write(i);
			}
			
			//bout.flush(); //버퍼에 남아 있는 데이터를 모두 강제로 출력ㄱ 시킨다.
			
			System.out.println("작업 끝..");
			
			//fout.close();
			bout.close(); // 보조스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다.
						  // 버퍼스트림의 close()에는 flush()기능도 있다.	
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
