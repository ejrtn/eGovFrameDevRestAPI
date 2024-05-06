package egovframework.ecall.main.dao;

import java.util.Map;

public interface UserDao {

	public void userSave(Map<String, Object> map) throws Exception;
	
	public Map<String,Object> login(Map<String, Object> map) throws Exception;

	public void userUpdate(Map<String, Object> map) throws Exception;

	public void userDelete(Map<String, Object> map) throws Exception;
}
