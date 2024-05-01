package egovframework.ecall.main.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

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
	
//	@Override
//	public Source noticeList(HttpServletRequest request) {
//		List<Map<String,Object>> result = new ArrayList<>();
//		Map<String,Object> map = new HashMap<>();
//		String data = "";
//		String getMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		try {
//			data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//
//			Map<String, Object> mapData = commonService.paging(jsonOrXmlAction.xmlToMap(data));
//			
//			return jsonOrXmlAction.createXml(noticeDao.noticeList(mapData),getMethodName);
//		}catch(Exception e){
//			map.put("result", e);
//			result.add(map);
//		}
//		
//		return jsonOrXmlAction.createXml(result,"noticeSave");
//	}
//
//	@Override
//	public Source noticeSave(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		List<Map<String,Object>> result = new ArrayList<>();
//		Map<String,Object> map = new HashMap<>();
//		String data = "";
//		String getMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		try {
//			data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//			noticeDao.noticeSave(jsonOrXmlAction.xmlToMap(data));
//			map.put("result", "success");
//			result.add(map);
//			return jsonOrXmlAction.createXml(result,getMethodName);
//		}catch(Exception e){
//			map.put("result", e);
//			result.add(map);
//		}
//		return jsonOrXmlAction.createXml(result,getMethodName);
//	}
//	
//	@Override
//	public Source noticeGet(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		List<Map<String,Object>> result = new ArrayList<>();
//		Map<String,Object> map = new HashMap<>();
//		String data = "";
//		String getMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		try {
//			data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//			return jsonOrXmlAction.createXml(noticeDao.noticeGet(jsonOrXmlAction.xmlToMap(data)),getMethodName);
//		}catch(Exception e){
//			map.put("result", e);
//			result.add(map);
//		}
//		return jsonOrXmlAction.createXml(result,getMethodName);
//	}
//	
//	@Override
//	public Source noticeUpdate(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		List<Map<String,Object>> result = new ArrayList<>();
//		Map<String,Object> map = new HashMap<>();
//		String data = "";
//		String getMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		try {
//			data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//			noticeDao.noticeUpdate(jsonOrXmlAction.xmlToMap(data));
//			map.put("result", "success");
//			result.add(map);
//			return jsonOrXmlAction.createXml(result,getMethodName);
//		}catch(Exception e){
//			map.put("result", e);
//			result.add(map);
//		}
//		return jsonOrXmlAction.createXml(result,getMethodName);
//	}
//
//	@Override
//	public Source noticeDelete(HttpServletRequest request){
//		// TODO Auto-generated method stub
//		List<Map<String,Object>> result = new ArrayList<>();
//		Map<String,Object> map = new HashMap<>();
//		String data = "";
//		String getMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		try {
//			data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//			noticeDao.noticeDelete(jsonOrXmlAction.xmlToMap(data));
//			map.put("result", "success");
//			result.add(map);
//			return jsonOrXmlAction.createXml(result,getMethodName);
//		}catch(Exception e){
//			map.put("result", e);
//			result.add(map);
//		}
//		return jsonOrXmlAction.createXml(result,getMethodName);
//	}

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
			
			Map<String, Object> mapData = commonService.paging(parameter);
			resultList = noticeDao.noticeList(mapData);
			result.put("result", resultList);
			HttpHeaders httpHeaders= new HttpHeaders();
			if(parameter.get("resultType").equals("xml")) {
				httpHeaders.setContentType(MediaType.APPLICATION_XML);
				return ResponseEntity.ok().body(jsonOrXmlAction.createXml(resultList,url));
			}else {
				System.out.println(ResponseEntity.ok().body(new JSONObject(result)));
			    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<Map<String,Object>>(result,httpHeaders, HttpStatus.OK);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			Map<String,Object> map = new HashMap<>();
			map.put("result", e);
			resultList = new ArrayList<>();
			resultList.add(map);
		}
		
		if(parameter.get("resultType").equals("xml")) {
			return ResponseEntity.ok().body(jsonOrXmlAction.createXml(resultList,url));
		}else {
			HttpHeaders httpHeaders= new HttpHeaders();
		    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			return new ResponseEntity<Map<String,Object>>(result,httpHeaders, HttpStatus.OK);
		}
	}
}
