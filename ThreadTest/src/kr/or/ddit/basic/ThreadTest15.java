package kr.or.ddit.basic;

//쓰레드에서 객체를 공통으로 사용하는 예제

/*
 	
 	원주율을 계산하는 쓰레드와
 	계산된 원주율을 출력하는 쓰레드가 있다.
 	
 	원주율을 저장하는 객체가 필요하다.
 	
 	이 객체를 두 쓰레드가 공통으로 사용해서 처리한다.
 	
 */

public class ThreadTest15 {

	public static void main(String[] args) {
		
		ShareData sd = new ShareData();
		
		// 쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		
		CalcPIThread ct = new CalcPIThread();
		
		ct.setSd(sd); // sd 주입
		
		PrintPIThread pt = new PrintPIThread(sd); // sd 주입
		
		ct.start();
		pt.start();
		

	}

}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread {
	private ShareData sd;
	
	
	// 공통으로 사용할 객체를 초기화
	
	// 방법 1) setter를 이용
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		if(sd == null) {
			System.out.println("원주율 계산 오류");
			return;
		}
		
		
		/*
		 
		 	PI = 
		 
		 */
		
		double sum = 0.0;
		for(int i =1; i<2100000000; i+=2) {
			if((i/2)%2 == 0) { // 짝수 이면
				sum += (1.0/i);
			} else {
				sum -= (1.0/i);
			}
		}
		
		sd.result = sum * 4; // 계산 완료
		sd.isOk = true;
		
	}
	
	
}
// 계산이 완료되면 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;
	
	//공통으로 사용할 객체를 초기화
	
	// 방법2) 생성자를 이용하여 초기화하기
	
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk == true) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println(" PI : " + Math.PI);
		System.out.println("오차 : "  + (Math.PI - sd.result));
	}
	
}

// 원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData {

	public double result; // 계산된 원주율이 저장될 변수
	
	//volatile ==> CPU의 각 코어에 캐시가 있는데 이 캐시를 사용하지 않고 직접 메모리에서 데이터값을 입출력
	
	// 계산이 완료되었는지를 나타내는 변수(완료되면 true가 된다)
	public volatile boolean isOk = false; // 계산이 완료되었는지를 나타내는 변수(완료되면 true가 된다.)
	
	
}