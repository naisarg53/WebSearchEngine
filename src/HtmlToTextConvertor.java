package WebSearchEngine;
import java.io.*;

import org.jsoup.Jsoup;

public class HtmlToTextConvertor {
	
	// This method converts HTML Files into text documents.
	public static void convertHtmlToText() throws IOException, FileNotFoundException, NullPointerException {
		org.jsoup.nodes.Document my_document = null;
		BufferedWriter bw = null;

		try {
			File file = new File("C:\\eclipse\\programs\\WebSearchEngine\\youtube\\");
			File[] file_Array = file.listFiles();
			int i = 0;
			for (File file1 : file_Array) 
			{
				my_document = Jsoup.parse(file1, "UTF-8");
				String my_string = file1.getName().substring(0, file1.getName().lastIndexOf('.'));
				bw = new BufferedWriter(new FileWriter("C:\\eclipse\\programs\\WebSearchEngine\\youtubetext\\" + my_string + ".txt"));
				bw.write(my_document.text());
				bw.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static void main(String[] args) {
		// 1. Pick a URL from the frontier
		try {
			new HtmlToTextConvertor();
			HtmlToTextConvertor.convertHtmlToText();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}