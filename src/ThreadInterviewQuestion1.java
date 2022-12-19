import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadInterviewQuestion1 {

  public static void main(String[] args) {
    //How to timeout a thread

    ExecutorService ExecutorService= Executors.newFixedThreadPool(10);

    ExecutorService.submit(()->{
      System.out.println("task is being executed");
    });

   // ExecutorService.shutdown(); // takes no new task and finish previous executed task
   // ExecutorService.shutdownNow(); // takes no new task and returns previous executed task and attemps,
    // but does guarantees to stop tasks

    //One way to stop a thread

    Callable task= ()->{
      return 1+2;
    };
    Runnable task1= ()->{
       int a= 1+2;
    };
    Future<Integer> futureValue= ExecutorService.submit(task);

    try {
      futureValue.get(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      futureValue.cancel(true); // have condition volatile/atomic variable which helps in getting a thread out of loop
    }

  }
}
