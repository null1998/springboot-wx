package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 武器材料开放日表(OpenDayWeaponMaterial)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:40:04
 */
public class OpenDayWeaponMaterial implements Serializable {
    private static final long serialVersionUID = -32661104705133809L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 武器材料外键
     */
    private Long weaponMaterialId;
    /**
     * 星期几
     */
    private Integer day;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeaponMaterialId() {
        return weaponMaterialId;
    }

    public void setWeaponMaterialId(Long weaponMaterialId) {
        this.weaponMaterialId = weaponMaterialId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
