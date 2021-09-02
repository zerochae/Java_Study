package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBook {
	
	private HashMap<String,Phone> phoneBookMap;
	private Scanner scan;
	
	public PhoneBook() {
		
		phoneBookMap = new HashMap<String,Phone>();
		scan = new Scanner(System.in);
		
	}

	public static void main(String[] args) {
		new PhoneBook().phoneBookStart();
	
	}
	
	//프로그램을 시작하는 메서드
	public void phoneBookStart() {
		
		System.out.println("=========================================");
		System.out.println("      전화번호 정보 관리 프로그램");
		System.out.println("=========================================");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			
			switch (choice) {
			case 1:
				insert();
				break;
			case 2: 
				update();
				break;
			case 3: 
				delete();
				break;
			case 4:
				search();
				break;
			case 5:
				displayAll();
				break;
			case 0:
				System.out.println("프로그램  종료");
				return;
			default:
				System.out.println("메뉴를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
				break;
			}
		}
		
	}
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println("검색할 정보가 없습니다");
			System.out.println("검색을 종료합니다");
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		System.out.println("검색한 " + name + " 전화번호 정보");
		System.out.println("================================");
		System.out.println(" 이    름 : " + p.getName());
		System.out.println(" 전화번호 : " + p.getTel());
		System.out.println(" 주    소 : " + p.getAddr());
		
	}

	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.print("이름 >> ");
		String name = scan.next();
		if(!phoneBookMap.containsKey(name)) {
			System.out.println("삭제할 정보가 없습니다.");
			return;
		} else {
			phoneBookMap.remove(name);
			System.out.println("삭제가 완료 되었습니다");
		}
	}

	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요");
		
		System.out.println("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다");
			return;
		}
		System.out.println("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		System.out.println("새로운 주소 >> ");
			String newAddr = scan.next();

		//방법1 ==> get()메서드로 value값을 구해서 처리	
			Phone p = phoneBookMap.get(name);
			p.setTel(newTel);
			p.setAddr(newAddr);
			
		//방법2 ==> key값에 새로운 전화번호 정보를 추가한다
		//			나중의 데이터로 변경된다
			
			phoneBookMap.put(name, new Phone(name, newTel, newAddr));
			
			System.out.println(name + "씨의 전화번호 정보를 변경했습니다");
			
			
		
	}

	// 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		
		Set<String> phonekeySet = phoneBookMap.keySet();
		
		System.out.println("===================================");
		System.out.println("번호  이름     전화번호    주소");
		System.out.println("===================================");
		
		if(phonekeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int cnt = 0; // 번호 출력용
			for(String name : phonekeySet ) {
				cnt++;
				Phone p = phoneBookMap.get(name);
				System.out.println(" " + cnt + "\t" + p.getName() +
						"\t" + p.getTel() + "\t" + p.getAddr());
			}
			
		}
		
		System.out.println("===================================");
		System.out.println("출력 끝..");
		
	}

	// 새로운 전화번호 정보를 등록하는 메서드
	// 이미 등록된 사람은 등록되지 않는다.
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 검사
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "은 이미 등록된 사람입니다.");
			return;
		} else {
			
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.println("주소 >> ");
		String addr = scan.nextLine();
	
		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + "씨 등록 완료");
		}
	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	
	private int displayMenu(){
		System.out.println();
		System.out.println(
				"-----------------------------\n"
				+ "1. 전화번호 등록\r\n"
				+ "2. 전화번호 수정\r\n"
				+ "3. 전화번호 삭제\r\n"
				+ "4. 전화번호 검색\r\n"
				+ "5. 전화번호 전체 출력\r\n"
				+ "0. 프로그램 종료\r\n"
				+ "-----------------------------");
		System.out.print("번호 입력 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	
	
}
 
class Phone{
	
	private String name;
	private String tel;
	private String addr;
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
