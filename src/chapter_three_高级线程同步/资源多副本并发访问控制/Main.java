package chapter_three_高级线程同步.资源多副本并发访问控制;

public class Main {

	public static void main(String[] args) {

		PrintQueue printQueue = new PrintQueue();

		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread" + i);
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}
