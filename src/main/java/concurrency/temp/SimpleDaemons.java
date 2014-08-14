package concurrency.temp;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-29
 * Time: 下午11:34
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDaemons implements Runnable {
    public void run(){
        try{
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }catch (Exception e){
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception{
        for(int i=0;i<10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(160);
    }
}
