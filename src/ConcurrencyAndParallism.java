import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrencyAndParallism {


  public static void main(String args[]){
    ConcurrencyAndParallism concurrencyAndParallism=new ConcurrencyAndParallism();
    concurrencyAndParallism.run();

  }

  private void run() {

    Semaphore semaphoreLocal=new Semaphore(1);
    new Thread(()->{
      System.out.println("parallel task1");
    }).start();

    new Thread(()->{
      System.out.println("parallel task2");
    }).start();

    new Thread(()->{
      System.out.println("parallel task3");
    }).start();

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for(int task=4;task<10;task++){
      int finalTask = task;
      executorService.submit(()->{
        System.out.println("parallel task"+ finalTask);
      });
    }

    for(int task=1;task<100;task++){
      int finalTask = task;
      executorService.submit(()->{
        try {
          semaphoreLocal.acquire();
        } catch (InterruptedException e) {
          System.out.println("Got an execption while acquiring lock");
        }
        System.out.println("concurrent task"+ finalTask);
        semaphoreLocal.release();
      });
    }


  }
}
