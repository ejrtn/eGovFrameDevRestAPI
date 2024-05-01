package egovframework.ecall.main.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.ecall.main.service.NoticeService;

@RestController
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value="noticeList.do")
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
