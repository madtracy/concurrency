package concurrency.temp;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-29
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Waiting for LiftOff");
    }
}
