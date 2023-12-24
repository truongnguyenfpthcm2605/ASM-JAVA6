package com.poly.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
		@Autowired
		HttpServletRequest request;
		
		@Autowired
		ServletContext app;
		
		public String getString(String name, String defaultValue) {
			return name!=null?name:defaultValue;
		}
		
		public int getInt(String name, int defaultValue) {
			try {
				return Integer.parseInt(name);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		public Double getDouble(String name, double defaultValue){
			try {
				return Double.parseDouble(name);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		public boolean getBoolean(String name, boolean defaultValue){
			try {
				return Boolean.parseBoolean(name);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		
		public Date getDate(String name, String pattern) {
		    try {
		        DateFormat dateFormat = new SimpleDateFormat(pattern);
		        Date date = (Date) dateFormat.parse(name);
		        return date;
		    } catch (ParseException e) {
		        throw new RuntimeException("Lỗi sai định dạng", e);
		    }
		}
		
		public File save(MultipartFile file , String folder) {
			File dir = new File(app.getRealPath(folder));
			if(!dir.exists()) dir.mkdirs();
			try {
				File svaefile = new File(dir,file.getOriginalFilename());
				file.transferTo(svaefile);
				return svaefile;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public List<File> saveFiles( MultipartFile[] attach, String folderName) throws IllegalStateException, IOException {
		    File folder = new File(app.getRealPath(folderName));
		    if (!folder.exists()) {
		        folder.mkdir();
		    }
		    List<File> listfile = new ArrayList<>();
		    for (MultipartFile file : attach) {
		        if (!file.isEmpty()) {
		            File outputFile = new File(folder ,file.getOriginalFilename());
		            file.transferTo(outputFile);
		            listfile.add(outputFile);
		        }
		    }
		    return listfile;
		
		}
		
		public String getStringfiles( MultipartFile[] attach, String folderName) throws IllegalStateException, IOException {
		    File folder = new File(app.getRealPath(folderName));
		    if (!folder.exists()) {
		        folder.mkdir();
		    }
		    StringBuilder str = new StringBuilder();
		    for (MultipartFile file : attach) {
		        if (!file.isEmpty()) {
		            File outputFile = new File(folder ,file.getOriginalFilename());
		            file.transferTo(outputFile);
		            str.append(outputFile.getName()).append("-");
		        }
		    }
		    return str.toString();
		
		}
		
}
