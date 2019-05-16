package net.th.order_service.fallbcak;

import net.th.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务，错降级处理
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {

        System.out.println("feign 调用product-service findbyid 异常");

        return null;
    }



}
