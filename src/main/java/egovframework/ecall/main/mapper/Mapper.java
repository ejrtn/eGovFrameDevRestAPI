package egovframework.ecall.main.mapper;

import java.util.List;
import java.util.Map;

public interface Mapper {
	
	// notice
	List<Map<String,Object>> noticeList(Map<String, Object> map) throws Exception;
	void noticeSave(Map<String, Object> map) throws Exception;
	List<Map<String,Object>> noticeGet(Map<String, Object> map) throws Exception;
	void noticeUpdate(Map<String, Object> map) throws Exception;
	void noticeDelete(Map<String, Object> map) throws Exception;
	
	// common
	void commonSave(Map<String, String> map) throws Exception;
	Map<String,String> commonGet(String key) throws Exception;
	void commonUpdate(Map<String, String> map) throws Exception;
	void commonDelete(String key) throws Exception;
	
	void userSave(Map<String, Object> map) throws Exception;
	Map<String, Object> login(Map<String, Object> map) throws Exception;
	void userDelete(Map<String, Object> map) throws Exception;
	void userUpdate(Map<String, Object> map) throws Exception;
}
