package com.laoh.demo.dao;

import com.laoh.demo.entity.OpenDayTalentMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 天赋材料开放日表(OpenDayTalentMaterial)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-19 00:39:56
 */
public interface OpenDayTalentMaterialDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OpenDayTalentMaterial queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OpenDayTalentMaterial> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param openDayTalentMaterial 实例对象
     * @return 对象列表
     */
    List<OpenDayTalentMaterial> queryAll(OpenDayTalentMaterial openDayTalentMaterial);

    /**
     * 新增数据
     *
     * @param openDayTalentMaterial 实例对象
     * @return 影响行数
     */
    int insert(OpenDayTalentMaterial openDayTalentMaterial);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OpenDayTalentMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OpenDayTalentMaterial> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OpenDayTalentMaterial> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OpenDayTalentMaterial> entities);

    /**
     * 修改数据
     *
     * @param openDayTalentMaterial 实例对象
     * @return 影响行数
     */
    int update(OpenDayTalentMaterial openDayTalentMaterial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

