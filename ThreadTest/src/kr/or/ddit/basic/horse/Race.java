package kr.or.ddit.basic.horse;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Race {

	public static void main(String[] args) {

		Horse[] horse = new Horse[] {

				new Horse("01번말"), new Horse("02번말"), new Horse("03번말"), new Horse("04번말"), new Horse("05번말"),
				new Horse("06번말"), new Horse("07번말"), new Horse("08번말"), new Horse("09번말"), new Horse("10번말"),

		};
		
		for (Horse h : horse) {
			h.start();
		}
		
		GameStateThread gameStateThread = new GameStateThread(horse);
		
		gameStateThread.start();
		
			try {
				for (Horse h : horse) {
				h.join();
				}
				gameStateThread.join();
			} catch (InterruptedException e) {}
		
			System.out.println();
			System.out.println("경기 끝..");
			System.out.println();
			
			// 등수의 오름차순으로 정렬
			
			Arrays.sort(horse);

			
			for (Horse h : horse) {
				System.out.println(h.toString());
			}
		
		

	}

}

class Horse extends Thread implements Comparable<Horse> {

	public static int currentRank = 0; // 각각의 말이 경기가 끝나면 1씩 증가, 즉 말의 등수를 구하는데 사용된다.
	
	private String horseName; // 말이름
	private int location; // 현재위치
	private int rank; // 등수

	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는)" + rank + "등 입니다.";

	}

	@Override
	public int compareTo(Horse o) {
		return this.rank - o.getRank();
	}

	@Override
	public void run() {
		Random rnd = new Random();
		for (int i = 0; i <= 50; i++) {
			this.location = i;
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
			}
		}

		// 한 마리의 말이 경주가 끝나면 등수를 구해서 저장한다
		currentRank++;
		this.rank = currentRank;

	}
}

// 경기 중 말의 위치를 표시하는 쓰레드

class GameStateThread extends Thread {
	private Horse[] horse; // 경기에 참가한 말들을 저장할 배열

	public GameStateThread(Horse[] horse) {
		this.horse = horse;
	}

	@Override
	public void run() {

		while (true) {
			
			if(Horse.currentRank == horse.length) {
				break;
			}
			
			for(int i=0; i<80; i++) {
				System.out.println();
			}
			
			for (int i = 0; i < horse.length; i++) {
				System.out.print(horse[i].getHorseName() + " : ");
				for (int j = 1; j <= 50; j++) {
					if (horse[i].getLocation() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

}
