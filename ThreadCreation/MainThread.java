package OOP.ThreadCreation;
public class MainThread{
    public static void main(String[] args){
        //Below is the Implementation Using Extends
        CreationOfThread t1 = new CreationOfThread();
        t1.start();
        for(int i=0;i<10000;i++){
            System.out.println(Thread.currentThread().getName());
        }


        //Below Is the Runnalbe implementation
        CrationOfThreadRunnableInterface  world = new CrationOfThreadRunnableInterface();
        Thread t2 = new  Thread(world);
        t2.start();
    }
}