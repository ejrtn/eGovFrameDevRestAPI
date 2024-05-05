package egovframework.ecall.main.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import egovframework.ecall.main.common.JsonOrXmlAction;
import egovframework.ecall.main.dao.NoticeDao;
import egovframework.ecall.main.service.CommonService;
import egovframework.ecall.main.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private JsonOrXmlAction jsonOrXmlAction;

	@Override
	public ResponseEntity<?> notice(HttpServletRequest request, String url) {
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> resultList = new ArrayList<>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		try {
			Enumeration<?> params = request.getParameterNames();
			while(params.hasMoreElements()) {
				String name = (String) params.nextElement();
				parameter.put(name, request.getParameter(name));
			}
			
			HttpHeaders httpHeaders= new HttpHeaders();
			
			if(url.equals("noticeList")) {
				parameter = commonService.paging(parameter);
				resultList = noticeDao.noticeList(parameter);
			}else if(url.equals("noticeGet")) {
				resultList = noticeDao.noticeGet(parameter);
			}else if(url.equals("noticeSave") || url.equals("noticeUpdate") || url.equals("noticeDelete")) {
				if(url.equals("noticeSave")) {
					noticeDao.noticeSave(parameter);
				}else if(url.equals("noticeUpdate")) {
					noticeDao.noticeUpdate(parameter);
				}else if(url.equals("noticeDelete")) {
					noticeDao.noticeDelete(parameter);
				}
				Map<String,Object> map = new HashMap<>();
				map.put("result", "success");
				resultList.add(map);
			}
			
			if(parameter.get("resultType").equals("xml")) {
				httpHeaders.setContentType(MediaType.APPLICATION_XML);
				return new ResponseEntity<>(jsonOrXmlAction.createXml(resultList,parameter,url),httpHeaders, HttpStatus.OK);
			}else {
				result.put("result", resultList);
			    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<>(jsonOrXmlAction.createJSONString(resultList,parameter),httpHeaders, HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Map<String,Object> map = new HashMap<>();
			map.put("result", e);
			resultList.add(map);
		}
		
		if(parameter.get("resultType").equals("xml")) {
			return ResponseEntity.ok().body(jsonOrXmlAction.createXml(resultList,parameter,url));
		}else {
			Map<String,Object> a = new HashMap<>();
			a.put("result", "");
			HttpHeaders httpHeaders= new HttpHeaders();
		    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			return new ResponseEntity<Map<String,Object>>(a,httpHeaders, HttpStatus.OK);
		}
	}
}
