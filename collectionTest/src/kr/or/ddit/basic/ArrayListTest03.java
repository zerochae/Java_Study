package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 
 *  1) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 별명중에 별명의 길이가 제일 긴 별명을 출력하시오
 * 
 * 	2) 1에서 별명의 길이가 같은 것이 있을 경우을 입력할 수 있을 경우 대해 처리하시오
 * 
 */	

public class ArrayListTest03 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
//		
		ArrayList<String> list = new ArrayList<>();
//		
//		int max = 0;
//		
//		for(int i =0; i<5; i++) {
//			String input = scan.next();
//			list.add(input);
//			if(list.get(i).length() > max) {
//				max = list.get(i).length();
//			}
//		}
//		
//		for(int i =0; i<5; i++) {
//			if(list.get(i).length() == max) {
//		System.out.println(list.get(i));
//			}
//		}
		
		int max = 0;
		
		for(int i =0; i<5; i++) {
			String input = scan.next();{
				list.add(input);
				if(list.get(i).length() > max ) {
					max = list.get(i).length();
				}
			}
		}
		
		for(int i = 0; i<5; i++) {
			if(list.get(i).length() == max) {
				System.out.println(list.get(i));
			}
		}
	}
}
