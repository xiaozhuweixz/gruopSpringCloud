package net.th.order_service.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Reids工具实现类
 */
@Component
public class RedisServiceImpl {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取Redis通过key获取value
     */
    public void getRedisValue(String strKeyValue){
        Object object = stringRedisTemplate.opsForValue().get(strKeyValue);
        if (object == null) {
            System.out.println("没有在Redis里面找到相对应的数据集合");
        }else {
            System.out.println("object: " + object.toString());
        }
    }

    /**
     * 存储Redis里面的键值对
     */
    public void setRedisValue(String strKeyValue, String strValue){

        stringRedisTemplate.opsForValue().set(strKeyValue, strValue);

    }

}
