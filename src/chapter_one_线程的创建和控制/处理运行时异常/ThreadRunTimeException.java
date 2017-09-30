package chapter_one_线程的创建和控制.处理运行时异常;

public class ThreadRunTimeException {

    public static void main(String[] args){
        Task task =new Task();
        Thread thread = new Thread(task);
        Thread.setDefaultUncaughtExceptionHandler(new RunTimeExceptionHandler());
        thread.start();
    }
}
