package chapter_two_线程同步基础.生产者消费者问题;

public class ProducerConsumerDemo {

    public static void main(String[] args){

        EventStorage storage = new EventStorage();

        Producer producer =new Producer(storage);
        Thread t1 = new Thread(producer);

        Consumer consumer =new Consumer(storage);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();
    }
}
