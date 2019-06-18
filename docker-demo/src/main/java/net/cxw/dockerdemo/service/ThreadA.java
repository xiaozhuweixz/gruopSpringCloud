package net.cxw.dockerdemo.service;

public class ThreadA extends Thread{


    public ThreadA(String name) {
        super(name);

    }


    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
            // i整除4时，调用yield
            if (i % 4 == 0)
                Thread.yield();
        }
    }

    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t2.start();
    }
}
