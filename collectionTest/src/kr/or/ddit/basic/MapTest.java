package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {
	
	public static void main(String[] args) {
		
		/*
		
			
		 
		 
		 
		 */
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		//자료 추가 ==> put(key값, value값)
		
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map ==> " + map);
		
		//자료 수정 ==> 데이터를 추가할 때 key값이 같으면 나중에 추가한 값이 저장된다.
		
		map.put("addr", "서울");
		System.out.println("map ==> " + map);
		
		// 자료 삭제 ==> remove(key값) : key값이 같은 자료를 찾아서 삭제한다.
		// 반환값 : 삭제된 자료의 value값
		
		String removeTel = map.remove("tel");
		System.out.println("map ==> " + map);
		System.out.println("삭제된 값 : " + removeTel);
		
		//자료 읽기 ==>  get(key값) : key값과 같이 저장된 value값을 반환한다.
		// 반환값 : value / key값이 없으면 null
		System.out.println("이름 : " + map.get("name"));
		System.out.println("나이 : " + map.get("나이"));
		
		// key값의 존재 여부를 나타내는 메서드 : containKey(key값)
		// ==> 지정한 key값이 있으면 true, 없으면 false
		
		System.out.println("tel 키값의 존재 여부 : " + map.containsKey("tel"));
		System.out.println("age 키값의 존재 여부 : " + map.containsKey("age"));
		
		//Map에 저장된 모든 데이터를 읽어와 처리하기
		
		//방법1) keySet() 메서드 이용하기
		//		==> Map의 모든 key값을 읽어와 Set형으로 반환한다.
		
		System.out.println("=================================================");
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("=================================================");
		//방법2 ) KeySet을 향상된 for문으로 사용하기
		for (String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " = " + value);
		}
		//방법3 ) value값만 읽어와 처리하기 ==> values()메서드 이용
		System.out.println("=================================================");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("=================================================");
		
		/*
		  방법4) Map에는 Entry라는 내부 class가 만들어져 있다.
		  이 Entry클래스는 key라는 변수와 value라는 변수로 구성되어 있다.
		  Map에서는 이 Entry클래스를 Set형식으로 저장하여 관리한다.
		  
		  - Entry 객체 전체를 가져오기(가져온 Entry 객체들은 Set형식으로 되어있다.)
		  ==> entrySet()메서드를 이용한다.
		 */

		// Entry라는 내부객체 전체 가져오기
		Set<Map.Entry<String,String>> mapSet = map.entrySet();
		
		Iterator<Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()) {
			// Entry객체 1개 구하기
			Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		
		System.out.println("=================================================");
	}

}
