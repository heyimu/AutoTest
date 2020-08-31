package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.INTERNAL;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/", description = "所有的get方法")
public class MyMethod {

    @GetMapping(path = "/getCookies")
    @ApiOperation(value = "获取到Cookies方法", httpMethod = "GET")
    public String getCookies(HttpServletResponse response){

        Cookie cookie = new Cookie("login","false");
        response.addCookie(cookie);
        return "获取到Cookies1111111111111111111";
    }

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "你的cookies是空的";
        }
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "false".equals(cookie.getValue())){
                return "cookies验证通过";
            }
        }
        return "这是一个需要携带cookies的方法";
    }

    /**
     * 带参数才嫩访问的get请求方式
     * 第一种： url:port/get/param/getList?key=value&key=value
     * 第二种： url:port/get/param/getMyList
     */
    @RequestMapping(value="/get/param/getList",method = RequestMethod.GET)
    public Map<String, Integer> getList(@RequestParam Integer start){
        Map<String, Integer> map = new HashMap<>();
        map.put("鞋子",1);
        map.put("衣服",2);

        return map;
    }

    @RequestMapping(value="/get/param/getMyList/{start}",method = RequestMethod.GET)
    public Map<String, Integer> getMyList(@PathVariable Integer start){
        Map<String, Integer> map = new HashMap<>();
        map.put("鞋子",1);
        map.put("衣服",2);

        return map;
    }



}
