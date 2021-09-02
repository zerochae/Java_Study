package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		
		/*
		 - Properties객체는 Map보다 축소된 기능의 객체라고 할 수 있다.
		 - Map은 Key값과 value값에 모든 형태의 객체를 사용할 수 있지만
		   Properties객체는 key와 value에 String만 사용할 수 있따.
		   
		   	map은 put(), get()메서드로 데이터를 입출력 하지만
		   	Properties는 setProperty(), getProperty()메서드를 통해서 입출력한다.
		 
		 - Properties는 데이터를 파일형태로 입출력할 수 있다.
		 
		 */
		
		
		Properties prop = new Properties();
		
	
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-6859-1546");
		prop.setProperty("age1", "20");
		prop.setProperty("age2", String.valueOf(20));
		prop.setProperty("arrd","대전");
		
				String name = prop.getProperty("name");
				String age = prop.getProperty("age");
				String tel = prop.getProperty("tel");
				String addr = prop.getProperty("addr");
				
				System.out.println("이름 : " + name);
				System.out.println("나이 : " + age);
				System.out.println("번호 : " + tel);
				System.out.println("주소 : " + addr);
	}

}
