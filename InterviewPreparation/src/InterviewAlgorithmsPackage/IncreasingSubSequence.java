package InterviewAlgorithmsPackage;

import java.util.*;

public class IncreasingSubSequence {
 
	private int[] input;
	
	public int countOfSubSequence(int start, int end){
		
		if(start == end )
			return 1;
		
		int subCount = countOfSubSequence(start, end-1) + 1;
		
		for(int k=end ; k >= start  ; k--){
			if(input[end] > input[k])
				subCount += 1;
		}		
			
		return subCount ;
	}
	
	public IncreasingSubSequence(int[] myArray){
		this.input = myArray;
	}
	
	public LinkedList<LinkedList<Integer>> generateIncreasingSubsequence(int start, int end){
		if(input == null && input.length == 0)
			return null;
		LinkedList<Integer>  thisList = new LinkedList<>();
		
		//single element is the base case for the reccurison
		
		if(start == end){
			thisList.add(input[start]);
			LinkedList<LinkedList<Integer>> myList = new LinkedList<LinkedList<Integer>>();
			myList.add(thisList);
			return myList;
		}
		
		//g
		LinkedList<LinkedList<Integer>> ll = generateIncreasingSubsequence(start, end-1);
		
		LinkedList<LinkedList<Integer>>  newLl = new LinkedList<LinkedList<Integer>>();
		
		for(LinkedList<Integer> sequence : ll){
			
			if(sequence.getLast() < input[end]){
				thisList = new LinkedList<>();
				thisList.addAll(sequence);
				thisList.add(input[end]);
				newLl.add(thisList);
			}
			
		}
		LinkedList<Integer> endList = new LinkedList<>();
		endList.add(input[end]);
		ll.add(endList);
		ll.addAll(newLl);
		
		return ll;
	
	}
	
	public void print(LinkedList<LinkedList<Integer>> theList){
		for(LinkedList<Integer> a : theList){
			
			for(Integer ll : a){
				
				System.out.print(ll+ "\t");
			}
			System.out.println("");
		}
		
	}
	
	public static void main(String[] args) {
//		int[] myArray = {1};
//		int[] myArray = {1,0} ;
		int[] myArray = {2,0,6,7} ;
//		int[] myArray = {1,2,0,5} ;
//		int[] myArray = {1,2,0,5,6} ;
		IncreasingSubSequence isq = new IncreasingSubSequence(myArray);
		LinkedList<LinkedList<Integer>> listOfSubSeq = isq.generateIncreasingSubsequence(0, myArray.length - 1);
		isq.print(listOfSubSeq);
		
		System.out.println(" Count is: " + isq.countOfSubSequence(0, myArray.length- 1));

	}

}
