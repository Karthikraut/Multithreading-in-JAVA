package CountDownLatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numOfServices =3;
        ExecutorService executorService = Executors.newFixedThreadPool(numOfServices);
        CountDownLatch countLatch = new CountDownLatch(numOfServices);
        executorService.submit(new DependantService(countLatch));
        executorService.submit(new DependantService(countLatch));
        executorService.submit(new DependantService(countLatch));
        countLatch.await();

        System.out.println("Excecutor Service work has been finished!!");
        executorService.shutdown();

    }
}

class DependantService implements Callable<String>{
    private final CountDownLatch countLatch; 
    DependantService(CountDownLatch countLatch){
        this.countLatch =countLatch;
    }

    @Override
    public String call() throws Exception {
       try{
           System.out.println(Thread.currentThread().getName()+ " service started....");
           Thread.sleep(2000);
        }finally{
           countLatch.countDown();
       }

       return "ok";
    }
    
}
