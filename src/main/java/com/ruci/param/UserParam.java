package com.ruci.param;

import lombok.Data;

import java.util.Date;

@Data
public class UserParam {

    private Integer id;

    private String name;

    //
    private Integer age;
    //
    private String email;
    //
    private String phone;
    //
    private Integer gender;
    //
    private Date createDate;
    //
    private String userName;

    private String password;

    public UserParam(){}
}
