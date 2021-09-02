//package kr.or.ddit.basic;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Scanner;
//import java.util.Set;
//
///*
// 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고 
// 	  Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
// 	  (Map의 구조는 Key값으로 '이름'을 사용하고 'value값으로는 Phone클래스의 인스턴스'로 한다.
// 	  
// 	  HashMap<String,Phone> 변수명 ;
// 	  
// 	  아래 메뉴 및 예시에 맞는 기능을 구현하시오  
// 	  ==> 삭제,검색은 '이름'을 입력받아서 처리한다.
// 		실행 예시)
// 		-----------------------------
// 		1. 전화번호 등록
// 		2. 전화번호 수정
// 		3. 전화번호 삭제
// 		4. 전화번호 검색
// 		5. 전화번호 전체 출력
// 		0. 프로그램 종료
// 		-----------------------------
// 		번호입력 >> 1
// 		
// 		새롭게 등록할 전화번호 정보를 입력하세요.
// 		이름 >> 홍길동
// 		전화번호 >> 010-0000-1111
// 		주소 >> 대전시 중구 대흥동
// 		
// 		'홍길동' 전화번호 등록 완료!!
// 		------------------------------
// 		번호입력 >> 1
// 		
// 		새롭게 등록할 전화번호 정보를 입력하세요.
// 		이름 >> 홍길동
// 		
// 		'홍길동'은 이미 등록된 사람입니다.
// 		--------------------------------
// 		번호입력 >> 5
// 		--------------------------------------------------
// 		번호	이름	  전화번호			  주소
// 		--------------------------------------------------
// 		  1		홍길동	010-0000-1111	대전시 중구 대흥동
// 		--------------------------------------------------
// 		출력 완료...
// */
//
//
//
//public class PhoneBookTest {
//	
//	public static void main(String[] args) {
//		
//		Phone phone = new Phone();
//		
//		phone.mainMenu();
//		
//	}
//}
//
//class Phone{
//	
//	
//	String name;
//	String phoneNum;
//	String addr;
//	static int peopleNum = 1;
//	
//	static HashMap<String,Phone> map = new HashMap<String, Phone>();
//
//	public boolean equals(Phone phone) {
//		if (this.name == null && phone.name != null) {
//			return false;
//		}
//		if (this.name.equals(phone.name)) {
//			return true;
//		}
//		return false;
//	}
//
//	public Phone(String name, String phoneNum, String addr) {
//		super();
//		this.name = name;
//		this.phoneNum = phoneNum;
//		this.addr = addr;
//	}
//
//	public Phone() {
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPhoneNum() {
//		return phoneNum;
//	}
//
//	public void setPhoneNum(String phoneNum) {
//		this.phoneNum = phoneNum;
//	}
//
//	public String getAddr() {
//		return addr;
//	}
//
//	public void setAddr(String addr) {
//		this.addr = addr;
//	}
// 
//	static Scanner scan = new Scanner(System.in);
//	
//	public void mainMenu() {
//		System.out.println(
//				"-----------------------------\n"
//				+ "1. 전화번호 등록\r\n"
//				+ "2. 전화번호 수정\r\n"
//				+ "3. 전화번호 삭제\r\n"
//				+ "4. 전화번호 검색\r\n"
//				+ "5. 전화번호 전체 출력\r\n"
//				+ "0. 프로그램 종료\r\n"
//				+ "-----------------------------");
//		System.out.print("번호 입력 >> ");
//		int menuNum = scan.nextInt();
//		
//		switch (menuNum) {
//		case 1: insertNum();
//			break;
//		case 2: modify();
//			break;
//		case 3:delete();
//			break;
//		case 4: search();
//			break;
//		case 5: printList();
//			break;
//		case 0: exit();
//			break;
//		default: 
//		System.out.println("정확하게 입력해주세요");
//		mainMenu();
//			break;
//		}
//	}
//	
//	public void insertNum() {
//
//		System.out.println("\n새롭게 등록할 번호를 입력하세요.");
//		System.out.print("이름 >> ");
//		String name = scan.next();
//		
//		if(map.containsKey(name)) {
//			System.out.println("이미 등록한 사람입니다.");
//			mainMenu();
//		}
//
//		System.out.println("전화번호 >> ");
//		String phoneNum = scan.next();
//		System.out.println("주소 >> ");
//		String addr = scan.next();
//		map.put(name, new Phone(name, phoneNum, addr));
//		System.out.println("정상등록 되었습니다.");
//		mainMenu();
//	}
//	public void printList() {
//		
//		Set<String> keySet = map.keySet();
//		Iterator<String> keySetit = keySet.iterator();
//		
//		if(map.size() == 0) {
//			System.out.println("등록된 정보가 없습니다.");
//		} else {
//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("번호\t\t이름\t\t\t전화번호\t\t주소");
//		
//		
//		while(keySetit.hasNext()) {
//			String key = keySetit.next();
//			String value = map.get(key).toString();
//			System.out.print(peopleNum);
//			System.out.println(value);
//			peopleNum++;
//		}
//		System.out.println("--------------------------------------------------------------------------");	
//		System.out.println();
//		}
//		mainMenu();
//	}
//	
//	public void exit() {
//		System.out.println("프로그램을 종료합니다. 감사합니다.");
//		return;
//	}
//
//	@Override
//	public String toString() {
//		return "\t\t"+getName() +"\t\t\t"+ getPhoneNum() +"\t\t\t"+ getAddr();
//	}
//	
//	public void delete() {
//		System.out.println("삭제할 이름을 입력하세요.");
//		String name = scan.next();
//		map.remove(name);
//		if(!map.containsKey(name)) {
//			System.out.println("정상 삭제 되었습니다.");
//		} else {
//			System.out.println("삭제가 실패 하였습니다.");
//		}
//		mainMenu();		
//	}
//	public void modify() {
//		System.out.print("수정할 이름을 입력하세요.");
//		String name = scan.next();
//		System.out.print("번호를 입력하세요 >> ");
//		String phoneNum = scan.next();
//		System.out.println("주소를 입력하세요 >> ");
//		String addr = scan.next();
//		map.put(name, new Phone(name, phoneNum, addr));
//		System.out.println("정상 수정 되었습니다.");
//		mainMenu();
//	}
//	public void search() {
//		System.out.println("검색할 이름을 입력하세요.");
//		String name = scan.next();
//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("번호\t\t이름\t\t\t전화번호\t\t주소");
//		System.out.println(map.get(name));
//		System.out.println("--------------------------------------------------------------------------");
//		mainMenu();
//	}
//	
//}
