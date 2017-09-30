package chapter_two_线程同步基础.同步方法;

public class SynchronizedDemo {
    public static void main(String[] args){

        //创建账户
        Account account = new Account();
        account.setBalance(1000);

        //创建公司对象，对账户数据进行累加操作
        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        //创建银行对象， 对账户数据进行累减操作
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        System.out.printf("Account: Initial Balance: %f\n", account.getBalance());
        companyThread.start();
        bankThread.start();

        try{
            companyThread.join();
            bankThread.join();
            System.out.printf("Account: Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
