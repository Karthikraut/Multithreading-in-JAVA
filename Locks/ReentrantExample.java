package Locks;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock  = new ReentrantLock();

    public void Outter(){
        lock.lock();
        try{
            Inner();
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
    }

    public void Inner(){
        lock.lock();
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
    }
    public static void main(String[] args){
        ReentrantExample res = new ReentrantExample();
        res.Outter();
    }
}
