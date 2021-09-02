package kr.or.ddit.basic;

public class Main {
	
	public static void main(String[] args) {
		
		int money = 20;
		int price = 3;
		int cnt = 4;
		int sum = 0;
		int count = 1;
		while(cnt --> 0) {
			sum += (price * count);
			count++;
		}
		int result = 0;
		if(sum > money) {
			result = sum - money;
		} else {
			result = 0;
		}
		System.out.println(result);
	}
}
