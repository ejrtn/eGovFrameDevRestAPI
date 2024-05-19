package egovframework.ecall.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.ecall.main.service.FileService;

@RestController
@CrossOrigin
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
	
	@PostMapping(value="/fileUpload.do")
	public String fileUpload(@RequestPart(value="file",required = false) MultipartFile file,@RequestParam(value="type",required = false) String type) {
		return fileService.fileUpload(file,type);
	}
	
	@GetMapping(value="/getImage.do")
	public ResponseEntity<?> getImage(@RequestParam(value="path",required = false) String path, @RequestParam(value="type",required = false) String type) {
		return fileService.getImage(path,type);
	}
	
	@GetMapping(value="/getVideo.do")
	public ResponseEntity<?> getVideo(@RequestParam(value="path",required = false) String path, @RequestParam(value="type",required = false) String type) {
		return fileService.getVideo(path,type);
	}
	
	@GetMapping(value="/imageRandomName.do")
	public String imageRandomName(@RequestParam(value="type",required = false) String type) {
		return fileService.imageRandomName(type);
	}
	
	@PostMapping(value="/fileDelete.do")
	public String fileDelete(HttpServletRequest request) {
		return fileService.fileDelete(request);
	}
	
	@PostMapping(value="/fileList.do")
	public String fileList(HttpServletRequest request) {
		return fileService.fileList(request);
	}
}
