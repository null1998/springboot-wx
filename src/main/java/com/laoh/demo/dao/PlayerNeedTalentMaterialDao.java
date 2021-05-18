package com.laoh.demo.dao;

import com.laoh.demo.entity.PlayerNeedTalentMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 原神角色所需天赋材料表(PlayerNeedTalentMaterial)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-19 00:40:07
 */
public interface PlayerNeedTalentMaterialDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlayerNeedTalentMaterial queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PlayerNeedTalentMaterial> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 对象列表
     */
    List<PlayerNeedTalentMaterial> queryAll(PlayerNeedTalentMaterial playerNeedTalentMaterial);

    /**
     * 新增数据
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 影响行数
     */
    int insert(PlayerNeedTalentMaterial playerNeedTalentMaterial);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PlayerNeedTalentMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PlayerNeedTalentMaterial> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PlayerNeedTalentMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<PlayerNeedTalentMaterial> entities);

    /**
     * 修改数据
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 影响行数
     */
    int update(PlayerNeedTalentMaterial playerNeedTalentMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

