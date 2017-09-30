package chapter_two_线程同步基础.锁的公平性;

public class LockFairDemo {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread thread[] = new Thread[10];

        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();

            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
