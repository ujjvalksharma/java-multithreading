import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*

1) Fixed threadPool
2) Cached Threadpool : no fixed number of thread, a thread is created on go if no thread is available to handle a task
3) sechulded threadpool
4)

 */
public class TypesOfThreadPool {

  public static void main(String args[]){

    //fixed threadpool
    ExecutorService executorService1= Executors.newFixedThreadPool(10);
    executorService1.submit(()->{
      System.out.println("Runing task from fixed threadpool");
    });
    ExecutorService executorService2= Executors.newCachedThreadPool();
    executorService2.submit(()->{
      System.out.println("Runing task from fixed threadpool");
    });
    ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(10);
    scheduledExecutorService.schedule(()->{
      System.out.println("Run after 10 second delay");
    },10, TimeUnit.SECONDS);
    scheduledExecutorService.scheduleAtFixedRate(()->{ //does not wait a task to complete and would start another task after 15 seconds of interval
      System.out.println("sechule at each 10 seconds");
    },10,15,TimeUnit.SECONDS);

    scheduledExecutorService.scheduleWithFixedDelay(()->{ //does wait a task to complete and would start another task after 15 seconds of interval
      System.out.println("sechule at each 10 seconds");
    },10,15,TimeUnit.SECONDS);
  }
}
