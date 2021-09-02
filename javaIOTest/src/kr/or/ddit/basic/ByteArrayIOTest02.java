package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		byte[] outSrc = null;

		byte[] temp = new byte[4]; // 입력용 스트림에 사용할 배열

		// 스트림 객체 생성

		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			while(input.available()>0) {
				/*
				input.read(temp);
				output.write(temp);
				*/
				
				int len = input.read(temp); // 실제 읽어온 데이터 수를 반환한다.
				
				//temp 배열의 내용 중 0번째부터 len개수만큼 출력한다.
				output.write(temp,0,len);
				
				
				System.out.println("반복문 안에서 temp : " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			
			System.out.println(" inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

		// 입력용 스트림으로 데이터를 읽어와 출력용 스트림으로 출력
		int data; // 읽어온 데이터가 저장될 변수 선언

		// read()메서드 ==> 더 이상 읽어올 데이터가 없으면 1을 반환한다.
		while ((data = input.read()) != -1) {
			output.write(data); // 읽어온 데이터 출력하기
		}

		// 출력된 스트림값을 배열로 변환하기
		outSrc = output.toByteArray();

		// 입출력 작업을 모두 마치면 사용했던 스트림을 닫아 준다.
		// ==> 자원 반납
		try {
			input.close();
			output.close();
		} catch (Exception e) {
		}
		

	}

}
