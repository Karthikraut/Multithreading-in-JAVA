# Locks in Java

## Introduction to Locks
Locks in Java provide thread synchronization mechanisms to ensure that shared resources are accessed safely in multithreading environments. There are two types of locks in Java:

1. **Intrinsic Locks (Monitor Locks)**: These are built into Java using the `synchronized` keyword.
2. **Explicit Locks (Reentrant Locks)**: These are provided by the `java.util.concurrent.locks` package.

## Intrinsic Locks (Monitor Locks)
Intrinsic locks are automatically acquired and released when a thread enters and exits a `synchronized` block or method. These locks are reentrant, meaning the same thread can acquire the lock multiple times without causing a deadlock.

### Example: Using `synchronized`
```java
public class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

### Why Do We Need Explicit Locks?
While `synchronized` blocks provide thread safety, they have limitations:
- Lack of flexibility in acquiring and releasing locks.
- Cannot try acquiring a lock with a timeout.
- No explicit lock checking.

To address these, Java introduced `ReentrantLock` in the `java.util.concurrent.locks` package.

## Explicit Locks (`ReentrantLock`)
A `ReentrantLock` provides greater control over locking mechanisms, allowing:
- Explicit lock acquisition and release (`lock.lock()` and `lock.unlock()`).
- Attempting to acquire a lock with a timeout (`tryLock()`).
- Fair locking policies.

### Example: Using `ReentrantLock`
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class BankAccount {
    private int balance = 100;
    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        try {
            if (lock.tryLock()) {
                try {
                    if (balance >= amount) {
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " withdrew " + amount);
                    } else {
                        System.out.println("Insufficient balance");
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock, try again later.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Reentrant Lock Explanation
A **reentrant lock** allows a thread to acquire the same lock multiple times without getting blocked. This prevents deadlock situations when a thread needs to call another synchronized method inside a locked method.

#### Example: Understanding Reentrant Lock
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outer() {
        lock.lock();
        try {
            System.out.println("Outer method acquired lock");
            inner();
        } finally {
            lock.unlock();
            System.out.println("Outer method released lock");
        }
    }

    public void inner() {
        lock.lock();
        try {
            System.out.println("Inner method acquired lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
            System.out.println("Inner method released lock");
        }
    }

    public static void main(String[] args) {
        ReentrantExample re = new ReentrantExample();
        re.outer();
    }
}
```

### Why is it Called "Reentrant"?
- The same thread can acquire the lock multiple times without blocking itself.
- The JVM keeps track of the lock count.
- When the lock count reaches zero (i.e., all `unlock()` calls match `lock()` calls), the lock is fully released.

### How Deadlock is Prevented
In the above example:
1. The `outer()` method locks the `ReentrantLock`.
2. Inside `outer()`, it calls `inner()`, which also locks the same `ReentrantLock`.
3. Since `ReentrantLock` tracks lock ownership by thread, it allows reentrance instead of blocking the thread.
4. The lock count increases when a thread reacquires the lock.
5. The thread releases the lock in `finally` blocks, ensuring proper unlocking and avoiding deadlocks.

## Summary
- **Intrinsic locks (`synchronized`)** are simpler but less flexible.
- **Explicit locks (`ReentrantLock`)** provide more control, preventing deadlocks and allowing timed lock attempts.
- **Reentrant locks** allow a thread to reacquire a lock multiple times, preventing self-blocking.
- **Deadlocks are avoided** as Java keeps track of which thread holds the lock, ensuring safe reentrance.

Using `ReentrantLock` wisely improves performance and prevents concurrency issues in Java applications.

