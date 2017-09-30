package chapter_one_线程的创建和控制;

/**
 * 通过线程的interrupt方法中断线程
 *
 */

public class InterruptingThread {

    public static void main(String[] args) {
        Thread task = new PrimeGenerator();

        task.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.interrupt();
    }
}
