package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/*
 
 	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하라
 		  컴퓨터의 숫자는 난수를 이용하여 구한다.
 		  (스트라이크 S, 볼은 B로 나타낸다.)
 		  
 	예시) 컴퓨터의 난수 ==> 9 5 7
 	
 	실행예시)
 	숫자입력 => 3 5 6
 	3 5 6 ==> 1S 0B
 	숫자입력 ==> 7 8 9
 	7 8 9 ==> 0S 2B
 	숫자입력 => 9 7 5
 	9 7 5 ==> 1S 2B
 	숫자입력 => 9 5 7
 	9 5 7 ==> 3S 0B
 	
 	추카추카
 	
 
 */

public class BaseBallTest {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		HashSet<Integer> hashSet = new HashSet<Integer>();
		Random random = new Random();

		while (hashSet.size() < 3) {
			hashSet.add(random.nextInt(9) + 1);
		}

		ArrayList<Integer> list = new ArrayList<Integer>(hashSet);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		Collections.shuffle(list);
		System.out.println(list);

		int S = 0;
		int B = 0;

		while (S != 3) {
			list2.clear();
			S = 0;
			B = 0;
			
			while(list2.size() != 3) {
				list2.add(scan.nextInt());
			}
			
			for(int i =0; i<3; i++) {
				for(int j =0; j<3; j++) {
					if(list.get(i) == list2.get(i)) {
						if(i == j) {
							S++;
						} else {
							B++;
						}
					}
				}
			}
			System.out.println("S : " + S + "/ B : " + B);
		}
		System.out.println(" 3S 축하합니다");
	}

	public void inputNum() {
		int n1, n2, n3;

		do {
			
			System.out.println("숫자입력 =>");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();

			if (n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 숫자는 입력할 수 없어요");
				System.out.println("다시 입력해");
			}
		} while (n1 == n2 || n1 == n3 || n2 == n3);

		ArrayList<Integer> userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
}
