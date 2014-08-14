package concurrency.sharing;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-31
 * Time: 下午11:33
 * To change this template use File | Settings | File Templates.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static synchronized int serialNumber(){
        return serialNumber++;
    }
}
