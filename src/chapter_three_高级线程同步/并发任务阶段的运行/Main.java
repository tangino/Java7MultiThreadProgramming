package chapter_three_高级线程同步.并发任务阶段的运行;

import java.util.concurrent.Phaser;

public class Main {

	public static void main(String args[]) {
		//创建一个Phaser，指定参与者个数为3
		Phaser phaser = new Phaser(3);

		FileSearch system = new FileSearch("c:\\Windows", "log", phaser);

		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);

		FileSearch documents = new FileSearch("C:\\Documents And Settings", "long", phaser);

		Thread systemThread = new Thread(system, "system");
		systemThread.start();

		Thread appsThread = new Thread(apps, "apps");
		appsThread.start();

		Thread documentsThread = new Thread(documents, "documents");
		documentsThread.start();

		try {
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Terminated: " + phaser.isTerminated());
	}
}
