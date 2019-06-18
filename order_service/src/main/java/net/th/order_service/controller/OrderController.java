package net.th.order_service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.th.order_service.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("api/v1/order")
public class OrderController {


    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;



    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId, HttpServletRequest request){

        String token = request.getHeader("token");
        String cookie = request.getHeader("cookie");

        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("data", productOrderService.save(userId, productId));
        //productOrderService.save(userId, productId);
        return  data;

    }




    //注意，方法签名一定要要和api方法一致
    private Object saveOrderFail(int userId, int productId, HttpServletRequest request){

        //监控报警
        String saveOrderKye = "save-order";

        String sendValue = redisTemplate.opsForValue().get(saveOrderKye);
        final String ip = request.getRemoteAddr();
        new Thread( ()->{
            if (StringUtils.isBlank(sendValue)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ip);
                //发送一个http请求，调用短信服务 TODO
                redisTemplate.opsForValue().set(saveOrderKye, "save-order-fail", 20, TimeUnit.SECONDS);

            }else{
                System.out.println("已经发送过短信，20秒内不重复发送");
            }

        }).start();


        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
        return msg;
    }

    /**
     * 提取上传方法为公共方法
     * @param uploadDir 上传文件目录
     * @param file 上传对象
     * @throws Exception
     */
    public void executeUpload(String uploadDir,MultipartFile file) throws Exception
    {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String filename = UUID.randomUUID().toString().replace("-","") + suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir + File.separator + filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public @ResponseBody
    String uploads(HttpServletRequest request, MultipartFile[] file)
    {
        try {
            String uploadDir = "/Users/caixiaowei/Desktop";
            System.out.println(uploadDir);
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists())
            {
                dir.mkdir();
            }
            //遍历文件数组执行上传
            for (int i =0;i<file.length;i++) {
                if(file[i] != null) {
                    //调用上传方法
                    executeUpload(uploadDir, file[i]);
                }
            }
        }catch (Exception e)
        {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }



    @RequestMapping(value = "/downloadByEvalId",method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadByEvalId(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fileName = "bootstrap.yml";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "/Users/caixiaowei/Downloads/gruopSpringCloud/order_service/src/main/resources/";
            File file = new File(realPath+ File.separator +fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
