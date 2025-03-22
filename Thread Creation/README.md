# Multi-Threading in Java

## What is Multi-Threading?
Imagine you're a chef in a kitchen. If you do tasks one at a time—chopping vegetables, cooking the sauce, then grilling the meat—that's like a single-threaded process.

Multi-threading is like having multiple chefs, each handling a different task simultaneously. One is chopping, another is stirring, and a third is grilling. This makes the cooking process much faster and more efficient.

In computing, multi-threading allows a program to execute multiple parts (threads) concurrently within the same process, sharing resources like memory.

## Why is Multi-Threading Important?
- **Responsiveness**: A long-running task in a single thread might freeze an application. Multi-threading helps offload such tasks to keep the UI responsive.
- **Resource Utilization**: Modern CPUs have multiple cores, and multi-threading helps utilize them efficiently by executing different threads on different cores.
- **Concurrency**: Multi-threading enables concurrency, allowing multiple tasks to progress simultaneously. This is crucial for applications like web servers handling multiple client requests.

## CPU and Cores: The Hardware Foundation
- **CPU (Central Processing Unit)**: The brain of the computer, responsible for executing instructions.
- **Core**: A processing unit within a CPU. A CPU can be single-core, dual-core, quad-core, or more.

Earlier CPUs had only one core, and the system used **time-sharing** to switch between tasks, creating an illusion of simultaneous execution. With multi-core processors, true parallel execution is possible, significantly improving performance.

## Programs, Processes, and Threads: The Software Hierarchy
- **Program**: A set of instructions to perform a task (e.g., Microsoft Word, a web browser).
- **Process**: An instance of a program running on the system.
- **Thread**: The smallest unit of execution within a process. A process can have multiple threads sharing memory and resources.

Example: In Microsoft Word, multiple threads handle typing, spell-checking, and auto-saving independently, making the application more responsive.

## Multi-tasking vs. Multi-threading
| Feature | Multi-tasking | Multi-threading |
|---------|--------------|----------------|
| Definition | Running multiple processes concurrently | Running multiple threads within a single process |
| Example | Running a browser, a music player, and a text editor simultaneously | A word processor handling typing, spell-checking, and auto-saving in parallel |
| Scope | Higher-level (operating system level) | More granular (within a process) |

## Multi-Threading in Java
Java has built-in support for multi-threading in the `java.lang` package.
- In **single-core** environments, the JVM and OS switch between threads to simulate concurrency.
- In **multi-core** environments, multiple threads can run in parallel on different cores.

When a Java program starts, the **main thread** is created, responsible for executing the `main` method.

## Creating Threads in Java
There are two primary ways to create threads in Java:

### 1. Extending the `Thread` Class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + getName() + ": " + i);
            try {
                Thread.sleep(100); // Pause for 100 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
                return;
            }
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("First Thread");
        MyThread t2 = new MyThread();
        t2.setName("Second Thread");
        t1.start();
        t2.start();
    }
}
```

### 2. Implementing the `Runnable` Interface
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(100); // Pause for 100 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
                return;
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable, "First Thread");
        Thread t2 = new Thread(runnable, "Second Thread");
        t1.start();
        t2.start();
    }
}
```

### Key Notes:
- The `start()` method creates a new thread and executes the `run()` method in that thread.
- Directly calling `run()` does not create a new thread; it runs in the current thread.
- The `Thread.sleep(ms)` method pauses execution for the specified milliseconds.

## Conclusion
Multi-threading is a powerful feature that enhances application responsiveness, resource utilization, and concurrency. Java provides built-in support for creating and managing threads using `Thread` and `Runnable`. Understanding and using multi-threading effectively can significantly improve the performance of modern applications.

