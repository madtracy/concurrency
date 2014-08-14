package concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-2-2
 * Time: 下午11:55
 * To change this template use File | Settings | File Templates.
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int pronderFactor;
    private Random rand = new Random(47);
    private void pause() throws InterruptedException{
        if(pronderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(pronderFactor * 250));
    }
    public Philosopher(Chopstick left, Chopstick right, int ident, int ponder){
        this.left = left;
        this.right = right;
        id = ident;
        pronderFactor = ponder;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println(this + " " + "thinking");
                //pause();
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println(this + " " + "grabbing right");
                right.take();
                System.out.println(this + " " + "grabbing left");
                left.take();
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e){
            System.out.println(this + " " + "exiting via interrupt");
        }
    }
    public String toString(){ return "Philosopher " + id; }
}
