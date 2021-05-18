package com.laoh.demo.service;

import com.laoh.demo.entity.Player;

import java.util.List;

/**
 * 原神角色表(Player)表服务接口
 *
 * @author makejava
 * @since 2021-05-19 00:40:06
 */
public interface PlayerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Player queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Player> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param player 实例对象
     * @return 实例对象
     */
    Player insert(Player player);

    /**
     * 修改数据
     *
     * @param player 实例对象
     * @return 实例对象
     */
    Player update(Player player);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
