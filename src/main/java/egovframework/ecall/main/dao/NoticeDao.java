package egovframework.ecall.main.dao;

import java.util.List;
import java.util.Map;

public interface NoticeDao {

	public List<Map<String,Object>> noticeList(Map<String, Object> map) throws Exception;
	
	public int noticeLastId() throws Exception;

	public void noticeSave(Map<String, Object> map) throws Exception;
	
	public List<Map<String,Object>> noticeGet(Map<String, Object> map) throws Exception;

	public void noticeUpdate(Map<String, Object> map) throws Exception;

	public void noticeDelete(Map<String, Object> map) throws Exception;
}
