package concurrency.terminate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-8-21
 * Time: 下午10:49
 * To change this template use File | Settings | File Templates.
 */
public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = new ArrayList<BigInteger>();

    private volatile boolean cancelled;

    @Override
    public void run(){
      BigInteger p = BigInteger.ONE;
      while(!cancelled){
          p = p.nextProbablePrime();
          synchronized (this){
              primes.add(p);
          }
      }
    }

    public void cancle(){cancelled = true;}

    public synchronized List<BigInteger> get(){
        return new ArrayList<BigInteger>(primes);
    }
}
