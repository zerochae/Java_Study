package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
	
	public static void main(String[] args) {
		
		List<Horse2> list = new ArrayList<Horse2>();
		
		list.add(new Horse2("1번마"));
		list.add(new Horse2("2번마"));
		list.add(new Horse2("3번마"));
		list.add(new Horse2("4번마"));
		list.add(new Horse2("5번마"));
		list.add(new Horse2("6번마"));
		list.add(new Horse2("7번마"));
		list.add(new Horse2("8번마"));
		list.add(new Horse2("9번마"));
		list.add(new Horse2("0번마"));
		
		for(Horse2 h : list) {
			h.start();
		}
		
		Print print = new Print(list);
		print.start();
		
		Clear clear = new Clear();
		clear.start();
		
	}

}

class Horse2 extends Thread implements Comparable<Horse2>{
	
	private String name;
	private int rank = 0;
	private int location = 0;
	public volatile boolean goal = false;
	
	public Horse2(String name){
		this.name = name;
	}
	
	Random rnd = new Random();
	@Override
	public void run() {
		int cnt = 0;
		while(true) {
			location += cnt;
			try {
				Thread.sleep(1000 * (int)rnd.nextInt(4));
			}catch (InterruptedException e) {			}
			if(location == 50) {
				break;
			}
			cnt++;
		}
	}

	@Override
	public int compareTo(Horse2 o) {
		return 0;
	}
	
	public String getHName() {
		return name;
	}
	
	public boolean isGoal() {
		return goal;
	}
	public void setGoal (boolean goal) {
		this.goal = goal;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getLocation() {
		return location;
	}
	
}

class Print extends Thread{
	List<Horse2> list;
	
	public Print(List<Horse2> h1) {
		this.list = h1;
	}
	
	@Override
	public void run() {
		String arr[] = new String[50];
		int rank = 1;
		boolean ing = true;
		while(ing) {
			
			for(Horse2 h2 : list) {
				if(h2.isGoal() == true) {
					System.out.print(h2.getHName() + " : ");
					for(int j =0; j<50; j++) {
						arr[j] = "*";
						System.out.print(arr[j]);
					}
					System.out.println();
					continue;
				}
				
				System.out.print(h2.getHName() + " : ");
				for(int i =0; i<50; i++) {
					arr[i] = "-";
					if(h2.getLocation() == i) {
						arr[i] = ">";
					}
				}
				
				for(int j=0; j<50; j++) {
					System.out.print (arr[j]);
				}
				System.out.println();
				
				if(h2.getLocation() >= 50) {
					h2.setRank(rank);
					rank++;
					h2.setGoal(true);
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class Clear extends Thread{
	@Override
	public void run() {
		try {
			while(true) {
				for(int i =0; i<80; i++) {
					System.out.println();
				}
				Thread.sleep(999);
			}
		} catch (Exception e) {
		}
	}
}
