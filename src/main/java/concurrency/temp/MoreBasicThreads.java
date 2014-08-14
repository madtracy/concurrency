package concurrency.temp;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-29
 * Time: 下午6:36
 * To change this template use File | Settings | File Templates.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for(int i=0; i<5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }
}
