package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	
	//Logger클래스의 인스턴스를 받아온다
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		 // 로그 메시지 출력하기
		 // 형식1) logger객체변수.레벨이름
		 // 형식2) logger.log(level1.레벨이름, "출력할메시지");
		logger.trace("이것은 log4j의 [Trace]레벨의 메시지입니다");
		logger.debug("이것은 log4j의 [DUBUG]레벨의 메시지입니다");
		logger.info("이것은 log4j의 [INFO]레벨의 메시지입니다");
		logger.warn("이것은 log4j의 [WARN]레벨의 메시지입니다");
		logger.error("이것은 log4j의 [ERROR]레벨의 메시지입니다");
		logger.fatal("이것은 log4j의 [FATAL]레벨의 메시지입니다");
		
		logger.log(Level.DEBUG, "[DEBUG]레벨의 메시지");
		
	}

}
