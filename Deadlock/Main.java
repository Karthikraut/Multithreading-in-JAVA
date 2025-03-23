package Deadlock;

public class Main {
    public static void main(String[] args){
        Pen pen= new Pen();
        Paper paper =new Paper();
        Thread t1 =new Thread(new Task1(pen,paper),"Thread-1");
        Thread t2 =new Thread(new Task2(pen,paper),"Thread-2");
        t1.start();
        t2.start();
    }
}

class Task1 implements Runnable{
    Pen pen;
    Paper paper;
    Task1(Pen pen, Paper paper){
        this.pen =pen;
        this.paper = paper;
    }

    @Override
    public void run(){
        synchronized (paper){ //This is the code for resolution of code
            pen.writewithpenandpaper(paper);
        }
    }
}

class Task2 implements Runnable{
    Paper paper;
    Pen pen;
    Task2(Pen pen, Paper paper){
        this.paper =paper;
        this.pen =pen;
    }

    @Override
    public void run(){
        paper.writewithpenandpaper(pen);
    }
}
