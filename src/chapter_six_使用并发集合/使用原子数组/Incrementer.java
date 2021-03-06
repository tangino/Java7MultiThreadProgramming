package chapter_six_使用并发集合.使用原子数组;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Incrementer implements Runnable {

	private AtomicIntegerArray vector;

	public Incrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}

	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndIncrement(i);
		}
	}

}
