package concurrency.temp;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-29
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */

class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }
    public String call(){
        return "result of TaskWithResult "+id;
    }
}


public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for(int i=0;i<10;i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for(Future<String> fs : results)
            try{
                System.out.println(fs.get());
            }catch (Exception e){
                System.out.println(e);
            }finally {
                exec.shutdown();
            }
    }



}
