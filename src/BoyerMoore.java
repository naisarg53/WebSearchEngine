package WebSearchEngine;
public class BoyerMoore {

    static int NO_OF_CHARS = 10000; 
      
   //A utility function to get maximum of two integers 
    static int max (int a, int b) { return (a > b)? a: b; } 
 
    //The preprocessing function 
    static void badCharHeuristic( char []str, int size,int badchar[]) 
    { 
     int i; 
 
     // Initialize all occurrences as -1 
     for (i = 0; i < NO_OF_CHARS; i++) 
          badchar[i] = -1; 
  
     for (i = 0; i < size; i++) 
          badchar[(int) str[i]] = i; // A=65, 65th position in array as 0
     //	System.out.print(badchar[65]);
    } 
 
    /* A pattern searching function that uses Bad 
    Character Heuristic of Boyer Moore Algorithm */
    static int search( char txt[],  char pat[]) 
    { 
     int m = pat.length; // 3
     int n = txt.length; // 8
     int count=0;
     int badchar[] = new int[NO_OF_CHARS]; 
 
     badCharHeuristic(pat, m, badchar); 
 
     int s = 0;  // s is shift of the pattern
     
     while(s <= (n - m)) // 0 < 5
     { 
         int j = m-1;  // j = 2
 
         /* compare pattern character until match is found  */
         while(j >= 0 && pat[j] == txt[s+j]) // 2>=0 && C == A condition failed  C == A  3rd iter match found 
             j--; 
 
         /* if match found found j will  be -1 */
         if (j < 0) 
         { 
             System.out.println("counter "+(count)+" Patterns occur at shift = " + s); 
 
             /* Shift the pattern so that the next character in text aligns with the last occurrence of it in pattern. 
                The condition s+m < n is necessary for the case when pattern occurs at the end of text */
             count++;
             System.out.print("m="+m+" "+badchar[txt[s+m]]+" "+txt[s+m]);
             s += (s+m < n)? m-badchar[txt[s+m]] : 1; // s=4 + 3-badchar[D] s = 4 + 3 - (-1) = 8
             System.out.print(s);
 
         } 
 
         else
        	 /* Shift the pattern until last occurance is in the pattern
        	   max function is used  to get positive value        	   */
             
        	// System.out.print(badchar[txt[4]]);
             s += max(1, j - badchar[txt[s+j]]); // s=0 max(1, 2-badchar[txt[0+2]]) max(1, 2-badchar[A]) max(1, 2-0) s=2 again s=4
     } 
     return count;
    } 
    /*
    public static void main(String args[]) {  
        
        char []txt = "ABAAABCD".toCharArray();  
        char []pat = "ABC".toCharArray();  
        search(txt, pat);  
    }  */
}