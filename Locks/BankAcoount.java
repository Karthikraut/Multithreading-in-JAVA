package Locks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class BankAcoount {
    private int amount=100;
    private final Lock lock = new ReentrantLock();
    
    public void withdrawn(int amnt){

        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName()+" is withdrawing amount...");
                try{
                    amount-=amnt;
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + " successfully withdrawn amount Balance:- "+amount);
                }catch(Exception e){
                    Thread.currentThread().getName();
                }finally{
                    lock.unlock();
                }
            }else{
                System.out.println(Thread.currentThread().getName()+ " tried to acquired lock but failed try again later");
            }
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }
    }
}
