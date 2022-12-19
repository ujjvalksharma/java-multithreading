import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;

public class ProducerConsumer {

  Queue<Integer> queue=new LinkedList<Integer>();
  ReentrantLock reentrantLock = new ReentrantLock(true);
  Condition isQueueEmpty=reentrantLock.newCondition();;
  Condition isQueueFull = reentrantLock.newCondition();
  private int consume() throws InterruptedException {
    reentrantLock.lock();

    if(queue.size()==0){
      isQueueEmpty.wait();
    }
    queue.poll();
    isQueueFull.notifyAll();
    reentrantLock.unlock();
  }

  private void produce(int i) throws InterruptedException {
    reentrantLock.lock();

if(queue.size()==10){
  isQueueFull.wait();
}
    queue.add(i);
    isQueueEmpty.notifyAll();
    reentrantLock.unlock();
  }

  public static void main(String[] args) {

    ProducerConsumer producerConsumer =new ProducerConsumer();
  //  reentrantLock=
    producerConsumer.produce(10);
    int num= producerConsumer.consume();

  }


}
