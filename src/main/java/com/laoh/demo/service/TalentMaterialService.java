package com.laoh.demo.service;

import com.laoh.demo.entity.TalentMaterial;

import java.util.List;

/**
 * 天赋材料表(TalentMaterial)表服务接口
 *
 * @author makejava
 * @since 2021-05-19 00:40:07
 */
public interface TalentMaterialService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TalentMaterial queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TalentMaterial> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param talentMaterial 实例对象
     * @return 实例对象
     */
    TalentMaterial insert(TalentMaterial talentMaterial);

    /**
     * 修改数据
     *
     * @param talentMaterial 实例对象
     * @return 实例对象
     */
    TalentMaterial update(TalentMaterial talentMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
