package WebSearchEngine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class Sorting {

	public static void rankFiles(Hashtable<?, Integer> fname, int occur) {

		ArrayList<Map.Entry<?, Integer>> my_list = new ArrayList(fname.entrySet());

		Collections.sort(my_list, new Comparator<Map.Entry<?, Integer>>() {

			public int compare(Map.Entry<?, Integer> obj1, Map.Entry<?, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		Collections.reverse(my_list);

		if (occur != 0) {
			System.out.println("\n------Top 5 search results -----\n");

			int my_number = 5;
			int j = 1;
			while (my_list.size() > j && my_number > 0) {
				System.out.println("(" + j + ") " + my_list.get(j) + " times ");
				j++;
				my_number--;
			}
		} else {

		}
		/*
		// Transfer as List and sort it
		ArrayList<Map.Entry<?, Integer>> my_list = new ArrayList<>(fname.entrySet());

		Collections.sort(my_list, new Comparator<Map.Entry<?, Integer>>() {

			public int compare(Map.Entry<?, Integer> obj1, Map.Entry<?, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		Collections.reverse(my_list);

		if (occur != 0) {
			System.out.println("\n------Top 5 search results -----\n");
			
			for(int j=1, my_number=5; j<my_list.size() && my_number>0;j++, my_number--) {
				System.out.println("(" + j + ") " + my_list.get(j) + " times ");				
			}
						
		}*/

	}

}
