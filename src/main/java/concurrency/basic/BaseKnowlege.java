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
 * 7.How to stop thread in java
 *  1)after run() or call() method finished
 *  2)use volatile flag
 *    Demo: terminate.PrimeGenerator.java
 *    存在的问题:
 *      需要等待一次循环执行完再检测标志位,如果循环内代码执行时间很长，那么可能需要很久才能检测一次cancelled标志;更有可能永远都不会检测cancelled标志
 *      Demo:terminate.BrokenPrimeProducer.java
 *  3)use interrupt
 *     1)阻塞库方法(Thread.sleep(),Object.wait()等),都会检查线程何时中断,并且在发现中断时提前返回。它们在相应中断时执行的操作
 *       包括:清除中断状态,抛出InterruptedException,表示阻塞操作由于中断而提前结束。
 *     2)JVM并不能保证阻塞方法检测到中断的速度，但在实际情况中响应速度还是非常快的。
 *     3)当线程在非阻塞状态下中断时，它的中断状态将被设置，然后根据江北取消的操作来检查中断状态以判断发生了中断。
 *
 *     Demo:terminate.PrimeProducer.java
 *     Demo:terminate.timeout
 *
 *
 * 8.What happens when an Exception occurs in a thread?
 *   If not caught thread will die,if an uncaught exception handler is registered then it will get a call back.
 *
 * 9.How do you share data between two thread in java
 *  1)wait and notify
 *   Demo: cooperation.InterThreadCommunicationExample.java
 *  2)concurrent data-structure like BlockingQueue
 *   Demo: cooperation.TestBlockingQueues.java
 *
 * 10.Difference between notify and notifyAll in java
 *  1)Main difference between notify and notifyAll is that notify method will only notify one Thread and
 *    notifyAll method will notify all Threads  which are waiting on that monitor or lock.
 *  2)notify会唤醒一个等待的线程，至于唤醒哪个则取决于线程的调度器
 *  3)notifyAll会唤醒所有的等待线程，但是他们会争夺锁；最后只有一个会获得锁并执行，其他的线程则继续等待(所以wait一般都是在循环里
 *  调用的)
 *
 *  Demo:cooperation.NotificationTest.java
 *

 *
 *
 * 11.何时使用notify何时使用notifyAll
 *    1)You can use notify over notifyAll if all thread are waiting for same condition
 *      and only one Thread at a time can benefit from condition becoming true.
 *
 *
 * 12.Why wait notify and notifyAll called from synchronized block or method in Java
 *  1)IllegalMonitorStateException in Java which will occur if we don't call wait (), notify () or notifyAll () method from synchronized context
 *  2) Any potential race condition between wait and notify method in Java
 *
 * 13. Why wait, notify and notifyAll are not inside thread class?
 *   1)One reason which is obvious is that Java provides lock at object level not at thread level
 *   2)In Java in order to enter critical section of code, Threads needs lock and they wait for lock,
 *     they don't know which threads holds lock instead they just know the lock is hold by some thread
 *     and they should wait for lock instead of knowing which thread is inside the synchronized block and
 *     asking them to release lock

 *
 * 14. What is ThreadLocal variable in Java
 *   Demo:sharing.ThreadLocalTest.java
 *   Demo:sharing.ThreadLocalVariableHolder.java
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
 *
 *
 *
 *
 *
 *
 */

