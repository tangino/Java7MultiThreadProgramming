package chapter_seven_定制并发类.通过实现ThreadFactory为ForkJoin框架生成定制线程;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkerThread extends ForkJoinWorkerThread {

	private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

	protected MyWorkerThread(ForkJoinPool pool) {
		super(pool);
	}

	@Override
	protected void onStart() {
		super.onStart();

		System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
		taskCounter.set(0);
	}

	@Override
	protected void onTermination(Throwable exception) {
		System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
		super.onTermination(exception);
	}

	public void addTask() {
		int counter = taskCounter.get().intValue();
		counter++;
		taskCounter.set(counter);
	}
}
