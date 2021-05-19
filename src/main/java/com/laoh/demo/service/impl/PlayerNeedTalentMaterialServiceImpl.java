package com.laoh.demo.service.impl;

import com.hyd.common.util.IdGenerator;
import com.laoh.demo.entity.PlayerNeedTalentMaterial;
import com.laoh.demo.dao.PlayerNeedTalentMaterialDao;
import com.laoh.demo.service.PlayerNeedTalentMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 原神角色所需天赋材料表(PlayerNeedTalentMaterial)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:59:17
 */
@Service("playerNeedTalentMaterialService")
public class PlayerNeedTalentMaterialServiceImpl implements PlayerNeedTalentMaterialService {
    @Resource
    private PlayerNeedTalentMaterialDao playerNeedTalentMaterialDao;
    @Resource
    private IdGenerator idGenerator;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PlayerNeedTalentMaterial queryById(Long id) {
        return this.playerNeedTalentMaterialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PlayerNeedTalentMaterial> queryAllByLimit(int offset, int limit) {
        return this.playerNeedTalentMaterialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public PlayerNeedTalentMaterial insert(PlayerNeedTalentMaterial playerNeedTalentMaterial) {
        playerNeedTalentMaterial.setId(idGenerator.snowflakeId());
        this.playerNeedTalentMaterialDao.insert(playerNeedTalentMaterial);
        return playerNeedTalentMaterial;
    }

    /**
     * 修改数据
     *
     * @param playerNeedTalentMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public PlayerNeedTalentMaterial update(PlayerNeedTalentMaterial playerNeedTalentMaterial) {
        this.playerNeedTalentMaterialDao.update(playerNeedTalentMaterial);
        return this.queryById(playerNeedTalentMaterial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.playerNeedTalentMaterialDao.deleteById(id) > 0;
    }
}
