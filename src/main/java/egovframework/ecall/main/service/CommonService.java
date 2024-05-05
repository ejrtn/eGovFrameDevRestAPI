package egovframework.ecall.main.service;

import java.util.Map;

public interface CommonService {

	public boolean commonSave(Map<String,String> map);
	public Map<String,String> commonGet(String key);
	public boolean commonUpdate(Map<String, String> map);
	public boolean commonDelete(String key);
	
	/**
	 * 페이징 처리를 위한 파라미터 추가
	 * keyList = [firstIndex , pageSize , pageUnit , pageTotal] 추가
	 * @param parameter
	 * @return
	 */
	public Map<String,Object> paging(Map<String, Object> parameter);
}
