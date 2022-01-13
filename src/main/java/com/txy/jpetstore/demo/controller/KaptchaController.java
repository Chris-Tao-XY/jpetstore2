package com.txy.jpetstore.demo.controller;


//import com.bjpowernode.pojo.Mykaptcha;
import com.txy.jpetstore.demo.domain.Mykaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RequestMapping("/code")
@RestController
public class KaptchaController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/img")
    public void createImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        final int width = 120; // 图片宽度
        final int height = 32; // 图片高度
        final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)
        final OutputStream output = response.getOutputStream(); // 获得可以向客户端返回图片的输出流
        // (字节流)
        // 创建验证码图片并返回图片上的字符串
        String code = Mykaptcha.create(width, height, imgType, output);
        System.out.println("验证码内容: " + code);
        // 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )
        session.setAttribute("MY_KAPTCHA", code);
        stringRedisTemplate.opsForValue().set("MY_KAPTCHA", code, Duration.ofSeconds(120));
    }
    @GetMapping(value = "/verify/{code}")
    public Map<String,Object> verify(@PathVariable("code") String code) throws IOException {
        Map<String ,Object>map=new HashMap<>();
        String realkaptcha = stringRedisTemplate.opsForValue().get("MY_KAPTCHA");
        System.out.println(realkaptcha);
        if (!Objects.equals(realkaptcha,code)){
            map.put("code",501);
            map.put("message","no");
            map.put("wrongmessage","wrong code!");
        }else{
            map.put("code",200);
            map.put("message","ok");
        }
        return map;
    }
}
