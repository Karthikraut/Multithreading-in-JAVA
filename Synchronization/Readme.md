# Synchronization in Java

## 1. Introduction to Synchronization
Synchronization in Java is a mechanism that ensures only one thread can access a shared resource at a time. It helps prevent **race conditions** and maintains data consistency in multithreaded programs.

### Why is Synchronization Needed?
- When multiple threads modify a shared resource simultaneously, **data inconsistency** can occur.
- Synchronization ensures that only one thread accesses a critical section of the code at a time.
- It helps avoid unpredictable results and maintains thread safety.

## 2. Code Explanation
The provided code demonstrates **thread synchronization** using a shared counter.

### 2.1 Counter Class
```java
package Synchronization;

public class Counter {
    private int count = 0;
    
    public void increment() {
        count++; // Not synchronized - leads to race conditions
    }

    public int getCount() {
        return count;
    }
}
```
#### Explanation:
- `count` is a shared resource.
- `increment()` increases the value of `count` but is **not synchronized**, which may lead to incorrect results when accessed by multiple threads simultaneously.
- `getCount()` returns the current value of `count`.

### 2.2 MyThread Class
```java
package Synchronization;

public class MyThread extends Thread {
    Counter counter;
    
    MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
```
#### Explanation:
- This class extends `Thread` and runs `increment()` 1000 times.
- **Issue:** The `synchronized` keyword on `run()` **does not synchronize access to `increment()`**, because each thread has its own `run()` method execution.
- **Solution:** `synchronized` should be applied to `increment()` in the `Counter` class instead.

### 2.3 Test Class (Main Method)
```java
package Synchronization;

public class Test {
    public static void main(String[] args) {
        Counter counter = new Counter();
        
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        System.out.println(counter.getCount());
    }
}
```
#### Explanation:
- A shared `Counter` object is created.
- Two threads (`t1` and `t2`) operate on the same counter.
- `start()` is used to begin execution of both threads.
- `join()` ensures that the main thread waits for `t1` and `t2` to finish before printing the result.
- **Issue:** Since `increment()` is not synchronized, the output may be inconsistent.

## 3. Fixing Synchronization Issue
To ensure thread safety, we should **synchronize the increment method** in `Counter`:
```java
public synchronized void increment() {
    count++;
}
```
Now, only one thread can access `increment()` at a time, preventing race conditions.

## 4. Key Definitions
- **Thread:** A lightweight process that runs concurrently with other threads.
- **Synchronization:** A mechanism to control thread execution so that only one thread accesses a critical section at a time.
- **Race Condition:** A situation where multiple threads access shared data simultaneously, leading to inconsistent results.
- **join():** A method that ensures one thread waits for another to complete before continuing execution.
- **synchronized Keyword:** Used to lock a method or block so that only one thread can execute it at a time.

## 5. Output Before and After Synchronization
### Without Synchronization:
```
Random values (e.g., 1543, 1768, etc.) due to race conditions
```
### With Synchronization:
```
2000 (Ensuring consistency)
```
## 6. Conclusion
- Without synchronization, **race conditions** lead to inconsistent results.
- Synchronizing critical methods ensures **data consistency and thread safety**.
- Use `synchronized` or other concurrency mechanisms (e.g., `ReentrantLock`, `AtomicInteger`) when working with shared resources.

This explanation and code will help you in interviews and improve your understanding of Java multithreading and synchronization. 🚀

