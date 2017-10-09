package chapter_four_使用执行器.任务后续操作;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {

	private String name;

	/**
	 * 
	 * @param 需要获取结果的任务
	 */
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name = ((ExecutableTask) callable).getName();
	}

	/**
	 * 当目标任务执行完毕
	 * 这个后续任务就会被执行，这里是获取并打印其他任务返回的结果
	 */
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been canceled\n", name);
		} else {
			System.out.printf("%s: Has finished\n", name);
		}
	}

}
