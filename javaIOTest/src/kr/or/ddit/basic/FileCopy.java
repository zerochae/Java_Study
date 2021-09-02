package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

// 2021.08.05
/*
    d드라이브의 d_other폴더에 있는 '스펀지밥.png'파일을 
    d드라이브의 d_other폴더의 하위 폴더 중에서 '연습용'폴더에 '스폰지밥-복사본.png'파일로 복사하는 프로그램을 작성하시오
 */

public class FileCopy {

	public static void main(String[] args) {
		new FileCopy().fileCopyStart();
	}





	public void fileCopyStart() {


		//      File file = new File("D:/D_other/스펀지밥.png");
		File file = openDialog();

		if(file==null || !file.exists()) {
			System.out.println("원본 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
		// 대상 파일 선택
		File targetFile = saveDialog();
		if(targetFile == null) {
			System.out.println("대상 파일을 선택하지 않았습니다.");
			System.out.println("복사 작업을 중지 합니다. ");
			return;
		}



		//try ~ catch 밖으로 나간 fin.close()
		FileInputStream fin = null;
		FileOutputStream fout = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;


		try {
			// 입력용 스트립 객체 생성
			fin = new FileInputStream(file);
			bis = new BufferedInputStream(fin);

			//출력용 스트림 객체 행성
			//fout = new FileOutputStream("D:/D_other/연습용/스펀지밥_복사본.png");
			fout = new FileOutputStream(targetFile);
			bos = new BufferedOutputStream(fout);

			System.out.println("복사 시작...");

			int data;    //읽어온 데이터를 저장할 변수

//			while( (data=fin.read()) !=-1) {
//				fout.write(data);
//			}
			while( (data=bis.read()) !=-1) {
				bos.write(data);
			}
			
			bos.flush();
			
			System.out.println("복사 완료");


		} catch (IOException e) {
			e.printStackTrace();

		}finally {
			if(bis!=null) {
				try { bis.close();
				}catch(IOException e) {}
			}
			if (bos!=null) {
				try{bos.close();
				}catch(IOException e) {}

			}
//			if(fin!=null) {
//				try { fin.close();
//				}catch(IOException e) {}
//			}
//			if (fout!=null) {
//				try{fin.close();
//				}catch(IOException e) {}
//				
//			}

		}
	}//--------------------------------------------

	// 열기용 창을 보여주고 선택한 파일을 반환하는 메서드
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

	//---------------------------------------------------------------

	// 저장용 창을 보여주고 선택한 파일을 반환하는 메서드
	private File saveDialog() {

		// SWIING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();

		// 보여줄  파일의 확장자 설정
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
		//int result = chooser.showOpenDialog(new Panel());

		// 저장용 창
		int result = chooser.showSaveDialog(new Panel());

		File selectedFile = null;

		// '저장' 또는 '열기'버튼을 눌렀을 경우의 처리하기
		if (result == JFileChooser.APPROVE_OPTION) {
			// 선택한 파일 객체 구하기
			selectedFile = chooser.getSelectedFile();
			//   System.out.println("선택한 파일 : " + selectedFile.getAbsoluteFile());

			// 선택한 파일 정보를 이용해서 실제 읽기 또는 쓰기 작업을 수행한다.

		}

		return selectedFile;
	}

}