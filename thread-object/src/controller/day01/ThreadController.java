package controller.day01;


import java.util.concurrent.TimeUnit;

/**
 * 自己定义死锁
 */
public class ThreadController {

    private static final Object objectA = new Object();
    private static final Object objectB = new Object();



    public static void main(String[] args) {

        new Thread(()->{
            synchronized (objectA){
                try {
                    TimeUnit.SECONDS.sleep(1l);  //当线程运行的比较快的时候是不会出现死锁，一旦在某个主线程中出现延时时，则会产生死锁。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectB){
                    System.out.println("A可能把B怼死了。");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (objectB){
                synchronized (objectA){
                    System.out.println("B可能把A怼死了");
                }
            }

        }).start();
    }
}
