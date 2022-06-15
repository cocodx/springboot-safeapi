package com.lg.safeapi.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 正能量导师
 * @date 2022/6/15-9:30
 * @Description
 */
@Data
public class User {

    private Integer id;
    private String name;
    private Date birthday;
}
