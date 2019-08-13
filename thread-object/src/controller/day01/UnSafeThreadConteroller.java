package controller.day01;

import java.util.concurrent.CountDownLatch;

/**
 * 线程安全
 */
public class UnSafeThreadConteroller {

    private static  int mun = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    /**
     * 用于初始化参数
     */
    public static void creatNum(){
        mun ++;
    }


    public static void main(String[] args) {
        for (int i=1; i<=10; i++) {
            new Thread(()->{
                for (int j=1; j<=100; j++) {
                        creatNum();
                }
                System.out.println("ThreadName:" + Thread.currentThread().getName());
                countDownLatch.countDown();
            }).start();
        }

        while (true){
            if (countDownLatch.getCount() == 0) {
                break;
            }
        }
        System.out.println("mun:" + mun);
    }
}
