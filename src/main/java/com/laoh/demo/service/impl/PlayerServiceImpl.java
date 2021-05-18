package com.laoh.demo.service.impl;

import com.laoh.demo.entity.Player;
import com.laoh.demo.dao.PlayerDao;
import com.laoh.demo.service.PlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 原神角色表(Player)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:40:06
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
    @Resource
    private PlayerDao playerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Player queryById(Long id) {
        return this.playerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Player> queryAllByLimit(int offset, int limit) {
        return this.playerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param player 实例对象
     * @return 实例对象
     */
    @Override
    public Player insert(Player player) {
        this.playerDao.insert(player);
        return player;
    }

    /**
     * 修改数据
     *
     * @param player 实例对象
     * @return 实例对象
     */
    @Override
    public Player update(Player player) {
        this.playerDao.update(player);
        return this.queryById(player.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.playerDao.deleteById(id) > 0;
    }
}
