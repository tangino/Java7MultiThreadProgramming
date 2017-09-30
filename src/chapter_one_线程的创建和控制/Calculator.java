package chapter_one_线程的创建和控制;

public class Calculator implements Runnable {

    private int number;

    public Calculator(int number){
        this.number = number;
    }

    /**
     * 每个线程计算和打印1~10的结果， 即打印乘法表
     */
    @Override
    public void run() {
        for (int i = 1; i<=10; i++){
            System.out.printf("%s: %d * %d = %d\n",
                    Thread.currentThread().getName(), number, i, i*number);
        }
    }
}