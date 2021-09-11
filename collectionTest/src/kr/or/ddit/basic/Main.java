package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static Integer dp[][][];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp = new Integer[101][101][101];
		
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		System.out.println(solve(a,b,c));
		
	}

	private static int solve(int a, int b, int c) {
		// TODO Auto-generated method stub
		return dp[a][b][c];
	}
}
