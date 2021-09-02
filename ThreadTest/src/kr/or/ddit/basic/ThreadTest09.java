package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 	
 	컴퓨터와 가위 바위 보를 진행하는 프로그램 작성하기
 	
 	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
 	사용자의 입력은 showInputDialog()메서드를 이용해서 입력 받는다.
 	
 	입력 시간은 5초로 제한하고 카운트 다운을 한다.
 	5초안에 입력이 없으면 게임에 진것으로 처리한다.
 	
 	5초안에 입력이 완료되면 승패를 구해서 출력한다.

 	결과예시) 
 		
 		- - 결과 - - 
 		컴퓨터 : 가위
 		사용자 : 바위
 		결  과 : 당신이 이겼어.
 		
 		5초안에 입력을 못했을 경우
 		- - 결과 - -
 		시간초과로 당신이 졌어요.
 
 */

public class ThreadTest09 {

	public static void main(String[] args) {
		
		new ThreadTest09().StartGame();
		
	}
	
	
	public void StartGame() {
		
		new InputData().start();
		new Count().start();
		
	}
}




class InputData extends Thread {

	public static String input;
	public static String com;
	
	public static boolean check;
	
	Random rnd = new Random();
	
	@Override
	public void run() {
		
		input = JOptionPane.showInputDialog(" 가위 바위 보! ");
		check = true;
		Result(InGame());

	}
	
	private String InGame() {
		
		int num = rnd.nextInt(3)+1; //1 : 가위 2 : 바위 3 : 보
		switch (num) 
		{
		case 1: com = "가위";
		break;
		case 2: com = "바위";
		break;
		case 3: com = "보";
		break;
		}
		
		String result = null;
		
		if(com.equals(input)) {
			result = "무승부";
			
		} else if (
				(com.equals("가위") && input.equals("바위")) ||
				(com.equals("바위") && input.equals("보"))   ||
				(com.equals("보") && input.equals("가위"))
				) {
			result = "니가 이겼어";
			
		} else if (
				(com.equals("가위") && input.equals("보"))   ||
				(com.equals("바위") && input.equals("가위")) ||
				(com.equals("보") && input.equals("바위"))
				) {
			result = "니가 졌어";
			
		} else {
			
			result = "잘못 입력해서 니가 졌음";
		}
		
		return result;
	}
	
	private void Result(String result) {
		
		System.out.println();
		System.out.println("==========================");
		System.out.println("========== 결과 ==========");
		System.out.println("컴퓨터    :    " + com);
		System.out.println("사용자    :    " + input);
		System.out.println("결  과    :    " + result);
		System.out.println("==========================");
		System.out.println("==========================");
	}
}

class Count extends Thread {

	@Override
	public void run() {

		for (int i = 5; i > 0; i--) {
			try {
				System.out.println(i + "초 남았습니다. 빨리 입력해");
				Thread.sleep(1000);
				if (InputData.check == true) {
					return;
				}
			} catch (Exception e) {
			}
		}
		System.out.println("시간 초과로 니가 졌음ㅋㅋ");
		System.exit(0);
	}
}

