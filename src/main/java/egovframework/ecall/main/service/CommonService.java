package egovframework.ecall.main.service;

import java.util.Map;

public interface CommonService {

	public boolean commonSave(Map<String,String> map);
	public Map<String,String> commonGet(String key);
	public boolean commonUpdate(Map<String, String> map);
	public boolean commonDelete(String key);
	
	public Map<String,Object> paging(Map<String, Object> parameter);
}
