package chapter_six_使用并发集合.使用阻塞式线程安全列表;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class BlokingDequeDemo {

	public static void main(String[] args) throws Exception {
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);

		Client client = new Client(list);

		Thread thread = new Thread(client);

		thread.start();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				// 如果列表为空，这调用take方法的线程被阻塞，直到列表数据可用
				String request = list.take();
				System.out.printf("Main: Request: %s at %s. Size: %d\n", request, new Date(), list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}

		System.out.printf("Main: End of the program.\n");
	}
}
