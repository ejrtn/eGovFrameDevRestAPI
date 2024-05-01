package egovframework.ecall.main.service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;

import org.springframework.http.ResponseEntity;

public interface NoticeService {
	
//	public Source noticeList(HttpServletRequest request);
//	
//	public Source noticeSave(HttpServletRequest request);
//	
//	public Source noticeGet(HttpServletRequest request);
//
//	public Source noticeUpdate(HttpServletRequest request);
//
//	public Source noticeDelete(HttpServletRequest request);
	
	public ResponseEntity<?> notice(HttpServletRequest request,String url);
}
