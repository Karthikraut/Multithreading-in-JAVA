package ThreadCommunication;

class SharedResource {
    private int data;
    private boolean hasData = false; // Initially, there's no data

    // Produce method
    public synchronized void produce(int value) {
        while (hasData) {  // If data is already present, wait
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // Notify the consumer that data is available
    }

    // Consume method
    public synchronized int consume() {
        while (!hasData) { // If there's no data, wait
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // Notify the producer that data is consumed
        return data;
    }
}

// Producer class
class Producer implements Runnable {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            resource.produce(i); // Producing values 1 to 5
            try {
                Thread.sleep(1000); // Simulating some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Consumer class
class Consumer implements Runnable {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            resource.consume(); // Consuming values
            try {
                Thread.sleep(1500); // Simulating some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main class to run threads
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();
    }
}
