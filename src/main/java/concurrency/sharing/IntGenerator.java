package concurrency.sharing;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-31
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){canceled = true;}
    public boolean isCanceled(){return canceled;}
}
