package kr.or.ddit.basic;

public class ThreadTest06 {
	
	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		
		//데몬 쓰레드로 설정하기
		// ==> 이 메서드는 반드시 start()메서드 호출전에 설정한다.
		autoSave.setDaemon(true);
		
		autoSave.start();
		
		try {
			for(int i =1; i<=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("main 쓰레드 종료...");
			
	}

}

// 자동 저장하는 쓰레드 (3초에 한번씩 자동 저장하는 쓰레드)
class AutoSaveThread extends Thread{
	
	// 작업 내용을 저장하는 메서드
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

			}
			save();
		}
	}
	
}
