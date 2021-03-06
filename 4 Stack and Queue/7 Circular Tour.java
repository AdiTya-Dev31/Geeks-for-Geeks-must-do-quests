/**

Suppose there is a circle. There are N petrol pumps on that circle. You will be given two sets of data.
1. The amount of petrol that every petrol pump has.
2. Distance from that petrol pump to the next petrol pump.
Find a starting point where the truck can start to get through the complete circle without exhausting its petrol in between.
Note :  Assume for 1 litre petrol, the truck can go 1 unit of distance.

Input:
The first line of input will be the number of test cases. Then T test cases follow. Each Test case contains 2 lines. The first line will contain an integer N denoting the number of petrol pumps and in the next line are N space separated values petrol and distance denoting the amount of petrol every petrol pump has and the distance to next petrol pump respectively .

Output:
The output of each test case will be the index of the the first point from where a truck will be able to complete the circle otherwise -1 .

Your Task:
Your task is to complete the function tour() which takes the required data as inputs and returns an integer denoting a point from where a truck will be able to complete the circle (The truck will stop at each petrol pump and it has infinite capacity). If there exists multiple such starting points, then the function must return the first one out of those.

Expected Time Complexity: O(N)
Expected Auxiliary Space : O(N)

Constraints:
1 <= T <= 100
1 <= N <= 10000
1 <= petrol, distance <= 1000

Example (To be used only for expected output)
Input:
1
4
4 6 6 5 7 3 4 5
Output:
1

Explanation:
Testcase 1: there are 4 petrol pumps with amount of petrol and distance to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The first point from where truck can make a circular tour is 2nd petrol pump. Output in this case is 1 (index of 2nd petrol pump).


**/



// Explanation  -->   https://www.youtube.com/watch?v=nTKdYm_5-ZY

/* ===================== OPTIMIZED SOLN O(n) ========================== */

/*  ALGORITHM
    
    1) keep track of (petrol in surplus) -> +ve value and (deficient petrol)-> -ve value
    2) loop from 0 -> N , if(-ve petrol) then
            add this -ve to deficient
            reset surplus to 0
            start = i+1
    3) after loop ends if (surplus+deficient)>=0 ==> we can make circular tour else return -1;

*/

class GfG
{
    int tour(int petrol[], int distance[])
    {
    	int N = petrol.length, start = 0, pLeft = 0, pNeed = 0;
    	
    	for(int i=0; i<N; i++){
    	    pLeft += petrol[i]-distance[i];
    	    if(pLeft < 0){      // reset if less petrol
    	        pNeed += pLeft; // storing -ve value to pNeed
    	        pLeft = 0;      // resetting fuel in tank to 0
    	        start = i+1;    // resetting start point
    	    }
    	}
    	return (pLeft+pNeed < 0) ? -1 : start;
    }
}







/* ===================== BRUTE FORCE O(n^2) =========================== */

class GfG
{
    int tour(int petrol[], int distance[])
    {
    	int N = petrol.length, start = 0, end = 0, pLeft = 0;
    	int count = N;
    	
    	while(count > 0){
    	    pLeft += petrol[end]-distance[end];
    	    if(pLeft < 0){      // reset if less petrol
    	        pLeft = 0;
    	        start++;
    	        end = start;
    	        count--;
    	    }
    	    else{  // continue moving if sufficient petrol
    	        end = (end+1)%N; // circularly incrementing end pointer
    	        if(end == start) return start; // i.e, complete tour
    	    }
    	}
    	return -1;
    }
}
