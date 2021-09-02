package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Deque {

	static int arr[] = new int[10000];
	static int index = 0;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = 0;
			String[] str = br.readLine().split(" ");
			if (str.length == 2) {
				num = Integer.parseInt(str[1]);
			}
			switch (str[0]) {
			case "push_back":
				push_back(num);
				break;
			case "push_front":
				push_front(num);
				break;
			case "pop_back":
				pop_back();
				break;
			case "pop_front":
				pop_front();
				break;
			case "size":
				size();
				break;
			case "empty":
				empty();
				break;
			case "front":
				front();
				break;
			case "back":
				back();
				break;
			}
		}
	}

	static public void push_back(int x) {
		index++;
		int n = 0;
		for(int i =0; i<index; i++) {
			if(arr[i] != 0) {
				n++;
			} else {				
				break;
			}
		}
		if(n == 0) {
			arr[0] = x;
		} else {
			arr[n-1] = x;
		}
	}

	static public void push_front(int x) {
		int temp = arr[index];

		for (int i = index; i >= 1; i--) {
			arr[index] = arr[index - i];
		}
		arr[++index] = temp;
		arr[0] = x;
	}

	static public void pop_back() {

		int n = 0;
		int sum = 0;

		for (int i = 0; i < index; i++) {
			sum += arr[i];
			if (arr[i] != 0) {
				n++;
			} else {
				break;
			}
		}
		if (sum == 0) {
			System.out.println(-1);
			return;
		} else {
			System.out.println(arr[n-1]);
		}
		arr[n-1] = 0;
	}

	static public void pop_front() {
		
		int n = 0;
		int sum = 0;
		for (int i = 0; i <= index; i++) {
			sum += arr[i];
			if (arr[i] != 0) {
				n++;
			} else {
				break;
			}
		}
		if (sum == 0) {
			System.out.println(-1);
		} else {	
			System.out.println(arr[0]);
			for (int i = 0; i < n; i++) {
				arr[i] = arr[i + 1];
			}
			arr[n-1] = 0;
		}

	}

	static public void size() {
		int n = 0;
		for (int i = 0; i <= index; i++) {
			if (arr[i] != 0) {
				n++;
			} else {
				break;
			}
		}
		System.out.println(n);
	}

	static public void empty() {

		int sum = 0;
		for (int i = 0; i <= index; i++) {
			sum += arr[i];
		}
		if (sum == 0) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static public void front() {

		if (arr[0] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(arr[0]);
		}
	}

	static public void back() {
		int n = 0;
		int sum = 0;
		for (int i = 0; i <= index; i++) {
			sum += arr[i];
			if (arr[i] != 0) {
				n++;
			} else {
				break;
			}
		}
		if (sum == 0) {
			System.out.println(-1);
		} else {
			System.out.println(arr[n - 1]);
		}
	}

}
