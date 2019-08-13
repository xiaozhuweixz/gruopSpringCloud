package controller.day01;

import java.util.concurrent.TimeUnit;

/**
 * 线程的挂起和恢复    wait()为线程的挂起，notify()为唤醒线程   必须使用统一线程锁进行匹配，synchronized锁住的对象也必须统一对象
 */
public class MyThreadController implements Runnable{

    private static Object object = new Object();
    private static Object waitObject = new Object();
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (object) {
            System.out.println("线程开始----------1");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束----------2");
        }
    }

    public static void main(String[] args) {
        Thread statrThread = new Thread(new MyThreadController());
        statrThread.start();
        try {
            TimeUnit.SECONDS.sleep(3l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (object){
            object.notify();
        }
    }
}
