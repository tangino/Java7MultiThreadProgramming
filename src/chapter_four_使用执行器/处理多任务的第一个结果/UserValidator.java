package chapter_four_使用执行器.处理多任务的第一个结果;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {

	private String name;

	public UserValidator(String name) {
		this.name = name;
	}

	public boolean validate(String name, String password) {
		Random random = new Random();

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);

			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			return false;
		}
		return random.nextBoolean();
	}
	
	public String getName(){
		return this.name;
	}

}
