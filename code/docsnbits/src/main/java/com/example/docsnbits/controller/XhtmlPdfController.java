package com.example.docsnbits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.docsnbits.service.HtmlpdfInterface;

public class XhtmlPdfController {
	
	@Autowired
	private HtmlpdfInterface htmlpdfinterface;
	
	@PostMapping(value="/xhtmlpdf", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
	public String getFile(@RequestPart("file") MultipartFile file)
	{
		try
		{
			htmlpdfinterface.setFile(file);
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		return "File uploaded successfully";
	}

}
