package egovframework.ecall.main.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

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
			
			ServletInputStream inputStream = request.getInputStream();
			String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
		    
		    
		    if(messageBody.equals("")) {
				Enumeration<?> params = request.getParameterNames();
				while(params.hasMoreElements()) {
					String name = (String) params.nextElement();
					parameter.put(name, request.getParameter(name));
				}
		    }else {
			    parameter = mapper.readValue(messageBody, Map.class);	
		    }
		    
			if(url.equals("noticeList")) {
				parameter = commonService.paging(parameter);
				resultList = noticeDao.noticeList(parameter);
			}else if(url.equals("noticeGet")) {
				resultList = noticeDao.noticeGet(parameter);
			}else if(url.equals("noticeSave") || url.equals("noticeUpdate") || url.equals("noticeDelete")) {
				if(url.equals("noticeSave")) {
					noticeDao.noticeSave(parameter);
					Map<String,String> a = new HashMap<String,String>();
					a.put("key", "noticeTotal");
					a.put("value", String.valueOf(Integer.valueOf(commonService.commonGet("noticeTotal").get("value"))+1));
					commonService.commonUpdate(a);
					
				}else if(url.equals("noticeUpdate")) {
					noticeDao.noticeUpdate(parameter);
					
				}else if(url.equals("noticeDelete")) {
					noticeDao.noticeDelete(parameter);
				}
				result.put("result", "success");
				resultList.add(result);
			}
			
			if(parameter.get("resultType").equals("json")) {
				return ResponseEntity.ok(jsonOrXmlAction.createJSONString(resultList, parameter));
			}else {
				return ResponseEntity.ok(jsonOrXmlAction.createXml(resultList, parameter, url));
			}
		}catch(Exception e){
			e.printStackTrace();
			Map<String,Object> map1 = new HashMap<>();
			map1.put("result","error");
			map1.put("msg",e.getMessage());
			resultList.add(map1);
			
			Map<String,Object> a = new HashMap<>();
			a.put("result", "");
			
			if(parameter.get("resultType").equals("json")) {
				return ResponseEntity.ok(jsonOrXmlAction.createJSONString(resultList, parameter));
			}else {
				return ResponseEntity.ok(jsonOrXmlAction.createXml(resultList, parameter, url));
			}
		}
	}
	
	
}
