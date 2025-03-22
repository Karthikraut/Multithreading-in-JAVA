public class CreationOfThread extends Thread {
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            System.err.println(Thread.currentThread().getName());
        }
    }
}
