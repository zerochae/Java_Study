package kr.or.ddit.basic;

public class ThreadTest07 {
	
	public static void main(String[] args) {
		
		//사용자로부터 데이터 입력받기
		//String str = JOptionPane.showInputDialog("아무거나 입력");

		// 사용자로부터 데이터 입력 받기
		// ==> 입력값은 문자열로 처리된다.
		// ==> 입력창에서 '취소'버튼을 누르면 null값이 저장된다.
		//System.out.println(" 입력한 값 : " + str);
		
		
		//카운트 다운
		for(int i=10; i>=1; i--) {
			try {
				System.out.println(i);
				Thread.sleep(1000); // 1초동안 잠시 멈춘다.
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
