# Java Multi-Threading: Thread Lifecycle and Methods

## Thread States: The Thread Lifecycle
A thread goes through various states during its lifecycle:

- **NEW**: A thread is in this state when it is created but has not yet started.
- **RUNNABLE**: After the `start()` method is called, the thread becomes runnable. It is ready to run and is waiting for CPU time. Java combines the "ready" and "running" states into `RUNNABLE`.
- **BLOCKED**: A thread enters this state when it is waiting for a monitor lock to enter a synchronized block or method.
- **WAITING**: A thread enters this state when it is waiting indefinitely for another thread to perform a particular action. This can happen when calling methods like `wait()`, `join()`, or `park()`.
- **TIMED_WAITING**: Similar to `WAITING`, but the thread waits for a specified amount of time. This can happen when calling methods like `sleep()`, `wait(timeout)`, `join(timeout)`, or `parkNanos()`.
- **TERMINATED**: A thread enters this state when it has finished executing its `run()` method or when an uncaught exception occurs.

## Example Code Demonstrating Thread Lifecycle
```java
package OOP.ThreadLifecycle;

public class Threadlifecycle extends Thread {
    @Override
    public void run(){
        System.err.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Threadlifecycle t1 = new Threadlifecycle();
        System.out.println(t1.getState()); // NEW
        t1.start();
        System.out.println(t1.getState()); // RUNNABLE
        Thread.sleep(100);
        System.out.println(t1.getState()); // TIMED_WAITING
        t1.join();
        System.out.println(t1.getState()); // TERMINATED
    }
}
```

## Thread Class Methods
The `Thread` class provides several methods for controlling and managing threads. Let's explore some of the most important ones:

- **`start()`**: Starts a new thread and executes the `run()` method.
- **`run()`**: Contains the code that will be executed in the thread.
- **`sleep(long millis)`**: Pauses execution for a specified amount of time.
  ```java
  try {
      Thread.sleep(1000); // Pause for 1 second
  } catch (InterruptedException e) {
      // Handle interruption
  }
  ```
- **`join()`**: Allows one thread to wait for another thread to complete its execution.
  ```java
  Thread t = new Thread(() -> {
      // Some task
  });
  t.start();
  try {
      t.join();
      System.out.println("Thread 't' has finished.");
  } catch (InterruptedException e) {
      // Handle interruption
  }
  ```
- **`setPriority(int priority)`**: Sets the priority of the thread.
  ```java
  Thread t = new Thread(() -> {
      // Some task
  });
  t.setPriority(Thread.MAX_PRIORITY);
  t.start();
  ```
- **`setName(String name)/getName()`**: Sets and retrieves the name of a thread.
  ```java
  Thread t = new Thread(() -> {
      // Some task
  });
  t.setName("MyWorkerThread");
  System.out.println("Thread name: " + t.getName());
  t.start();
  ```
- **`interrupt()`**: Interrupts a thread that is blocked or waiting.
  ```java
  Thread t = new Thread(() -> {
      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          System.out.println("Thread interrupted!");
          return;
      }
      // Some task
  });
  t.start();
  t.interrupt();
  ```
- **`isInterrupted()`**: Checks whether a thread has been interrupted.
  ```java
  Thread t = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
          // Some task
      }
      System.out.println("Thread was interrupted.");
  });
  t.start();
  t.interrupt();
  ```

## Important Considerations
- **Thread Safety**: When multiple threads access and modify shared data, it's crucial to ensure thread safety using synchronization mechanisms (like locks) to prevent race conditions and data corruption.
- **Deadlock**: A situation where two or more threads are blocked indefinitely, waiting for each other to release resources. Proper resource management is essential to avoid deadlocks.
- **Livelock**: Similar to deadlock, but threads continuously change their state in response to each other, without making any progress.
- **Starvation**: A situation where a thread is repeatedly denied access to resources and is unable to make progress.

## Choosing Between `Thread` and `Runnable`
- If your class already extends another class, you must implement the `Runnable` interface, as Java does not support multiple inheritance.
- Implementing `Runnable` promotes loose coupling, as your class is not tied to the `Thread` class.
- Extending `Thread` might be simpler for basic use cases.

**In general, implementing `Runnable` is preferred due to its flexibility and adherence to good object-oriented design principles.**

## Conclusion
This guide covers Java multi-threading fundamentals, including thread states, lifecycle, and essential methods. Multi-threading can be complex, but with practice and experimentation, you can master it.

Feel free to explore more advanced topics like thread synchronization and concurrent programming!

