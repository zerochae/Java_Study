package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest04 {
	
	public static void main(String[] args) {
		
		FileTest04 test = new FileTest04();
		
		File file = new File("D:/D_Other");
		
		test.displayFileList(file);
		
	}
	
	// 디렉토리 정보를 갖고 있는 File객체를 매개변수로 받아서 
	// 해당 디렉토리 안에 있는 모든 파일과 자식 디렉토리 목록을 출력하는 메소드
	
	public void displayFileList(File dir) {
		
		if(!dir.isDirectory()) {
			System.out.println("디렉토리(폴더)만 사용 가능합니다 .");
			return;
		}
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		//해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 구한다.
		File[] files = dir.listFiles();
		//String[] strFiles = dir.list();
		
		// 출력할 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		// 가져온 파일과 디렉토리 목록 개수만큼 반복
		
		for(int i =0; i<files.length; i++) {
			String fileName = files[i].getName(); // 파일명 또는 디렉토리 명
			String attr = ""; // 파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일의 크기
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			
			System.out.printf("%s %5s %12s %s\n",sdf.format(new Date(files[i].lastModified())),attr,size,fileName);
			
		}
	}

}
