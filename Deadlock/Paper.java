package Deadlock;

public class Paper {
    public synchronized void writewithpenandpaper(Pen pen){
        System.out.println(Thread.currentThread().getName()+ " writng with pen and paper");
        pen.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finished writing");
    }
}
