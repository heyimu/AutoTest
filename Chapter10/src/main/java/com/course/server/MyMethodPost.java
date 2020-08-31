package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "所有的post方法")
@RequestMapping("/v1")
public class MyMethodPost {

    private static Cookie cookie;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功获取cookies信息")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName")String userName,
                        @RequestParam(value = "password")String password){
        if ("zhangsan".equals(userName) && "123".equals(password)){
            cookie = new Cookie("login","false");
            response.addCookie(cookie);
            return "登陆成功";
        }

        return "用户名/密码错误";
    }


    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
        User u = new User();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie1 : cookies) {
            if ("login".equals(cookie1.getName())
                    && "false".equals(cookie1.getValue())
                    && "张三".equals(user.getName())){

                u.setName("lisi");
                u.setAge("18");
                u.setSex("man");
                return u.toString();
            }
        }
        return "参数不合法";
    }
}
