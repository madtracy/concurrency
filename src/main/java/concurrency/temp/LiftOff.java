package concurrency.temp;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-29
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public class LiftOff implements Runnable{
    protected int countDown = 10;//Default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){

    }
    public LiftOff(int countDown){
        this.countDown = countDown;
    }
    public String status(){
        return "#"+id+"("+(countDown>0?countDown:"Liftoff!")+"),";
    }
    public void run(){
        while(countDown-- > 0){
            System.out.print(status());
            Thread.yield();
        }
    }
}
