package chapter_one_线程的创建和控制.使用工厂类创建线程;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread();
    }
}
