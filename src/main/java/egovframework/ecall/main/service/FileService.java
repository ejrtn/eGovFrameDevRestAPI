package egovframework.ecall.main.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String fileUpload(MultipartFile multipartFile,String type);
	public ResponseEntity<byte[]> getImage(String path, String type);
	public ResponseEntity<byte[]> getVideo(String path, String type);
	public String fileDelete(HttpServletRequest request);
	public String imageRandomName(String type);
}
