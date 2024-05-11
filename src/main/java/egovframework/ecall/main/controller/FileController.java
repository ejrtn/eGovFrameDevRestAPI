package egovframework.ecall.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.ecall.main.service.FileService;

@RestController
@CrossOrigin
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	
	@PostMapping(value="/fileUpload.do")
	public String fileUpload(@RequestPart(value = "file", required = false) MultipartFile file) {
		System.out.println(file);
		return fileService.fileUpload(file);
	}
}
