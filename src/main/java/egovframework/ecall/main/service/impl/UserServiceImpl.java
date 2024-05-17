package egovframework.ecall.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.ecall.main.common.JsonOrXmlAction;
import egovframework.ecall.main.dao.UserDao;
import egovframework.ecall.main.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JsonOrXmlAction jsonOrXmlAction;

	@Override
	public ResponseEntity<?> user(HttpServletRequest request, String url) {
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> resultList = new ArrayList<>();
		Map<String,Object> parameter = new HashMap<String, Object>();
		
		// TODO Auto-generated method stub
		try {
			ObjectMapper mapper = new ObjectMapper();
			parameter = mapper.readValue(StreamUtils.copyToByteArray(request.getInputStream()), Map.class);
			
			HttpHeaders httpHeaders= new HttpHeaders();
			
			if(url.equals("login")) {
				if(userDao.login(parameter) != null) {
					result.put("result", "success");
				}else {
					result.put("result", "nodata");
				}
			}else if(url.equals("userSave") || url.equals("userDaoUpdate") || url.equals("userDaoDelete")) {
				if(url.equals("userSave")) {
					userDao.userSave(parameter);
				}else if(url.equals("userUpdate")) {
					userDao.userUpdate(parameter);
				}else if(url.equals("userDelete")) {
					userDao.userDelete(parameter);
				}
				result.put("result", "success");
			}
			resultList.add(result);
			
			if(parameter.get("resultType").equals("xml")) {
				httpHeaders.setContentType(MediaType.APPLICATION_XML);
				return new ResponseEntity<>(jsonOrXmlAction.createXml(resultList,parameter,url),httpHeaders, HttpStatus.OK);
			}else {
			    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<>(jsonOrXmlAction.createJSONString(resultList,parameter),httpHeaders, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String,Object> map = new HashMap<>();
			map.put("result","error");
			map.put("msg",e.getMessage());
			resultList.add(map);
		}
		
		if(parameter.get("resultType").equals("xml")) {
			return ResponseEntity.ok().body(jsonOrXmlAction.createXml(resultList,parameter,url));
		}else {
			Map<String,Object> a = new HashMap<>();
			a.put("result", "");
			HttpHeaders httpHeaders= new HttpHeaders();
		    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			return new ResponseEntity<>(jsonOrXmlAction.createJSONString(resultList,parameter),httpHeaders, HttpStatus.OK);
		}
	}

}
