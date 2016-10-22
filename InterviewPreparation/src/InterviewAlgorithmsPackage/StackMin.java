package InterviewAlgorithmsPackage;

import java.util.LinkedList;
import java.util.*;

public class StackMin implements Stack {
	
	private int[] myStack ;
	private int top ;
	private LinkedList<Integer> minStackList = new LinkedList();
	
	public int[] getMyStack() {
		return myStack;
	}
	
	public LinkedList<Integer> getMinStackList() {
		return minStackList;
	}
	public StackMin() {
		this.myStack = new int[5];
		this.top = -1;
		minStackList.add(Integer.MAX_VALUE);
	}
	
	public StackMin(int size){
		this.myStack  = new int[size];
		this.top = -1;
		minStackList.add(Integer.MAX_VALUE);
	}

	@Override
	public void push(int value) throws Exception {
		if(isFull())
			throw new Exception("Stack is Full");
		
		if (isEmpty()){
		    minStackList.removeFirst();
		    minStackList.addFirst( value );
	    }
		else if(value <= minStackList.get(0))
			minStackList.addFirst( value );
		this.myStack[++top] = value;
		 
	}

	@Override
	public int pop()throws Exception  {
		if(isEmpty()){
			throw new Exception("Stack is Empty!");
		}
		int temp = myStack[top];
		if(myStack[top] == minStackList.getFirst() )
			minStackList.removeFirst();
		 top--;
		 
		 if(minStackList.isEmpty())
			minStackList.add(Integer.MAX_VALUE);
		 
		return temp ;
	}

	@Override
	public boolean isEmpty() {
		
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == myStack.length-1;
	}

	public static void main(String[] args) throws Exception {
		int[][] myStackData = {
				{1,2,3,4,5},
				{5,4,3,2,1},
				{5,6,7,8,1},
				{5,4,3,2,7},
				{2,2,2,2,2}
		};
		
		StackMin stkMin = new StackMin();
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		//decreasing stack
		stkMin.addToStack(myStackData[0]);
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		//increasing stack
		stkMin = new StackMin();
		stkMin.addToStack(myStackData[1]);
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		//decreasing and then increasing stack
		stkMin = new StackMin();
		stkMin.addToStack(myStackData[2]);
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		//increasing and then decreasing
		stkMin = new StackMin();
		stkMin.addToStack(myStackData[3]);
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		
		stkMin.print(stkMin.getMyStack());
		stkMin = new StackMin();
		stkMin.addToStack(myStackData[4]);
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		stkMin.print(stkMin.getMyStack());
		
		
		
		stkMin.pop();
		stkMin.pop();
		stkMin.print(stkMin.getMyStack(), stkMin.getMinStackList());
		stkMin.print(stkMin.getMyStack());
	}
	private void addToStack(int[] arr) throws Exception{
		for(int k=0; k < arr.length; k++ )
		 push(arr[k]);
	}
	private void print(int[] arr) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append("Elements in Stack is : \t");
		
		for(int k=0; k <= top; k++ )
		{
			
			sb.append(arr[k ] + "\t");			
			
		}
		System.out.println(sb.toString());
	}
     public void print(int[] mystack, LinkedList<Integer> list){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Elements in Stack is : \t");
		for(int a : mystack)
			sb.append(a + "\t");
		sb.append("And MinList is: \t");
		for(Integer a : list)
			sb.append(a + "\t");
		System.out.println(sb.toString());
	}
	public int getMinStackValue() {
		return minStackList.get(0);
	}

}
