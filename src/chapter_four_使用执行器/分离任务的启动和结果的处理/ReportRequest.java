package chapter_four_使用执行器.分离任务的启动和结果的处理;

import java.util.concurrent.CompletionService;

/*
 * 通过CompletionService的submit方法负责启动任务。
 */
public class ReportRequest implements Runnable {

	private String name;

	private CompletionService<String> service;

	public ReportRequest(String name, CompletionService<String> service) {
		this.name = name;
		this.service = service;
	}

	@Override
	public void run() {
		ReportGenerator reportGenerator = new ReportGenerator(name, "Report");

		service.submit(reportGenerator);
	}

}
