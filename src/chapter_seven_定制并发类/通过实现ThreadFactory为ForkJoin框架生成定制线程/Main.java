package chapter_seven_定制并发类.通过实现ThreadFactory为ForkJoin框架生成定制线程;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyWorkerThreadFactory factory = new MyWorkerThreadFactory();

		ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);

		int array[] = new int[100000];

		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}

		MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);

		pool.execute(task);

		task.join();

		pool.shutdown();

		pool.awaitTermination(1, TimeUnit.DAYS);

		System.out.printf("Main: Result: %d\n", task.get());

		System.out.println("Main: End of the program\n");
	}
}
