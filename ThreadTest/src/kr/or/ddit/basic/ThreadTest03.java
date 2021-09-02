package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		
		/*
		 	
		 	Thread가 수행되는 시간 체크해보기
		 
		 */
		
		Thread th = new Thread(new SumRunner());
		
		
		// 1970년 1월 1일 0시 0분 0초(표준시간)부터 현재까지 경과한 시간
		// 밀리세컨드(1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join(); //현재 실행중인 쓰레드(여기서는 메인 쓰레드)에서 대상이 되는 쓰레드(변수 th에 저장된 쓰레드)가 종료될때까지 기다린다.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
				
		System.out.println("경과시간 : " + (endTime - startTime));
		
	}
}

class SumRunner implements Runnable{

	@Override
	public void run() {
		long sum = 0;
		for(long i = 1L; i<=100_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}
