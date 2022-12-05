package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.class)

public class ListnerSample {
	
@Test
public void test1 ()
{
	System.out.println("Test1");
}
@Test
public void test2 ()
{
	System.out.println("Test2");
}
@Test(timeOut = 2000)// for failing test
public void test3 () throws InterruptedException
{
	Thread.sleep(3000);
	System.out.println("Test3");
}
@Test(dependsOnMethods = {"test3"})
public void test4 ()//test4 method skipped b'coz test3 has failed & test4 depends on test3 
{
	System.out.println("Test4");
}
	

}
