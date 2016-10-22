import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

class Solution {
	
	
	private static Queue<Integer> q = new Queue<Integer>();
	private static Set<Integer>  set = new HashSet<>();
	
	public static void main(String[] args){
		
	
		int[] w = {125,200,175,300 ,325};
		populateQueue(w);
		int[] f = { 1,3,1,2,5 };
		int counts = solution(w ,f , 4, 400 );
		System.out.println(" " + counts);
	}
	
	public static int solution(int[] weight, int[] floors, int X, int Y) {
    	
    	int numberOfPeopleOnElevator = 0;
        int currentWeightOnElevator = 0 ;
        int countOfStops =0 ;
    	
    	int k = 0; 
    	
    		for( ; k < weight.length ; k++)
    		{
    		  if(currentWeightOnElevator + weight[k] >  Y ||  numberOfPeopleOnElevator > X)
    		  {   
    			  countOfStops = countOfStops + set.size() + 1 ;
    			  numberOfPeopleOnElevator = 0;
    		      currentWeightOnElevator = 0 ;
    		      set.clear();	
    		      k--;
    			  
    		  }
    		  else {
    			  currentWeightOnElevator = currentWeightOnElevator + weight[k];
    			  numberOfPeopleOnElevator++;
    			  set.add(floors[k]);
    			 // q.deQueue();
    		  }
    	     }
    		 countOfStops = countOfStops + set.size() + 1 ;
    	
		return countOfStops;
      
    }
    
    private static void populateQueue(int[] A){
    	for(int k=0; k < A.length ; k ++){
    		q.enQueue(A[k]);
    	}   	
    }  
   
}

class Queue<Integer> {
    private  LinkedList<Integer> myQueue ;
     
    public Queue(){
        myQueue = new LinkedList<Integer>();
    }
    
    public boolean isEmpty(){
       return myQueue.size() == 0;
   }
   
   public void enQueue(Integer value){
        myQueue.addLast(value);
       
   }
   
   public Integer deQueue(){
 	  return myQueue.removeFirst();
       
   }
   
 }