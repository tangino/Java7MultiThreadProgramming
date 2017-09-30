package chapter_one_线程的创建和控制.线程的休眠和恢复;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("FileClock has been interrupted\n");
            }
        }
    }
}
