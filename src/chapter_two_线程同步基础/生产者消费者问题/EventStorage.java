package chapter_two_线程同步基础.生产者消费者问题;

import java.util.Date;
import java.util.LinkedList;

public class EventStorage {

    private int maxSize;
    private int runCount;
    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void set() {
        runCount++;

        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        notifyAll();
    }

    public synchronized void get(){
        System.out.println("Get runCount: " + runCount);
        while(storage.size() == 0){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Get: %d: %s\n", storage.size(), storage.poll());
        notifyAll();
    }
}
