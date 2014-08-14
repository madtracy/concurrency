package concurrency.temp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-29
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
