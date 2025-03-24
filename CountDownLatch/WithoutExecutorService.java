package CountDownLatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WithoutExecutorService {
    public static void main(String[] args) throws InterruptedException {
       int numOfServices =3;
       CountDownLatch countLatch = new CountDownLatch(numOfServices);
       for(int i=0;i<numOfServices;i++){
            Thread t1 = new Thread(new DependantService(countLatch));
            t1.start();
       }
       countLatch.await();
       System.out.println("Threads work has been done!!");

    }
}

class DependantService implements Runnable{
    private final CountDownLatch countLatch; 
    DependantService(CountDownLatch countLatch){
        this.countLatch =countLatch;
    }

    @Override
    public void run() {
       try{
           System.out.println(Thread.currentThread().getName()+ " service started....");
           Thread.sleep(2000);
        }catch(Exception e){
            //Here handle the exception
        }
        finally{
           countLatch.countDown();
       }
    }
    
}
