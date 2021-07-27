package WebSearchEngine;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private HashSet<String> links;
	private static final int MAX_DEPTH = 500;
	ArrayList<String> arr1 = new ArrayList<String>();

	public WebCrawler() {
		links = new HashSet<String>();
	}

	public void get_page_links(String myURL, int len) {
		// 4. Check if you have already crawled the URLs

		if ((!links.contains(myURL)) && (len < MAX_DEPTH)) {
			try {
				// 4. (i) If not add it to the index
				if (links.add(myURL)) {
					System.out.println(myURL);
					arr1.add(myURL);
					int i = arr1.indexOf(myURL);
					String myString = Integer.toString(i);
					String str = myURL;
					saveUrl(myString, str);
				}
				// 2. Fetch the HTML code
				Document my_document = Jsoup.connect(myURL).get();
				// 3. Parse the HTML to extract links to other URLs
				Elements link_on_page = my_document.select("a[href]");
				
				len++;
				
				// 5. For each extracted myURL... go back to Step 4.
				for (Element page : link_on_page) {
					get_page_links(page.attr("abs:href"), len);
				}
			} catch (IOException e) {
				System.err.println("For '" + myURL + "': " + e.getMessage());
			}
		}
	}

	public void saveUrl(final String filename, final String urlString) throws MalformedURLException, IOException {
		{
			try {

				// Create myURL object
				URL url = new URL(urlString);
				BufferedReader my_readr = new BufferedReader(new InputStreamReader(url.openStream()));

				// Enter filename in which you want to download
				String str = filename + ".html";

				BufferedWriter my_writer = new BufferedWriter(
						new FileWriter("C:\\eclipse\\programs\\WebSearchEngine\\youtube\\" + str));

				// read each line from stream till end
				String line;
				while ((line = my_readr.readLine()) != null) {
					my_writer.write(line);
				}

				my_readr.close();
				my_writer.close();
				System.out.println("Successfully Downloaded.");
			}

			// Exceptions
			catch (MalformedURLException mue) {
				System.out.println("Malformed URL Exception raised");
			} catch (IOException ie) {
				System.out.println("IOException raised");
			}
		}
	}

	public static void main(String[] args) {
		// 1. Pick a URL from the frontier
		new WebCrawler().get_page_links("https://www.youtube.com/", 100);
	}
}