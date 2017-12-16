package chapter_seven_定制并发类.通过实现ThreadFactory为ForkJoin框架生成定制线程;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private int array[];

	private int start, end;

	public MyRecursiveTask(int array[], int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		Integer ret = 0;
		MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
		thread.addTask();
		return null;
	}

	private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
		int value;

		try {
			value = task1.get().intValue() + task2.get().intValue();
		} catch (InterruptedException e) {
			e.printStackTrace();
			value = 0;
		} catch (ExecutionException e) {
			e.printStackTrace();
			value = 0;
		}

		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value;
	}

}
