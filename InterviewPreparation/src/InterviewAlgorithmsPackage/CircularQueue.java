package InterviewAlgorithmsPackage;

public  class CircularQueue implements Queue{

	private int front, back;
	
	private int[] myQueue;
	
	public int[] getMyQueue() {
		return myQueue;
	}

	public static void main(String[] args) throws Exception {
		CircularQueue 	CQueue =new  CircularQueue();
		//dequeu Empty queue
		//CQueue.deQueue();
		CQueue.enQueue(4);
		print(CQueue);
		//CQueue.deQueue();
		print(CQueue);
		CQueue.enQueue(5);
		CQueue.enQueue(6);
		CQueue.enQueue(7);
		CQueue.enQueue(8);
		print(CQueue);
		CQueue.deQueue();
		print(CQueue);
		CQueue.enQueue(9);
		print(CQueue);
		CQueue.deQueue();
		print(CQueue);
		CQueue.enQueue(10);
		print(CQueue);
		CQueue.deQueue();
		print(CQueue);
        CQueue.deQueue();
        print(CQueue);
        CQueue.deQueue();
        print(CQueue);
        CQueue.deQueue();
        print(CQueue);
        CQueue.deQueue();
        print(CQueue);
        CQueue.deQueue();
        print(CQueue);
	}
	
	public static void print(CircularQueue CQueue){
		System.out.println(" Front = " + CQueue.getFront() + "\t Back = " + CQueue.getBack() + "\t" + CQueue.toString());

	}
    
	public int getFront() {
		return front;
	}

	public void setFront(int front) {
		this.front = front;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {
		this.back = back;
	}

	public CircularQueue() {
	  myQueue = new int[5];
	  front = 0;
	  back = -1;
		
	}
	
	public CircularQueue(int size){
		myQueue = new int[size];
		front = 0 ;
		back = -1;
	}
	@Override
	public boolean isFull() {
		
		return ( !isEmpty()) && (( front == 0 && back == myQueue.length-1) || (back + 1 == front));
	}

	@Override
	public boolean isEmpty() {
		return (front == 0 && back == -1);
	}

	@Override
	public void enQueue(int someValue) throws Exception{
		if(! isFull()){
			back = ++back % myQueue.length;
			myQueue[back] = someValue;
			
		}
		else
			throw new Exception("Queue is Full so you can not add an element !");
	}

	@Override
	public void deQueue() throws Exception {
		if(!isEmpty()){
			
			//is the element to be deleted the last one ?			
			if(front == back){				
				front = 0;
				back = -1;
				System.out.print("The last Element is deleted !");
				return;
			}
			front = ++front % myQueue.length;
			return;
		}
		else 
			throw new Exception("Queue is Empty !");
		
	}
	@Override
	public String toString(){
		int length = myQueue.length;
		String currentElementsOnQueue = "\t";
		for (int k = front; k != back; k = ++k% length){			
			currentElementsOnQueue += "\t" + myQueue[k];
		}
		return currentElementsOnQueue + "\t" + myQueue[back];
	}

}
