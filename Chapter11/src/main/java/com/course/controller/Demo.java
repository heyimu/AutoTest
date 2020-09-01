package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "/v1", description = "1.0版本的demo")
@RequestMapping("/v1")
public class Demo {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户的数量", httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        System.out.println(user);
        int addUser = template.insert("addUser",user);
        return addUser;
    }

    @RequestMapping(value = "/updateUser", method =RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        int updateUser = template.update("updateUser", user);
        return updateUser;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public int deleteUser(@RequestParam Integer id){
        int deleteUser = template.delete("deleteUser", id);
        return deleteUser;
    }

}
