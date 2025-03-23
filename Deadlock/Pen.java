package Deadlock;

public class Pen {
    public synchronized void writewithpenandpaper(Paper paper){
        System.out.println(Thread.currentThread().getName()+ " writing with pen and paper");
        paper.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finished writing");
    }
}
