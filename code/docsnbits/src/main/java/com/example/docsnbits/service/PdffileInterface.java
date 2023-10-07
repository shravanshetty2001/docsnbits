package com.example.docsnbits.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PdffileInterface {
	public void setFile(MultipartFile fileName);
	public Map<String, Object> convertFile(String fileName);

}
