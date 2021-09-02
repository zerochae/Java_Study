
package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SetTest {
	
	public static void main(String[] args) {
		/*
		 
		 	- List와 Set의 차이점
		 	
		 	1. List
		 	
		 	- 데이터의 순서(index)가 있다.
		 	- 중복되는 데이터를 저장할 수 있다.
		 	
		 	2. Set
		 	
		 	- 데이터의 순서(index)가 없다.
		 	- 중복되는 데이터를 저장할 수 없다.

		 */
		
		HashSet hs1 = new HashSet();
		
		// set에 데이터를 추가할 때도 add()메서드를 사용한다.
		
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);

		System.out.println("set의 개수 : " + hs1.size());
		System.out.println("set 데이터 : " + hs1);
		System.out.println("========================================");
		
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("set 데이터 : " +hs1);
		System.out.println("========================================");		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("set 데이터 : " +hs1);
		System.out.println("========================================");		
	
		//Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에
		// 해당 자료를 삭제한 후 추가하는 방법을 사용한다.
		
		// 삭제하는 메서드 : remove(삭제할자료)
		// 반환값 : 삭제성공(true) 삭제실패 (false)
		// clear() : 전체 자료 삭제
		
		// "FF"데이터를 "EE"로 변경하기
		hs1.remove("FF");
		System.out.println("삭제 후 set : " + hs1);
		hs1.add("EE");
		System.out.println("set 데이터 : " + hs1);
		System.out.println("========================================");		
		
//		hs1.clear();
//		System.out.println("set 데이터 : " + hs1);
		
		/*
		 
		 - Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다.
		 - 그래서 데이터를 하나씩 얻기 위해서는 Iterator형 객체로 변환해야한다.
		 
		 - Set형의 데이터들을 Iterator형 객체로 변환하는 메서드 ==> Iterator()
		 */
		
		Iterator it = hs1.iterator();
		
		// Iterator에서 데이터를 하나씩 꺼내오기
		
		//Iterator의 hasNext() ==> Iterator의 데이터를 가리키는 포인터
		//						   이 포인터가 가르키는 곳 다음번째에 데이터가 있으면 true / 없으면 false;
		//							
		while(it.hasNext()) {
			// Iterator의 next()메서드
			// 	==> Iterator의 포인터를 다음번째 위치로 이동시키고
			//		이동한 곳의 데이터를 반환한다.
			System.out.println(it.next());
		}
		
		System.out.println("========================================");		
		
		
		
		// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성ㅎ ㅐ보자.
		// 번호는 1번부터 24번까지 있고. 추첨할 인원은 3명이다.
		// 당첨자 출력하기
		
		Random random = new Random();
		
		
		
		HashSet<Integer> testSet = new HashSet<Integer>();
		
		while(testSet.size() < 3) {
			testSet.add(random.nextInt(24) + 1);
		}
		
		Iterator<Integer> iter = testSet.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		// Set의 데이터를 하나씩 꺼내오는 방법2
		for(Object data : hs1) {
			System.out.println(data);
			
		// Set형의 자료를 List형으로 변환하기
		// List객체를 생성할 때 생성자의 인수값으로 Set데이터를 넣기
		
			ArrayList<Integer> testList = new ArrayList<Integer>(testSet);
			System.out.println("List데이터 출력");
			for(int i =0; i<testList.size(); i++) {
				System.out.println(testList.get(i));
			}
		}
	}
}
