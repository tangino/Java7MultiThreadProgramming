package chapter_four_使用执行器.分离任务的启动和结果的处理;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主要内容是使用CompletionService的submit方法和poll方法对任务的启动和结果的处理进行分离操作
 * 方法使在不同的任务中调用CompletionService的这两个方法。
 * @author dns
 *
 */
public class SeparateTaskDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		// 创建两个不同名称的任务
		ReportRequest faceRequest = new ReportRequest("Face", service);

		ReportRequest onlineRequest = new ReportRequest("Online", service);

		Thread faceThread = new Thread(faceRequest);

		Thread onlineThread = new Thread(onlineRequest);

		ReportProcessor processor = new ReportProcessor(service);
		Thread senderThread = new Thread(processor);

		System.out.printf("Main: Starting the Thread\n");
		faceThread.start();
		onlineThread.start();
		senderThread.start();

		try {
			System.out.printf("Main: Waiting for the report generators.\n");
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Shuting down the executor.\n");
		executor.shutdown();

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		processor.setEnd(true);
		System.out.println("Main: Ends");
	}

}
