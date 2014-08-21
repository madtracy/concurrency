package concurrency.basic;

/**
 * User: tracy
 * Time: 2014/8/21 16:22
 */
public class PossibleReordering {
    static int x=0,y=0;
    static int a=0,b=0;

    public static void main(String[] args) throws InterruptedException{
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a=1;
                x=b;//a=1与x=b没有数据流依赖，可能先执行x=b再执行a=1
            }
        });

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                b=1;
                y=a;
            }
        });
        one.start();other.start();
        one.join();other.join();
        System.out.println("("+x+","+y+")");
    }
}

/*
  (0,0),(0,1),(1,0),(1,1)都有可能
 */