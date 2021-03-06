package chapter_one_线程的创建和控制.处理线程组运行时异常;

public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n", t.getId());
        e.printStackTrace();
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();
    }
}
