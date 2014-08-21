package concurrency.basic;

/**
 * User: tracy
 * Time: 2014/8/15 18:15
 *
 *
 *1.Difference between thread and progress in java
 *  1)Thread is subset of process
 *  2)Two process runs on different memory space,but all threads share same memory space.
 *  3)Each thread has its own stack to store local data to that thread
 *
 *
 *2.Difference between Runnable() and Callable()
 *  1)Runnable interface has run() method to define task while Callable interface uses call() method for task definition
 *  2)run() method does not return any value, it's return type is void while call method returns value
 *  3) Another difference on run and call method is that run method can not throw checked exception, while call method can throw checked exception in Java.
 *
 *
 *3.Difference between Thread and Runnable interface in java
 *  1)Java doesn't support multiple inheritance
 *  2)Runnable interface represent a Task which can be executed by either plain Thread or Executors or any other means.
 *    so logical separation of Task as Runnable than Thread is good design decision.
 *  3)Separating task as Runnable means we can reuse the task and also has liberty to execute it from different means.
 *    since you can not restart a Thread once it completes. again Runnable vs Thread for task, Runnable is winner
 *  4)Inheriting all Thread methods are additional overhead just for representing a Task which can can be done easily with Runnable.
 *
 *
 * 4.difference between run() and start()
 *  1)Main difference is that when program calls start() method a new Thread is created and code inside run() method
 *    is executed in new Thread while if you call run() method directly no new Thread is created and code inside run()
 *    will execute on current Thread.
 *  2)Another difference between start vs run in Java thread is that you can not call start() method twice on thread
 *    object. once started, second call of start() will throw IllegalStateException in Java while you can call run()
 *    method twice

 *  Demo:@RunAndStart.java
 *
 * 5.Difference between CyclicBarrier and CountDownLatch in java
 *  1)CyclicBarrier可以重复使用，CountDownLatch只能使用一次
 *
 *  Demo: @components.CountDownLatchDemo.java,components.CyclicBarrierDemo.java
 *
 *
 * 6.Java Memory Model
 *   假设一个线程为变量 aVariable 赋值: aVariable = 3;内存模型需要解决这个问题：在什么条件下,读取aVariable的线程将看到这个值为3
 *
 *   产生问题的原因:
 *    1)在编译器中生成的指令顺序，可以喝源代码中的顺序不同
 *    2)编译器会把变量保存在寄存器而不是内存中
 *    3)处理器可以采用乱序或者并行等方式来执行指令
 *    4)缓存可能会改变将写入变量提交到主内存的次序
 *    5)保存在处理器本地混存中的值，对于其他处理器是不可见的
 *
 *   Java语言规范要求JVM在线程中维护一种类似串行的语义：只要程序的最终结果与在严格串行环境中执行的结果相同，那么上述所有操作都是允许的。
 *
 *   如果一个线程的各个操作之间不存在数据流依赖行，那么这些操作是可以乱序执行的;即使这些操作顺序执行，但在将缓存刷新到主内存的不同时序中
 *   也可能出现这种情况。
 *
 *   Demo: @PossibleReordering.java
 *
 *   Java内存模型是通过各种操作来定义的，包括：
 *    1)reads and writes to variables
 *    2)lock and unlock monitors
 *    3)starting and joining with threads
 *
 *   JMM为程序中所有操作定义了一个偏序关系(Happens-Before),如果想要保证执行操作B的线程看到操作A的结果(无论A与B是否在同一个线程中),那么
 *   必须保证A与B之间满足Happens-Before关系。如果两个操作之间缺乏Happens-Before关系,那么JVM可以对他们任意地进行重排序。
 *
 *
 *   Happens-Before规则包括：
 *    1)程序顺序规则。Each action in a thread happens-before every action in that thread that comes later in the program order
 *      ps:It should be noted that the presence of a happens-before relationship between two actions does not necessarily imply that
 *      they have to take place in that order in an implementation. If the reordering produces results consistent with a legal execution,
 *      it is not illegal.
 *    2)监视器锁规则。An unlock on a monitor lock happens-before every subsequent lock on that same monitor lock
 *    3)volatile变量规则。A write to a volatile field happens-before every subsequent read of that same field
 *    4)线程启动规则。A call to Thread.start on a thread happens-before every action in the started thread
 *    5)线程结束规则。Any action in a thread happens-before any other thread detects that thread has terminated, either by successfully return from Thread.join or by Thread.isAlive returning false
 *    6)中断规则。 A thread calling interrupt on another thread happens-before the interrupted thread detects the interrupt (either by having InterruptedException Thrown, or invoking isInterrupted or interrupted).
 *    7)终结器规则。The end of a constructor for an object happens-before the start of the finalizer for that object
 *    8)传递性。If A happens-before B, and B happens-before C, then A happens-before C.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
