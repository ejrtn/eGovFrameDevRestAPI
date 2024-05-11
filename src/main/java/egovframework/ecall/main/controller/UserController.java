package egovframework.ecall.main.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.ecall.main.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userSave.do")
	public ResponseEntity<?> userSave(HttpServletRequest request) {
		return userService.user(request,"userSave");
	}
	
	@PostMapping(value="/login.do")
	public ResponseEntity<?> login(HttpServletRequest request) {
		return userService.user(request,"login");
	}
	
	@RequestMapping(value="/loginUpdate.do")
	public ResponseEntity<?> userUpdate(HttpServletRequest request) {
		return userService.user(request,"userUpdate");
	}
	
	@RequestMapping(value="/loginDelete.do")
	public ResponseEntity<?> userDelete(HttpServletRequest request) {
		return userService.user(request,"userDelete");
	}
}
