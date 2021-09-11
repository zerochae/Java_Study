package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/uploadedFiles.do")
public class UploadedFileListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 업로드한 파일 전체 목록을 구하는 서블릿
      
      
      // 사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
      String uploadPath = "D:\\D_Other\\uploadFiles";
            
      // 저장될 폴더가 없으면 새로 생성한다.
      File fileUploadDir = new File(uploadPath);
      if(!fileUploadDir.exists()) {
         fileUploadDir.mkdirs();
      }
            
      //파일이 저장된 폴더에서 파일 목록을 구해서 List에 담아준다.
      File[] allFiles = fileUploadDir.listFiles();
      List<UploadFileVO> fileList = new ArrayList<UploadFileVO>();
      
      for(File file : allFiles) {
    	  
    	  if(file.isFile()) { //디렉토리(폴더)는 제외
    	  
         UploadFileVO fvo = new UploadFileVO();
         fvo.setFileName(file.getName());
         fvo.setFileSize((long)Math.ceil(file.length()/1024.0));
         fvo.setUploadStatus("Success");
         fileList.add(fvo);
    	  }
      }
      
      request.setAttribute("uploadFileList", fileList);
      RequestDispatcher rd = 
            request.getRequestDispatcher("/fileupload/fileuploadList.jsp");
      rd.forward(request, response);
      
            
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}