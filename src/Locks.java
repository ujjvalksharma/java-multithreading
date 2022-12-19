import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

  public static void main(String[] args) throws InterruptedException {
    Locks locks=new Locks();
    locks.run();

  }

  private void run() throws InterruptedException {

    //use Syncronized keyword
    useSyncronizedLock();
    useReterrantLock();
  }

  private void useReterrantLock() throws InterruptedException {

    ReentrantLock reentrantLock=new ReentrantLock(true) ; //fairness to get the lock
    boolean isLockPresent= reentrantLock.tryLock(10, TimeUnit.SECONDS);

    while(!isLockPresent) {
      if (!isLockPresent) {
        try {

        } finally {
          reentrantLock.unlock();
        }
      } else {

      }
    }
    reentrantLock.lock(); //lock
    reentrantLock.unlock();//unlock
  }

  private void useSyncronizedLock() {

    synchronized (this){ //lock

    } //unlock

    //Thread.interrupted(); //this is used to stop a current thread

    if(Thread.currentThread().isInterrupted()){ // what to do if current thread is interrupted
throw new InterruptedException();
    }
  }
}
