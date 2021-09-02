package kr.or.ddit.basic.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		
		//외부에서 new명령으로 생성 불가
		//MySingleton test1 = new MySingleton();
		
		//싱글톤 객체 생성
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		// 참조값이 같으므로 전부다 같다
		System.out.println(test2.hashCode() == test3.hashCode());
		System.out.println(test2.equals(test3));
		System.out.println(test2 == test3);
		System.out.println(test2.toString().equals(test3.toString()));
		test2.displayTest();
		 
	}
	
}
