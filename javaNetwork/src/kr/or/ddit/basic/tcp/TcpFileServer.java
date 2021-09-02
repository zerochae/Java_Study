package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//- 파일 전송하는 프로그램 작성하기
/*
	
		서버와 클라이언트가 접속이 되면 클라이언트가 D:/D_Other/폴더에 있는 스폰지밥.png파일을 서버로 전송
		서버는 클라이언트가 전송한 파일 데이터를 받아서 D:/D_Other/Down/ 폴더에 같은 이름으로 저장
		
	
	
		소켓이 생성되면 전송할 파일의 파일명을 먼저 전송한 후 파일의 내용을 읽어서 전송한다
*/
public class TcpFileServer {

	public static void main(String[] args) throws IOException {
		File saveDir = new File("D:/D_Other/down/"); // 저장할 폴더

		if (!saveDir.exists()) { // 저장할 폴더가 없으면
			saveDir.mkdirs(); // 폴더를 생성한다.
		}

		ServerSocket server = null;
		Socket socket = null;

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		DataInputStream dis = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다..");

			socket = server.accept();

			System.out.println("파일 다운로드 시작");

			// 소켓의 InputStream객체를 구한다
			InputStream is = socket.getInputStream();
			dis = new DataInputStream(is);

			// 첫번째로 수신받은 데이터는 전송되어 올 파일명이다
			String fileName = dis.readUTF();

			// 저장할 파일위치와 파일명을 지정하여 File객체를 생성한다
			File saveFile = new File(saveDir, fileName);

			// 소켓에서 파일 내용을 읽어와서 파일로 출력한다
			bis = new BufferedInputStream(is); // 입력용 스트림(소켓)
			bos = new BufferedOutputStream(new FileOutputStream(saveFile)); // 출략용 스트림(파일)

			byte[] temp = new byte[1024];
			int length = 0;
			// 소켓으로 받은 데이터를 파일로 출력한다.
			while ((length = bis.read(temp)) > 0) {
				bos.write(temp);
			}

			bos.flush();
			System.out.println("파일 다운로드 완료..");
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패");
			e.printStackTrace();
		} finally {
			//사용했던 자원들을 반납한다
			if(dis != null )try {dis.close();} catch(IOException e) {}
			if(bos != null )try {bos.close();} catch(IOException e) {}
			if(bis != null )try {bis.close();} catch(IOException e) {}
			if(socket != null )try {socket.close();} catch(IOException e) {}
			if(server != null )try {server.close();} catch(IOException e) {}
		}
	}
}
