package com.zylo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

public class PDFHandler implements RequestHandler<PDFData, Boolean> {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public Boolean handleRequest(PDFData event, Context context) {
		LambdaLogger logger = context.getLogger();
		// process event
		logger.log("PDFHandler - Generating PDF: " + gson.toJson(event));
		try {
			String outputFilePath = "/tmp/"+event.getOutputFile();
			File pdfDest = new File(outputFilePath);
			ConverterProperties converterProperties = new ConverterProperties();
			HtmlConverter.convertToPdf(event.getHtml(), new FileOutputStream(pdfDest), converterProperties);
			logger.log("Generated PDF");
		    
			AmazonS3 s3 = AmazonS3Client.builder().build();
			s3.putObject(event.getS3bucket(), event.getS3key() ,pdfDest);

		    System.out.println("Uploaed to S3 : bucket: "+event.getS3bucket()+" - key: "+event.getS3key());
		    
		} catch (Exception e) {
			logger.log("Error Generating PDF:: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
}