package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class DaedeokHotel {

	private Scanner scan;
	private HashMap<Integer, Room> roomMap;

	public static void main(String[] args) {

		new DaedeokHotel().hotelstart();

	}

	public DaedeokHotel() {

		scan = new Scanner(System.in);
		roomMap = new HashMap<Integer, Room>();
		createRooms();
	}

	private void createRooms() {

		if (roomMap.isEmpty()) {

			int roomNum = 201;

			while (true) {

				String roomType = ""; 
				
				if (201 <= roomNum && roomNum <= 209) {
					roomType = "싱글룸  ";
				} else if (301 <= roomNum && roomNum <= 309) {
					roomType = "더블룸  ";
				} else if (401 <= roomNum && roomNum <= 409) {
					roomType = "스위트룸";
				}
				
				String name = "-";
				
				roomMap.put(roomNum, new Room(roomNum, roomType, name));

				if (roomNum == 409) {
					break;
				}
				if (roomNum % 10 == 9) {
					roomNum +=92;
				} else {
					roomNum++;
				}
			}
		}
	}

	public void hotelstart() {

		
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");

		while (true) {
			int choice = hotelMenu();

			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				showRooms();
				break;
			case 4:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("메뉴를 잘못 입력했습니다");
				System.out.println("다시 입력해주세요");
				break;
			}
		}
	}

	private int hotelMenu() {

		System.out.println("\n-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃  3.객실상태  4.업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("번호 입력 >> ");
		int num = scan.nextInt();
		return num;
	}

	private void showRooms() {

		Set<Integer> keySet = roomMap.keySet();
		ArrayList<Integer> keyList = new ArrayList<>(keySet);
		Collections.sort(keyList);
		Iterator<Integer> it = keyList.iterator();

		System.out.println("----------------------------------------------");
		System.out.println("\t\t현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호\t\t방 종류\t\t   투숙객 이름");
		System.out.println("----------------------------------------------");
		while (it.hasNext()) {
			int key = it.next();
			Room r = roomMap.get(key);
			System.out.println(" "+key + "\t\t " + r.getRoomType() + "\t\t" + r.getName());
		}
	}

	private void checkOut() {

		System.out.println();
		System.out.println("체크 아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력 >> ");
		int roomNum = scan.nextInt();

		Room r = roomMap.get(roomNum);
		if (!roomMap.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}

		if (r.getName().equals("-")) {
			System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
			return;
		} else {
			System.out.println(roomNum + "호 객실의 " + r.getName() + "님의 체크아웃이 완료 되었습니다.");
			roomMap.put(roomNum, new Room(roomNum, r.getRoomType(), "-"));
		}
	}

	private void checkIn() {

		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("\t\t체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸 ");
		System.out.print("방 번호 입력 >> ");
		
		int roomNum = scan.nextInt();
		
		Room r = roomMap.get(roomNum);

		if (!roomMap.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}

		if (!r.getName().equals("-")) {
			System.out.println(roomNum + "은 이미 등록 손님이 있습니다.");
			return;
			
		} else {
			System.out.println("투숙객 성함을 입력해주세요.");
			String name = scan.next();

			roomMap.put(roomNum, new Room(roomNum, r.getRoomType(), name));

			System.out.println(roomNum + "호가 체크인 되었습니다.");
		}
	}

	class Room {

		int roomNum;
		String roomType;
		String name;

		public Room(int roomNum, String roomType, String name) {
			super();
			this.roomNum = roomNum;
			this.roomType = roomType;
			this.name = name;
		}

		public int getRoomNum() {
			return roomNum;
		}

		public void setRoomNum(int roomNum) {
			this.roomNum = roomNum;
		}

		public String getRoomType() {
			return roomType;
		}

		public void setRoomType(String roomType) {
			this.roomType = roomType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
