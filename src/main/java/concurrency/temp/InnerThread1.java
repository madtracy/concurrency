package concurrency.temp;

/**
 * Created with IntelliJ IDEA.
 * User: tracy
 * Date: 14-1-31
 * Time: 下午1:08
 * To change this template use File | Settings | File Templates.
 */
public class InnerThread1 {
    private int countDown = 5;
    private Inner inner;
    private class Inner extends Thread {
        Inner(String name){
            super(name);
            start();
        }
        public void run(){
            try{
                while(true){
                    System.out.println(this);
                    if(--countDown == 0) return;
                    sleep(10);
                }
            }catch (Exception e){
                System.out.println("interrupted");
            }
        }
        public String toString(){
            return getName()+": "+countDown;
        }
    }
    public InnerThread1(String name){
        inner = new Inner(name);
    }
}
