package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();

		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
				
		System.out.println("한글 모든 경우의 수 : 11172");
				
		System.out.println("정렬전 : " + list);
		
		//정렬은 Collections.srot()메서드를 이용하여 정렬한다.
		//Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬한다.
		// 자바의 기본 데이터들은 오름차순 정렬이 되도록 내부 정렬 기준이 만들어져 있따.
		
		Collections.sort(list);
		
		System.out.println("정렬후 : " + list);
		
		System.out.print("내림차순 정렬 : [");
		
		for(int i =list.size()-1; i>=0; i--) {
			System.out.print(list.get(i)+" ");
		}
		System.out.print("]\n");
		Collections.shuffle(list); //자료 섞기
		System.out.println("자료 섞기 후 : " + list);
		
		/* 정렬과 관련된 interFace는 Comparable, Comparator 이렇게 두가지가 있다.
		 * 
		 * Comparable은 Collection에 추가되는 데이터 자체의 정렬기준을 넣고 싶을 때 구현하는 인터페이스이다. 
		 *  ==> 내부 정렬 기준
		 *  
		 *  Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 사용한다.
		 *  ==> 외부 정렬 기준
		 * 
		 * Comparable에서는 CompareTo()메서드를 재정의하고,
		 * Comparator에서는 Compare()메서드를 재정의해서 정렬 기준을 정한다.
		 * 
		 * String클래스, Wrapper클래스, Date클래스, File클래스에는 내부 정렬 기준이
		 * 구현되어 있다.
		 * (내부 정렬 기준은 기본적으로 오름차순으로 처리되도록 구현되어 있다.)
		 * 
		 */
		
		//-----------------------------------------------------
		
		//외부 정렬 기준ㅇ르 사용해서 정렬하기
		Collections.sort(list,new Desc());
		System.out.println("내림차순 정렬 후 : " + list);
		
		
	}
}
		//외부 정렬 기준을 정해주는 클래스 작성하기
		class Desc implements Comparator<String> {
			
			/*compare()메서드의 반환값의 역할
			 * - 반환값이 0 		==> 두 값이 같다.
			 * - 반환값이 양수 		==> 두 값의 순서가 바뀐다.
			 * - 반환값이 음수		==> 두 값의 순서가 바뀌지 않는다. 
			 * 
			 * 예) 오름차순의 경우  ==> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수 반환
			 */
			
			// 111 2222 333 444 5555 6666
			@Override
			public int compare(String str1, String str2) {
//				if(str1.compareTo(str2) > 0) {
//					return -1;
//				} else if(str1.compareTo(str2) < 0) {
//					return 1;
//				} else {
//				return 0;
//				}
				return str1.compareTo(str2) * -1;
			}
		}

