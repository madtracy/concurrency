package concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-2-3
 * Time: 上午12:46
 * To change this template use File | Settings | File Templates.
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws Exception{
        int ponder = 5;
        int size = 3;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for(int i = 0; i < size; i++)
            sticks[i] = new Chopstick();
        for(int i = 0; i < size; i++){
            if(i < (size-1))
                exec.execute(new Philosopher(sticks[i], sticks[i+1], i, ponder));//the first n-1 nones pick right first;
            else
                exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));//the last one pick left first;
        }

        System.out.println("Press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();
    }
}
