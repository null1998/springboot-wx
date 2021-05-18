package com.laoh.demo.service;

import com.laoh.demo.entity.PlayerNeedTalentMaterial;

import java.util.List;

/**
 * 原神角色所需天赋材料表(PlayerNeedTalentMaterial)表服务接口
 *
 * @author makejava
 * @since 2021-05-19 00:40:07
 */
public interface PlayerNeedTalentMaterialService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlayerNeedTalentMaterial queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PlayerNeedTalentMaterial> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 实例对象
     */
    PlayerNeedTalentMaterial insert(PlayerNeedTalentMaterial playerNeedTalentMaterial);

    /**
     * 修改数据
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 实例对象
     */
    PlayerNeedTalentMaterial update(PlayerNeedTalentMaterial playerNeedTalentMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
