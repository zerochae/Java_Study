package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackTest {
	
	public static void main(String[] args) {
		
		Browser b = new Browser();
		
		b.goURL("1. 네이버");
		b.goURL("2. 구글");
		b.goURL("3. 다음");
		b.goURL("4. 네이트");
		
		b.history();
		
		System.out.println("뒤로가기 후");
		
		b.goBack();
		b.history();
		b.goBack();
		b.history();
		b.goBack();
		b.history();
		
		b.goForward();
		b.history();
		b.goForward();
		b.history();
//		b.goForward();
//		b.history();
		
		System.out.println("새로운 사이트 접속하기 후 ..");
		b.goURL("5.대덕IT");
		b.history();
		
		
	}

}

// 웹브라우저의 앞으로가기, 뒤로가기 기능 구현(스택 이용)

class Browser{
	//브라우저의 문자열 주소를 담을 String LinkedList 생성
	private LinkedList<String> back; //이전 방문 내역
	private LinkedList<String> forward; //다음 방문내역
	private String currentURL;
	
	public Browser() {
		back = new LinkedList<String>();
		forward = new LinkedList<String>();
		currentURL= "";
	}
	
	//site를 방문하는 메서드 ==> 매개변수에는 방문할 URL주소가 저장된다.
	public void goURL(String url) {
		System.out.println(url + " site에 접속");
		
		//현재 페이지가 있는지 검사
		if(currentURL != null && !("".equals(currentURL))) {
			back.push(currentURL); // 현재 페이지를 back스택에 추가한다.
		}
		currentURL = url;
		forward.clear();
	}
	
	//뒤로가기
	public void goBack() {
		// isEmpty() ==> List가 비어 있으면 true, 그렇지 않으면 false 반환
		if(!back.isEmpty()) {//back스택이 비어있지 않으면
			forward.push(currentURL); //현재 페이지를 forward스택에 추가
			currentURL = back.pop(); //back스택에서 1개의 데이터를 꺼내와 현재 페이지로 설정한다.
		}
	}
	
	//앞으로가기
	public void goForward() {
		if(!forward.isEmpty()) {
			back.push(currentURL); //현재 페이지를 back스택에 추가
			currentURL = forward.pop(); // forward스택에서 1개의 데이터를 꺼내와 현재 페이지로 설정한다.
		} 
	}
	
	//방문 기록 확인하기
	public void history() {
		System.out.println("---------------------------");
		System.out.println("방\t문\t기\t록");
		System.out.println("---------------------------");
		System.out.println("back => " + back);
		System.out.println("현재 => " + currentURL);
		System.out.println("forward => " + forward);
		System.out.println("---------------------------\n");
	}
}