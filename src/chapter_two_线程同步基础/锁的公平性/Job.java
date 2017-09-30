package chapter_two_线程同步基础.锁的公平性;

public class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob();
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
