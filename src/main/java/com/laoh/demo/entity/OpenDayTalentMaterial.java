package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 天赋材料开放日表(OpenDayTalentMaterial)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:39:55
 */
public class OpenDayTalentMaterial implements Serializable {
    private static final long serialVersionUID = 850600247742287114L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 天赋材料外键
     */
    private Long talentMaterialId;
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

    public Long getTalentMaterialId() {
        return talentMaterialId;
    }

    public void setTalentMaterialId(Long talentMaterialId) {
        this.talentMaterialId = talentMaterialId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
