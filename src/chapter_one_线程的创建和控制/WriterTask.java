package chapter_one_线程的创建和控制;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * 每秒向Deque属性添加一个时间对象
 */

public class WriterTask implements Runnable {

    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));

            deque.addFirst(event);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

