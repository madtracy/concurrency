package concurrency.deadlock;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-2-2
 * Time: 下午11:52
 * To change this template use File | Settings | File Templates.
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void take() throws InterruptedException{
        while(taken)
            wait();
        taken = true;
    }
    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
