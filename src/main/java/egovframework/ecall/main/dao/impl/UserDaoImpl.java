package egovframework.ecall.main.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.ecall.main.dao.CommonDao;
import egovframework.ecall.main.dao.UserDao;
import egovframework.ecall.main.mapper.Mapper;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void userSave(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.userSave(map);
	}

	@Override
	public Map<String, Object> login(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		return mapper.login(map);
	}

	@Override
	public void userUpdate(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.userUpdate(map);
	}

	@Override
	public void userDelete(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.userDelete(map);
	}

	
	
}
