package kr.or.ddit.basic;

public class ThreadTest05 {
	
	public static void main(String[] args) {
		
		Thread upth = new UpperThread();
		Thread loth = new LowerThread();
		
		// 우선순위 변경은 start()메서드 호출 전에 설정한다.
		
		upth.setPriority(9);
		loth.setPriority(3);
		
		System.out.println("upth의 우선 순위 : " + upth.getPriority());
		System.out.println("loth의 우선 순위 : " + loth.getPriority());
		
		upth.start();
		loth.start();
	}
}

//대문자 출력
class UpperThread extends Thread{
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++) {
			System.out.println(c);
			for(int i =1; i<=1_000_000_000; i++) {
				// 시간 때우기
			}
		}
	}
}

//소문자 출력
class LowerThread extends Thread{
	@Override
	public void run() {
		for(char c='a'; c<='z'; c++) {
			System.out.println(c);
			for(int i =1; i<=1_000_000_000; i++) {
				// 시간 때우기
			}
		}
	}
}