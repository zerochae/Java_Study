package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 예제
public class ThreadTest10 {
	
	public static void main(String[] args) {
		
			TargetThread target = new TargetThread();
		
			StatePrintThread thread = new StatePrintThread(target);
			
			thread.start();
	}
}

// 쓰레드 상태의 검사 대상이 되는 쓰레드

class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i = 1L; i<=20_000_000_000L; i++) {} // 시간 지연용
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
			for(long i = 1L; i<=20_000_000_000L; i++) {} // 시간 지연용
	}
}

//TargetThread의 상태를 출력하는 쓰레드

class StatePrintThread extends Thread{
	private TargetThread target;
	
	//생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 현재 상태값 구하기 ==> getState()메서드 이용
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state == Thread.State.NEW) { // NEW상태이면
				target.start(); 			// 스레드 실행
			}
			
			if(state == Thread.State.TERMINATED) { // 종료 상태이면
				break; //무한 루프를 빠져나간다.
			} 
			
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
}
