package InterviewAlgorithmsPackage;

public interface Queue {
	
	boolean isFull();
	boolean isEmpty();
	void enQueue(int someValue)throws Exception;
	void deQueue() throws Exception;

}
