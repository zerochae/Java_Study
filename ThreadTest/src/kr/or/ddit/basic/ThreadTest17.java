package kr.or.ddit.basic;

/*
 	은행의 입출금을 쓰레드로 처리하는 예제
 	(synchronized를 이용한 동기화 처리 예제)
 */

public class ThreadTest17 {

	private int balance; // 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금처리를 하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금처리를 하는 메서드(반환값 ==> 성공 : true, 실패 : false)
	public synchronized boolean withdraw(int money) {
		if (balance >= money) {
			for (int i = 1; i <= 1000000; i++) {
			} // 시간 지연용
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		
		ThreadTest17 account = new ThreadTest17();
		account.setBalance(10000); // 잔액을 10000원으로 설정
		
		//익명구현체로 쓰레드 구현
		
		Runnable test = new Runnable() {
			@Override
			public void run() {
				boolean result = account.withdraw(6000); //6000원 출금
				System.out.println("쓰레드에서 result = " + result + ", balance = " + account.getBalance());
			}
		};
		//================================
		
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
			
	}

}
