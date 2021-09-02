package kr.or.ddit.basic;

import java.util.Scanner;

public class Star10 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int arr[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = star(i, j);
			}
		}
	}

	public static int star(int x, int y) {
		if ((x == 0) || (y == 0))
			return 0;
		if ((x == 3) && (y == 3))
			return 1;
		return star(x / 3, y / 3);
	}
}
