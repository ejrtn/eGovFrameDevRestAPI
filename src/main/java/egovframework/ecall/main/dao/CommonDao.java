package egovframework.ecall.main.dao;
import java.util.Map;

public interface CommonDao {
	
	public void commonSave(Map<String,String> map) throws Exception;
	public Map<String,String> commonGet(String key) throws Exception;
	public void commonUpdate(Map<String, String> map) throws Exception;
	public void commonDelete(String key) throws Exception;
}
