package chapter_three_高级线程同步.并发任务间的数据交换;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

	private List<String> buffer;

	private final Exchanger<List<String>> exchanger;

	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;

		/*
		 * 交换数据10次
		 */
		for (int i = 0; i < 10; i++) {
			System.out.printf("Consumer: Cycle %d\n", cycle);

			try {
				//把线程置于休眠，等待生产者线程到达进行数据交换
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/*
			 * 消费数据
			 */
			for (int j = 0; j < 10; j++) {
				String message = buffer.get(0);
				System.out.println("Consumer: " + message);
				buffer.remove(0);
			}

			cycle++;
		}
	}
}
