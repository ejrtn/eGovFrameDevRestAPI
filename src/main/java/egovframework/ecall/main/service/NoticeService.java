package egovframework.ecall.main.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface NoticeService {
	
	public ResponseEntity<?> notice(HttpServletRequest request,String url);
}
