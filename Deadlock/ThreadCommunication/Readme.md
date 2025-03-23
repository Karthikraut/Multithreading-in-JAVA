# Thread Communication in Java

## Overview
Thread communication is a fundamental concept in Java's multithreading where multiple threads interact with a shared resource in a synchronized manner. This example demonstrates the **Producer-Consumer** problem using `wait()`, `notify()`, and `notifyAll()`.

## Concepts Covered
### 1. **wait() Method**
- Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on the same object.
- Releases the lock on the shared object.
- Used inside a synchronized block/method.

### 2. **notify() Method**
- Wakes up **one** of the threads waiting on the object's monitor.
- The thread that gets woken up is chosen arbitrarily.

### 3. **notifyAll() Method**
- Wakes up **all** threads waiting on the object's monitor.
- Only one thread will successfully acquire the lock and proceed; others will continue waiting.

## Code Example
```java
package Deadlock.ThreadCommunication;

class SharedResource {
    private int data;
    private boolean hasData = false; // Initially, no data is present

    // Produce method
    public synchronized void produce(int value) {
        while (hasData) {  // Wait if data is already present
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notifyAll(); // Notify all waiting consumers
    }

    // Consume method
    public synchronized int consume() {
        while (!hasData) { // Wait if no data is available
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notifyAll(); // Notify all waiting producers
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
        Thread consumerThread1 = new Thread(new Consumer(resource));
        Thread consumerThread2 = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}
```

## How notifyAll() Works
Instead of using `notify()`, we used `notifyAll()`. This wakes up **all waiting threads** instead of just one. In scenarios where multiple consumers and producers exist, `notifyAll()` ensures **fair execution** by waking up all waiting threads, preventing any potential deadlocks.

## Expected Output (Order May Vary)
```
Produced: 1
Consumed: 1
Consumed: 1
Produced: 2
Consumed: 2
Consumed: 2
Produced: 3
Consumed: 3
Consumed: 3
```

## Why Use `notifyAll()` Instead of `notify()`?
- **`notify()` wakes up only one waiting thread**, which might not be the one that needs execution next.
- **`notifyAll()` wakes up all waiting threads**, ensuring that at least one thread gets executed if possible.
- **Useful when multiple consumers and producers exist**, avoiding unnecessary delays or deadlocks.

## Summary
- `wait()` makes a thread wait until it's notified.
- `notify()` wakes up one waiting thread.
- `notifyAll()` wakes up all waiting threads to ensure fair execution.
- Used in **Producer-Consumer** scenarios to prevent **race conditions** and **deadlocks**.

## License
This project is open-source and available for learning purposes. 🚀

