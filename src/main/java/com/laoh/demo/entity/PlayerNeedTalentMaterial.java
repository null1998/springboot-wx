package com.laoh.demo.entity;

import java.io.Serializable;

/**
 * 原神角色所需天赋材料表(PlayerNeedTalentMaterial)实体类
 *
 * @author huangyanduo
 * @since 2021-05-19 00:40:06
 */
public class PlayerNeedTalentMaterial implements Serializable {
    private static final long serialVersionUID = 339829899745224013L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 原神角色id
     */
    private Long playerId;
    /**
     * 天赋材料id
     */
    private Long talentMaterialId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTalentMaterialId() {
        return talentMaterialId;
    }

    public void setTalentMaterialId(Long talentMaterialId) {
        this.talentMaterialId = talentMaterialId;
    }

}
