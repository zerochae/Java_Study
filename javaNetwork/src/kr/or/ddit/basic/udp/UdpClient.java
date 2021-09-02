package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*

 	UDP방식 : 비연결 지향, 신뢰성이 없다. 데이터가 순서대로 도착한다는 보장이 없다
 			  그렇지만 TCP방식보다 속도가 빠르다.
 			  
 	DatagramSocket객체와 DatagramPacket객체를 이용해서 통신하다.
 	- DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다.(우체부)
 	- DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다. (소포, 편지)
 			==> 수신용 생성자와 송신용 생성자를 따로 제공한다.
 			
 	TCP의 경우는 스트림을 이용하여 송수신하지만
 	UDP의 경우는 데이터그램을 이용하여 송수신한다
 
 */

public class UdpClient {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// 송신용, 수신용 패킷객체 변수 선언
		DatagramPacket inpacket, outpacket;

		// 수신 받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];

		try {

			DatagramSocket socket = new DatagramSocket();

			// 접속할 곳의 주소 정보 생성
			// InetAddress address = InetAddress.getByName("localhost");
			// InetAddress address = InetAddress.getByName("127.0.0.1");
			InetAddress address = InetAddress.getByName("192.168.46.54");

			while (true) {
				// 전송할 메시지를 입력한다.
				System.out.println("보낼 메시지 입력 : ");
				String msg = scan.nextLine();

				if ("/end".equals(msg)) {
					break;
				}

				// 전송용 패킷 객체 생성
				byte[] sendMsg = msg.getBytes("UTF-8");
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, 8888);

				// 전송하기
				socket.send(outpacket);

				if ("/end".equals(msg)) { // 메시지 중지 여부 결정
					break;
				}

				// ===============================================================
				// 서버에서 온 데이터를 받아서 출력하기

				// 수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);

				// 수신하기
				socket.receive(inpacket);

				String data = new String(bMsg, 0, inpacket.getLength(), "UTF-8");
				System.out.println("서버의 응답 데이터 : " + data);
				System.out.println();

			} // while문 끝...

			System.out.println("통신 끝..");
			socket.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
