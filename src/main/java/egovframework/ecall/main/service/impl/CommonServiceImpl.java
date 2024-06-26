package egovframework.ecall.main.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.ecall.main.dao.CommonDao;
import egovframework.ecall.main.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	private CommonDao commonDao;
	
	@Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
	
	@Override
	public Map<String, String> commonGet(String key) {
		// TODO Auto-generated method stub
		try {
			return commonDao.commonGet(key);
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

	@Override
	public boolean commonSave(Map<String, String> map) {
		// TODO Auto-generated method stub

		try {
			commonDao.commonSave(map);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean commonUpdate(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			commonDao.commonUpdate(map);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean commonDelete(String key) {
		// TODO Auto-generated method stub
		try {
			
			commonDao.commonDelete(key);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Object> paging(Map<String,Object> parameter) {
		// TODO Auto-generated method stub
		
		try {
			int firstIndex = (Integer.parseInt(parameter.get("pageNumber").toString())-1)*propertiesService.getInt("pageSize");
			parameter.put("firstIndex", firstIndex);
			parameter.put("pageSize", propertiesService.getInt("pageSize"));
			
			
			
			int pageTotal;
			if(Integer.parseInt(commonDao.commonGet("noticeTotal").get("value")) % propertiesService.getInt("pageSize") > 0) {
				pageTotal = Integer.parseInt(commonDao.commonGet("noticeTotal").get("value"))/propertiesService.getInt("pageSize")+1;
				
			}else {
				pageTotal = Integer.parseInt(commonDao.commonGet("noticeTotal").get("value"))/propertiesService.getInt("pageSize");
			}
			parameter.put("pageTotal",pageTotal);
			parameter.put("pageUnit",propertiesService.getInt("pageUnit"));
			return parameter;
		}catch(Exception e){
			
		}
		
		return null;
	}


}
