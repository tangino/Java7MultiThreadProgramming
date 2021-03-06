package chapter_three_高级线程同步.并发任务间的数据交换;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {
	public static void main(String[] args) {

		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();

		Exchanger<List<String>> exchanger = new Exchanger<>();

		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);

		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);

		threadProducer.start();
		threadConsumer.start();
	}
}
