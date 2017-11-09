package chapter_six_使用并发集合.使用原子变量;

public class Company implements Runnable {

	private Account account;

	public Company(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.addAmount(1000);
		}
	}

}
