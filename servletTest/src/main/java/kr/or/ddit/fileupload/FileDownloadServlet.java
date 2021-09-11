package kr.or.ddit.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fileDownLoad.do")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*

	 	파일을 다운로드하는 과정
	 	
	 1. FIleInputStream 객체를 이용해서 다운로드할 파일을 서버에서 읽는다.
	 2. 파일을 다운로드하려면 response객체의 setContenType()메서드를 이용하여
	 	content-type을 'application/octet-stream; charset=utf-8'
	 3. response객체의 헤더를 다음과 같이 설정한다.
	    1) 헤더명 : 'Content-Disposition'
	    2) 헤더값 : 'attachment; filename="= 다운로드할 파일명";'
	 4. response객체의 getOutputStream()을 사용하여 출력할 스트림 객체를 구한 후 
	    이 출력 스트림으로 전송하면 된다.
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// 파라미터로 넘어온 다운로드할 파일 이름을 구한다.
			String fileName = request.getParameter("filename");
			
			// 파일이 저정된 폴더 설정
			String uploadPath = "D:\\D_Other\\uploadFiles";
		
			// 파일 경로와 파일명을 연결한 전체 파일 경로를 만든다
			String filePath = uploadPath + File.separator + fileName;
			
			File file = new File(filePath);
			
			OutputStream os = null;
			FileInputStream fis = null;
			
			if(file.exists()) { // 다운로드할 파일이 있을 때
				// Content-Type 설정
				response.setContentType("application/octet-stream; charset=utf-8");
				
				
				//response의 header설정
				String headerValue = "attachment; filename=\"" + getDispostion( fileName , getBrowser(request)) + "\"";
				response.setHeader("Content-Disposition", headerValue);
				
				try {
					// 출력용 스트림 객체 생성
					os = response.getOutputStream();
					
					//파일 입력용 스트림 객체 생성
					fis = new FileInputStream(file);
					
					byte[] buffer = new byte[1024*100];
					int len = -1;
					while((len=fis.read(buffer)) > 0) {
						os.write(buffer,0,len);
					}
					os.flush();
				} catch (Exception e) {
					System.out.println("입출력 오류" + e.getMessage());
				} finally {
					if(fis != null) try {fis.close();} catch(IOException e) {};
					if(os != null) try {os.close();} catch(IOException e) {};
				}
				
				
			} else { // 다운로드할 파일이 없을 때
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().println("<h3>"+ fileName +" 파일은 존재하지 않습니다. </h3>");
				
			}
			
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 사용자의 웹 브라우저 알아내기
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		
		if(header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if(header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if(header.indexOf("Opera") > -1) {
			return "Opera";
		} else if(header.indexOf("Trident/7.0") > -1) { // IE 11이상
			return "MSIE";
		} 
		
		return "Firefox";
	}
	
	// 브라우저별로 한글 파일명을 인코딩하는 메서드
	
	private String getDispostion(String fileName, String brower) {
		
		String encodedFileName = null;
		
		try {
			
			if(brower.equals("MSIE")) {
				encodedFileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
			} else if(brower.equals("Firefox") || brower.equals("Opera")) {
				encodedFileName = "\""+ new String(fileName.getBytes("utf-8"),"8859_1") + "\"";
			} else if(brower.equals("Chrome")) {
				StringBuffer sb = new StringBuffer();
				for(int i =0; i<fileName.length(); i++) {
					char c = fileName.charAt(i);
					if(c > '~') {
						sb.append(URLEncoder.encode("" + c , "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				encodedFileName = sb.toString();
			} else {
				throw new RuntimeException("지원하지 않는 브라우저입니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return encodedFileName;
	}
	
	

}
