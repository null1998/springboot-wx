package com.laoh.demo.dao;

import com.laoh.demo.entity.OpenDayWeaponMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 武器材料开放日表(OpenDayWeaponMaterial)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-19 00:40:04
 */
public interface OpenDayWeaponMaterialDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OpenDayWeaponMaterial queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OpenDayWeaponMaterial> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 对象列表
     */
    List<OpenDayWeaponMaterial> queryAll(OpenDayWeaponMaterial openDayWeaponMaterial);

    /**
     * 新增数据
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 影响行数
     */
    int insert(OpenDayWeaponMaterial openDayWeaponMaterial);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OpenDayWeaponMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OpenDayWeaponMaterial> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OpenDayWeaponMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OpenDayWeaponMaterial> entities);

    /**
     * 修改数据
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 影响行数
     */
    int update(OpenDayWeaponMaterial openDayWeaponMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

