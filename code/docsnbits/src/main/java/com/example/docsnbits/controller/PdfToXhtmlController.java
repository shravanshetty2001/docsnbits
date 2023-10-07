package com.example.docsnbits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.docsnbits.service.PdffileInterface;

@RestController
@CrossOrigin(origins="*")
public class PdfToXhtmlController {
	
	@Autowired
	private PdffileInterface pdffileinterface;
	
	@PostMapping(value="/fileconvert", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public String getFile(@RequestPart ("file") MultipartFile file)
	{
		try
		{
			pdffileinterface.setFile(file);		
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		return "File uploaded successfully";
	}

}
