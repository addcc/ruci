package com.ruci.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginParam {

    private Integer id;

    @NotNull(message = "账号不能为空")
    @Length(min = 5,message = "账号长度不能小于5")
    @Length(max = 20,message = "密码长度不能超过20")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 5,message = "账号长度不能小于5")
    @Length(max = 20,message = "密码长度不能超过20")
    private String password;

    private String pass;

    public LoginParam(){

    }
}
