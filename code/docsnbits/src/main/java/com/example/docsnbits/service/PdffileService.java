package com.example.docsnbits.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.docsnbits.dto.Pdffile;

@Service
public class PdffileService implements PdffileInterface {
	@Override
	public void setFile(MultipartFile multipartFile)
	{
		File file=new File("temp.pdf");
		try (OutputStream os=new FileOutputStream(file))
		{
			os.write(multipartFile.getBytes());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Pdffile pdf=new Pdffile(); 
		PdffileService pdffileService=new PdffileService();
		pdf.setFileName(multipartFile.getOriginalFilename());
		pdffileService.convertFile(file.getAbsolutePath());
		file.delete();		
	}

	@Override
	public Map<String, Object> convertFile(String fileName){
		// TODO Auto-generated method stub
		Map<String, Object> response=new HashMap<String, Object>();
		Path path=Paths.get(fileName);
		try(Writer output=new PrintWriter(path.getFileName()+".html","utf-8")) {
			PDDocument document=PDDocument.load(new File(fileName));
			new PDFDomTree().writeText(document, output);
			response.put("success", true);
			response.put("html", output.toString());
		}
		catch(Exception e)
		{
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
		
		
	}

}
