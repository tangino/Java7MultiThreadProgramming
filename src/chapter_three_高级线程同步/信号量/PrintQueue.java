package chapter_three_高级线程同步.信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PrintQueue {
	
	private final Semaphore semaphore;
	
	public PrintQueue(){
		semaphore = new Semaphore(1);
	}

	public void printJob(Object document){
		
		try {
			semaphore.acquire();
			
			long duration = (long)(Math.random()*10);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", 
					Thread.currentThread().getName(), duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
