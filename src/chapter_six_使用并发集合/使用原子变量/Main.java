package chapter_six_使用并发集合.使用原子变量;

public class Main {

	public static void main(String[] args) {
		Account account = new Account();

		account.setBalance(1000);

		Company company = new Company(account);

		Thread companyThread = new Thread(company);

		Bank bank = new Bank(account);

		Thread bankThread = new Thread(bank);

		System.out.printf("Account: Initial Balance: %d\n", account.getBalance());

		companyThread.start();
		bankThread.start();

		try {
			companyThread.join();
			bankThread.join();

			System.out.printf("Account: Final Balance: %d\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
