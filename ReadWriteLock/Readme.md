# ReadWrite Lock in Java

## Introduction
A **ReadWriteLock** in Java is used to handle concurrent access to shared resources by multiple threads efficiently. It allows multiple threads to read the resource simultaneously but ensures that only one thread can write at a time, preventing data inconsistency.

## Understanding ReadWriteLock
Java provides the `ReentrantReadWriteLock` class, which consists of two locks:
1. **Read Lock:** Allows multiple threads to read simultaneously if no thread is writing.
2. **Write Lock:** Ensures exclusive access to only one thread for writing.

This mechanism improves performance compared to a standard `synchronized` block because it allows parallel reads when no write operation is happening.

## Code Explanation
The following Java program demonstrates the use of `ReentrantReadWriteLock`:

```java
package ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " incremented");
            }
        };

        Thread t1 = new Thread(readTask);
        Thread t2 = new Thread(writeTask);
        Thread t3 = new Thread(readTask);

        t1.start();
        t2.start();
        t3.start();
    }
}
```

## How It Works
1. The `increment()` method uses a **write lock**, ensuring only one thread can update the `count` at a time.
2. The `getCount()` method uses a **read lock**, allowing multiple threads to read the `count` value concurrently.
3. In `main()`, three threads are created:
   - `t1` and `t3` perform read operations.
   - `t2` performs write operations.
4. The read threads (`t1`, `t3`) can execute simultaneously unless a write operation (`t2`) is in progress.
5. The write thread (`t2`) gets exclusive access, blocking all other reads and writes until it completes.

## Advantages of ReadWriteLock
- **Improves performance** by allowing concurrent reads.
- **Ensures data consistency** by preventing simultaneous writes.
- **Prevents starvation** (writers won’t be blocked indefinitely by continuous readers).

## When to Use ReadWriteLock
- When you have a **high read-to-write ratio**, meaning more reads than writes.
- When **performance** is a concern, and synchronized blocks cause unnecessary thread blocking.

## Conclusion
`ReadWriteLock` is a useful concurrency control mechanism in Java that optimizes read-heavy applications while ensuring safe data updates. This example illustrates how multiple readers can work simultaneously, but writes are exclusive to maintain data integrity.

