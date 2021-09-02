package kr.or.ddit.basic;

public class ThreadTest02 {
/*
	멀티 쓰레드 프로그램
	
	 Thread를 사용하는 방법
	
	 방법1)
	 Thread클래스를 상속한 class를 작성한 후 이 class의 인스턴스를
	 생성한 후 이 인스턴스의 start()메서드를 호출해서 실행한다.
	
	 방법2)
	 Runnable인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를
	 생성한 후에 Thread객체를 생성할 때 Thread객체의 생성자에 넣어서 생성한 후
	 이 Thread객체에 start()메서드를 호출해서 실행한다.	
	 
	 방법3)
	 익명구현체 

*/
	public static void main(String[] args) {
		//방법1)
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		//방법2)
		MyRunner runner = new MyRunner();
		Thread th2 = new Thread(runner);
		th2.start();
		
		//방법3)
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for(int i =0; i<200; i++) {
					System.out.print("@");
					try {
						//Thread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다
						// 시간은 millisecond(1/1000초) 단위를 사용한다.
						// 즉, 1000은 1초를 의미한다.
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		Thread th3 = new Thread(r);
		th3.start();
		
		System.out.println("Main Method end");
		
		
	}
}

//방법1)
class MyThread1 extends Thread{
	@Override
	public void run() {
		//이 run()메서드 안에 쓰레드에서 처리할 내용을 기술한다.
		for(int i =0; i<200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다
				// 시간은 millisecond(1/1000초) 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

//방법2)
class MyRunner implements Runnable {
	@Override
	public void run() {
		//이 run()메서드 안에 쓰레드에서 처리할 내용을 기술한다.
		for(int i =0; i<200; i++) {
			System.out.print("$");
			try {
				//Thread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다
				// 시간은 millisecond(1/1000초) 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
