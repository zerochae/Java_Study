package kr.or.ddit.basic;

public class ThreadTest04 {
	
	
	
	
	/*
	 
	 	1 ~ 20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리했을 때와
	 	여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 보자
	 
	 */
		
	public static void main(String[] args) {
		
		//단독으로 처리하기
		SumThread sumTh = new SumThread(1L, 2_000_000_000L);
		
		long startTime = System.currentTimeMillis();
		sumTh.start();
		
		
		try {
			sumTh.join();
		} catch (InterruptedException e) {
		
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 떄의 경과시간 : " + (endTime - startTime));
		System.out.println("==========================================================");
		
		// 여러 쓰레드가 협력해서 처리하기
		
		SumThread[] smArr = new SumThread[] {
			new SumThread(500_000_001L, 1_000_000_000L),	
			new SumThread(1_000_000_001L, 1_500_000_000L),	
			new SumThread(1_500_000_001L, 2_000_000_000L),	
			new SumThread(1L, 500_000_000L),	
		};
		
		startTime = System.currentTimeMillis();
		
		for (SumThread sumThread : smArr) {
			sumThread.start();
		}
		for(int i =0; i<smArr.length; i++) {
			try {
				smArr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리했을 떄의 경과시간 : " + (endTime - startTime));
		
	}
}

//합계를 구하는 쓰레드
class SumThread extends Thread{
	
	
	// 합계를 구할 영역의 시작값고 ㅏ종료값이 저장될 변수 선언
	private long startNum, endNum;
	
	public SumThread(long startNum, long endNum) {
		
		this.startNum = startNum;
		this.endNum = endNum;
	}
	@Override
	public void run() {
		long sum = 0L;
		for(long i =startNum; i<=endNum; i++) {
			sum += i;
		}
		System.out.println(startNum + "부터 " + endNum + "까지의 합계 : " + sum );
		
	}
}
