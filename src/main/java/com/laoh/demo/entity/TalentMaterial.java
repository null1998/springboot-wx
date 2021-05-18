package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 天赋材料表(TalentMaterial)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:40:07
 */
public class TalentMaterial implements Serializable {
    private static final long serialVersionUID = 584593924362567938L;
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
