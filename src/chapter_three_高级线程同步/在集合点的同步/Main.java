package chapter_three_高级线程同步.在集合点的同步;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {

		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;

		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

		Results results = new Results(ROWS);

		Grouper grouper = new Grouper(results);

		/*
		 * CyclicBarrier有点类似于JavaScript中的回调机制
		 * 当指定的所有线程都执行完毕后，CyclicBarrier中指定的线程才会被执行
		 */
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

		Searcher searcher[] = new Searcher[PARTICIPANTS];

		for (int i = 0; i < PARTICIPANTS; i++) {
			searcher[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock,
					results, 5, barrier);
			Thread thread = new Thread(searcher[i]);
			thread.start();
		}

		System.out.printf("Main: The main thread has finished.\n");
	}

}
