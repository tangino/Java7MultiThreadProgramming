package chapter_three_高级线程同步.等待多个并发事件的完成;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {

	private final CountDownLatch controller;

	public Videoconference(int number) {
		this.controller = new CountDownLatch(number);
	}

	public void arrive(String name) {
		System.out.printf("%s has arrived.", name);
		controller.countDown();

		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
		
		try {
			controller.await();
			
			System.out.printf("VideoConference: All the participants have come\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
