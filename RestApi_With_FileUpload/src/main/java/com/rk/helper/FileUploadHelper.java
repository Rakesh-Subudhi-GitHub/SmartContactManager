package com.rk.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	//public final String UPLOAD_DIR="C:\\Users\\star\\eclipse-workspace\\SpringBoot\\RestApi_With_FileUpload\\src\\main\\resources\\static\\image";
	
	public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	public FileUploadHelper()throws IOException
	{
		//handel the UPLOAD_DIR  path
	}
	
	public boolean uploadFile(MultipartFile fileDATA)
	{
		 boolean flag=false;
		 
		 try {
			 
			 //read the file
/*			 InputStream is=fileDATA.getInputStream();
			 byte data[]=new  byte[is.available()];
			 is.read(data);
			 
			 //write the file
			 //FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+fileDATA.getOriginalFilename());
			 
			 FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+File.separator+fileDATA.getOriginalFilename());
			 
			 fos.write(data);
			 fos.flush();
			 fos.close();
			 flag=true;																																			*/
			 
			
			 //Files.copy(InputStream,path target,options);
			 Files.copy(fileDATA.getInputStream(),
					 												Paths.get(UPLOAD_DIR+File.separator+fileDATA.getOriginalFilename()),
					 																													StandardCopyOption.REPLACE_EXISTING);
			 flag=true;
			 
		 		}//try
		 
		 catch (Exception e) {
		flag=false;
		e.printStackTrace();
		 }
		 
		 return flag;
	}//method
}//class
