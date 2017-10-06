package chapter_three_高级线程同步.资源多副本并发访问控制;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

	private final Semaphore semaphore;

	private boolean freePrinters[];

	private Lock lockPrinters;

	public PrintQueue() {
		/**
		 * 信号量用于控制可以有多少个线程进入临界区
		 * 信号量的作用并不是用来替换锁，使用信号量的同时也需要使用锁
		 */
		semaphore = new Semaphore(3);

		freePrinters = new boolean[3];

		for (int i = 0; i < 3; i++) {
			freePrinters[i] = true;
		}

		lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {

		try {
			semaphore.acquire();  //请求进入临界区的权限

			int assignedPrinter = getPrinter();  //真正的临界区，必须使用Lock保护

			long duration = (long) (Math.random() * 10);
			System.out.printf("Printer %d, %s: PrintQueue: Printing a Job during %d seconds\n", assignedPrinter, Thread.currentThread().getName(),
					duration);
			TimeUnit.SECONDS.sleep(duration);

			freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();  //释放权限
		}
	}

	/**
	 * 因为信号量初始值为3，所以可以有3个线程同时进入getPrinter定义的临界区。
	 * @return 当前空闲的打印机
	 */
	private int getPrinter() {
		int ret = -1;

		try {
			lockPrinters.lock();  //使用Lock保护数据，防止数据出错
			System.out.printf("Thread %d enter getPrinter\n", Thread.currentThread().getId());
			for (int i = 0; i < freePrinters.length; i++) {
				if (freePrinters[i]) {
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockPrinters.unlock();
		}
		return ret;
	}
}
