package com.laoh.demo.service;

import com.laoh.demo.entity.WeaponNeedWeaponMaterial;

import java.util.List;

/**
 * 武器所需材料表(WeaponNeedWeaponMaterial)表服务接口
 *
 * @author makejava
 * @since 2021-05-19 00:40:10
 */
public interface WeaponNeedWeaponMaterialService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeaponNeedWeaponMaterial queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WeaponNeedWeaponMaterial> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 实例对象
     */
    WeaponNeedWeaponMaterial insert(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial);

    /**
     * 修改数据
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 实例对象
     */
    WeaponNeedWeaponMaterial update(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
