package concurrency.terminate;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * User: tracy
 * Date: 14-8-21
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled){//如果queue满了，永远不会检测cancelled
                queue.put(p = p.nextProbablePrime());
            }
        }catch (InterruptedException consumed){

        }
    }

    public void cancel(){cancelled = true;}
}
