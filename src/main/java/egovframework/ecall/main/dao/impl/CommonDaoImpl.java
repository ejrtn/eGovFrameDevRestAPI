package egovframework.ecall.main.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.ecall.main.dao.CommonDao;
import egovframework.ecall.main.mapper.Mapper;

@Repository
public class CommonDaoImpl implements CommonDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void commonSave(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.commonSave(map);
	}

	@Override
	public Map<String,String> commonGet(String key) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		return mapper.commonGet(key);
	}

	@Override
	public void commonUpdate(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.commonUpdate(map);
	}

	@Override
	public void commonDelete(String key) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.commonDelete(key);
	}
	
	
}
