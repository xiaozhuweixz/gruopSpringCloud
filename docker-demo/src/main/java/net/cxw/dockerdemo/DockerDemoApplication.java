package net.cxw.dockerdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
public class DockerDemoApplication {

    @Autowired
    Environment environment;

    /**
     * SpringBoot 的run启动顺序
     * 1、首先调用spring-boot-2.0.3.RELEASE.jar里面META-INF里面的spring.factories类，去初始化监听器
     *  会先调用public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources)构造方法去初始化
     *  如：webApplicationType（web的类型）、getSpringFactoriesInstances（加载spring.factories）、deduceMainApplicationClass（通过反射去获取到运行的主main方法）等
     *  在ru方法里面加载监听器、构建环境、容器的创建、容器的刷新、容器的舒初始化、容器接口扩展接口的初始化、Banner的加载、启动的错误的加载
     *
     *  总结上面几点运行顺序
     *  第一步：获取并启动监听器
     *  第二步：构造容器环境
     *  第三步：创建容器
     *  第四步：实例化SpringBootExceptionReporter.class，用来支持报告关于启动的错误
     *  第五步：准备容器
     *  第六步：刷新容器
     *  第七步：刷新容器后的扩展接口
     *
     * @param args
     */


    public static void main(String[] args) {
        SpringApplication.run(DockerDemoApplication.class, args);
    }


//    @RequestMapping("/user/find")
//    @ResponseBody
//    public Object findUser(){
//        Map<String, String > map = new HashMap<>();
//        map.put("name", "cxw.net");
//        map.put("age","28");
//        return map;
//    }



    @RequestMapping(value = "/environment", method = RequestMethod.GET)
    public String environment() {
        //输出environment 类型
        System.out.println(environment.getClass());
        return environment.toString();
    }



}
