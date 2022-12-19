import java.util.concurrent.CompletableFuture;

/*
Threads can be expensive for storage,cpu,cores. PLus some I/O can be blocking and put callable
and future in block state, so we should prefer asyncronous code. In this we call asyn callback chaining similar to Javascript
 */
public class AsyncronousCode {

  public static void main(String args[]){

    for(int i=0;i<10;i++){

      CompletableFuture.supplyAsync(()->i*2)
              .thenApply((num)->num+2)
              .thenApply((num)->num+2)
              .thenAcceptAsync(integer -> {
                integer=integer+2;
              });

    }
  }
}
