package controller.day01;


/**
 * 单例模式线程安全   ------->随着类加载的时候加载实例
 */
public class SignThreadController {

    private static  SignThreadController signThreadController = new SignThreadController();

    private static SignThreadController getInstance(){
        return signThreadController;
    }

    private SignThreadController(){

    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {

            new Thread(()->{
                System.out.println(SignThreadController.getInstance());
            }).start();

        }
    }

}
