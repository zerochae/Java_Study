package kr.or.ddit.basic;

/*
 
 	wait(), notity()메서드드를 이용해서 두 번 쓰번 쓰기
 
 */

public class ThreadTest20 {
	
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
	}

}

//공통으로 사용할 객체

class WorkObject{
	
	public synchronized void methodA() {
		System.out.println("우리 영채 스쿼트 중");
		
		notify();
		try {
			wait();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public synchronized void methodB() {
		System.out.println("우리 영채 뻠삥 중");
		
		notify();
		try {
			wait();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

//WorkObject의 A메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workobj;

	public ThreadA(WorkObject workobj) {
		super();
		this.workobj = workobj;
	}
	
	@Override
	public void run() {
		for(int i =1; i<=10; i++) {
			workobj.methodA();
		}
		synchronized (workobj) {
			workobj.notify();
		}
	}
}

//WorkObject에서 B메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workobj;

	public ThreadB(WorkObject workobj) {
		super();
		this.workobj = workobj;
	}
	
	@Override
	public void run() {
		for(int i =1; i<=10; i++) {
			workobj.methodB();
		}
		synchronized (workobj) {
			workobj.notify();
		}
	}
}