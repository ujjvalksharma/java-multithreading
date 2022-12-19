import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*


Thread1 gets lockA and sleep for 3 seconds meanwhile thread2 gets lockB then sleeps for 5 seconds
in that time thread1 is awake and tries to acquire B, but fails as thread2 has that lock.
Now thread2 is awake and wants lock A, but it is acquired by thread1 hence 2 threads in deadlock state

How do we detech deadlock at runtime? We can use thread dumps or similar tool for instance Jstack
 */
public class DeadLockExample {

  public static void main(String[] args) {
    DeadLockExample deadLockExample=new DeadLockExample();

    deadLockExample.run();

  }

  private void run() {

    Lock lockA=new ReentrantLock();
    Lock lockB=new ReentrantLock();
    new Thread(()->{

     // lockA.lock(); // to avoid deadlock use
      try {
        boolean LockAacquired= lockA.tryLock(3,TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        // if unable to acqauire lock do something
      }
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      lockB.lock();
      lockA.unlock();
      lockB.unlock();
    }).start();

    new Thread(()->{

      lockB.lock();
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      lockA.lock();
      lockB.unlock();
      lockA.unlock();
    }).start();

  }

  //method1 to detch deadlock
  public void detechDeadLock(){
    ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
    long[] threadId=threadMXBean.findDeadlockedThreads();
    if(threadId!=null&&threadId.length>0){
      System.out.println("Deadlock found");
    }

  }
}
