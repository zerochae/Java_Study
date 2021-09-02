package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Lotto {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		start();
	}

	static public void start() {
		System.out.println("\n==================");
		System.out.println("------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("===================");
		getMenu();
	}

	public static void exit() {
		System.out.println("\n시스템을 종료합니다.");
		System.out.println("감사합니다.");
		return;
	}

	public static void getMenu() {
		System.out.print("메뉴 선택 >> ");
		int menu = scan.nextInt();
		switch (menu) {
		case 1:
			buy();
			break;
		case 2:
			exit();
			break;
		default:
			System.out.println("정확하게 입력해주세요.");
			getMenu();
		}
	}

	public static void buy() {
		System.out.println("\nLotto 구입 시작");
		System.out.println("\n(1000원에 로또번호 하나입니다.)\n");
		System.out.print("금액 입력 >> ");

		int money = scan.nextInt();
		if (money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패");
			start();
		} else if (money >= 1000000000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패");
			start();
		} else {
			int firstMoney = money;
			int cnt = 0;
			while (money >= 1000) {
				money -= 1000;
				cnt++;
			}

			// ------------------------당첨번호

			HashSet<Integer> winset = new HashSet<Integer>();
			Random random = new Random();
			StringBuilder sb = new StringBuilder();
			System.out.println("내 로또번호");
			while (winset.size() != 6) {
				winset.add(random.nextInt(45) + 1);
			}
			ArrayList<Integer> winlist = new ArrayList<Integer>(winset);
			Collections.sort(winlist);
			
			// ---------------------------
			
			int one = 0;
			int two = 0;
			int three = 0;
			int four = 0;

			for (int i = 0; i < cnt; i++) {
				HashSet<Integer> lotto = new HashSet<Integer>();
				while (lotto.size() != 6) {
					lotto.add(random.nextInt(45) + 1);
				}
				ArrayList<Integer> list = new ArrayList<Integer>(lotto);
				Collections.sort(list);
				System.out.println("로또번호" + (i + 1) + " : " + list);
				int round = i + 1;
				int win = 0;
				int bonus = random.nextInt(45)+1;
				
				 
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j) == winlist.get(j)) {
							win++;
						}
						if(list.get(j) == bonus) {
							bonus = 1;
						}
					}
					if (win == 3) {
						four++;
						sb.append(round + "회차번호 4등 당첨").append("\n");
					} else if (win == 4) {
						three++;
						sb.append(round + "회차번호 3등 당첨").append("\n");
					} else if (win == 5 && bonus == 1) {
						two++;
						sb.append(round + "회차번호 2등 당첨").append("\n");
					} else if (win == 6) {
						one++;
						sb.append(round + "회차번호 1등 당첨").append("\n");
					}
				}
			
			System.out.println("받은 금액은 " + firstMoney + "원이고 거스름돈은 " + money + "원 입니다.");
			System.out.println("당첨번호는 " + winlist);
			if(sb.equals(null)) {
				System.out.println("당첨되지 않았습니다!!");
			} else {
				System.out.println(sb.toString());
			}
			System.out.println("당첨결과 >> 1등 : " + one + " 2등 : " + two + " 3등 : " + three + " 4등 : " + four);
		}
	}
}
