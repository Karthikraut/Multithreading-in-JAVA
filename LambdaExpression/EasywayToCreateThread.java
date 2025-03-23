package LambdaExpression;

public class EasywayToCreateThread {
   //First way to create thread
    Runnable runnable1 = new Runnable() {
        @Override
        public void run(){
            System.out.println("Thread is running...");
        }
    };

    Thread t1 = new Thread(runnable1);

    //Second way to create thread:- Using lambda function
    Runnable runnable2= ()->{
        System.out.println("Thread is Running...");
    };

    Thread t2 = new Thread(runnable2);

    //Third way to Create Thread:- Simplifying lambda function
    Thread t3 = new Thread(()->{
        System.out.println("Thread is Running...");
    });

    
}
