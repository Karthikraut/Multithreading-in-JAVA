package OOP.ThreadLifecycle;

public class Threadlifecycle extends Thread {
    @Override
    public void run(){
        System.err.println(Thread.currentThread().getName());
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Threadlifecycle t1 =new Threadlifecycle();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());//Runnable
        Thread.sleep(100);
        System.out.println(t1.getState()); //Timed_Waiting
        t1.join();
        System.out.println(t1.getState());
    }
}
