# Easy Ways to Create Threads in Java using Lambda Expressions

## Overview
In Java, threads can be created in multiple ways. This example demonstrates three different approaches, including the use of **Lambda Expressions**, which simplify thread creation.

## Approaches to Creating Threads

### 1️⃣ Using `Runnable` with Anonymous Class
Before Java 8, we used **anonymous inner classes** to create a thread using the `Runnable` interface:

```java
Runnable runnable1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread is running...");
    }
};

Thread t1 = new Thread(runnable1);
t1.start();
```
✅ **Pros**: Works in all Java versions.
❌ **Cons**: Verbose, requires an explicit class declaration.

---

### 2️⃣ Using Lambda Expression (Java 8+)
Lambda expressions simplify thread creation by removing boilerplate code:

```java
Runnable runnable2 = () -> {
    System.out.println("Thread is Running...");
};

Thread t2 = new Thread(runnable2);
t2.start();
```
✅ **Pros**: More readable and concise.
❌ **Cons**: Works only in Java 8 and later.

---

### 3️⃣ Directly Passing Lambda Expression
We can directly pass a lambda function while creating the thread:

```java
Thread t3 = new Thread(() -> {
    System.out.println("Thread is Running...");
});

t3.start();
```
✅ **Pros**: Even **shorter** and **more concise**.
❌ **Cons**: Readability might decrease in complex scenarios.

---

## Summary
| Approach | Java Version | Code Complexity | Readability |
|----------|-------------|----------------|-------------|
| Anonymous Inner Class | Java 5+ | High | Medium |
| Lambda Expression | Java 8+ | Medium | High |
| Direct Lambda in Thread | Java 8+ | Low | High |

Using **Lambda Expressions** is the best way to create threads **when simplicity and readability** are required. 🚀

---

## Execution Example
Here's the complete Java code for reference:

```java
package LambdaExpression;

public class EasywayToCreateThread {
    public static void main(String[] args) {
        // First way
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread is running...");
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();

        // Second way (Lambda Expression)
        Runnable runnable2 = () -> {
            System.out.println("Thread is Running...");
        };
        Thread t2 = new Thread(runnable2);
        t2.start();

        // Third way (Direct Lambda Expression)
        Thread t3 = new Thread(() -> {
            System.out.println("Thread is Running...");
        });
        t3.start();
    }
}
```
