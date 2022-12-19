import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*


Fetch data from multiple api and then collect everthing within 5 seconds
 */
public class ScatterGather {

  public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {

    ScatterGather scatterGather=new ScatterGather();
    scatterGather.run();
  }

  private void run() throws InterruptedException, TimeoutException, ExecutionException {

    //method1
    List<String> result=new ArrayList<String>();

    new Thread(()->{
      result.add("data1"); //api1
    }).start();
    new Thread(()->{
      result.add("data2"); //api2
    }).start();
    new Thread(()->{
      result.add("data3"); //api1
    }).start();
    new Thread(()->{
      result.add("data4"); //api2
    }).start();

    Thread.sleep(5000);

    System.out.println(result);

    result.clear();

    //method2

    ExecutorService executorService= Executors.newCachedThreadPool();
    CountDownLatch countDownLatch=new CountDownLatch(4); //initize for 4 task
    List<Future<String>> futureValues=new ArrayList<Future<String>>();
    futureValues.add( executorService.submit(()->{
      countDownLatch.countDown(); //tells that 1 task is being performed
      return "data1";
    }));

    futureValues.add( executorService.submit(()->{
      countDownLatch.countDown(); //tells that 1 task is being performed
      return "data1";
    }));

    futureValues.add( executorService.submit(()->{
      countDownLatch.countDown(); //tells that 1 task is being performed
      return "data1";
    }));

    futureValues.add( executorService.submit(()->{
      countDownLatch.countDown(); //tells that 1 task is being performed
      return "data1";
    }));

    countDownLatch.await(5, TimeUnit.SECONDS);

    System.out.println("futureValues: "+futureValues);

    //method 3

    Set<String> result1= Collections.synchronizedSet(new HashSet<String>());

    CompletableFuture<Void> task1= CompletableFuture.runAsync(()->{
      result1.add("data1");
    });

    CompletableFuture<Void> task2= CompletableFuture.runAsync(()->{
      result1.add("data2");
    });

    CompletableFuture<Void> task3= CompletableFuture.runAsync(()->{
      result1.add("data3");
    });

    CompletableFuture<Void> task4= CompletableFuture.runAsync(()->{
      result1.add("data4");
    });

    CompletableFuture<Void> allTask= CompletableFuture.allOf(task1, task2, task3, task4);

    allTask.get(5,TimeUnit.SECONDS);




  }
}
