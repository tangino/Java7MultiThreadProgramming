package chapter_two_线程同步基础.锁方式解决生产者消费者问题;

public class Producer implements Runnable {

    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()) {
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
