package chapter_three_高级线程同步.在集合点的同步;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {

	private int firstRow;
	private int lastRow;

	private MatrixMock mock;

	private Results results;

	private int number;

	private final CyclicBarrier barrier;

	public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {

		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	@Override
	public void run() {

		/*
		 * 每个线程都从指定行数中查找指定的数字，这里是数字5的出现次数
		 */
		int counter;
		System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);

		for (int i = firstRow; i < lastRow; i++) {
			int row[] = mock.getRow(i);

			counter = 0;

			for (int j = 0; j < row.length; j++) {
				if (row[j] == number) {
					counter++;
				}
			}
			results.setData(i, counter);
		}

		System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

		/*
		 *  这是一个集合点
		 */
		try {
			barrier.await();  //当指定数目的全部线程都调用await方法后返回，然后执行barrier构造函数的第二个参数中的线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
