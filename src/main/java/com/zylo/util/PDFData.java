package com.zylo.util;

public class PDFData {

	private String html;

	private String outputFile;
	
	private String s3key;
	
	private String s3bucket;


	public PDFData() {
		super();
	}

	public PDFData(String html, String outputFile, String bucket, String s3key) {
		super();
		this.html = html;
		this.outputFile = outputFile;
		this.s3bucket = bucket;
		this.s3key = s3key;
	}
	
	public String getS3bucket() {
		return s3bucket;
	}

	public void setS3bucket(String s3bucket) {
		this.s3bucket = s3bucket;
	}
	
	public String getS3key() {
		return s3key;
	}

	public void setS3key(String s3key) {
		this.s3key = s3key;
	}


	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

}