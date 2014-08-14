package concurrency.sharing;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-31
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class Evengenerator extends IntGenerator {
    private int currentEvenValue = 0;
    public synchronized int next(){
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new Evengenerator());
    }
}
