package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeNum {
	
	static boolean prime[];
	
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str[] = br.readLine().split(" ");
		
		int m = Integer.parseInt(str[0]);
		int n = Integer.parseInt(str[1]);
		
		prime = new boolean[n + 1];
		prime();
		
		for(int i =m; i <=n; i++) {
			if(!prime[i]) {
				System.out.println(i);
			}
		}
	}
	public static void prime() {
		
		prime[0] = prime[1] = true;
		
		for(int i = 2; i<Math.sqrt(prime.length); i++) {
			if(prime[i])continue;
			for(int j = i*i; j<prime.length; j+= i) {
				prime[j] = true;
			}
		}
	}
}
