package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataOIOTest {
	 
	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("D:/D_Other/test.dat");
			
			//자료형 단위로 출력할 보조스트림 DataOutputStream객체 생성
			DataOutputStream dos = new DataOutputStream(fout);
			
			dos.writeInt(400); // 정수형 출력
			dos.writeFloat(3.141592f); //실수형(float)으로 출력
			dos.writeBoolean(true); //논리형으로 출력
			dos.writeUTF("ABCD안녕abcd"); //문자열형식으로 출력
			
			System.out.println("출력 완료...");
			dos.close(); // 스트림 닫기
			//-------------------------------------------
			
			 
			
			//출력한 자료 읽어오기
			DataInputStream dis = new DataInputStream(new FileInputStream("D:/D_Other/test.dat"));
			
			// DataInputStream으로 자료를 읽어올 때는
			// 출력할 때의 순서와 같은 순서로 일거와야 한다.
			System.out.println("정수형 : " + dis.readInt());
			System.out.println("실수형 : " + dis.readFloat());
			System.out.println("논리형 : " + dis.readBoolean());
			System.out.println("문자열 : " + dis.readUTF());
			System.out.println("읽기 작업 끝..");
			dis.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
