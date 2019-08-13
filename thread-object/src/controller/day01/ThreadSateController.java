package controller.day01;


/**
 * 线程状态的演示
 */
public class ThreadSateController {


    public static void main(String[] args) {
        Object o = new Object();
        //1、新增状态----->new Thread()的时候,但是调用start的时候进入运行状态
//        new Thread(()->{
//
//        }).start();
        //--------------------------------
        //2、阻塞状态---->线程阻塞于synchronized锁，等待获取synchronized锁的状态
//        new Thread(()->{
//            synchronized (o){
//                System.out.println("o:" + o.toString());
//            }
//        }).start();
         //3、等待(WAITING)：Object.wait()、join()、 LockSupport.park(),进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
//        new Thread(()->{
//            synchronized (o){
//                try {
//                    o.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
          //4、超时等待(TIME_WAITING)：Object.wait(long)、Thread.join()、LockSupport.parkNanos()、LockSupport.parkUntil，该状态不同于WAITING，
//         new Thread(()->{
//             synchronized (o){
//                 try {
//                     o.wait(10000);
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }).start();
        //5、终止(TERMINATED)：表示该线程已经执行完毕
    }
}
