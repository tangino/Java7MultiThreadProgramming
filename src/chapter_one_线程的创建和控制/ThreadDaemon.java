package chapter_one_线程的创建和控制;

import java.util.ArrayDeque;
import java.util.Deque;

public class ThreadDaemon {

    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();

        WriterTask writer = new WriterTask(deque);

        //创建3个工作线程，这些线程主要添加事件队列
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }

        //清理旧事件的线程（守护线程），只有工作线程休眠的时候才能获取cpu时钟
        CleanerTask cleaner = new CleanerTask(deque);

        cleaner.start();
    }
}

