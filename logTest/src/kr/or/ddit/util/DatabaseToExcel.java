package kr.or.ddit.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseToExcel {
	
	static Logger logger  = Logger.getLogger(DatabaseToExcel.class);
	
	public void saveExcelFile() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		XSSFWorkbook workbook = null;
		FileOutputStream fos = null;
		
		try {
			
			conn = DBUtil3.getConnection();
			logger.info("Conn 연결");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM MYMEMBER";
			rs = stmt.executeQuery(sql);
			logger.info("QUERY : " + sql);
			workbook = new XSSFWorkbook();
			String sheetName = "MYMEMBER";
			XSSFSheet sheet = workbook.createSheet(sheetName);
			logger.info("시트 생성 : " + sheetName);
			
			XSSFRow row = sheet.createRow(0);
			
			String head0 = "MEM_ID";
			String head1 = "MEM_PASS";
			String head2 = "MEM_NAME";
			String head3 = "MEM_TEL";
			String head4 = "MEM_ADDR";
			
			row.createCell(0).setCellValue(head0);
			row.createCell(1).setCellValue(head1);
			row.createCell(2).setCellValue(head2);
			row.createCell(3).setCellValue(head3);
			row.createCell(4).setCellValue(head4);
			logger.info("헤더 생성 [ " + head0 + ", " + head1 + ", " + head2 + ", " + head3 + ", " + head4 + " ]" );
			
			int r = 1;
			
			logger.info("Start Insert Data");
			while(rs.next()) {
				String id = rs.getString("MEM_ID");
				String pass = rs.getString("MEM_PASS");
				String name = rs.getString("MEM_NAME");
				String tel = rs.getString("MEM_TEL");
				String addr = rs.getString("MEM_ADDR");
				
				row = sheet.createRow(r++);
				
				row.createCell(0).setCellValue(id);
				row.createCell(1).setCellValue(pass);
				row.createCell(2).setCellValue(name);
				row.createCell(3).setCellValue(tel);
				row.createCell(4).setCellValue(addr);
				
				logger.info("사용한 DATA : [ " + id +", " + pass + ", " + name + ", " + tel + ", " + addr +" ]");
			}
			
			String saveAt = "D:\\A_TeachingMaterial\\3.HighJava\\workspace\\logTest\\MEMBER.xlsx";
			fos = new FileOutputStream(saveAt);
			logger.info("저장 경로 : " + saveAt);
			workbook.write(fos);
			logger.info("저장 성공");
			
		} catch (SQLException e) {
			logger.error("저장실패" + e);
		} catch (FileNotFoundException e) {
			logger.error("저장실패" + e);
		} catch (IOException e) {
			logger.error("저장실패" + e);
		} catch (NullPointerException e) {
			logger.error("저장실패" + e);
		} finally {
			if(workbook != null) try {workbook.close(); } catch(IOException e) {};
			if(fos != null) try {fos.close(); } catch(IOException e) {};
			if(rs != null) try {rs.close(); } catch(SQLException e) {};
			if(stmt != null) try {stmt.close(); } catch(SQLException e) {};
			if(conn != null) try {conn.close(); } catch(SQLException e) {};
		}
	}
}

