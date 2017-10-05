package chapter_three_高级线程同步.等待多个并发事件的完成;

public class Main {

	public static void main(String[] args) {
		Videoconference conference = new Videoconference(10);

		Thread threadConference = new Thread(conference);

		threadConference.start();

		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			Thread t = new Thread(p);
			t.start();
		}
	}

}
