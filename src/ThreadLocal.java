import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*


If all threads want to use a single object, we have to use locking to syncronize it,
but that slows down the performance, so we create local object of that object for each thread and
this can be achived using thread local
 */
public class ThreadLocal {

  public static void main(String args[]){
    ThreadLocal threadLocal=new ThreadLocal();
    threadLocal.run();
  }

  private void run() {

    // thread pool of 10 threads
    ExecutorService executorService = Executors.newFixedThreadPool(10);
     ThreadLocal threadLocal =
            new ThreadLocal();
    for(int i=0;i<100;i++){

      int finalI = i;
      executorService.submit(()->{
        System.out.println("I am printing : "+ finalI);
      });

    }


  }
}
