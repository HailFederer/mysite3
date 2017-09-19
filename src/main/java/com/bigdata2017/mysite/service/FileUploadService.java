package com.bigdata2017.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private static String SAVE_PATH = "/bigdata/workspace/mysite3/gallery";
	private static String PREFIX_URL = "/bigdata/workspace/mysite3/gallery/images";
	
	public String restore(MultipartFile multipartFile) {
		
		String url = "";
		
		try {				
				String originalFilename =  multipartFile.getOriginalFilename();
				if("".equals(originalFilename)) {
					return null;
				}
				String extName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());		
				Long size = multipartFile.getSize();
				String saveFileName = genSaveFileName(extName);
		
				writeFile(multipartFile, saveFileName);
				
				url = PREFIX_URL +"/"+ saveFileName;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return url;
	}
	
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH +"/"+ saveFileName);
		fos.write(fileData);
		fos.close();
	}
	
	public void deleteFile(String saveFileName){
		
		String savedFileName = "";
		StringTokenizer st = new StringTokenizer(saveFileName, "/");
		int count = st.countTokens();
		for(int i=0; i<count; i++) {
			savedFileName = st.nextToken();
		}
		
		System.out.println(savedFileName);
		
		File file = new File(SAVE_PATH +"/"+ savedFileName);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	private String genSaveFileName(String extName) {
		
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH+1);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
}
