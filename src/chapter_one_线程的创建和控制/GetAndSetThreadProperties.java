package chapter_one_线程的创建和控制;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class GetAndSetThreadProperties {

    public static void main(String[] args){
        Thread threads[] = new Thread[10];

        /**
         *
         * <li>{@link #NEW}<br>
         *     A thread that has not yet started is in this state.
         *     </li>
         * <li>{@link #RUNNABLE}<br>
         *     A thread executing in the Java virtual machine is in this state.
         *     </li>
         * <li>{@link #BLOCKED}<br>
         *     A thread that is blocked waiting for a monitor lock
         *     is in this state.
         *     </li>
         * <li>{@link #WAITING}<br>
         *     A thread that is waiting indefinitely for another thread to
         *     perform a particular action is in this state.
         *     </li>
         * <li>{@link #TIMED_WAITING}<br>
         *     A thread that is waiting for another thread to perform an action
         *     for up to a specified waiting time is in this state.
         *     </li>
         * <li>{@link #TERMINATED}<br>
         *     A thread that has exited is in this state.
         *     </li>
         */
        Thread.State status[] = new Thread.State[10];

        //设置线程优先级
        for (int i = 0; i<10; i++){
            threads[i] = new Thread(new Calculator(i));

            if (i%2==0){  //偶数线程高优先级
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }

            threads[i].setName("Thread " + i);
        }

        try(FileWriter file = new FileWriter("E:\\workspace\\MultiThreadProgramming\\ThreadCreatingAndControlling\\data\\log.txt");
            PrintWriter pw = new PrintWriter(file)){
            //把线程开始之前的状态写入文件
            for (int i = 0; i<10; i++){
                pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            //开启线程
            for (int i = 0; i<10; i++){
                threads[i].start();
            }
            
            boolean finish = false;
            
            while(!finish){
                for (int i = 0; i<10; i++){
                    if (threads[i].getState() != status[i]){
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }
                for (int i = 0; i<10; i++){
                    //当线程状态为TERMINATED时finish才为真
                    finish = (threads[i].getState() == State.TERMINATED)?true:false;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, State status) {
        pw.printf("Main: Id %d - ", thread.getId(), thread.getName());
        pw.printf("Main: Priority: %d\n", thread.getPriority());
        pw.printf("Main: Old State: %s\n", status);
        pw.printf("Main: New State: %s\n", thread.getState());
        pw.printf("Main: *********************************************\n");
    }
}


