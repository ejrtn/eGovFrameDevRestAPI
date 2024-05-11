package egovframework.ecall.main.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import egovframework.ecall.main.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Override
	public String fileUpload(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println(multipartFile.getContentType());
			String properties = multipartFile.getContentType().contains("image") ? "imageFileUploadPath" : "videoFileUploadPath";
			
	        File file = null;
	        System.out.println(multipartFile.isEmpty());
	        if(!multipartFile.isEmpty()){
	        	int c = 0;
	        	while(true) {
	        		String file_name = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        		System.out.println(new File(propertiesService.getString(properties) + "/" + file_name).isFile());
		        	if(new File(propertiesService.getString(properties) + "/" + file_name).isFile()) {
		        		// 저장된 파일로 변경하여 이를 보여주기 위함
		                c += 1;
		                file_name = file_name + "(" + String.valueOf(c) + ")";
		        	}else {
		        		
		        		file = new File(propertiesService.getString(properties) + "/" + file_name);
		        		multipartFile.transferTo(file);
		        		return fileUrl(propertiesService.getString(properties) + "/" + file_name).toString();
		        	}
	        	}
            }
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ResponseEntity<Resource> fileUrl(String filePath) {
		
		try {
			// TODO Auto-generated method stub
			File file = new File(filePath);
			Resource resource =  (Resource) new FileSystemResource(filePath);
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s",file.getName()));
			headers.setContentType(MediaType.parseMediaType(Files.probeContentType(Paths.get(filePath))));
			
			return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
