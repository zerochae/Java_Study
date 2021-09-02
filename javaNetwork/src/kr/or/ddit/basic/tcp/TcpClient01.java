package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 예) 192.168.46.82
		// 2) 지정된 IP주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : 예) PC-23
		// 4) 지정된 컴퓨터 이름 : localhost
		
		String serverIp = "localhost";
		
		System.out.println("서버에 연결 중입니다");
		
		// 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다
		// Socket객체는 생성이 완료되면 해당 서버로 요청신호를 보낸다
		Socket socket = new Socket(serverIp,7777);
		
		// 이 부분은 서버와 연결이 완료된 이후에 처리될 부분이다
		System.out.println("서버에 연결되었습니다");
		System.out.println();
		
		// 서버에서 보내온 메시지를 받아서 출력한기
		// 데이터를 받기위해 InputStream객체를 생성한다.
		//			==> Socket객체의 getInputStream()메서드 이용
		InputStream is = socket.getInputStream();
		
		DataInputStream dis = new DataInputStream(is);
		
		// 서버가 보낸 자료를 받아서 출력하기
		System.out.println("서버에서 보낸 메시지 : " + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료함");
		
		// 소켓 , 스트림 닫기
		dis.close();
		socket.close();
	}
}
