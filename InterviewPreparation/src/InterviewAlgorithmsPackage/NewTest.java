package InterviewAlgorithmsPackage;

import org.testng.annotations.Test;
import org.testng.log4testng.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest {
	
  private CircularQueue myQueue ;
  private  static final Logger LOGGER =   Logger.getLogger(NewTest.class);
  
  

@DataProvider(name="Jos")
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1},
      new Object[] { 2},
    };
  }
  @BeforeClass
  public void beforeClass() {
	   myQueue = new CircularQueue();
	  //logger = new Logger(null, 0);
  }

  @AfterClass
  public void afterClass() {
  }
  @Test (dataProvider ="Jos" )
  public void myTest(int value){
	  LOGGER.info("Starting Mytest Methode");
	  try {
		myQueue.deQueue(); 
		
		myQueue.enQueue(value);
		int[] arr = myQueue.getMyQueue();
		int val = arr[myQueue.getBack()] ;
		Assert.assertEquals(val,value );
		myQueue.deQueue();
		System.out.println("***" + arr[myQueue.getBack()]);
		Assert.assertEquals(arr[myQueue.getFront()], value);
	} catch (Exception e) {
		
		LOGGER.info("You Can not deQueue an empty Queue !");
		//Assert.
	}
	  
  }

}
