package kr.or.ddit.basic.generic;

class NonGeneric {

	private Object value;

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
}

/*
 	제네릭 클래스를 만드는 방법
 	형식)
 	class 클래스명<제네릭타입글자>{
 		제네릭타입글자 변수명; // 변수 선언에 제네릭을 사용할 경우
 		...
 		
 		제네릭타입글자 메서드명(매개변수들...){ // 메서드의 반환값으로 사용할 경우}
 			...
 			return 값;
 		}
 		
 		void 메서드명(제네릭타입글자 변수명){ // 메서드의 매개변수에 사용할 경우
 			...
 		}
 		
 		
 
 
 */


class MyGeneric<T>{
	
	private T value;
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	
}

public class GenericTest {

	public static void main(String[] args) {

		NonGeneric ng1 = new NonGeneric();
		ng1.setValue("가나다라");

		NonGeneric ng2 = new NonGeneric();
		ng2.setValue(100);
	
		String temp1 = (String)ng1.getValue();
		
		System.out.println("반환값 (문자열) : " + temp1);
		
		Integer temp2 = (Integer) ng2.getValue();
		
		System.out.println("반환값 (정수형) : " + temp2);
		
//		Integer temp3 = (Integer)ng1.getValue();
//		
//		System.out.println("반환값 (정수형) : " + temp3);
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		
		mg1.setValue("우리나라");
		mg2.setValue(500);
		
		String temp3 = mg1.getValue();
		Integer temp4 = mg2.getValue();
		System.out.println("제네릭 반환값(문자열) : " + temp3);
		System.out.println("제네릭 반환값(정수형) : " + temp4);
		 
		
	}
}
