package chapter_two_线程同步基础.使用锁同步;

public class LockDemo {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread thread[] = new Thread[10];

        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
