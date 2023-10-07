package com.example.docsnbits.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface HtmlpdfInterface {
	public void setFile(MultipartFile multipartFile) throws IOException;

}
