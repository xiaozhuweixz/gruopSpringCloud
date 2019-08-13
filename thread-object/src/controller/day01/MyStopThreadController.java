package controller.day01;

import java.util.concurrent.TimeUnit;

/**
 *  线程终止
 */
public class MyStopThreadController implements Runnable{

    private static int i = 0;
    private static int j = 0;

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
        while (!Thread.interrupted()) {   //是否有中断标识
            System.out.println("i==========>" + ++i);
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("j==========>" + ++j);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyStopThreadController());
        thread.start();
        TimeUnit.SECONDS.sleep(1L);
        //thread.stop();  //废弃方法，开发中不要使用。因为一调用，线程就立刻停止，此时有可能引发相应的线程安全性问题
        thread.interrupt();
    }
}
