package chapter_two_线程同步基础.同步代码块;

public class Cinema {

    //两个电影院
    private long vacanciesCinema1;
    private long vacanciesCinema2;

    //创建同步对象
    private final Object controlCinema1, controlCinema2;

    public Cinema() {
        //初始化同步对象
        controlCinema1 = new Object();
        controlCinema2 = new Object();

        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
    }

    public boolean sellTickets1(int number) {
        synchronized (controlCinema1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean sellTickets2(int number) {
        synchronized (controlCinema2) {
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean returnTickets1(int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1 += number;
            return true;
        }
    }

    public boolean returnTickets2(int number) {
        vacanciesCinema2 += number;
        return true;
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }


    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}
