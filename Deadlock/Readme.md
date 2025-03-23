# Deadlock in Java and Its Resolution

## What is Deadlock?
A **deadlock** happens in multi-threaded programs when two or more threads are stuck waiting for each other forever. This happens when:

1. Thread-1 locks **Resource A** and waits for **Resource B**.
2. Thread-2 locks **Resource B** and waits for **Resource A**.

Since both threads are waiting for each other, they never proceed, causing a **deadlock**.

## Deadlock in Our Code
In our Java program, we have two shared resources:
- `Pen`
- `Paper`

We also have two tasks:
- `Task1` (which writes using the `Pen` and `Paper`)
- `Task2` (which also writes using `Pen` and `Paper`)

### **Before Fix: Code with Deadlock**
```java
package Deadlock;

public class Main {
    public static void main(String[] args){
        Pen pen= new Pen();
        Paper paper = new Paper();
        Thread t1 = new Thread(new Task1(pen, paper), "Thread-1");
        Thread t2 = new Thread(new Task2(pen, paper), "Thread-2");
        t1.start();
        t2.start();
    }
}

class Task1 implements Runnable{
    Pen pen;
    Paper paper;
    Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run(){
        pen.writewithpenandpaper(paper); // Locks Pen first, then Paper
    }
}

class Task2 implements Runnable{
    Paper paper;
    Pen pen;
    Task2(Pen pen, Paper paper){
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run(){
        paper.writewithpenandpaper(pen); // Locks Paper first, then Pen
    }
}
```

### **Why Does This Code Cause Deadlock?**
1. `Thread-1` locks `Pen` and waits for `Paper`.
2. `Thread-2` locks `Paper` and waits for `Pen`.
3. Both threads are stuck waiting for each other, causing a **deadlock**.

## **After Fix: Code Without Deadlock**
We resolved the deadlock by **changing the locking order** in `Task1`.

```java
class Task1 implements Runnable{
    Pen pen;
    Paper paper;
    Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run(){
        synchronized (paper) { // Lock Paper first
            pen.writewithpenandpaper(paper);
        }
    }
}
```

### **How This Fix Works?**
- Now, **both threads try to lock `Paper` first** before locking `Pen`.
- Since both follow the same locking order, **deadlock is avoided**.

## Summary
✅ **Deadlock occurs** when multiple threads hold locks on different resources and wait for each other forever.  
✅ **Fixing deadlock** requires ensuring a consistent locking order across all threads.  
✅ **In our solution**, we always lock `Paper` first before `Pen`, preventing circular waiting.  

This way, our program runs smoothly without getting stuck! 🚀

