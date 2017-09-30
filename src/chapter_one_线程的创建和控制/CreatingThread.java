package chapter_one_线程的创建和控制;

public class CreatingThread {

    public static void main(String[] args){
        //创建10个Calculator对象并运行

        for (int i = 0; i<10; i++){
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }



}
