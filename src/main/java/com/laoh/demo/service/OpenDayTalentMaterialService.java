package com.laoh.demo.service;

import com.laoh.demo.entity.OpenDayTalentMaterial;

import java.util.List;

/**
 * 天赋材料开放日表(OpenDayTalentMaterial)表服务接口
 *
 * @author makejava
 * @since 2021-05-19 00:39:57
 */
public interface OpenDayTalentMaterialService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OpenDayTalentMaterial queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OpenDayTalentMaterial> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param openDayTalentMaterial 实例对象
     * @return 实例对象
     */
    OpenDayTalentMaterial insert(OpenDayTalentMaterial openDayTalentMaterial);

    /**
     * 修改数据
     *
     * @param openDayTalentMaterial 实例对象
     * @return 实例对象
     */
    OpenDayTalentMaterial update(OpenDayTalentMaterial openDayTalentMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
