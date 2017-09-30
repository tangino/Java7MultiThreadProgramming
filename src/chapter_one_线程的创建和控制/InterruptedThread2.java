package chapter_one_线程的创建和控制;

import java.util.concurrent.TimeUnit;

public class InterruptedThread2 {

    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("D:\\", "temp.png");

        Thread thread = new Thread(searcher);

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
