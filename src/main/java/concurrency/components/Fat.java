package concurrency.components;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-2-5
 * Time: 上午12:01
 * To change this template use File | Settings | File Templates.
 */
public class Fat {
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;
    public Fat(){
        for(int i = 1; i < 10000; i++){
            d += (Math.PI + Math.E) / (double)i;
        }
    }
    public void operation(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}
