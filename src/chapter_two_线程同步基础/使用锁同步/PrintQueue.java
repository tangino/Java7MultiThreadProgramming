package chapter_two_线程同步基础.使用锁同步;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    private final Lock queueLoack = new ReentrantLock(false);

    public void printJob(){
        queueLoack.lock();
        try{
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during " +
                    (duration/1000)+ " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLoack.unlock();
        }
    }
}
