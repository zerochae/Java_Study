package kr.or.ddit.basic;

public class ThreadTest21 {
	
	public static void main(String[] args) {
		 
		
		DataBox dataBox = new DataBox();
		
		DataInputThread input = new DataInputThread(dataBox);
		DataOutputThread output = new DataOutputThread(dataBox);
		
		input.start();
		output.start();
	}
}

// 데이터를 공통으로 사용하는 클래스

class DataBox{
	private String data;
	
	// data값이 null이면 data변수에 문자열이 채워질때까지 기다리고.
	// data변수에 값이 있으면 해당 문자열 반환한다.
	// 문자열을 반환한 후에는 data변수값을 null로 만든다.
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		String temp = data;
		System.out.println("Thread에서 읽은 데이터 : " + data);
		data = null;
		notify();
		return temp;
	}
	
	// data변수에 값이 있으면 data변수값이 null이 될때까지 기다리고
	// data 변수의 값이 null이되면 새로운 data값을 저장한다.
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		this.data = data;
		System.out.println("Thread에서 새로 저장한 데이터 : " + data);
		notify();
	}
}

//데이터를 공급하는 스레드

class DataInputThread extends Thread{
	private DataBox databox;

	public DataInputThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i =1; i<=3; i++) {
			databox.setData(i + "번째 Data");
		}
	}
}

//데이터를 꺼내가는 쓰레드

class DataOutputThread extends Thread{
	private DataBox databox;

	public DataOutputThread(DataBox databox) {
		this.databox = databox;
	}

	@Override
	public void run() {
		for(int i =1; i<=3; i++) {
			String data = databox.getData();
			// 가져온 데이터를 처리하는 내용을 기술한다.
		}
	}
	
}
