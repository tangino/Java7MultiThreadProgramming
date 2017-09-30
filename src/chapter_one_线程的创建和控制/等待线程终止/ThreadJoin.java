package chapter_one_线程的创建和控制.等待线程终止;

import java.util.Date;

public class ThreadJoin {
    public static void main(String[] args){

        DataSourceLoader dsLoader = new DataSourceLoader();
        Thread t1 = new Thread(dsLoader);

        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread t2 = new Thread(ncLoader);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
