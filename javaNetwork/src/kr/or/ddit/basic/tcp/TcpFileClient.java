package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TcpFileClient {
	
	public static void main(String[] args) {
		
		TcpFileClient client = new TcpFileClient();
		
		//File file = new File("D:/D_other/porshe2.jpg");
		File file = client.openDialog();
		
		
		if(!file.exists()) { // 전솔항 파일이 있는지 검사
			System.out.println("전송할 파일이 없습니다");
			System.out.println("작업 중단");
			return;
		}
		
		//전송할 파일명 구하기
		String fileName = file.getName();
		
		Socket socket = null;
		DataOutputStream dos = null;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			socket = new Socket("192.168.46.82",7777);
			System.out.println("파일 전송을 시작합니다");
			 
			//소켓용 OutputStream 객체 생성
			OutputStream out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			
			//서버에 접속하면 첫번째로 파일명을 구한다.
			dos.writeUTF(fileName);
			
			//파일 내용을 읽어와서 소켓으로 출력한다
			bis = new BufferedInputStream(new FileInputStream(file)); // 입력용 스트림(파일)
			bos = new BufferedOutputStream(out);	// 출력용 스트림(소켓)
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			//파일 내용을 읽어와 소켓을 통해서 전송한다
			
			while((length=bis.read(temp))>0) {
				bos.write(temp,0,length);
			}
			bos.flush();
			System.out.println("파일 다운로드 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//사용했던 자원들을 반납한다
			if(dos != null )try {dos.close();} catch(IOException e) {}
			if(bos != null )try {bos.close();} catch(IOException e) {}
			if(bis != null )try {bis.close();} catch(IOException e) {}
			if(socket != null )try {socket.close();} catch(IOException e) {}
		}
				
	}

	private File openDialog() {
		// SWIING의 파일 열기, 저장 창 연습
				JFileChooser chooser = new JFileChooser();

				// 보여줄 파일의 확장자 설정
				FileNameExtensionFilter img = new FileNameExtensionFilter("Imange File", new String[] { "png", "jpg", "gif" });

				FileNameExtensionFilter txt = new FileNameExtensionFilter("text파일", "txt");

				FileNameExtensionFilter doc = new FileNameExtensionFilter("Ms-Word문서", "docx", "doc");

				chooser.addChoosableFileFilter(img);
				chooser.addChoosableFileFilter(txt);
				chooser.addChoosableFileFilter(doc);

				// 확장자 목록 중에 기본적으로 선택될 확장자 지정
				chooser.setFileFilter(img);

				// 전체 파일 목록 (*.*) 표시 여부 설정 (true:설정, flase:해제)
				chooser.setAcceptAllFileFilterUsed(true);

				// Dialog창에 나타날 기본 경로 설정
				chooser.setCurrentDirectory(new File("D:/D_Other"));

				// 열기용 창
				int result = chooser.showOpenDialog(new Panel());

				// 저장용 창
				//    int result = chooser.showSaveDialog(new Panel());

				File selectedFile = null;

				// '저장' 또는 '열기'버튼을 눌렀을 경우의 처리하기
				if (result == JFileChooser.APPROVE_OPTION) {
					// 선택한 파일 객체 구하기
					selectedFile = chooser.getSelectedFile();
					//      System.out.println("선택한 파일 : " + selectedFile.getAbsoluteFile());

					// 선택한 파일 정보를 이용해서 실제 읽기 또는 쓰기 작업을 수행한다.

				}

				return selectedFile;
	}
}
