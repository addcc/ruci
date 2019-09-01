package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_role")
public class UserRole {

    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "rid")
    private Integer rid;
}
