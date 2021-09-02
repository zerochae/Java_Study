package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DatabaseToExcel;

/*
 	
 	데이터를 추가할 때 회원ID는 양방향 암호화 방식으로 암호화하고,
 	비밀번호는 단방향 암호화 방식으로 암호화해서 저장한다.
 	
 	전체 자료를 출력할 때는 회원 ID는복호화해서 출력한다.
 	
 */

public class MemberController {
   private Scanner scanner;
   private IMemberService service;
   private DatabaseToExcel dte;
   
   public MemberController() {
      service = MemberServiceImpl.getInstance();
      scanner = new Scanner(System.in);
      dte = new DatabaseToExcel();
   }
   
   public static void main(String[] args) {
      new MemberController().startMember();
   }
   
   public void startMember() {
      loop:
      while (true) {
         int choice = displayMenu();
         switch (choice) {
         case 1:   // 추가
            insert();
            break;
         case 2:   // 삭제
            delete();
            break;
         case 3:   // 수정
            update();
            break;
         case 4:   // 출력
            displayMember();
            break;
         case 5:   // 자료수정2
            update2(); 
            break;
         case 6: // 엑셀에 자료 저장
        	save();
        	break;
         case 0:
            System.out.println("작업을 마칩니다.");
            break loop;
         default:
            System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
         }
      }   
   }
   
  
   private void save() {
	   
	    dte.saveExcelFile();
   }

private void update2() {
      System.out.println();
      System.out.println("수정할 회원 정보를 입력하세요.");
      System.out.print("회원ID>>");
      String memId = scanner.next();
      
      int count = service.getMemberCount(memId);
      if (count == 0) {
         System.out.println(memId + "는 없는 회원ID 입니다.");
         System.out.println("수정 작업을 종료합니다.");
         return;
      }
      
      int num;
      String updateField = null;
      String updateTitle = null;
      
      do {
         System.out.println();
         System.out.println("수정할 항목 선택.");
         System.out.println("1.비밀번호 2.회원이름 3.전화번호 4.주소");
         System.out.println("-----------------------------------");
         System.out.print("수정할 항목 선택>>");
         num = scanner.nextInt();
         switch (num) {
         case 1:
            updateField = "mem_pass";
            updateTitle = "비밀번호";
            break;
         case 2:
            updateField = "mem_name";
            updateTitle = "이름";
            break;
         case 3:
            updateField = "mem_tel";
            updateTitle = "전화번호";
            break;
         case 4:
            updateField = "mem_addr";
            updateTitle = "주소";
            break;
         default:
            
            break;
         }
      } while (num<1 || num>4);
      
      System.out.println();
      scanner.nextLine();   // 버퍼 비우기
      System.out.print("새로운 " + updateTitle+">>");
      String updateData = scanner.nextLine();
      
      // 수정 작업에 필요한 데이터들을 Map에 저장한다.
      Map<String, String> paramMap = new HashMap<String,String>();
      paramMap.put("memId", memId); // 회원 ID 추가
      paramMap.put("field", updateField); // 수정할 컬럼명 추가
      paramMap.put("data", updateData); // 수정할 데이터 추가
      
      int cnt = service.updateMember2(paramMap);
      
      if(cnt > 0) {
    	  System.out.println("수정 작업 성공");
      } else {
    	  System.out.println("실패!");
      }
   }
   
