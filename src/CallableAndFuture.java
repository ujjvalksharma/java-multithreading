/*


In runnable we can't return a value, but in callable we can return a value,
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableAndFuture {

  public static void main(String args[]){
    CallableAndFuture callableAndFuture=new CallableAndFuture();
    callableAndFuture.run();
  }

  private void run() throws ExecutionException, InterruptedException {

    ExecutorService executorService=Executors.newFixedThreadPool(10);
    Future<Integer> futureVaslue= executorService.submit(()->{

      Thread.sleep(50000);
      return 10;
    }); //works same as javascript promise

    //Now lets say we perform some operation while is promise is wokring
    System.out.println("operation1");
    System.out.println("operation2");
    System.out.println("operation3");
    Integer value=futureVaslue.get(10, TimeUnit.SECONDS); //blocking operation as it is waiting for promise to complete,
    // so operation4,5,6 cannot be executed. Wait for 10 seconds or throw a timeout exeception
    System.out.println("operation4");
    System.out.println("operation5");
    System.out.println("operation6");


  }
}
