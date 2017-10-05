package chapter_three_高级线程同步.等待多个并发事件的完成;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
	
	private Videoconference conference;
	
	private String name;
	
	public Participant(Videoconference videoconference, String name) {
		this.conference = videoconference;
		this.name = name;
	}

	@Override
	public void run() {
		long duration = (long)(Math.random()*10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		conference.arrive(name);
	}

}
