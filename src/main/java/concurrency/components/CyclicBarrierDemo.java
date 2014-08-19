package concurrency.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * User: tracy
 * Date: 14-2-4
 *
 * 1.CyclicBarrier执行顺序:
 *  1)n个线程call await();
 *  2)CyclicBarrier的Runnable任务执行;
 *  3)n个线程的await()之后的部分执行.
 *
 * 2.When to use
 *  1) To implement multi player game which can not begin until all player has joined.
 *  2) Perform lengthy calculation by breaking it into smaller individual tasks, In general to implement
 *     Map reduce technique
 *
 * 3.important points
 *  1)CyclicBarrier can perform a completion task once all thread reaches to barrier, This can
 *    be provided while creating CyclicBarrier.
 *  2)If CyclicBarrier is initialized with 3 parties means 3 thread needs to call await method to break the barrier.
 *  3)Thread will block on await() until all parties reaches to barrier, another thread interrupt or await timed out.
 *  4)If another thread interrupt the thread which is waiting on barrier it will throw BrokenBarrierException.
 *  5)CyclicBarrier.reset() put Barrier on its initial state, other thread which is waiting or not yet reached barrier
 *    will terminate with java.util.concurrent.BrokenBarrierException.
 *
 *
 * 4.Refer
 * http://javarevisited.blogspot.com/2012/07/cyclicbarrier-example-java-5-concurrency-tutorial.html
 */
class Horse implements Runnable{
    private static  int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;
    public Horse(CyclicBarrier b){ barrier = b; }
    public synchronized int getStrides(){ return strides; }
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    strides += rand.nextInt(3);
                }
                barrier.await();
                System.out.println("test");
            }
        }catch (InterruptedException e){
            System.out.println("interrupted exception");
        }catch (BrokenBarrierException e){
            throw new RuntimeException(e);
        }
    }
    public String toString(){ return "Horse " + id + " "; }
    public String tracks(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < getStrides(); i++)
            s.append("*");
        s.append(id);
        return s.toString();
    }
}
public class CyclicBarrierDemo {
    static final int  FINISH_LINE = 20;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public CyclicBarrierDemo(int nHouses, final int pause){
        barrier = new CyclicBarrier(nHouses,new Runnable() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                for(int i = 0; i < FINISH_LINE; i++)
                    s.append("=");
                System.out.println(s);
                for(Horse horse : horses)
                    System.out.println(horse.tracks());
                for(Horse horse : horses)
                    if(horse.getStrides() >= FINISH_LINE){
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                try{
                    TimeUnit.MILLISECONDS.sleep(pause);
                }catch (InterruptedException e){
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });
        for(int i = 0; i < nHouses; i++){
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        new CyclicBarrierDemo(nHorses,pause);
    }
}
