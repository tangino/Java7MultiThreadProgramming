package chapter_four_使用执行器.取消任务;

import java.util.concurrent.Callable;

public class CancelableTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		
		while(true){
			System.out.printf("Task: test\n");
			Thread.sleep(100);
		}
	}

}
