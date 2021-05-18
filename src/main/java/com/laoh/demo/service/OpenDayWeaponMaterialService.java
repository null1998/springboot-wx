package com.laoh.demo.service;

import com.laoh.demo.entity.OpenDayWeaponMaterial;

import java.util.List;

/**
 * 武器材料开放日表(OpenDayWeaponMaterial)表服务接口
 *
 * @author makejava
 * @since 2021-05-19 00:40:04
 */
public interface OpenDayWeaponMaterialService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OpenDayWeaponMaterial queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OpenDayWeaponMaterial> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 实例对象
     */
    OpenDayWeaponMaterial insert(OpenDayWeaponMaterial openDayWeaponMaterial);

    /**
     * 修改数据
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 实例对象
     */
    OpenDayWeaponMaterial update(OpenDayWeaponMaterial openDayWeaponMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
