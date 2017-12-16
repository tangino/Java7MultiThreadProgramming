package chapter_seven_定制并发类.实现ThreadFactory接口;

import java.util.Date;

public class MyThread extends Thread {

	private Date creationDate;
	private Date startDate;
	private Date finishDate;

	public MyThread(Runnable target, String name) {
		super(target, name);
		setCreationDate();
	}

	public void setCreationDate() {
		this.creationDate = new Date();
	}

	public void setStartDate() {
		this.startDate = new Date();
	}

	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
	}

	public void setFinishDate() {
		this.finishDate = new Date();
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" creation date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}

	private Object getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}
}
