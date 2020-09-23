package com.course.dao;

import com.course.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int login( User user);

    int addUser(User user);

    List<User> getUserInfo(User user);

    int updateUserInfo(User user);
}
