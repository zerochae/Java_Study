package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
	public static void main(String[] args) {

		try {

			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);

			// 수신용 패킷변수와 송신용 패킷변수 선언
			DatagramPacket inpacket, outpacket;
			System.out.println("서버 실행중 ^^ ");

			while (true) {
				// 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];

				// 수신용 패킷 객체 생성
				// ==> 수신된 데이터가 저장될 byte형 배열,배열의 길이를 지정해서 생성한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);

				// 데이터를 수신한다.
				// ==> receive()메서드 이용
				// ==> 이 메서드는 데이터가 올 때까지 기다린다.
				// ==> 수신된 데이터의 패킷정보는 인수로 지정한 패킷변수에 저장된다.
				socket.receive(inpacket);

				// 수신 받은 패킷객체를 이용하여 상대방의 주소, 포트번호 등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();

				System.out.println("상대방의 IP정보 : " + address);
				System.out.println("상대방의 Port정보 : " + port);
				System.out.println();

				// 상대방이 보낸 메시지 출력하기
				// 수신용 패킷에서 수신된 데이터와 관련된 정보를 알려주는 메서드
				// - inpacket.getLength() ==> 실제 읽어온 데이터의 길이를 반환한다.
				// - inpacket.getData() ==> 실제 읽어온 데이터를 byte형 배열로 반환한다.
				
				// 실제 읽어온 데이터(byte형배열)의 길이를 문자열로 변환하기
				String msg = new String(bMsg,0,inpacket.getLength(),"UTF-8");
				// 위와 같음
				String msg2 = new String(inpacket.getData(),0,inpacket.getLength());
				
				if("/end".equals(msg)) { // 통신 종료 메시지 확인
					break;
				}
				
				System.out.println("클라이언트가 보낸 메시지 : " + msg);
				System.out.println();
				
				// ======================================================================
				
				// 상대방에게 메시지 보내기(수신 받은 메시지를 그대로 송신한다.)
				
				// 송신할 메시지를 byte형 배열로 변환한다.
				byte sendMsg[] = msg.getBytes("UTF-8");
				
				// 송신용 패킷객체 생성하기
				// ==> 전송할 데이터가 저장된 byte형 배열,
				// 	   전송할 자료의 길이(배열의 길이),상대방 주소 정보, 포트 번호
				// ==> 위 4가지를 지정하여 생성한다.
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 송신하기 ==> send()메서드 이용
				socket.send(outpacket);
				System.out.println("송신 완료..");
			} // while문 끝.
			
			System.out.println("통신 끝 ^^");
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
