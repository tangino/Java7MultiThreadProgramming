package chapter_one_线程的创建和控制;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {

    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);

        thread.start();


        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
