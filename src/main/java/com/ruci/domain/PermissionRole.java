package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PermissionRole {

    @TableField("rid")
    private Integer rid;
    @TableField("pid")
    private Integer pid;
}
