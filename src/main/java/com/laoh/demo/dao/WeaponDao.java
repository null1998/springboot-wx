package com.laoh.demo.dao;

import com.laoh.demo.entity.Weapon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 武器表(Weapon)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-19 00:40:08
 */
public interface WeaponDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Weapon queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Weapon> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param weapon 实例对象
     * @return 对象列表
     */
    List<Weapon> queryAll(Weapon weapon);

    /**
     * 新增数据
     *
     * @param weapon 实例对象
     * @return 影响行数
     */
    int insert(Weapon weapon);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Weapon> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Weapon> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Weapon> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Weapon> entities);

    /**
     * 修改数据
     *
     * @param weapon 实例对象
     * @return 影响行数
     */
    int update(Weapon weapon);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

