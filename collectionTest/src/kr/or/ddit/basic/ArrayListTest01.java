package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	
	public static void main(String[] args) {
		
		// ArrayList의 기본 사용법은 Vector와 같다
		
		ArrayList list1 = new ArrayList();
		
		//add()메서드로 데이터를 추가한다.
		
		list1.add("aaaa");
		list1.add("bbbb");
		list1.add(123);
		list1.add('k');
		list1.add(true);
		list1.add(123.45);
		
		System.out.println("list1 ==> " + list1);
		System.out.println("size ==> " + list1.size());
		
		//get()메서드로 데이터를 꺼내온다.
		
		System.out.println("1번째 데이터 : " + list1.get(1));
		
		// 데이터 끼워넣기
		
		list1.add(3,"zzzz");
		System.out.println("list1 ==> " + list1);
		
		//데이터 변경하기
		
		String temp = (String)list1.set(3, "YYYY");
		System.out.println("변경 전 데이터 : " + temp);
		System.out.println("list1 ==> " + list1);
		
		// 데이터 삭제
		
		list1.remove(3);
		System.out.println("list1 ==> " + list1);
		
		list1.remove("bbbb");
		System.out.println("list1 ==> " + list1);
		System.out.println("---------------------------");
		// 제네릭
		
		ArrayList<String> list2 = new ArrayList<String>();
		
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for(int i = 0; i<list2.size(); i++) {
			System.out.println(i + "번째 데이터 ==> " + list2.get(i));
		}
		System.out.println("========================");
		for (String string : list2) {
			
			System.out.println(string);
			
		}
		
		//contains (비교객체) ==> list에 '비교객체'가 있으면 true 없으면 false를 반환한다.
		System.out.println("========================");
		System.out.println("DDDD값 존재여부 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ값 존재여부 : " + list2.contains("ZZZZ"));
		
		//indexOf(비교객체) ==> list에 '비교객체'가 있으면 '비교객체'가 있는 index값을 반환하고 없으면 -1을 반환한다
		System.out.println("DDDD값의 위치 : " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ값의 위치 : " + list2.indexOf("ZZZZ"));
		
		//toArray() ==> list안의 데이터를 배열로 변환하여 반환한다.
		//			==> 기본적으로 Object형 배열로 반환한다.
		
		Object[] strArr = list2.toArray();
		//String[] strArr = list2.toArray();
		System.out.println("==================================");
		System.out.println("배열의 개수 : " + strArr.length);
		System.out.println("==================================");
		
		
		// List의 제네릭 타입과 같은 종류의 배열로 만들기
		String[] strArr2 = list2.toArray(new String[0]);
		for(int i = 0; i<strArr2.length; i++) {
			System.out.println(i + "번째 데이터 : " + strArr2[i]);
		} 
	
	}

}
