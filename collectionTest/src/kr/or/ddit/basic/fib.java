package kr.or.ddit.basic;

import java.util.Scanner;

public class fib {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		int x = Fb(n);
		
		System.out.println(x);
	}
	
	public static int Fb(int n) {
		if(n  <= 0) return 0;
		if(n ==1) return 1;
		return Fb(n-1) + Fb(n-2); 
	}
}
