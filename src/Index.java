package WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Index {
			
	public static void main(String[] args) throws Exception {

		System.out.println("---------- WEB SEARCH ENGINE ----------");
		Index websearch = new Index();

		Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();		
		Scanner sc = new Scanner(System.in);
		String option = "yes";
		do {	
		
		System.out.println("Enter search query : ");
		String input = sc.nextLine();
		long count_file = 0;
		int occurance = 0;
		int word_found = 0; 
		try {
			
			File my_dir = new File("C:\\eclipse\\programs\\WebSearchEngine\\youtubetext");

			File[] fileArray = my_dir.listFiles();

			int i=0;
			
			while(i < fileArray.length) {
				
				occurance = websearch.searchWord(fileArray[i], input); // find occurance of word in each file

				hashtable.put(fileArray[i].getName(), occurance); 
				
				if (occurance != 0) {
					word_found++; // counts number of files in which frequency of word != 0 
				}

				count_file++; // count total number of files
				i++;

			}

			System.out.println("\nTotal No. of Files for input " + input + " is : " + word_found);
			
			if (word_found == 0) { // no match found

				System.out.println("\nSearching Suggestions...");
				System.out.println("\nPlease wait...");

				Dictionary.creatDictionary();
				websearch.suggestions(input);

			}
			
			Sorting.rankFiles(hashtable, word_found);
			System.out.println("\n\nDo you want to continue(yes/no)??");
			option = sc.nextLine();	
			if(option.equals("no")) {
				sc.close();
				System.out.println("Exited");
			}
			
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		
		}while(option.equals("yes"));
	}

	// Find Positions and Occurrences of the words
	public int searchWord(File filePath, String s1) throws IOException {
		
		String my_data = "";
		
		try {
			BufferedReader my_Object = new BufferedReader(new FileReader(filePath));
			String line = null;

			while ((line = my_Object.readLine()) != null) {

				my_data = my_data + line;

			}
			my_Object.close();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		char txt[] = my_data.toCharArray();
		char pat[] = s1.toCharArray();
		
		int result = BoyerMoore.search(txt, pat);

		if (result != 0) {
			System.out.println("\nFile that contains above query in list = " + filePath.getName());
			System.out.println("--------------------------------------\n");
		}
		
		return result;
	}
	
	public static void spellCheck(String pattern) throws IOException{
		//Scanner sc= new Scanner(System.in);
//		System.out.println("Enter Word to search:");
//		String sword=sc.nextLine();
	
		String filename="dictionary.txt";
		File file = new File(filename);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			ArrayList<String> dictWords = new ArrayList<String>();
			String str=null;
			while((str= reader.readLine())!=null)
			{
				dictWords.add(str);
			}
			
			int ed,minED=10, secMinED=10;
			int sugWordOne=0;
			int sugWordTwo=0;
			for(int i = 0; i<dictWords.size();i++){
				String dw=dictWords.get(i);
				ed = EditDistance.editDistance(dw, pattern);
//				System.out.println(dw+"  "+ed);
				if(ed<secMinED) {
					if(ed<minED) {
						minED=ed;
						sugWordOne=i;
					}
					else {
						secMinED=ed;
						sugWordTwo=i;
					}
				}
			}
			System.out.println("Did you mean: "+dictWords.get(sugWordOne)+" or "+dictWords.get(sugWordTwo)+"?");
		}	
	}

	//Using Regular Expressions for pattern matching
	public void suggestions(String pattern) {
		try {
			spellCheck(pattern);
		}catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}
	