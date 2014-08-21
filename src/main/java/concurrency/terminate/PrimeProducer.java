package concurrency.terminate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-8-21
 * Time: 下午11:59
 * To change this template use File | Settings | File Templates.
 */
public class PrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled;

    PrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            BigInteger p = BigInteger.ONE;
            while(!Thread.currentThread().isInterrupted()){
                queue.put(p = p.nextProbablePrime());//阻塞库方法都会检测线程何时中断，并且在发现中断时提前返回
            }
        }catch (InterruptedException e){
            /* 允许线程退出 */
        }
    }

    public void cancle(){cancelled = true;}

}
