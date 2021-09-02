package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Zero {

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Integer> stack = new LinkedList<Integer>();

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				stack.pop();
			} else {
				stack.push(num);
			}
		}
		int sum = 0;
		while (stack.size() != 0) {
			sum += stack.pop();
		}
		System.out.println(sum);
	}
}
