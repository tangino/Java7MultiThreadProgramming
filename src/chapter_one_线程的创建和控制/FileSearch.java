package chapter_one_线程的创建和控制;

import java.io.File;


/**
 * 通过捕获InterruptedException来终止线程
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);

        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file) throws InterruptedException {
        File list[] = file.listFiles();

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
        }

        /*
          如果线程被中断，则抛出InterruptedException异常
         */
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(fileName)) {
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        /*
          如果线程被中断，则抛出InterruptedException异常
         */
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
}
