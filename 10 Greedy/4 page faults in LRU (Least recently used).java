// url - https://practice.geeksforgeeks.org/problems/page-faults-in-lru/0

/**

In operating systems that use paging for memory management, page replacement algorithm are needed to decide which page needs to be replaced when the new page comes in. Whenever a new page is referred and is not present in memory, the page fault occurs and Operating System replaces one of the existing pages with a newly needed page. Given a sequence of pages and memory capacity, your task is to find the number of page faults using Least Recently Used (LRU) Algorithm.

Input:
The first line of input contains an integer T denoting the number of test cases. Each test case contains n number of pages and next line contains space seaprated sequence of pages. The following line consist of the capacity of the memory.
Note: Pages are referred in the order left to right from the array (i.e index 0 page is referred first then index 1 and so on). Memory is empty at the start.

Output:
Output the number of page faults.

Constraints:
1<=T<=100
1<=n<=1000
4<=capacity<=100

Example:
Input:
2
9
5 0 1 3 2 4 1 0 5
4
8
3 1 0 2 5 4 1 2
4

Output:
8
7

Explanation:
Testcase 1:
memory allocated with 4 pages 5, 0, 1, 3: page fault = 4
page number 2 is required, replaces LRU 5: page fault = 4+1 = 5
page number 4 is required, replaces LRU 0: page fault = 5 + 1 = 6
page number 1 is required which is already present: page fault = 6 + 0 = 6
page number 0 is required which replaces LRU 1: page fault = 6 + 1 = 7
page number 5 is required which replaces LRU 3: page fault = 7 + 1  =8.

**/




import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            int n = Integer.parseInt(br.readLine());
            String[] pages = br.readLine().trim().split("\\s+");
            int c = Integer.parseInt(br.readLine());
            
            
            //----------- SOLUTION ----------------------------------
            
            Set<String> s = new LinkedHashSet<>(c);    // using linked hashset to store in order
            int faults = 0;
            
            for(int i=0; i<n; i++){
                if(!s.contains(pages[i])){                        // not present in set (FAULT)
                    if(s.size()<c) s.add(pages[i]);               // space available  -->  add page
                    else{
                        Iterator<String> itr = s.iterator();      // space not available
                        s.remove(itr.next());                     // remove LRU i.e, the first item in set
                        s.add(pages[i]);                          // add the new page
                    }
                    faults++;
                }
                else{                                             // already present in set (HIT)
                    s.remove(pages[i]);                           // remove the already present page
                    s.add(pages[i]);                              // reenter that page
                }
            }
            System.out.println(faults);
        }
    }
    
}









/*

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
 {
    public static void solution(String[] str, int n, int p){
        Set<String> st = new LinkedHashSet<>();
        int i=0, faults = 0;
        while(st.size()!=p && i<n){                 // filling pages till we reach the max limit
            if(!st.contains(str[i])) faults++;      // if not found --> faults++ --> add to the set
            else st.remove(str[i]);                 // if found  --> remove the existing one and add it again so that its now the most recently used
            st.add(str[i]);
            i++;
        }
        while(i<n){                                 // for the rest of the pages
            if(!st.contains(str[i])){               // if not found --> faults++ --> and remove the very first element (LRU) of the set --> add to the set
                faults++;
                Iterator itr = st.iterator();
                if(itr.hasNext()) st.remove(itr.next());
            }
            else st.remove(str[i]);                 // if found --> remove that element from the set --> add to the set
            st.add(str[i]);
            i++;
        }
        System.out.println(faults);
    }
     
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-->0){
		    int n = Integer.parseInt(br.readLine());
		    String str[] = br.readLine().trim().split("\\s+");
		    int p = Integer.parseInt(br.readLine());
		    solution(str, n, p);
		}
	}
}
*/
