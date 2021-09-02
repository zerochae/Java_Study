package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest01 {

	public static void main(String[] args) {
		//객체 생성
		Vector v1 = new Vector();
		System.out.println("처음 크기 : " + v1.size());
		// 데이터 추가 : add(추가할 데이터)
		// 반환값 : 성공(true) / 실패(false)
		v1.add("aaa");
		v1.add(new Integer(111));
		v1.add(123); //오토박싱이 이루어진다.
		v1.add('a');
		v1.add(3.14);
		boolean r = v1.add(false);
		
		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r);
		System.out.println("v1 => " + v1);
		System.out.println();
		
		//데이터 추가2 : addElement(추가할 데이터)
		// ==> 이전 버전에서 지원하는 메서드
		// ==> 이전 버전으로 작성된 프로그램을 위해서 남겨놓은 메서드
		v1.addElement("CCCC");
		System.out.println("v1 ==>" + v1);
		
		// 데이터 추가2 : add(index,데이터)
		// ==> 'index'번째 '데이터'를 끼워 넣는다.
		// ==> 'index'는 0번부터 시작한다.
		// ==> 반환값은 없다.
		
		v1.add(1,"kk");
		System.out.println("v1 ==>" + v1);
		
		//데이터 꺼내오기 : get(index);
		// ==> 'index'번째 데이터를 반환
		int data = (int) v1.get(3);
		System.out.println("3번째 데이터 : " + data);
		
		// 데이터 변경하기 : set(index, 새로운데이터);
		// ==> 'index' 번째의 데이터를 '새로운데이터'로 덮어쓴다.
		// ==> 반환값 : 원래의 데이터
		String temp = (String) v1.set(0, "zzzz");
		System.out.println("v1 ==> " + v1);
		System.out.println("반환값 : " + temp);
		// 데이터 삭제하기 : remove(index);
		// ==> 'index'번째의 데이터를 삭제한다.
		// ==> 반환값 : 삭제된 데이터
		
		temp = (String) v1.remove(0);
		System.out.println("v1 ==>" + v1);
		System.out.println("반환값 : " + temp);
		
		//데이터 삭제하기2 : remove(삭제할데이터);
		// ==> '삭제할 데이터'를 찾아 삭제한다.
		// ==> '삭제할 데이터'가 여러개이면 앞에서부터 삭제된다.
		// ==> 반환값 : 삭제성공(true) / 삭제실패(false)
		// 삭제할 데이터가 '정수형'이거나 'char형'일 경우에는 
		// 반드시 객체로 변환해서 사용해야 한다다
		v1.remove("CCCC");
		System.out.println("삭제후 : " + v1);
		
		v1.remove(new Integer(123));
		System.out.println("삭제후 : " + v1);
		
		v1.remove(new Character('a'));
		System.out.println("삭제후 : " +v1);
		
		v1.remove(false);
		v1.remove(3.14);
		System.out.println("삭제후 : " +v1);
		
		//전체 데이터 삭제 : clear();
		v1.clear();
		System.out.println("전체 삭제 후 : " + v1);
		
		//--------------------------------------------------------------
		
		/* 제네릭(Generic Type) ==> 클래스 내부에서 사용할 데이터의 타입을 외부에서 지정
		 * 						==> 객체를 선언할 때 < >안에 그 객체가 사용할 데이터의 타입을 정해주는 것을 말한다.
		 * 						==> 객체를 선언하면 다른 종류의 데이터를 저장할 수 없다
		 * 						==> 제너릭으로 선언할 수 있는 데이터 타입은 클래스형이어야 한다
		 * 							(int ==> Integer, boolean ==> Boolean, char ==> Character 등)
		 * 						==> 제너릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다
		 */
		
		 Vector<String> v2 = new Vector<String>();
		 v2.add("아뇽");
		 
		 v2.add(Integer.toString(123));
		 String temp2 = v2.get(0);
		 System.out.println("v2 ==> " + v2);
		
		 Vector<Vector> vv = new Vector<Vector>();
		 Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();
		 
		 v2.clear();
		 
		 v2.add("AAA");
		 v2.add("BBB");
		 v2.add("CCC");
		 v2.add("DDD");
		 v2.add("EEE");
		 
		 Vector<String> v3 = new Vector<String>();
		 v3.add("BBB");
		 v3.add("EEE");
		 
		 System.out.println("v2 ==> " + v2);
		 System.out.println("v3 ==> " + v3);
		 
		 //데이터 전부 삭제하기 : removeAll(Collection객체)
		 //	==> 'Collection객체'가 가지고 있는 데이터를 찾아서 모두 삭제한다
		 // ==> 반환값 : 삭제성공(true), 삭제실패(false)
		 v2.removeAll(v3);
		 System.out.println("v2 ==> " + v2);
		 //----------------------------------------------
		 System.out.println("----------------------------");
		 v2.clear();
		 
		 v2.add("AAA");
		 v2.add("BBB");
		 v2.add("CCC");
		 v2.add("DDD");
		 v2.add("EEE");
		 
		 //벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다
		 //for문 사용
		for(int i = 0; i<v2.size(); i++) {
			System.out.println(i + "번째 자료 : " + v2.get(i));
			
		}
		System.out.println("----------------빵채~~------------");
		 //향상된 for문
		for(String s : v2) {
			System.out.println(s);
		}
	}
}
