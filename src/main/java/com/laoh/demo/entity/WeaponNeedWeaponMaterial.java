package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 武器所需材料表(WeaponNeedWeaponMaterial)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:40:10
 */
public class WeaponNeedWeaponMaterial implements Serializable {
    private static final long serialVersionUID = -96643955894449682L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 武器id
     */
    private Long weaponId;
    /**
     * 武器材料id
     */
    private Long weaponMaterialId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }

    public Long getWeaponMaterialId() {
        return weaponMaterialId;
    }

    public void setWeaponMaterialId(Long weaponMaterialId) {
        this.weaponMaterialId = weaponMaterialId;
    }

}
