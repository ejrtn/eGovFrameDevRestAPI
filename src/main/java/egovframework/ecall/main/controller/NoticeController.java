package egovframework.ecall.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.ecall.main.service.NoticeService;

@RestController
@CrossOrigin
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	@GetMapping(value="noticeList.do")
	public ResponseEntity<?> noticeList(HttpServletRequest request) {
		return noticeService.notice(request,"noticeList");
	}
	
	@RequestMapping(value="noticeSave.do")
	public ResponseEntity<?> noticeSave(HttpServletRequest request) {
		return noticeService.notice(request,"noticeSave");
	}
	
	@RequestMapping(value="noticeGet.do")
	public ResponseEntity<?> noticeGet(HttpServletRequest request) {
		return noticeService.notice(request,"noticeGet");
	}
	
	@RequestMapping(value="noticeUpdate.do")
	public ResponseEntity<?> noticeUpdate(HttpServletRequest request) {
		return noticeService.notice(request,"noticeUpdate");
	}
	
	@RequestMapping(value="noticeDelete.do")
	public ResponseEntity<?> noticeDelete(HttpServletRequest request) {
		return noticeService.notice(request,"noticeDelete");
	}
}
