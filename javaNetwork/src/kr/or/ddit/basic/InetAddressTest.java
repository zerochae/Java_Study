package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	
	public static void main(String[] args) throws UnknownHostException {
		
		//InetAddress클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com 사이트의 IP정보 가져오기
		
		InetAddress naverIP = InetAddress.getByName("www.naver.com");
		System.out.println("naver");
		System.out.println("HostName : " + naverIP.getHostName());
		System.out.println("HostAddress : " + naverIP.getHostAddress());
		System.out.println("toString : + " + naverIP.toString());
		System.out.println("==========================================");
		
		//자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("내컴퓨터");
		System.out.println("HostName : " + localIP.getHostName());
		System.out.println("HostAddress : " + localIP.getHostAddress());
		System.out.println("toString : " + localIP.toString());
		System.out.println("==========================================");
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		
		for (InetAddress i : ipArr) {
			System.out.println(i);
		}
		
	}
	

}
