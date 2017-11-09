package chapter_six_使用并发集合.按优先级排序的阻塞式列表;

import java.util.concurrent.PriorityBlockingQueue;

public class Task implements Runnable {

	private int id;

	private PriorityBlockingQueue<Event> queue;

	public Task(int id, PriorityBlockingQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Event event = new Event(id, i);
			queue.add(event);
		}
	}

}
