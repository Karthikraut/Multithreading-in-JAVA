package Locks;

public class Main {
    public static void main(String[] args){
        BankAcoount sbi = new BankAcoount();
        Runnable mythread = new Runnable(){
            @Override
            public void run(){
                sbi.withdrawn(50);
            }
        };

        Thread t1 = new Thread(mythread);
        Thread t2 = new Thread(mythread);
        t1.start();
        t2.start();
    }
}
