package concurrency.sharing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-31
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator g,int ident){
        generator = g;
        id = ident;
    }
    public void run(){
        while(!generator.isCanceled()){
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val + " not even!");
                generator.cancel();
            }
        }
    }
    public static void test(IntGenerator gp, int count){
        System.out.println("Press Control-c to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++)
            exec.execute(new EvenChecker(gp,i));
        exec.shutdown();
    }

    public static void test(IntGenerator gp){
        test(gp,10);
    }
}
