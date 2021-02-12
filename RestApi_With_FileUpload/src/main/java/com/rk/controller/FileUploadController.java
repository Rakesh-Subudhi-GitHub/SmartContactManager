package com.rk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rk.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUpload;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uplaodFile(@RequestParam("file") MultipartFile file)
	{
		System.out.println("file name ::   "+file.getOriginalFilename());
		System.out.println("file size  ::  "+file.getSize());
	
		var flag=false;
		
		flag=fileUpload.uploadFile(file);
		
		if(flag)
		{
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
		}
		else
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something get Wrong");
				
	}//method
}//class
