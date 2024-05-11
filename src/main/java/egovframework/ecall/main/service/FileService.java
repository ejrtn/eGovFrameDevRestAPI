package egovframework.ecall.main.service;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String fileUpload(MultipartFile multipartFile);
	public ResponseEntity<Resource> fileUrl(String filePath);
}
