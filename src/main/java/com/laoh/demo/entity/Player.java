package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 原神角色表(Player)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:40:05
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 934129287074573431L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 名字
     */
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
