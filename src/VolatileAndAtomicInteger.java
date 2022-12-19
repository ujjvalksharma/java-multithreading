/*

//https://stackoverflow.com/questions/30956461/why-is-this-code-working-without-volatile
Each thread have local cache and shared cache, so sometimes when a variable
 is updated it is updated in local cache and its changes are not
 visible to another thread. This is called visibility problem and to solve it. We use volatile keyword.

 Votatile keyword flushes changes from local cache to shared cache and then pushes it again to local cache of other thread


Why are two thread able to read num value?


The volatile keyword guarantees that changes are visible amongst multiple threads,
but you're interpreting that to mean that opposite is also true; that the absence of
the volatile keyword guarantees isolation between threads, and there's no such guarantee.

Also, while your code example is multi-threaded, it isn't necessarily concurrent.
 It could be that the values were cached per-thread, but there was enough time for the JVM to propagate
  the change before you printed the result.

 You are right that with volatile, you can ensure/guarantee that your 2
 threads will see the appropriate value from main memory at all times, and never a thread-specific cached version of it.

Without volatile, you lose that guarantee. And each thread is working with its own cached version of the value.

However, there is nothing preventing the 2 threads from resynchronizing
 their memory if and when they feel like it, and eventually viewing the same value (maybe).
 It's just that you can't guarantee that it will happen, and you most certainly cannot guarantee
 when it will happen. But it can happen at some indeterminate point in time.

The point is that your code may work sometimes, and sometimes not. But even if every
time you run it on your personal computer, is seems like it's reading the variable properly,
it's very likely that this same code will break on a different machine. So you are taking big risks.
 */

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileAndAtomicInteger {

  volatile int  num; // we can also use syncronize keyword
  AtomicInteger atomicInteger=new AtomicInteger();

  class CustomThread1 extends Thread{

    @Override
    public void run() {

      while(num==0) {
        System.out.println("Thread1 is running");
      }
    }
  }

  class CustomThread2 extends Thread{

    @Override
    public void run() {
      num=10;
      atomicInteger.set(100);


    }
  }

  public static void main(String args[]){

    VolatileAndAtomicInteger volatileAndAtomicInteger=new VolatileAndAtomicInteger();
    volatileAndAtomicInteger.run();
   //

  }

  private void run() {
    this.num=0;
    CustomThread1 customThread1=new CustomThread1();
    customThread1.start();

    CustomThread2 customThread2=new CustomThread2();
    customThread2.start();
  }

}
