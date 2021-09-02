package kr.or.ddit.basic;

import java.util.Random;

/*
 
 	3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데 
 	출력을 끝낸 순서대로 결과를 나타내는 프로그램을 구현하라
 	
*/

public class ThreadTest13 {
	
	static boolean ending = false;

	public static void main(String[] args) {
		
		DisplayCharacter[] dis = new DisplayCharacter[] {
				new DisplayCharacter("사랑해"),
				new DisplayCharacter("그리고"),
				new DisplayCharacter("기억해")
		};
		
		for (DisplayCharacter dc : dis) {
			dc.start();
		}
		for (DisplayCharacter dc : dis) {
			try {
				dc.join();
			}catch (Exception e) {
			}
		}
		System.out.println("경기 결과");
		System.out.println("순위 : " + DisplayCharacter.setRank);
	}
	
}

// A ~ Z까지 출력하는 쓰레드

class DisplayCharacter extends Thread{
	public static String setRank = ""; // 출력을 마친 순서대로 나타낼 변수
	private String name; // 이름이 저장될 변수
	
	//생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		Random rnd = new Random();
		for(char c ='A'; c <= 'Z'; c++) {
			System.out.println(name +"의 출력 문자 : " + c);
			try {
				// 일시정지 시간을 난수를 이용하여 지정한다.
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {}
		}
		System.out.println(name + "출력 끝.....");
		
		//출력을 끝낸 순서대로 이름을 배치한다.
		setRank += name + " ";
	}
}