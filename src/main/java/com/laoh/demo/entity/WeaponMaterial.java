package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 武器材料表(WeaponMaterial)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:40:09
 */
public class WeaponMaterial implements Serializable {
    private static final long serialVersionUID = -29577686799387283L;
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
