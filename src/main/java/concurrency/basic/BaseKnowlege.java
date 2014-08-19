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

