package com.laoh.demo.dao;

import com.laoh.demo.entity.WeaponNeedWeaponMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 武器所需材料表(WeaponNeedWeaponMaterial)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-19 00:40:10
 */
public interface WeaponNeedWeaponMaterialDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeaponNeedWeaponMaterial queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WeaponNeedWeaponMaterial> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 对象列表
     */
    List<WeaponNeedWeaponMaterial> queryAll(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial);

    /**
     * 新增数据
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 影响行数
     */
    int insert(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeaponNeedWeaponMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WeaponNeedWeaponMaterial> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeaponNeedWeaponMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<WeaponNeedWeaponMaterial> entities);

    /**
     * 修改数据
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 影响行数
     */
    int update(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

