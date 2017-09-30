package chapter_one_线程的创建和控制.线程的中断;

public class PrimeGenerator extends Thread {
    
    @Override
    public void run(){
        long number = 1l;
        
        while (true){
            if (isPrime(number)){
                System.out.printf("Number %d is Prime\n", number);
            }

            if (isInterrupted()){
                System.out.printf("The prime Generator has been Interrupted");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2){
            return true;
        }

        for (long i = 2; i<number; i++){
            if ((number % i) == 0){
                return false;
            }
        }
        return true;
    }
}
