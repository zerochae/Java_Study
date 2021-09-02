package kr.or.ddit.basic.horse;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new Helper();
		timer.schedule(task, 3000, 5000);
		
	}
}

class Helper extends TimerTask {

	int i = 0;

	@Override
	public void run() {
		System.out.println("Timer Task running :  "+ new Date() + i++);
	}

}