package net.cxw.dockerdemo.controller;



import net.cxw.dockerdemo.util.MyLog;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HttpAspectController {

    @MyLog(value = "用户登录系统")
    @RequestMapping("/user/find")
    @ResponseBody
    public Object findUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, String > map = new HashMap<>();
        map.put("name", "cxw.net");
        map.put("age","28");
        return map;
    }
}
