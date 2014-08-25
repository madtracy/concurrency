package concurrency.cooperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * User: tracy
 * Time: 2014/8/25 12:03
 */
public class InterThreadCommunicationExample {
    public static void main(String[] args) {
        final Queue sharedQ = new LinkedList();

        Thread producer = new Producer(sharedQ);
        Thread consumer = new Consumer(sharedQ);
        producer.start();
        consumer.start();

    }
}


class Producer extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final Queue sharedQ;

    public Producer(Queue sharedQ){
        super("Producer");
        this.sharedQ = sharedQ;
    }

    @Override
    public void run(){
        for(int i=0;i<4;i++){
            synchronized (sharedQ){
                while (sharedQ.size()>=1){
                    try{
                        logger.debug("Queue is full,waiting");
                        sharedQ.wait();
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                logger.debug("producing : "+i);
                sharedQ.add(i);
                sharedQ.notify();
            }
        }
    }

}


class Consumer extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Queue<Integer> shareQ;

    public Consumer(Queue shareQ){
        super("Consumer");
        this.shareQ = shareQ;
    }

    @Override
    public void run(){
        while(true){
            synchronized (shareQ){
                while (shareQ.size()==0){
                    try{
                        logger.debug("Queue is empty, waiting");
                        shareQ.wait();
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                int number = shareQ.poll();
                logger.debug("consuming : " + number);
                shareQ.notify();
                if(number==3){break;}
            }
        }
    }
}