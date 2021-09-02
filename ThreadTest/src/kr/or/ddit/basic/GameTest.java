package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class GameTest {
	
	public static boolean inputCheck; // 입력 여부 확인용
	
	public static void main(String[] args) {
		
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서 컴퓨터의 가위 바위 보 정하기
		
		String[] data = {"가위","바위","보"};
		
		Random rnd = new Random();
		int index = rnd.nextInt(3); // 0~2 사이의 난수
		String com = data[index];
		
		// 사용자의 가위 바위 보 입력 받기
		gt.start(); // 카운트 다운 실행
		String man = "";
		do{
			man = JOptionPane.showInputDialog("가위 바위 보!");
		//}while(!(man.equals("가위") ||man.equals("바위") || man.equals("보")));
		}while(!man.equals("가위")  && !man.equals("바위") && !man.equals("보"));
		inputCheck = true;
		
		//결과 판정
		
		String result = ""; // 결과 저장 변수
		if(com.equals(man)) {
			result = "무승부";
		} else if(man.equals("가위") && com.equals("보")   ||
				  man.equals("바위") && com.equals("가위") ||
				  man.equals("보")   && com.equals("바위"))	{
			result = "당신이 이겼습니다.";
		} else {
			result = "당신이 졌습니다.";
		}
		
		//결과 출력
		
		System.out.println("-- 결과 --");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("결과 : " + result );
		
		
	}
}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작");
		for(int i =5; i>=1; i--) {
			if(GameTest.inputCheck== true) {
				return ;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("시간초과로 당신이 졌습니다.");
	}
	
}
