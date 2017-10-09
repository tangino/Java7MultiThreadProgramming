package chapter_three_高级线程同步.并发任务阶段的运行;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {

	private String initPath;

	private String end;

	private List<String> results;

	private Phaser phaser;

	public FileSearch(String initPath, String end, Phaser phaser) {
		this.initPath = initPath;
		this.end = end;
		this.phaser = phaser;

		this.results = new ArrayList<>();
	}

	@Override
	public void run() {
		//当3个参与者都执行到这里的时候才继续执行后面的代码
		phaser.arriveAndAwaitAdvance(); 
		System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

		File file = new File(initPath);

		if (file.isDirectory()) {
			directoryProcess(file);
		}

		if (!checkResults()) {
			return;
		}

		filterResults();

		if (!checkResults()) {
			return;
		}

		showInfo();

		/*
		 * 所有工作处理完毕，注销线程，每个线程到达这里后马上注销不等待其他参与者，
		 * 执行一次参与者减1，
		 */
		phaser.arriveAndDeregister();
		System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
	}

	private void directoryProcess(File file) {
		File list[] = file.listFiles();

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
	}

	private void fileProcess(File file) {
		if (file.getName().endsWith(end)) {
			results.add(file.getAbsolutePath());
		}
	}

	private void filterResults() {
		List<String> newResults = new ArrayList<>();
		long actualDate = new Date().getTime();

		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));

			long fileDate = file.lastModified();

			if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				newResults.add(results.get(i));
			}
		}
		results = newResults;
	}

	private boolean checkResults() {
		if (results.isEmpty()) {
			System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());

			System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
			//把没有数据的线程从参与者中排除
			phaser.arriveAndDeregister();
			return false;
		} else {
			System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(),
					results.size());
			phaser.arriveAndAwaitAdvance(); //保证所有线程都执行完且集合中有数据
			return true;
		}
	}

	private void showInfo() {
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}
		phaser.arriveAndAwaitAdvance();  //等待所有线程执行结果显示
	}
}
