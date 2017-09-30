package chapter_one_线程的创建和控制;

public class ThreadRunTimeException {

    public static void main(String[] args){
        Task task =new Task();
        Thread thread = new Thread(task);
        Thread.setDefaultUncaughtExceptionHandler(new RunTimeExceptionHandler());
        thread.start();
    }
}
