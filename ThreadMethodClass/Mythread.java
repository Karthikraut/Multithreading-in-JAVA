package OOP.ThreadMethodClass;

public class Mythread extends Thread{
    public Mythread(String name){
        super(name);
    }

    @Override
    public void run(){
       
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println(Thread.currentThread().getName()+" - Priority:- "+ Thread.currentThread().getPriority());
    }

    public static void main(String[] args) throws InterruptedException{
        Mythread t1 = new Mythread("High Priority");
        Mythread t2 = new Mythread("Low Priority");
        Mythread t3 = new Mythread("Medium Priority");
        t1.setPriority(4);
        t2.setPriority(2);
        t3.setPriority(3);
        t1.start();
        t2.start();
        t3.start();
        // t1.join();
        // System.out.println("Hello");
    }
}

//start, join, setPriority, interrupt, yield, setDaemon

// Interrupt:- If t1 is thread which is running and if we do t1.thread() then it will be blocked

// Yield:- If t1 is thread which is running and if we do t1.yirld then it will give another thread chance to execute

//Daemon Thread:- Background Thread :- if all other thread are killed and there are no thread then thought the daemon thread is running 
//it gets terminated

