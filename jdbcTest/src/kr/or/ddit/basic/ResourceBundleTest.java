package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
 	ResourceBundle객체 ==> 파일의 확장자가 properties인 파일의 내용을 일거와
 						   ky값과 value값을 분리해서 정보를 갖고 있는 객체
 					   ==> 읽어올 파일의 내용은 'key값=value값' 형태로 되어 있어야 한다.	    	
 */

public class ResourceBundleTest {

	public static void main(String[] args) {
		//ResourceBundle객체를 이용하여 파일 내용 읽어오기
		
		//ResoureceBundle객체 생성ㅎ하기
		//getBunlge() 메서드에 파일을 지정할 때 패키징 표기법으로 지정한다
		// 그리고, 마지막의 파일명의 확장자는 지정하지 않는다
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		//읽어온 내용 출력하기
		System.out.println("driver : " +bundle.getString("driver"));
		System.out.println("url : " +bundle.getString("url"));
		System.out.println("user : " +bundle.getString("user"));
		System.out.println("pass : " +bundle.getString("pass"));
	}
}
