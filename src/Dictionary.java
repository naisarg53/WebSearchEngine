package WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
	public static ArrayList<String> dictionory(String filename,ArrayList<String> aListWords) throws Exception{
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s;
		String word = null;
		
		while((s = br.readLine()) != null) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(s);
			while (scan.hasNext()) {
				word = scan.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
//				word=word.split(".");
				aListWords.add(word);
			}
		}
		br.close();
		Set<String> set = new HashSet<>(aListWords);
		aListWords.clear();
		aListWords.addAll(set);
		Collections.sort(aListWords);
		return aListWords;
	}
	
	public static void creatDictionary() {
		File directory = new File("C:\\eclipse\\programs\\WebSearchEngine\\youtubetext\\");
		String[] children = directory.list();
		ArrayList<String> aListWords = new ArrayList<String>();
		for (String str:children)
		{
			String strx = str.substring(str.lastIndexOf(".")+1);
			if (strx.equals("txt"))
			{
				try {
					dictionory("C:\\eclipse\\programs\\WebSearchEngine\\youtubetext\\" + "/"+ str,aListWords);
					FileWriter writer = new FileWriter("dictionary.txt"); 
					for(String s: aListWords) {
					  writer.write(s + System.lineSeparator());
					}
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
