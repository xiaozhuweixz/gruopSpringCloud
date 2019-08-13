package controller.day01;


import java.util.concurrent.TimeUnit;

/**
 * 饿汉模式   ------->  使用实例的收才回去创建对象
 */

public class LazySignThreadController {

    private static volatile LazySignThreadController lazySignThreadController = null;

    private LazySignThreadController(){

    }
    public static synchronized LazySignThreadController getInstance(){
        if (null == lazySignThreadController) {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //不使用volatile关键字和synchronized关键字的时候
            //lazySignThreadController = new LazySignThreadController();
//            synchronized (LazySignThreadController.class){  //锁住整个类对象
//                if (null == lazySignThreadController){
//                    lazySignThreadController = new LazySignThreadController();
//                }
//            }
// ----------------------------------------------------------------------------------------
            lazySignThreadController = new LazySignThreadController();
        }
        return lazySignThreadController;
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            new Thread(()->{
                System.out.println("object:" + LazySignThreadController.getInstance() +"------->"+ Thread.currentThread().getName());
            }).start();
        }
    }
}
