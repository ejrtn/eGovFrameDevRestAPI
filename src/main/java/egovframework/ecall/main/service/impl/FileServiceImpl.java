package egovframework.ecall.main.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.ecall.main.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Override
	public String fileUpload(MultipartFile multipartFile, String type) {
		// TODO Auto-generated method stub
		try {
			String properties = multipartFile.getContentType().contains("image") ? "imageFilePath" : "videoFilePath";
			
	        File file = null;
	        if(!multipartFile.isEmpty()){
	        	int c = 0;
	        	String file_name = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        	String new_file_name = file_name;
	        	String ext = file_name.substring(file_name.lastIndexOf(".")); //확장자
	        	while(true) {
	        		file = new File(propertiesService.getString(properties)+"/" + type + "/" + new_file_name);
		        	if(file.isFile()) {
		        		// 저장된 파일로 변경하여 이를 보여주기 위함
		                c += 1;
		                // file_name.substring(0, file_name.lastIndexOf(".") - 1) 확장자 제거 이름
		                new_file_name = file_name.substring(0, file_name.lastIndexOf(".") - 1) + " (" + String.valueOf(c) + ")" + ext;
		        	}else {
		        		multipartFile.transferTo(file);
		        		Map<String,String> map = new HashMap();
		        		map.put("media_path", new_file_name);
		        		map.put("type", multipartFile.getContentType());
		        		return new_file_name;
		        	}
	        	}
            }
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ResponseEntity<byte[]> getImage(String path, String type) {
		
		try {
			File file = new File(propertiesService.getString("imageFilePath") + "/" + type + "/" + path);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Contest-type", Files.probeContentType(file.toPath()));
			
			return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<byte[]> getVideo(String path, String type) {
		try{
			// TODO Auto-generated method stub
			File file = new File(propertiesService.getString("videoFilePath") + "/" + type + "/" + path);
						
			HttpHeaders headers = new HttpHeaders();
			headers.add("Contest-type", Files.probeContentType(file.toPath()));
						
			return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Override
	public String fileDelete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try{
			ServletInputStream inputStream = request.getInputStream();
			String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			
			String[] paths = mapper.readValue(messageBody, Map.class).get("srcs").toString().split("\n");
			for(int i=0;i<paths.length;i++) {
				File file=null;
				if(paths[i].contains("getImage")) {
					file = new File(propertiesService.getString("imageFilePath") + "/" + mapper.readValue(messageBody, Map.class).get("type") + "/" + paths[i].replace("getImage.do?path=", ""));
				}else {
					file = new File(propertiesService.getString("videoFilePath") + "/" + mapper.readValue(messageBody, Map.class).get("type") + "/" + paths[i].replace("getVideo.do?path=", ""));
				}
				if(file.isFile()) {
					file.delete();
				}
			}
			return "success";
        } catch (IOException e) {
        	System.out.println(e);
        	e.printStackTrace();
        }
        return "error";
	}
	
	public String imageRandomName(String type) {
		File folder = new File(propertiesService.getString("imageFilePath") + "/" +type);
		Random random = new Random();
		int i = random.nextInt((int) folder.list().length);
		int c = 0;
		for(String name: folder.list()) {
			if(c==i) return name;
			else c++;
		}
		return "";
	}
	
	@Override
	public String fileList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try{
			ServletInputStream inputStream = request.getInputStream();
			String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			
			File folder;
			
			String path = mapper.readValue(messageBody, Map.class).get("path").toString();
			if(path.equals("image")) {
				folder = new File(propertiesService.getString("imageFilePath") + "/" + mapper.readValue(messageBody, Map.class).get("type") + "/");
			}else {
				folder = new File(propertiesService.getString("videoFilePath") + "/" + mapper.readValue(messageBody, Map.class).get("type") + "/");
			}
			String fileList = "";
			int c = 0;
			for(String name: folder.list()) {
				fileList += name;
				if(c != folder.list().length-1) {
					fileList += "\n";
				}
				c += 1;
			}
			return fileList;
        } catch (IOException e) {
        	System.out.println(e);
        	e.printStackTrace();
        }
        return null;
	}
}
