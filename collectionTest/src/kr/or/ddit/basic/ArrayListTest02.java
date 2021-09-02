package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제) 5명의 사람 이름을 입력받아ArrayList에 저장한 후에 이들중 '김'씨 성의 이름을 모두 출력하시오
 */

public class ArrayListTest02 {

	public static void main(String[] args) {
//
		Scanner scan = new Scanner(System.in);
//
		ArrayList<String> list = new ArrayList<>();
//		
		for(int i =0; i<5; i++) {
			String input = scan.next();
			list.add(input);
			if (list.get(i).split("")[0].contains("김")) {
				System.out.println(list.get(i));
			}
//			if(list.get(i).substring(0, 1).equals("김")) {
//				System.out.println(list.get(i));
//			}
//			if(list.get(i).charAt(0) == '김') {
//				System.out.println(list.get(i));
//			}
//			if(list.indexOf("김") == 0) {
//				System.out.println(list.get(i));
//			}
//			if(list.get(i).startsWith("김")) {
//				System.out.println(list.get(i));
//			}
			
		}
		
		
	}
}
