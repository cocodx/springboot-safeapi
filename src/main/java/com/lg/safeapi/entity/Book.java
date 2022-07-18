package com.lg.safeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 正能量导师
 * @date 2022/6/15-9:31
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * 开启二级缓存，对应的实体类要实现序列化
 */
public class Book implements Serializable {

    private Integer id;
    private String author;
    private String name;
    private Double price;
    private Date createTime;
    private String description;

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
