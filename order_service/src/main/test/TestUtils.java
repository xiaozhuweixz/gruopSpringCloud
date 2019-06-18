import net.th.order_service.OrderServiceApplication;
import net.th.order_service.domain.UserDomain;
import net.th.order_service.service.impl.RedisServiceImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes={OrderServiceApplication.class})//启动整个springboot工程
public class TestUtils {

    @Autowired
    public RedisServiceImpl redisService;


    @Test
    public void getRedisValue(){
        String userId = "1";
        redisService.getRedisValue("user:"+userId);

    }

    @Test
    public void setRedisValue(){

        UserDomain userDomain = new UserDomain();
        userDomain.setCreatTime(new Date());
        userDomain.setEamil("1111@qq.com");
        userDomain.setId("111111111");
        userDomain.setName("cxw");

        redisService.setRedisValue("user:1",userDomain.toString());

    }

}
