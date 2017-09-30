package chapter_two_线程同步基础.锁方式解决生产者消费者问题;

public class Main {

    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);

        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(mock, buffer);

        Thread threadProducer = new Thread(producer, "Producer");

        Consumer consumers[] = new Consumer[3];

        Thread threadConsumers[] = new Thread[3];

        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
        }

        threadProducer.start();

        for (int i = 0; i<3; i++){
            threadConsumers[i].start();
        }
    }
}
