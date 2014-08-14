package concurrency.temp;

import javax.net.ssl.SSLParameters;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-31
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionThread implements Runnable {
    public void run(){
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try{

            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        }catch (Exception e){
            System.out.println("Exception catched!");
        }
    }
}
