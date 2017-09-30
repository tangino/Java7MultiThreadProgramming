package chapter_one_线程的创建和控制;

import java.util.Random;

public class ThreadGroupTask implements Runnable{
    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());

        while (true){

        }
    }
}
