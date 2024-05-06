package egovframework.ecall.main.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface UserService {

	public ResponseEntity<?> user(HttpServletRequest request, String url);
	
}
