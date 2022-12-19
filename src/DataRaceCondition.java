import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*


This happens when two threads are trying to update a single variable,
but in java all memory operations are atomic in nature :). BUt java doesn't gaurante that for double
and long as those are 64 bit values.

// race condition example and its solution: We can fix race caondition by applying so when a thread
wants to update a value it needs to acquire
and another has that lock then that task cannot be performed

Data race: when multiple thread access a shared variable without synronization and atleast on of them wants to write it
race condition:when multiple thread access a shared variable and the value of that variable dependsn on the order of execution
 */
public class DataRaceCondition {

  public static void main(String[] args) {

    DataRaceCondition dataRaceCondition=new DataRaceCondition();

    dataRaceCondition.run();
  }

  private void run() {

    Map<String,String> map=new ConcurrentHashMap<String,String>();
    Lock lock=new ReentrantLock(); //locking is solution to race condition
    new Thread(()->{
      lock.lock();
      if(map.containsKey("book1")){
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        map.put("book1","read");
      }

      lock.unlock();

    }).start();

    new Thread(()->{

      lock.lock();
      if(map.containsKey("book1")){
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        map.put("book1","un-read");
      }
      lock.unlock();

    }).start();


  }
}