   private void update() {
      System.out.println();
      System.out.println("수정할 회원 정보를 입력하세요.");
      System.out.print("회원ID>>");
      String memId = scanner.next();
      
      int count = service.getMemberCount(memId);
      if (count == 0) {
         System.out.println(memId + "는 없는 회원ID 입니다.");
         System.out.println("수정 작업을 종료합니다.");
         return;
      }
      
      System.out.println("수정할 내용을 입력하세요.");
      System.out.print("새로운 비밀번호>> ");
      String newPass = scanner.next();
      System.out.print("새로운 회원이름>> ");
      String newName = scanner.next();
      System.out.print("새로운 전화번호>> ");
      String newTel = scanner.next();
      scanner.nextLine();
      System.out.print("새로운 주소>> ");
      String newAddr = scanner.nextLine();
      
      // 입력한 데이터 MemberVO객체에 담는다.
      MemberVO memVo = new MemberVO();
      memVo.setMem_id(memId);
      memVo.setMem_pass(newPass);
      memVo.setMem_name(newName);
      memVo.setMem_tel(newTel);
      memVo.setMem_addr(newAddr);
      
      int cnt = service.updateMember(memVo);
      if (cnt > 0) {
         System.out.println("추가 작업 성공~~~");
      } else {
         System.out.println("추가 작업 실패!!!");
      }
   }

   private void delete() {
      System.out.println();
      System.out.println("삭제할 회원 정보 입력:");
      System.out.print("회원ID>> ");
      String memId = scanner.next();
      
      int cnt = service.deleteMember(memId);
      if (cnt > 0) {
         System.out.println("삭제 작업 성공");
      } else {
         System.out.println("삭제 작업 실패ㅋ");
      }
   }

   // 자료 추가
   private void insert() {
      System.out.println();
      System.out.println("추가할 회원 정보를 입력하세요.");
      
      int count = 0;
      String memId = null;
      do {
         System.out.print("회원 id>> ");
         memId = scanner.next();
         count = service.getMemberCount(memId);
         if (count > 0) {
            System.out.println("이미 등록된 id입니다.");
            System.out.println("다른 id 입력.");
         } 
      } while (count>0);
      
      System.out.print("비밀번호>> ");
      String memPass = scanner.next();
      System.out.print("이름>> ");
      String memName = scanner.next();
      System.out.print("전화번호>> ");
      String memTel = scanner.next();
      
      scanner.nextLine();   // 입력 버퍼 비우기
      System.out.print("주소>> ");
      String memAddr = scanner.nextLine();
      
      // 입력한 데이터 MemberVO객체에 담는다.
      MemberVO memVo = new MemberVO();
      memVo.setMem_id(memId);
      memVo.setMem_pass(memPass);
      memVo.setMem_name(memName);
      memVo.setMem_tel(memTel);
      memVo.setMem_addr(memAddr);
      
      int cnt = service.insertMember(memVo);
      if (cnt > 0) {
         System.out.println("추가 작업 성공~~~");
      } else {
         System.out.println("추가 작업 실패!!!");
      }
   }
   
   // 전체 회원 정보 출력
   private void displayMember() {
      List<MemberVO> memList = service.getALLMemberList();

      System.out.println("-----------------");
      System.out.println("id   비밀번호   이름   전화번호   주소");
      System.out.println("-----------------");

      if (memList == null || memList.size()==0) {
         System.out.println("회원 자료가 하나도 없습니다.");
      } else {
         for (MemberVO memberVO : memList) {
            System.out.println(
                  memberVO.getMem_id()+" "
                  + memberVO.getMem_pass()+" "
                  + memberVO.getMem_name()+" "
                  + memberVO.getMem_tel()+" "
                  + memberVO.getMem_addr()+" ");
         }
      }
      System.out.println("----------------------------");
      System.out.println("출력 끝....");
   }

   private int displayMenu() {
      System.out.println("====================");
      System.out.println("-- 작업 선택 --");
      System.out.println("1. 자료 추가");
      System.out.println("2. 자료 삭제");
      System.out.println("3. 자료 수정");
      System.out.println("4. 전체 자료 출력");
      System.out.println("5. 자료 수정 2 ");
      System.out.println("6. 엑셀 파일 저장 ");
      System.out.println("0. 작업 끝.");
      System.out.println("====================");
      System.out.print("원하는 작업 선택>> ");
      return scanner.nextInt();
   }
}