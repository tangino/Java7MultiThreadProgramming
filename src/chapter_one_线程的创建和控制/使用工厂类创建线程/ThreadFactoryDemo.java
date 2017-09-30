package chapter_one_线程的创建和控制.使用工厂类创建线程;

import chapter_one_线程的创建和控制.线程局部变量.SafeTask;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryDemo {

    public static void main(String[] args){
        ThreadFactory factory = new MyThreadFactory();
        SafeTask task = new SafeTask();
        Thread thread = factory.newThread(task);
        thread.start();
    }
}
