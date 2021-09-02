package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;

public class ThreadTest14 {

	/*
	 * 
	 * 10마리의 말들이 경주하는 프로그램을 작성하시오.
	 * 
	 * 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 이 클래스는 말이름(String), 현재위치(int), 등수(int)를 멤버변수로
	 * 갖는다. 그리고 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다. (Comparable인터페이스 구현)
	 * 
	 * 경기 구간은 1 ~ 50구간으로 되어 있다.
	 * 
	 * 경기 중 중간에 각 말들의 위치를 나타낸다. 
	 * 예) 
	 * 01번말 --->------------------------- 
	 * 02번말
	 * ----------->----------------- 
	 * ... 
	 * 10번말 ------------------->---------
	 * 
	 * 경기가 끝나면 등수 순으로 출력한다.
	 * 
	 */

	public static void main(String[] args) {

		Horse[] horse = new Horse[] {

				new Horse("1번말"), new Horse("2번말"), new Horse("3번말"), new Horse("4번말"), new Horse("5번말"),
				new Horse("6번말"), new Horse("7번말"), new Horse("8번말"), new Horse("9번말"), new Horse("10번말"), };

		for (Horse h : horse) {
			h.start();
		}

		Print print = new Print(horse);
		print.start();

		for (Horse h : horse) {
			try {
				h.join();
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
		
		Arrays.sort(horse);
		
		
		System.out.println("=============================================");
		System.out.println("==================경기 결과==================");
		for (Horse h : horse) {
			System.out.println( h.rank + "등"+  "    >>>>    " + h.getHorseName() );
		}
		System.out.println("=============================================");

	}
}

class Horse extends Thread implements Comparable<Horse> {
	public int rank;
	private String name;
	private int position;
	public boolean goal;

	public Horse() {
	}

	public Horse(String name) {
		super();
		this.name = name;
	}

	public boolean Goal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public String getHorseName() {
		return name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			position = i;
			try {
				Thread.sleep(RandomUtil.getInstance().nextInt(1000));
			} catch (InterruptedException e) {
			}
		}
		if (position == 50) {
			goal = true;
		}
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(rank, h.rank);
	}
}

class Print extends Thread {
	Horse[] horse;

	public Print(Horse[] h) {
		this.horse = h;
	}

	@Override
	public void run() {
		String arr[] = new String[50];
		int rank = 1;
		boolean ing = true;
		while (ing) {
			
			for (int i = 0; i < 100; i++) {
				System.out.println("");
			}
 
			for (Horse h : horse) {
				if (h.Goal() == true) {
					System.out.println(h.getHorseName() + " 도착 !!!!");
				} else {
					System.out.print(h.getHorseName() + " : ");
					for (int i = 0; i < 50; i++) {
						arr[i] = "-";
						if (h.getPosition() == i) {
							arr[i] = ">";
						}
					}

					for (int j = 0; j < 50; j++) {
						System.out.print(arr[j]);
					}
					System.out.println();
				}

				if (h.getPosition() == 50 && h.getRank()==0) {
					h.setRank(rank++);
					h.setGoal(true);
				}
			}

			if(rank==horse.length+1) ing = false;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class RandomUtil {
	private static RandomUtil instance = new RandomUtil();
	public static RandomUtil getInstance(){
		return instance;
	}
	Random rand = new Random();
	
	public int nextInt(int a){
		return rand.nextInt(a);
	}
}
