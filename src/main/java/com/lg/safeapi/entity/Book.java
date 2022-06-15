package com.lg.safeapi.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 正能量导师
 * @date 2022/6/15-9:31
 * @Description
 */
@Data
public class Book {

    private Integer id;
    private String author;
    private String name;
    private Double price;
    private Date createTime;
    private String description;
}
