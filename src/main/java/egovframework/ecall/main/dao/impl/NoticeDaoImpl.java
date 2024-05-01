package egovframework.ecall.main.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.ecall.main.dao.NoticeDao;
import egovframework.ecall.main.mapper.Mapper;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String,Object>> noticeList(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		return mapper.noticeList(map);
	}

	@Override
	public void noticeSave(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.noticeSave(map);
	}
	
	@Override
	public List<Map<String,Object>> noticeGet(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		return mapper.noticeGet(map);
	}

	@Override
	public void noticeUpdate(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.noticeUpdate(map);
	}

	@Override
	public void noticeDelete(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		mapper.noticeDelete(map);
	}
	
}
