import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

public class PDFGenerator {

	public static void main(String[] args) throws Exception{
		byte[] b = new byte[10000];
		FileInputStream fis = new FileInputStream("input2.html");
		fis.read(b);
		fis.close();
		String html = new String(b).trim();
		String out = "output.pdf";
		generatePDF(out, html);
		System.out.println("End");		
	}
	
	public static boolean generatePDF(String path, String html) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("Generating PDF: path = "+path);	
		File pdfDest = new File(path);
		ConverterProperties converterProperties = new ConverterProperties();
		HtmlConverter.convertToPdf(html, new FileOutputStream(pdfDest), converterProperties);
		System.out.println("Generated PDF");	
		return true;
	}
}
