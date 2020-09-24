package com.course.model;

import lombok.Data;
import org.json.JSONObject;

@Data
public class User extends JSONObject {

    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", userName=:" + userName +
                ", password=" + password  +
                ", age=" + age  +
                ", sex=" + sex  +
                ", permission:" + permission  +
                ", isDelete=:" + isDelete  +
                "}";
    }
}
