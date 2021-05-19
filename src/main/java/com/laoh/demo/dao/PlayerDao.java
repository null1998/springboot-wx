package com.laoh.demo.dao;

import com.laoh.demo.entity.Player;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 原神角色表(Player)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-19 00:40:05
 */
public interface PlayerDao {
    /**
     * 输入培养角色列表和星期几，查询当天需要刷天赋材料的角色
     * @param day 星期几
     * @param players 待培养的角色列表
     * @return 当天需要刷取天赋材料的角色
     */
    List<String> prepareTalentMaterialForPlayer(@Param("day") Integer day, @Param("players") List<String> players);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Player queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Player> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param player 实例对象
     * @return 对象列表
     */
    List<Player> queryAll(Player player);

    /**
     * 新增数据
     *
     * @param player 实例对象
     * @return 影响行数
     */
    int insert(Player player);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Player> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Player> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Player> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Player> entities);

    /**
     * 修改数据
     *
     * @param player 实例对象
     * @return 影响行数
     */
    int update(Player player);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

