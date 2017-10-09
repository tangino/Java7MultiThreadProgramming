package chapter_four_使用执行器.处理被拒绝的任务;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedTaskController implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.printf("RejectedTaskController: The Task %s has been rejected\n", r.toString());

		System.out.printf("RejectedTaskController: %s\n", executor.toString());

		System.out.printf("RejectedTaskController: Terminating: %s\n", executor.isTerminating());

		System.out.printf("RejectedTaskController: Terminated: %s\n", executor.isTerminated());
	}

}
