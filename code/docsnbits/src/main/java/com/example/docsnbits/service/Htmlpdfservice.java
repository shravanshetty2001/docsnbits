package com.example.docsnbits.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class Htmlpdfservice implements HtmlpdfInterface{
	
	@Override
	public void setFile(MultipartFile multipartFile) throws IOException
	{
		File file=new File("temp.html");
		try(OutputStream os=new FileOutputStream(file))
		{
			os.write(multipartFile.getBytes());
		}
		Document document=Jsoup.parse(file,"UTF-8");
		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
		File outputPdf=new File("outputPdf.pdf");
		try(OutputStream outputStream=new FileOutputStream(outputPdf))
		{
			PdfRendererBuilder builder=new PdfRendererBuilder();
			builder.withFile(outputPdf);
			builder.toStream(outputStream);
			builder.withW3cDocument(new W3CDom().fromJsoup(document),"/");
			builder.run();
		}
	}

}
